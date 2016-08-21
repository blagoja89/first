package by.mavs.railwayservice.entity;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

import by.mavs.railwayservice.dbutil.property.DBManyToOnePropertyItem;
import by.mavs.railwayservice.dbutil.property.DBOneToManyPropertyItem;
import by.mavs.railwayservice.dbutil.property.DBProperty;
import by.mavs.railwayservice.dbutil.property.DBSimplePropertyItem;
import by.mavs.railwayservice.dbutil.property.TypeProperty;

public class MapStation extends Entity {
	private Station stationDeparture;
	private Station stationArrival;
	private Date time;
	private int distance;
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public MapStation() {
		super();
	}

	public Station getStationDeparture() {
		return stationDeparture;
	}

	public void setStationDeparture(Station stationDeparture) {
		this.stationDeparture = stationDeparture;
	}

	public Station getStationArrival() {
		return stationArrival;
	}

	public void setStationArrival(Station stationArrival) {
		this.stationArrival = stationArrival;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + distance;
		result = prime * result
				+ ((stationArrival == null) ? 0 : stationArrival.hashCode());
		result = prime
				* result
				+ ((stationDeparture == null) ? 0 : stationDeparture.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MapStation other = (MapStation) obj;
		if (distance != other.distance)
			return false;
		if (stationArrival == null) {
			if (other.stationArrival != null)
				return false;
		} else if (!stationArrival.equals(other.stationArrival))
			return false;
		if (stationDeparture == null) {
			if (other.stationDeparture != null)
				return false;
		} else if (!stationDeparture.equals(other.stationDeparture))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MapStation [stationDeparture=" + stationDeparture
				+ ", stationArrival=" + stationArrival + ", time=" + time
				+ ", distance=" + distance + ", id=" + id + "]";
	}

	@Override
	public DBProperty getDbProperty() {
		DBProperty dbProperty = new DBProperty();
		dbProperty.setClassName(getClass());
		dbProperty.setNameTable("MAP_STATION");
		dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("id", "ID_MAP_STATION", Integer.TYPE));
		dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("distance", "DISTANCE", Integer.TYPE));
		dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("time", "TIME", Time.class));

		dbProperty.getDbManyToOnePropertyItems().add(new DBManyToOnePropertyItem("stationDeparture", "STATION_DEPARTURE", Station.class, "id", "ID_STATION", Integer.TYPE, false));
		dbProperty.getDbManyToOnePropertyItems().add(new DBManyToOnePropertyItem("stationArrival", "STATION_ARRIVAL", Station.class, "id", "ID_STATION", Integer.TYPE, false));
		
		
		return dbProperty;
	}

}
