package by.julia.bankservice.entity;


import by.julia.bankservice.dbutil.property.DBProperty;
import by.julia.bankservice.dbutil.property.DBSimplePropertyItem;

public class Message extends Entity{

	private int id;
	private String name;
	private String mail;
	private String message;
	private String type;
	
	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ id;
		result = prime * result + ((mail == null) ? 0 : mail.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Message other = (Message) obj;
		if (id == 0) {
			if (other.id != 0)
				return false;
		} else if (!(id == other.id))
			return false;
		if (mail == null) {
			if (other.mail != null)
				return false;
		} else if (!mail.equals(other.mail))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Message [id=" + id + ", name=" + name
				+ ", mail=" + mail + ", message=" + message + ", type=" + type + "]";
	}
	@Override
	public DBProperty getDbProperty() {
		DBProperty dbProperty = new DBProperty();
		dbProperty.setClassName(getClass());
		dbProperty.setNameTable("MESSAGE");
		dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("id", "ID_MESSAGE", Integer.TYPE));
		dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("name", "NAME", String.class));
		dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("mail", "EMAIL", String.class));
		dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("message", "MESSAGE", String.class));
		dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("type", "TYPE", String.class));
		return dbProperty;
	}


}
