package by.mavs.railwayservice.dbutil.property;

import by.mavs.railwayservice.entity.Entity;

public class DBExtendsPropertyItem {
	private String appName;
	private Class appClassName;
	private String dbName;
	private String dbFkName;
	private Class<? extends Entity> superClass;


	public DBExtendsPropertyItem(String appName, Class appClassName,
			String dbName, String dbFkName, Class<? extends Entity> superClass) {
		super();
		this.appName = appName;
		this.appClassName = appClassName;
		this.dbName = dbName;
		this.dbFkName = dbFkName;
		this.superClass = superClass;
	}

	public String getAppName() {
		return appName;
	}

	public String getDbName() {
		return dbName;
	}

	public String getDbFkName() {
		return dbFkName;
	}

	public Class<? extends Entity> getSuperClass() {
		return superClass;
	}

	public Class getAppClassName() {
		return appClassName;
	}

}