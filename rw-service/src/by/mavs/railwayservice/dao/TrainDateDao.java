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
import by.mavs.railwayservice.entity.TrainDate;
import by.mavs.railwayservice.exception.DAOTechnicalException;
import by.mavs.railwayservice.exception.PoolTechnicalException;

public class TrainDateDao extends AbstractDao<TrainDate> {

	@Override
	public Class<TrainDate> getEntityClass() {
		return TrainDate.class;
	}
	

	public List<TrainDate> findTrainDatesByOrder(String depStation, String arStation, Date date) throws DAOTechnicalException{
		List<TrainDate> list = new ArrayList<>();
		String query =dbQueryUtil.getfindTrainDatesByOrderQuery();
		try (ConnectionManager cm = ConnectionPool.getInstanceConnectionPool()
				.getConnection();
				PreparedStatement prstmt = cm.prepareStatement(query);) {
			try {
				prstmt.setString(1, depStation);
				prstmt.setString(2, arStation);
				prstmt.setDate(3, new java.sql.Date(date.getTime()) );
				System.out.println(prstmt);
				ResultSet resultSet = prstmt.executeQuery();
				list =  dbUtil.getListObjectByResultSet(new TrainDate(), resultSet);
			} catch (NoSuchMethodException | SecurityException
					| IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | InstantiationException e) {
				e.printStackTrace();
			}
		} catch (SQLException | PoolTechnicalException e) {
			LOG.error("catch exception in " + getEntityClass().getSimpleName()
					+ " throwing DAOTechnicalException", e);
			throw new DAOTechnicalException(e);
		}
		return list;
	}

}
