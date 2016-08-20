package by.mavs.railwayservice.dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import by.mavs.railwayservice.dbutil.ConnectionManager;
import by.mavs.railwayservice.dbutil.ConnectionPool;
import by.mavs.railwayservice.entity.Train;
import by.mavs.railwayservice.exception.DAOTechnicalException;
import by.mavs.railwayservice.exception.PoolTechnicalException;

public class TrainDao extends AbstractDao<Train> {

	@Override
	public Class<Train> getEntityClass() {
		return Train.class;
	}

	
	public List<Train> findTrainsByOrder(String depStation, String arStation, Date date) throws DAOTechnicalException{
		List<Train> list = new ArrayList<>();
		String query = dbQueryUtil.getFindTrainsByOrderQuery();
		try (ConnectionManager cm = ConnectionPool.getInstanceConnectionPool()
				.getConnection();
				PreparedStatement prstmt = cm.prepareStatement(query);) {
			try {
				prstmt.setString(1, depStation);
				prstmt.setString(2, arStation);
				prstmt.setDate(3, new java.sql.Date(date.getTime()) );
				ResultSet resultSet = prstmt.executeQuery();
				list =  dbUtil.getListObjectByResultSet(new Train(), resultSet);
			} catch (NoSuchMethodException | SecurityException
					| IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | InstantiationException e) {
				LOG.error(e.getMessage());
			}
		} catch (SQLException | PoolTechnicalException e) {
			LOG.error("catch exception in " + getEntityClass().getSimpleName()
					+ " throwing DAOTechnicalException", e);
			throw new DAOTechnicalException(e);
		}
		return list;
	}
	
}
