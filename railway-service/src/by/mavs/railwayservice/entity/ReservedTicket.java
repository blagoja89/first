package by.mavs.railwayservice.entity;

import by.mavs.railwayservice.dbutil.property.DBManyToOnePropertyItem;
import by.mavs.railwayservice.dbutil.property.DBProperty;
import by.mavs.railwayservice.dbutil.property.DBSimplePropertyItem;

public class ReservedTicket extends Entity {
	private int id;
	private TrainDate trainDate;
	private Ticket ticket;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TrainDate getTrainDate() {
		return trainDate;
	}

	public void setTrainDate(TrainDate trainDate) {
		this.trainDate = trainDate;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	@Override
	public DBProperty getDbProperty() {
		DBProperty dbProperty = new DBProperty();
		dbProperty.setClassName(getClass());
		dbProperty.setNameTable("RESERVED_TICKET");
		dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("id", "ID_RESERVED_TICKET", Integer.TYPE));

		dbProperty.getDbManyToOnePropertyItems().add(new DBManyToOnePropertyItem("trainDate", "TRAIN_DATE_ID", TrainDate.class,"id", "ID_TRAIN_DATE", Integer.TYPE, false));
		dbProperty.getDbManyToOnePropertyItems().add(new DBManyToOnePropertyItem("ticket", "TICKET_ID", Ticket.class,"id", "ID_TICKET", Integer.TYPE, false));

		return dbProperty;
	}

}
