package by.julia.bankservice.dbutil.property;


public class DBSimplePropertyItem {
	private String appName;
	private String dbName;
	private Class type;

	public DBSimplePropertyItem(String appName, String dbName, Class type) {
		super();
		this.appName = appName;
		this.dbName = dbName;
		this.type = type;
	}

	public String getAppName() {
		return appName;
	}

	public String getDbName() {
		return dbName;
	}

	public Class getType() {
		return type;
	}

}