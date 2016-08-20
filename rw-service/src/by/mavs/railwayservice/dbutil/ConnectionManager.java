package by.mavs.railwayservice.dbutil;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.mysql.jdbc.Driver;

public class ConnectionManager implements AutoCloseable {
	private boolean used;
	private Connection connection;
		
	/**
	 * @param id
	 * @throws SQLException if database error occurs while creating
	 */

	public ConnectionManager(String url, Properties prop) throws SQLException {
		DriverManager.registerDriver(new Driver());
		connection = DriverManager.getConnection(url, prop);
		used = false;
	}	
	
	/**
	 * @return a new default Statement object
	 * @throws SQLException if a database access error occurs or this method is called
	 * on a closed connection
	 * @see java.sql.Connection#createStatement()
	 */
	public Statement createStatement() throws SQLException {
		return connection.createStatement();
	}

	/**
	 * @param nameColumn
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#prepareStatement(java.lang.String)
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
	@Override
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