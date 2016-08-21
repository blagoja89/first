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
import by.mavs.railwayservice.entity.Ticket;
import by.mavs.railwayservice.exception.DAOTechnicalException;
import by.mavs.railwayservice.exception.PoolTechnicalException;

public class TicketDao extends AbstractDao<Ticket> {

	@Override
	public Class<Ticket> getEntityClass() {
		return Ticket.class;
	}
		
	public List<Ticket> findBetweenDate(Date dateFrom, Date dateTo, int userId) throws DAOTechnicalException{
		List<Ticket> list = new ArrayList<>();
		Ticket entity = new Ticket();
		String query = dbQueryUtil.getFindTicketBetweenDateQuery();

		try (ConnectionManager cm = ConnectionPool.getInstanceConnectionPool()
				.getConnection();
				PreparedStatement prstmt = cm.prepareStatement(query);) {
			LOG.info("sql query by prop - " + prstmt);
			try {
				prstmt.setDate(1, new java.sql.Date(dateFrom.getTime()));
				prstmt.setDate(2, new java.sql.Date(dateTo.getTime()));
				prstmt.setInt(3, userId);
				
				ResultSet resultSet = prstmt.executeQuery();
				list =  dbUtil.getListObjectByResultSet(entity, resultSet);
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
