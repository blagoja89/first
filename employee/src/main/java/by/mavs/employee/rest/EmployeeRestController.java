package by.mavs.employee.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import by.mavs.employee.model.beans.ServiceBean;
import by.mavs.employee.service.EmployeeService;

@Path("/employees")
public final class EmployeeRestController {

	
	private EmployeeService employeeService;

	@GET
	@Path("/{itemsPerPage}/{page}")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public ServiceBean getListEmployee(@PathParam("itemsPerPage") int itemsPerPage, @PathParam("page") int page) {
		return employeeService.service(itemsPerPage, page);
	}
	
	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
}
