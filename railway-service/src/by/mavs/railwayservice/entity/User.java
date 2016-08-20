package by.mavs.railwayservice.entity;

import java.util.List;

import by.mavs.railwayservice.dbutil.property.DBManyToOnePropertyItem;
import by.mavs.railwayservice.dbutil.property.DBOneToManyPropertyItem;
import by.mavs.railwayservice.dbutil.property.DBProperty;
import by.mavs.railwayservice.dbutil.property.DBSimplePropertyItem;
import by.mavs.railwayservice.dbutil.property.TypeProperty;

public class User extends Entity {
	private int id;
	private int role;
	private String login;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private boolean deleted;
	private Account account;
	private List<Ticket> tickets;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
	
	public User() {
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}
	

	@Override
	public String toString() {
		return "User [id=" + id + ", role=" + role + ", login=" + login
				+ ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", deleted="
				+ deleted + ", account=" + account + ", tickets=" + tickets
				+ "]";
	}

	@Override
	public DBProperty getDbProperty() {
		DBProperty dbProperty = new DBProperty();
		dbProperty.setClassName(getClass());
		dbProperty.setNameTable("USER_T");
		dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("id", "ID_USER", Integer.TYPE));
		dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("role", "ROLE", Integer.TYPE));
		dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("login", "LOGIN", String.class));
		dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("password", "PASSWORD", String.class));
		dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("firstName", "FIRST_NAME", String.class));
		dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("lastName", "LAST_NAME", String.class));
		dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("email", "EMAIL", String.class));
		dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("deleted", "DELETED", Boolean.TYPE));

		dbProperty.getDbManyToOnePropertyItems().add(new DBManyToOnePropertyItem("account", "ACCOUNT_ID", Account.class,"id", "ID_ACCOUNT", Integer.TYPE, false));
		dbProperty.getDbOneToManyPropertyItems().add(new DBOneToManyPropertyItem("tickets", "ID_USER","id",  "USER_ID", Ticket.class, Integer.TYPE, true));
		
		return dbProperty;
	}

}
