package by.mavs.news.dbutil;

import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import by.mavs.news.exception.PoolException;

public final class ConnectionPool {
	private final Logger logger = Logger.getLogger(ConnectionPool.class);
	private  ArrayBlockingQueue<ConnectionManager> connectionList;
	private ConnectionPoolManager manager = new ConnectionPoolManager();

	private String driver;
	private String url;
	private String username;
	private String password;
	private int connectionCounter;
	//12
	private int maxConnectionNumber;
	//5
	private int maxIdleConnectionNumber;

	public void init() {
		connectionList = new ArrayBlockingQueue<ConnectionManager>(maxConnectionNumber, true);
			for (int i = 0; i < maxIdleConnectionNumber; i++) {
				try {
					connectionList.add(createManager());
				} catch (PoolException e) {
					logger.warn("connections were not added while connection pool initialization", e);
				}
				connectionCounter++;
			}
		manager.setDaemon(true);
		manager.start();
	}


	/**
	 * @return connection from the library
	 * @throws PoolException
	 */
	public ConnectionManager getConnection() throws PoolException {
		ConnectionManager connectionManager = null;
		synchronized (connectionList) {
			/* synchronized to prevent appearance of more connection */
			if (connectionList.isEmpty() && (connectionCounter < maxConnectionNumber)) {
					connectionList.add(createManager());
				connectionCounter++;
			} else {
				try {
					connectionManager = connectionList.take();
				} catch (InterruptedException e) {
					logger.fatal("thread was interrupted while waiting for connection from pool", e);
					throw new PoolException(e);
				}
			}
		}
		connectionManager.setUsed(true);
		logger.info("connection getted");
		return connectionManager;
	}

	/**
	 * @param connectionToReturn
	 * @throws PoolException
	 */
	public void returnConnection(ConnectionManager connectionToReturn) {
		connectionToReturn.setUsed(false);
		logger.info("return connection");
		try {
			connectionList.put(connectionToReturn);
		} catch (InterruptedException e) {
			logger.error("thread was interrupted while returning connection to pool", e);
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
			if (connectionList.size() > maxIdleConnectionNumber) {
				try {
					connectionList.take().closeConnection();
				} catch (InterruptedException e) {
					logger.error("thread was interrupted while waiting for connection from pool", e);
				} catch (SQLException e) {
					logger.error("sql Exception while closing connection", e);
				}
				connectionCounter--;
				logger.info("adjusting connections. number of connections after adjusting: " + connectionList.size());
			}
		}
	}
	
	private ConnectionManager createManager() throws PoolException {
		ConnectionManager manager = new ConnectionManager();
		try {
			manager.initManager(driver, url, username, password);
		} catch (ClassNotFoundException | SQLException e) {
			logger.fatal("sql exeption while creating new connection", e);
			throw new PoolException(e);
		}
		return manager;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getMaxConnectionNumber() {
		return maxConnectionNumber;
	}

	public void setMaxConnectionNumber(int maxConnectionNumber) {
		this.maxConnectionNumber = maxConnectionNumber;
	}

	public int getMaxIdleConnectionNumber() {
		return maxIdleConnectionNumber;
	}

	public void setMaxIdleConnectionNumber(int maxIdleConnectionNumber) {
		this.maxIdleConnectionNumber = maxIdleConnectionNumber;
	}

	
}