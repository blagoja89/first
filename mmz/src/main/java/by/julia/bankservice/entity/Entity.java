package by.julia.bankservice.entity;

import java.io.Serializable;

public abstract class Entity implements Serializable, Cloneable, InfoDB {

	public abstract int getId();

	public abstract void setId(int id);

}
