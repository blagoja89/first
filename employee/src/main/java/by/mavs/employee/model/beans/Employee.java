package by.mavs.employee.model.beans;

import java.util.HashSet;
import java.util.Set;


public final class Employee extends MyEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3800725295105571617L;
	private int id;
	private String firstName;
	private String lastName;
	private Address address;
	private Set<OfficeEmployee> officeEmployees = new HashSet<OfficeEmployee>();

	public Employee() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Set<OfficeEmployee> getOfficeEmployees() {
		return officeEmployees;
	}

	public void setOfficeEmployees(Set<OfficeEmployee> officeEmployees) {
		this.officeEmployees = officeEmployees;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result
				+ ((officeEmployees == null) ? 0 : officeEmployees.hashCode());
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
		Employee other = (Employee) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (officeEmployees == null) {
			if (other.officeEmployees != null)
				return false;
		} else if (!officeEmployees.equals(other.officeEmployees))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Employee [id=");
		builder.append(id);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", address=");
		builder.append(address);
		builder.append(", workplaces=");
		builder.append(officeEmployees);
		builder.append("]");
		return builder.toString();
	}

}
