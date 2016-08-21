package by.mavs.railwayservice.dao;

import java.util.List;

import by.mavs.railwayservice.entity.ReservedTicket;
import by.mavs.railwayservice.entity.TrainDate;
import by.mavs.railwayservice.exception.DAOTechnicalException;

public class ReservedTicketDao extends AbstractDao<ReservedTicket> {

	@Override
	public Class<ReservedTicket> getEntityClass() {
		return ReservedTicket.class;
	}
	
	public List<ReservedTicket> findFilledPlace(int trainDateId){
		TrainDate td = new TrainDate();
		td.setId(trainDateId);
		ReservedTicket rt = new ReservedTicket();
		rt.setTrainDate(td);
		try {
			return findByExample(rt);
		} catch (DAOTechnicalException e) {
			LOG.debug(e.getMessage());
		}
		return null;
	}

}
