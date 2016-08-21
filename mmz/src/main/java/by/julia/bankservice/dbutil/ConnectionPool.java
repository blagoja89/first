package by.julia.bankservice.dbutil;


import by.julia.bankservice.exception.PoolTechnicalException;
import by.julia.bankservice.util.MySQLProperty;
import by.julia.bankservice.util.PropertyConst;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ConnectionPool {
	private static final Logger LOG = Logger.getLogger(ConnectionPool.class);
	private Logger logger = Logger.getLogger(ConnectionPool.class);
	private final int MAX_CONNECTION_NUMBER = 15;
	private final int MAX_IDLE_CONNECTION_NUMBER = 5;
	private String url;
	private Properties properties;
	private ArrayBlockingQueue<ConnectionManager> connectionList;
	private ConnectionPoolManager manager = new ConnectionPoolManager();
	private int connectionCounter;
	private static ConnectionPool pool = new ConnectionPool();
	
	public static ConnectionPool getInstanceConnectionPool() {
		return pool;
	}
	
	private ConnectionPool() {
		LOG.debug("get connection: database url = " + PropertyConst.DATABASE_URL + ", driver name = " + PropertyConst.DATABASE_DRIVER_NAME + ", login = " +  PropertyConst.DATABASE_LOGIN + ", password = " + PropertyConst.DATABASE_PASSWORD);
		url = MySQLProperty.getProperty(PropertyConst.DATABASE_URL);
		String login = MySQLProperty.getProperty(PropertyConst.DATABASE_LOGIN);
		String password = MySQLProperty.getProperty(PropertyConst.DATABASE_PASSWORD);
		String autoReconnect = MySQLProperty.getProperty(PropertyConst.DATABASE_AUTO_RECONNECT);
		String encoding = MySQLProperty.getProperty(PropertyConst.DATABASE_ENCODING);
		String unicode = MySQLProperty.getProperty(PropertyConst.DATABASE_UNICODE);

		properties = new Properties();
		properties.put("user", login);
		properties.put("password", password);
		properties.put("autoReconnect", autoReconnect);
		properties.put("characterEncoding", encoding);
		properties.put("useUnicode", unicode);
		connectionList = new ArrayBlockingQueue<ConnectionManager>(
				MAX_CONNECTION_NUMBER, true);
		try {
			for (int i = 0; i < MAX_IDLE_CONNECTION_NUMBER; i++) {
				connectionList.add(new ConnectionManager(url,
						properties));
				connectionCounter++;
			}
		} catch (SQLException e) {
			logger.warn("connections were not added while connection pool" +
					"initialization", e);
		}
		manager.setDaemon(true);
		manager.start();
	}
	
	/**
	 * @return connection from the library
	 * @throws PoolTechnicalException 
	 */
	public ConnectionManager getConnection() throws PoolTechnicalException {
		ConnectionManager connectionManager = null;
		synchronized (connectionList) {
		/* synchronized to prevent appearance of more connection*/
			if (connectionList.isEmpty() && (connectionCounter < MAX_CONNECTION_NUMBER)) {
				try {
					connectionManager = new ConnectionManager(url, properties);
				} catch (SQLException e) {
					logger.fatal("sql exeption while creating new connection", e);
					throw new PoolTechnicalException(e);
				}
				connectionCounter++;
			} else {
				try {
					connectionManager = connectionList.take();
				} catch (InterruptedException e) {
					logger.fatal("thread was interrupted while waiting for connection" +
							"from pool", e);
					throw new PoolTechnicalException(e);
				}
			}
		}
		connectionManager.setUsed(true);
		logger.info("connection getted");
		return connectionManager;
	}
	
	/**
	 * @param connectionToReturn
	 * @throws PoolTechnicalException 
	 */
	public void returnConnection(ConnectionManager connectionToReturn) {
		connectionToReturn.setUsed(false);		
		logger.info("return connection");
			try {
				connectionList.put(connectionToReturn);
			} catch (InterruptedException e) {
				 logger.error("thread was interrupted while returning connection" +
				 		"to pool", e);
			}
	}
	
	
	
	private class ConnectionPoolManager extends Thread {
		private static final long ADJUSTMENT_PERIOD = 50000L;
		public void run() {		
			try {
				while (!Thread.interrupted()) {
					TimeUnit.MILLISECONDS.sleep(ADJUSTMENT_PERIOD);
					adjustConnectionNumber();
				}
			} catch (InterruptedException e) {
				logger.error("ConnectionPoolManager was interrupted while sleeping", e);
			}
		}
		
		private void adjustConnectionNumber() {
			if (connectionList.size() > MAX_IDLE_CONNECTION_NUMBER) {
				try {
					connectionList.take().closeConnection();
				} catch (InterruptedException e) {	
					logger.error("thread was interrupted while waiting for connection" +
							"from pool", e);
				} catch (SQLException e) {
					logger.error("sql Exception while closing connection", e);
				}
				connectionCounter--;
				logger.info("adjusting connections. number of connections after adjusting: "
						+ connectionList.size());
			}			
		}
	}
}