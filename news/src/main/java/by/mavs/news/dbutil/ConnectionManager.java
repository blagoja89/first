package by.mavs.news.dbutil;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;



public final class ConnectionManager implements AutoCloseable {
		
	private boolean used;
	private Connection connection;
	private ConnectionPool pool;
	

	/**
	 * @param id
	 * @throws SQLException if database error occurs while creating
	 * @throws ClassNotFoundException 
	 */

	public void initManager(String driverClassName, String url, String user, String password) throws SQLException, ClassNotFoundException {
		Class.forName(driverClassName);
		connection = DriverManager.getConnection(url, user, password);
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
		pool.returnConnection(this);
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
	
	public ConnectionPool getPool() {
		return pool;
	}

	public void setPool(ConnectionPool pool) {
		this.pool = pool;
	}

}