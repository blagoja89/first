package by.julia.bankservice.dbutil;


import com.mysql.jdbc.Driver;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Properties;

public class ConnectionManager implements AutoCloseable {
	private static final Logger LOG = Logger.getLogger(ConnectionManager.class);
	static{
		try {
			DriverManager.registerDriver(new Driver());
		} catch (SQLException e) {
			LOG.error(e.getMessage());
		}
	}
	
	private boolean used;
	private Connection connection;
	
	/**
	 * @param prop
	 * @throws SQLException if database error occurs while creating
	 */

	public ConnectionManager(String url, Properties prop) throws SQLException {
		connection = DriverManager.getConnection(url, prop);
		used = false;
	}	
	
	/**
	 * @return a new default Statement object
	 * @throws SQLException if a database access error occurs or this method is called
	 * on a closed connection
	 * @see Connection#createStatement()
	 */
	public Statement createStatement() throws SQLException {
		return connection.createStatement();
	}

	/**
	 * @param sqlQuery
	 * @return
	 * @throws SQLException
	 * @see Connection#prepareStatement(String)
	 */
	public PreparedStatement prepareStatement(String sqlQuery) throws SQLException {
		return connection.prepareStatement(sqlQuery);
	}	

	public PreparedStatement prepareStatement(String sqlQuery, int autoGenKey) throws SQLException {
		return connection.prepareStatement(sqlQuery, autoGenKey);
	}	
	
	/**
	 * returns given database connection to the connection pool
	 */
	public void close() {
		ConnectionPool.getInstanceConnectionPool().returnConnection(this);
	}
	
	/**
	 * if given connection is out of the connection pool than this method will return true
	 * @return connection state
	 */
	public boolean isUsed() {
		return used;
	}
	
	public void setUsed(boolean used) {
		this.used = used;
	}
	
	public void closeConnection() throws SQLException {
		connection.close();			
	}
}