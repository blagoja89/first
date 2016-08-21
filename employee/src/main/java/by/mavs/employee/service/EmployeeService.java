package by.mavs.employee.service;

import java.util.List;

import by.mavs.employee.model.beans.Employee;
import by.mavs.employee.model.beans.ServiceBean;
import by.mavs.employee.model.dao.impl.EmployeeHibernateDAO;

public final class EmployeeService{

	private EmployeeHibernateDAO daoEmpl;
	
	public ServiceBean service(int itemsPerPage, int page) {
		int firstResult = (page - 1) * itemsPerPage;
		List<Employee> employees = daoEmpl.getEmployeeList(firstResult, itemsPerPage);
		int rowsNumber = daoEmpl.getRowsNumber();
		int totalPages = rowsNumber / itemsPerPage + (rowsNumber % itemsPerPage == 0 ? 0 : 1);
		return new ServiceBean(rowsNumber, totalPages, employees);
	}
	
	public EmployeeHibernateDAO getDaoEmpl() {
		return daoEmpl;
	}

	public void setDaoEmpl(EmployeeHibernateDAO daoEmpl) {
		this.daoEmpl = daoEmpl;
	}
	
}
