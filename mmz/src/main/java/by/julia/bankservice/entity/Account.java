package by.julia.bankservice.entity;


import by.julia.bankservice.dbutil.property.DBProperty;
import by.julia.bankservice.dbutil.property.DBSimplePropertyItem;

public class Account extends Entity {
	private static final long serialVersionUID = -1872510714173372687L;
	private int numberAccount;
	private double bill;
	private boolean deleted;
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Account(int id, int numberAccount, int bill, boolean deleted) {
		this.numberAccount = numberAccount;
		this.bill = bill;
		this.deleted = deleted;
		this.id = id;
	}

	public Account() {
		super();
	}

	public long getNumberAccount() {
		return numberAccount;
	}

	public void setNumberAccount(int numberAccount) {
		this.numberAccount = numberAccount;
	}

	public double getBill() {
		return bill;
	}

	public void setBill(double bill) {
		this.bill = bill;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Account)) return false;

		Account account = (Account) o;

		if (numberAccount != account.numberAccount) return false;
		if (Double.compare(account.bill, bill) != 0) return false;
		if (deleted != account.deleted) return false;
		return id == account.id;

	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = numberAccount;
		temp = Double.doubleToLongBits(bill);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		result = 31 * result + (deleted ? 1 : 0);
		result = 31 * result + id;
		return result;
	}

	@Override
	public String toString() {
		return "Account [id=" + getId() + ", numberAccount=" + numberAccount
				+ ", bill=" + bill + ", deleted=" + deleted + "]";
	}

	@Override
	public DBProperty getDbProperty() {
		DBProperty dbProperty = new DBProperty();
		dbProperty.setClassName(getClass());
		dbProperty.setNameTable("ACCOUNT");
		dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("id", "ID_ACCOUNT", Integer.TYPE));
		dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("numberAccount", "NUMBER", Integer.TYPE));
		dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("bill", "BILL", Double.TYPE));
		dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("deleted", "DELETED", Boolean.TYPE));
		return dbProperty;
	}

}
