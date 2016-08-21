package by.mavs.railwayservice.entity;

import java.util.List;

import by.mavs.railwayservice.dbutil.property.DBManyToOnePropertyItem;
import by.mavs.railwayservice.dbutil.property.DBOneToManyPropertyItem;
import by.mavs.railwayservice.dbutil.property.DBProperty;
import by.mavs.railwayservice.dbutil.property.DBSimplePropertyItem;

public class Wagon extends Entity {
	private int number;
	private Train train;
	private int type;
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Wagon() {
		super();
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + number;
		result = prime * result + ((train == null) ? 0 : train.hashCode());
		result = prime * result + type;
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
		Wagon other = (Wagon) obj;
		if (number != other.number)
			return false;
		if (train == null) {
			if (other.train != null)
				return false;
		} else if (!train.equals(other.train))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public DBProperty getDbProperty() {
		DBProperty dbProperty = new DBProperty();
		dbProperty.setClassName(getClass());
		dbProperty.setNameTable("WAGON");
		dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("id", "ID_WAGON", Integer.TYPE));
		dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("number", "NUMBER", Integer.TYPE));
		dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("type", "TYPE_WAGON", Integer.TYPE));

		dbProperty.getDbManyToOnePropertyItems().add(new DBManyToOnePropertyItem("train", "TRAIN_ID",Train.class, "id", "TRAIN_ID",  Integer.TYPE, true));
		
		return dbProperty;
	}


}
