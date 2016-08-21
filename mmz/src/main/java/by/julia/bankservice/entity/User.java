package by.julia.bankservice.entity;

import by.julia.bankservice.dbutil.property.DBManyToOnePropertyItem;
import by.julia.bankservice.dbutil.property.DBProperty;
import by.julia.bankservice.dbutil.property.DBSimplePropertyItem;

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
	private ClientInformation userInf;

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

	public ClientInformation getUserInf() {
		return userInf;
	}

	public void setUserInf(ClientInformation userInf) {
		this.userInf = userInf;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof User)) return false;

		User user = (User) o;

		if (id != user.id) return false;
		if (role != user.role) return false;
		if (deleted != user.deleted) return false;
		if (login != null ? !login.equals(user.login) : user.login != null) return false;
		if (password != null ? !password.equals(user.password) : user.password != null) return false;
		if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
		if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
		if (email != null ? !email.equals(user.email) : user.email != null) return false;
		return !(account != null ? !account.equals(user.account) : user.account != null);

	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + role;
		result = 31 * result + (login != null ? login.hashCode() : 0);
		result = 31 * result + (password != null ? password.hashCode() : 0);
		result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
		result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
		result = 31 * result + (email != null ? email.hashCode() : 0);
		result = 31 * result + (deleted ? 1 : 0);
		result = 31 * result + (account != null ? account.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", role=" + role + ", login=" + login
				+ ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", deleted="
				+ deleted + ", account=" + account + "]";
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
		dbProperty.getDbManyToOnePropertyItems().add(new DBManyToOnePropertyItem("account", "ACCOUNT_ID",
				Account.class,"id", "ID_ACCOUNT", Integer.TYPE, false));
		dbProperty.getDbManyToOnePropertyItems().add(new DBManyToOnePropertyItem("userInf", "CLIENT_INFORMATION_ID",
				ClientInformation.class,"id", "ID_CLIENT_INFORMATION", Integer.TYPE, false));
		return dbProperty;
	}

}
