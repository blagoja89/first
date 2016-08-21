package by.mavs.railwayservice.entity;

import java.sql.Time;
import java.util.Date;

import by.mavs.railwayservice.dbutil.property.DBManyToOnePropertyItem;
import by.mavs.railwayservice.dbutil.property.DBProperty;
import by.mavs.railwayservice.dbutil.property.DBSimplePropertyItem;

public class TrainDate extends Entity {
	private int id;
	private Train train;
	private Date departureDate;
	private Date departureTime;

	public TrainDate() {
	}

	public TrainDate(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Date getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}

	@Override
	public DBProperty getDbProperty() {
		DBProperty dbProperty = new DBProperty();
		dbProperty.setClassName(getClass());
		dbProperty.setNameTable("TRAIN_DATE");
		dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("id", "ID_TRAIN_DATE", Integer.TYPE));
		dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("departureDate", "DEPARTURE_DATE", Date.class));
		dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("departureTime", "DEPARTURE_TIME", Time.class));

		dbProperty.getDbManyToOnePropertyItems().add(new DBManyToOnePropertyItem("train", "TRAIN_ID", Train.class,
						"id", "ID_TRAIN", Integer.TYPE, false));

		return dbProperty;
	}

}
