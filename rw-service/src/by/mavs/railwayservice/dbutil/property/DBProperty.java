package by.mavs.railwayservice.dbutil.property;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DBProperty {

	private Class className;
//	private DBExtendsPropertyItem dbExtendsPropertyItem;
	private String nameTable;
	
	private List<DBSimplePropertyItem> dbSimplePropertyItems = new ArrayList<>();
	private List<DBOneToManyPropertyItem> dbOneToManyPropertyItems = new ArrayList<>();
	private List<DBManyToOnePropertyItem> dbManyToOnePropertyItems = new ArrayList<>();
	private List<String> nameFields;
	private Map<String,Class> fieldSettings;
	private List<String> nameAppFields;

	public Class getClassName() {
		return className;
	}

	public void setClassName(Class className) {
		this.className = className;
	}
//
//	public DBExtendsPropertyItem getDbExtendsPropertyItem() {
//		return dbExtendsPropertyItem;
//	}
//
//	public void setDbExtendsPropertyItem(DBExtendsPropertyItem dbExtendsPropertyItem) {
//		this.dbExtendsPropertyItem = dbExtendsPropertyItem;
//	}

	public List<DBSimplePropertyItem> getDbSimplePropertyItems() {
		return dbSimplePropertyItems;
	}

	public void setDbSimplePropertyItems(
			List<DBSimplePropertyItem> dbSimplePropertyItems) {
		this.dbSimplePropertyItems = dbSimplePropertyItems;
	}

	public List<DBOneToManyPropertyItem> getDbOneToManyPropertyItems() {
		return dbOneToManyPropertyItems;
	}

	public void setDbOneToManyPropertyItems(
			List<DBOneToManyPropertyItem> dbOneToManyPropertyItems) {
		this.dbOneToManyPropertyItems = dbOneToManyPropertyItems;
	}

	public List<DBManyToOnePropertyItem> getDbManyToOnePropertyItems() {
		return dbManyToOnePropertyItems;
	}

	public void setDbManyToOnePropertyItems(
			List<DBManyToOnePropertyItem> dbManyToOnePropertyItems) {
		this.dbManyToOnePropertyItems = dbManyToOnePropertyItems;
	}

	public String getNameTable() {
		return nameTable;
	}

	public void setNameTable(String nameTable) {
		this.nameTable = nameTable;
	}

	
	public int getFieldSize(){
		return getDbManyToOnePropertyItems().size()
				+ getDbSimplePropertyItems().size()
//				+ 	((dbExtendsPropertyItem != null)?1:
//					0)
					;
	}

	public List<String> getNameFields(){
		if(nameFields == null){
			nameFields = new ArrayList<>();
//			if(dbExtendsPropertyItem != null){
//				nameFields.add(dbExtendsPropertyItem.getDbName());
//			}
			for (DBSimplePropertyItem item : getDbSimplePropertyItems()) {
				nameFields.add(item.getDbName());
			}
			for (DBManyToOnePropertyItem item : getDbManyToOnePropertyItems()) {
				nameFields.add(item.getDbName());
			}
		}
		return nameFields;
	}
	
	public Map<String,Class> getNameAppFields(){
		if(fieldSettings == null){
			fieldSettings = new LinkedHashMap<>();
			for (DBSimplePropertyItem item : getDbSimplePropertyItems()) {
				fieldSettings.put(item.getAppName(), item.getType());
			}
			for (DBManyToOnePropertyItem item : getDbManyToOnePropertyItems()) {
				fieldSettings.put(item.getAppName(), item.getClassName());
			}
		}
		return fieldSettings;
	}
	
	public String getAppNameByDbName(String dbName){
		for (DBSimplePropertyItem item : getDbSimplePropertyItems()) {
			if(item.getDbName().equals(dbName)){
				return item.getAppName();
			}
		}
		for (DBManyToOnePropertyItem item : getDbManyToOnePropertyItems()) {
			if(item.getDbName().equals(dbName)){
				return item.getAppName();
			}
		}
		return null;
	}
	

	public String getDbNameByAppName(String appName){
		for (DBSimplePropertyItem item : getDbSimplePropertyItems()) {
			if(item.getAppName().equals(appName)){
				return item.getDbName();
			}
		}
		for (DBManyToOnePropertyItem item : getDbManyToOnePropertyItems()) {
			if(item.getAppName().equals(appName)){
				return item.getDbName();
			}
		}
		return null;
	}
}
