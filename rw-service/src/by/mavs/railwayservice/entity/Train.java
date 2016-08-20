package by.mavs.railwayservice.entity;

import java.beans.Transient;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import by.mavs.railwayservice.dbutil.property.DBOneToManyPropertyItem;
import by.mavs.railwayservice.dbutil.property.DBProperty;
import by.mavs.railwayservice.dbutil.property.DBSimplePropertyItem;
import by.mavs.railwayservice.util.DateUtil;

public class Train extends Entity {

	private int number;
	private List<Wagon> wagons;
	private List<TrainMapStation> trainMapStations;
	private Date timeDeparture;
	private int id;

	public Train(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Train() {
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public List<Wagon> getWagons() {
		return wagons;
	}

	public void setWagons(List<Wagon> wagons) {
		this.wagons = wagons;
	}

	public List<TrainMapStation> getTrainMapStations() {
		return trainMapStations;
	}

	public void setTrainMapStations(List<TrainMapStation> trainMapStations) {
		this.trainMapStations = trainMapStations;
	}

	public Date getTimeDeparture() {
		return timeDeparture;
	}

	public void setTimeDeparture(Date timeDeparture) {
		this.timeDeparture = timeDeparture;
	}

	@Override
	public DBProperty getDbProperty() {
		DBProperty dbProperty = new DBProperty();
		dbProperty.setClassName(getClass());
		dbProperty.setNameTable("TRAIN");
		dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("id", "ID_TRAIN", Integer.TYPE));
		dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("number", "NUMBER", Integer.TYPE));
		dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("timeDeparture", "TIME_DEPARTURE", Date.class));
		
		dbProperty.getDbOneToManyPropertyItems().add(new DBOneToManyPropertyItem("trainMapStations", "ID_TRAIN","id", "TRAIN_ID", TrainMapStation.class, Integer.TYPE, false));
		dbProperty.getDbOneToManyPropertyItems().add(new DBOneToManyPropertyItem("wagons", "ID_TRAIN","id", "TRAIN_ID", Wagon.class, Integer.TYPE, false));
		
		return dbProperty;
	}
	
}
