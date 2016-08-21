package by.mavs.employee.model.beans;


public final class OfficeEmployee extends MyEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6878835204823710273L;
	private int id;
	private Office office;
//	private Employee employee;
	private Position position;

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

//	public Employee getEmployee() {
//		return employee;
//	}
//
//	public void setEmployee(Employee employee) {
//		this.employee = employee;
//	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((office == null) ? 0 : office.hashCode());
		result = prime * result
				+ ((position == null) ? 0 : position.hashCode());
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
		OfficeEmployee other = (OfficeEmployee) obj;
		if (id != other.id)
			return false;
		if (office == null) {
			if (other.office != null)
				return false;
		} else if (!office.equals(other.office))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OfficeEmployee [id=");
		builder.append(id);
		builder.append(", office=");
		builder.append(office);
		builder.append(", position=");
		builder.append(position);
		builder.append("]");
		return builder.toString();
	}
	
	
}
