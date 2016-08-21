package by.julia.bankservice.dbutil.property;

public class DBManyToOnePropertyItem {
	private String appName;
	private String dbName;
	private Class className;
	private String appFkName;
	private String dbFkName;
	private Class classFkName;
	private boolean lazy;

	public DBManyToOnePropertyItem(String appName, String dbName,
			Class className, String appFkName, String dbFkName,
			Class classFkName, boolean lazy) {
		super();
		this.appName = appName;
		this.dbName = dbName;
		this.className = className;
		this.dbFkName = dbFkName;
		this.appFkName = appFkName;
		this.classFkName = classFkName;
		this.lazy = lazy;
	}

	public String getAppName() {
		return appName;
	}

	public String getDbName() {
		return dbName;
	}

	public Class getClassName() {
		return className;
	}

	public String getDbFkName() {
		return dbFkName;
	}

	public String getAppFkName() {
		return appFkName;
	}

	public Class getClassFkName() {
		return classFkName;
	}

	public boolean isLazy() {
		return lazy;
	}

}