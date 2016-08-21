package by.mavs.railwayservice.entity;

import by.mavs.railwayservice.dbutil.property.DBProperty;
import by.mavs.railwayservice.dbutil.property.DBSimplePropertyItem;
import by.mavs.railwayservice.dbutil.property.TypeProperty;

public class Station extends Entity {

	private String name;
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Station() {
	}

	public Station(String name, int id) {
		super();
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Station other = (Station) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Station [name=" + name + ", id=" + id+"]";
	}

	public void inint() {
		System.out.println("INIT STATION");
	}

	@Override
	public DBProperty getDbProperty() {
		DBProperty dbProperty = new DBProperty();
		dbProperty.setClassName(getClass());
		dbProperty.setNameTable("STATION");
		dbProperty.getDbSimplePropertyItems().add(
				new DBSimplePropertyItem("id", "ID_STATION", Integer.TYPE));
		dbProperty.getDbSimplePropertyItems().add(
				new DBSimplePropertyItem("name", "NAME_STATION",
						String.class));
		return dbProperty;
	}

}
