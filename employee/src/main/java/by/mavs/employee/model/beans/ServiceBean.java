package by.mavs.employee.model.beans;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import by.mavs.employee.model.beans.Employee;


@XmlRootElement
public final class ServiceBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -995164401251996968L;
	private int rowsNumber;
	private int totalPages;
	private List<Employee> employees;
	
	public ServiceBean(int rowsNumber, int totalPages, List<Employee> employees) {
		this.rowsNumber = rowsNumber;
		this.totalPages = totalPages;
		this.employees = employees;
	}

	public ServiceBean() {
	}

	public int getRowsNumber() {
		return rowsNumber;
	}

	public void setRowsNumber(int rowsNumber) {
		this.rowsNumber = rowsNumber;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}



}
