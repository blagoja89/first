package by.mavs.employee.model.beans;

import java.io.Serializable;

public abstract class MyEntity implements Serializable, Cloneable {

	public abstract int getId();

	public abstract void setId(int id);

}
