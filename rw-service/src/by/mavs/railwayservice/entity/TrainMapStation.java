package by.mavs.railwayservice.entity;

import by.mavs.railwayservice.dbutil.property.DBManyToOnePropertyItem;
import by.mavs.railwayservice.dbutil.property.DBProperty;
import by.mavs.railwayservice.dbutil.property.DBSimplePropertyItem;

public class TrainMapStation extends Entity {
	private Train train;
	private MapStation mapStation;
	private int seqNo;
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TrainMapStation() {
	}

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

	public MapStation getMapStation() {
		return mapStation;
	}

	public void setMapStation(MapStation mapStation) {
		this.mapStation = mapStation;
	}

	public int getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
	}
	

	@Override
	public DBProperty getDbProperty() {
		DBProperty dbProperty = new DBProperty();
		dbProperty.setClassName(getClass());
		dbProperty.setNameTable("TRAIN_MAP_STATION");
		dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("id", "ID_TRAIN_MAP_STATION", Integer.TYPE));
		dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("seqNo", "SEQ_NO", Integer.TYPE));

		dbProperty.getDbManyToOnePropertyItems().add(new DBManyToOnePropertyItem("mapStation", "MAP_STATION_ID", MapStation.class, "id", "MAP_STATION_ID", Integer.TYPE, false));
		dbProperty.getDbManyToOnePropertyItems().add(new DBManyToOnePropertyItem("train", "TRAIN_ID", Train.class, "id", "TRAIN_ID", Integer.TYPE,true));
		
		return dbProperty;
	}


}
