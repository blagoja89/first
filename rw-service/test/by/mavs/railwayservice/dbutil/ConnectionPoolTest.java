package by.mavs.railwayservice.dbutil;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

import by.mavs.railwayservice.dbutil.ConnectionManager;
import by.mavs.railwayservice.dbutil.ConnectionPool;
import by.mavs.railwayservice.exception.PoolTechnicalException;

public class ConnectionPoolTest {

	@Test(expected = PoolTechnicalException.class)
	public void getConnectionTest() throws PoolTechnicalException {
		ConnectionManager expected;
		try {
			expected = new ConnectionManager(null, null);
		} catch (SQLException e) {
			throw new PoolTechnicalException(
					"thread was interrupted while waiting for connection"
							+ "from pool", e);
		}
		ConnectionManager actual = ConnectionPool.getInstanceConnectionPool()
				.getConnection();
		Assert.assertEquals("For get connection: ", actual, expected);
	}

}
