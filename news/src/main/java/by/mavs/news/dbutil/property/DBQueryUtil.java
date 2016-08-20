package by.mavs.news.dbutil.property;




public final class DBQueryUtil {

	private static final String SQL_WHERE = " WHERE ";
	private static final String SQL_SELCT_ALL = "SELECT * FROM ";
	private static final String SQL_DELETE = "DELETE FROM ";
	private static final String SQL_UPDATE = "UPDATE ";
	private static final String SQL_SET = " SET ";
	private static final String SQL_VALUE = " = ?";
	private static final String SQL_IN = " IN ";

	/**
	 * Create get method by name method and type
	 * @param name
	 * @param type
	 * @return result
	 */
	public String getMethodGetName(String name, Class type) {
		StringBuilder result = new StringBuilder();
		result.append(
				(type.getSimpleName().equalsIgnoreCase("Boolean") && !name
						.equalsIgnoreCase("Boolean")) ? "is" : "get")
				.append(name.substring(0, 1).toUpperCase())
				.append(name.substring(1));
		return result.toString();
	}

	/**
	 * Create set method by name method
	 * @param name
	 * @return result
	 */
	public String getMethodSetName(String name) {
		StringBuilder result = new StringBuilder();
		result.append("set").append(name.substring(0, 1).toUpperCase())
				.append(name.substring(1));
		return result.toString();
	}

	/**
	 * Create query "iNSERT INTO 'nameTable' parameters VALUES questions ;"
	 * @param dbProperty
	 * @return query
	 */
	public String getInsertQuery(DBProperty dbProperty) {
		String query = "INSERT INTO " + dbProperty.getNameTable()
				+ getParamByFields(dbProperty) + " VALUES"
				+ getValueBySize(dbProperty.getFieldSize());
		return query;
	}

/**
 * Create query "UPDATE NameTable SET changeSet WHERE value"
 * size - list all columns
 * @param dbProperty
 * @return query
 */
	public String getUpdateQuery(DBProperty dbProperty) {
		StringBuilder changeSet = new StringBuilder("");

		int size = dbProperty.getNameFields().size();

		for (int i = 0; i < size; i++) {
			changeSet.append(dbProperty.getNameFields().get(i) + "=" + "?"
					+ ((i > size - 2) ? "" : ", "));
		}

		String query = SQL_UPDATE + dbProperty.getNameTable() + SQL_SET
				+ changeSet + SQL_WHERE + dbProperty.getNameFields().get(0)
				+ SQL_VALUE;
		return query;
	}


	/**
	 * Create query "DELETE FROM NameTable WHERE columnName value"
	 * @param dbProperty
	 * @return query
	 */
	public String getDeleteQuery(DBProperty dbProperty) {
		String query = SQL_DELETE + dbProperty.getNameTable() + SQL_WHERE
				+ dbProperty.getNameFields().get(0) + SQL_VALUE;
		return query;
	}
	
	
	
	public String getDeleteListQuery(DBProperty dbProperty, int sizeList) {
		String query = SQL_DELETE + dbProperty.getNameTable() + SQL_WHERE
				+ dbProperty.getNameFields().get(0) + SQL_IN + getValueBySize(sizeList);
		return query;
	}
	
	
	/**
	 * Find by id
	 * Create query "SELECT * FROM NameTable WHERE  id = ?"
	 * @param dbProperty
	 * @return query
	 */
	public String getFindByIdQuery(DBProperty dbProperty) {
		String query = SQL_SELCT_ALL + dbProperty.getNameTable() + SQL_WHERE
				+ dbProperty.getNameFields().get(0) + SQL_VALUE;
		return query;
	}


	/**
	 * Find all.
	 * Create query "SELECT * FROM NameTable".
	 * @param dbProperty
	 * @return query
	 */
	public String getFindAllQuery(DBProperty dbProperty) {
		String query = SQL_SELCT_ALL + dbProperty.getNameTable();
		return query;
	}

	/**
	 * Generation questions under request
	 * @param size
	 * @return part
	 */
	private String getValueBySize(int size) {
		String values = "";
		for (int i = 0; i < size; i++) {
			values += "?" + ((i > size - 2) ? ")" : ", ");
		}
		String part = " (" + values;
		return part;
	}

	/**
	 * Generates parameters name 
	 * size - list columns
	 * @param dbProperty
	 * @return part
	 */
	private String getParamByFields(DBProperty dbProperty) {
		String fields = "";
		int sizeN = dbProperty.getNameFields().size();
		for (int i = 0; i < sizeN; i++) {
			fields += dbProperty.getNameFields().get(i)
					+ ((i > sizeN - 2) ? ")" : ", ");
		}
		String part = " (" + fields;
		return part;
	}

	
}
