package by.mavs.news.dbutil.property;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class DBProperty {

	private Class className;
	private String nameTable;

	private List<DBSimplePropertyItem> dbSimplePropertyItems = new ArrayList<>();
	private List<String> nameFields;
	private Map<String, Class> fieldSettings;
	private List<String> nameAppFields;

	public Class getClassName() {
		return className;
	}

	public void setClassName(Class className) {
		this.className = className;
	}

	public List<DBSimplePropertyItem> getDbSimplePropertyItems() {
		return dbSimplePropertyItems;
	}

	public void setDbSimplePropertyItems(
			List<DBSimplePropertyItem> dbSimplePropertyItems) {
		this.dbSimplePropertyItems = dbSimplePropertyItems;
	}

	public String getNameTable() {
		return nameTable;
	}

	public void setNameTable(String nameTable) {
		this.nameTable = nameTable;
	}

	/**
	 * 
	 * @return field size
	 */
	public int getFieldSize() {
		return getDbSimplePropertyItems().size();
	}

	/**
	 * return list DbName
	 * 
	 * @return nameFields
	 */
	public List<String> getNameFields() {
		if (nameFields == null) {
			nameFields = new ArrayList<>();
			for (DBSimplePropertyItem item : getDbSimplePropertyItems()) {
				nameFields.add(item.getDbName());
			}
		}
		return nameFields;
	}

	/**
	 * return map with field name and class type from SimpleProperty and
	 * ManyToOneProperty
	 * 
	 * @return fieldSettings
	 */
	public Map<String, Class> getNameAppFields() {
		if (fieldSettings == null) {
			fieldSettings = new LinkedHashMap<>();
			for (DBSimplePropertyItem item : getDbSimplePropertyItems()) {
				fieldSettings.put(item.getAppName(), item.getType());
			}
		}
		return fieldSettings;
	}

	/**
	 * return field name by name column name
	 * 
	 * @param dbName
	 * @return item.getAppName()
	 */
	public String getAppNameByDbName(String dbName) {
		for (DBSimplePropertyItem item : getDbSimplePropertyItems()) {
			if (item.getDbName().equals(dbName)) {
				return item.getAppName();
			}
		}
		return null;
	}

	/**
	 * return column name by field name
	 * 
	 * @param appName
	 * @return item.getDbName()
	 */
	public String getDbNameByAppName(String appName) {
		for (DBSimplePropertyItem item : getDbSimplePropertyItems()) {
			if (item.getAppName().equals(appName)) {
				return item.getDbName();
			}
		}
		return null;
	}
}
