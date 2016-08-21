package by.julia.bankservice.dbutil.property;

public class DBOneToManyPropertyItem {
	private String appName;
	private String dbFkName;
	private String dbName;
	private String appFkName;
	private Class className;
	private Class classFkName;
	private boolean lazy;
	

	public DBOneToManyPropertyItem(String appName, String dbName, String appFkName,
			String dbFkName, Class className, Class classFkName, boolean lazy) {
		super();
		this.appFkName = appFkName;
		this.appName = appName;
		this.dbFkName = dbFkName;
		this.dbName = dbName;
		this.className = className;
		this.classFkName = classFkName;
		this.lazy = lazy;
	}

	public String getAppName() {
		return appName;
	}

	public String getDbFkName() {
		return dbFkName;
	}

	public Class getClassName() {
		return className;
	}

	public String getDbName() {
		return dbName;
	}

	public boolean isLazy() {
		return lazy;
	}

	public String getAppFkName() {
		return appFkName;
	}

	public Class getClassFkName() {
		return classFkName;
	}
	
}