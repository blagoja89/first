package by.mavs.news.model;


import java.io.Serializable;

public abstract class MyEntity implements Serializable, Cloneable, InfoDB {

	public abstract int getId();

	public abstract void setId(int id);

}
