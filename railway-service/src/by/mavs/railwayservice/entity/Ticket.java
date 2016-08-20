package by.mavs.railwayservice.entity;

import java.util.Date;

import by.mavs.railwayservice.dbutil.property.DBManyToOnePropertyItem;
import by.mavs.railwayservice.dbutil.property.DBProperty;
import by.mavs.railwayservice.dbutil.property.DBSimplePropertyItem;

public class Ticket extends Entity {

	private String number;
	private String trainName;
	private Date timeDeparture;
	private Date timeArrival;
	private String timeTrevel;
	private String stationDeparture;
	private String stationArrival;
	private User user;
	private Wagon wagon;
	private int place;
	private String price;
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Ticket() {
		super();
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Date getTimeDeparture() {
		return timeDeparture;
	}

	public void setTimeDeparture(Date timeDeparture) {
		this.timeDeparture = timeDeparture;
	}

	public Date getTimeArrival() {
		return timeArrival;
	}

	public void setTimeArrival(Date timeArrival) {
		this.timeArrival = timeArrival;
	}

	public String getStationDeparture() {
		return stationDeparture;
	}

	public void setStationDeparture(String stationDeparture) {
		this.stationDeparture = stationDeparture;
	}

	public String getStationArrival() {
		return stationArrival;
	}

	public void setStationArrival(String stationArrival) {
		this.stationArrival = stationArrival;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Wagon getWagon() {
		return wagon;
	}

	public void setWagon(Wagon wagon) {
		this.wagon = wagon;
	}

	public int getPlace() {
		return place;
	}

	public void setPlace(int place) {
		this.place = place;
	}
	

	public String getTrainName() {
		return trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	public String getTimeTrevel() {
		return timeTrevel;
	}

	public void setTimeTrevel(String timeTrevel) {
		this.timeTrevel = timeTrevel;
	}

	@Override
	public DBProperty getDbProperty() {
		DBProperty dbProperty = new DBProperty();
		dbProperty.setClassName(getClass());
		dbProperty.setNameTable("TICKET");
		dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("id", "ID_TICKET", Integer.TYPE));
		dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("number", "NUMBER_TICKET", String.class));
		dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("timeDeparture", "TIME_DEPARTURE", Date.class));
		dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("timeArrival", "TIME_ARRIVAL", Date.class));
		dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("trainName", "TRAIN_NAME", String.class));
		dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("timeTrevel", "TIME_TREVEL", String.class));
		dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("price", "PRICE", String.class));
		dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("place", "PLACE", Integer.TYPE));
		dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("stationDeparture", "STATION_DEPARTURE", String.class));
		dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("stationArrival", "STATION_ARRIVAL", String.class));

		dbProperty.getDbManyToOnePropertyItems().add(new DBManyToOnePropertyItem("user", "USER_ID", User.class,"id", "ID_USER", Integer.TYPE, true));
		dbProperty.getDbManyToOnePropertyItems().add(new DBManyToOnePropertyItem("wagon", "WAGON_ID", Wagon.class, "id", "WAGON_ID", Integer.TYPE, false));
		
		return dbProperty;
	}
}
