package by.mavs.employee.model.dao;


import java.sql.SQLException;
import java.util.List;

import by.mavs.employee.model.beans.Employee;



public interface IEmployeeDAO {
	
	public List<Employee> getEmployeeList(int firstResult, int resultsPerPage) throws SQLException;
	public int getRowsNumber() throws SQLException;
}
