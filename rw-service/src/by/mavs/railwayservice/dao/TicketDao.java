package by.mavs.railwayservice.dao;

import by.mavs.railwayservice.entity.Ticket;

public class TicketDao extends AbstractDao<Ticket> {

	@Override
	public Class<Ticket> getEntityClass() {
		return Ticket.class;
	}
		
}
