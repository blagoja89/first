package by.mavs.railwayservice.dbutil.property;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import by.mavs.railwayservice.entity.Entity;

public class DBQueryUtil {

	private static final String SQL_WHERE = " WHERE ";
	private static final String SQL_SELCT_ALL = "SELECT * FROM ";
	private static final String SQL_DELETE = "DELETE FROM ";
	private static final String SQL_UPDATE = "UPDATE ";
	private static final String SQL_SET = " SET ";
	private static final String SQL_VALUE = " = ?;";

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
				+ getValueBySize(dbProperty.getFieldSize()) + ";";
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
	 * Find by property
	 * Create query "SELECT * FROM NameTable WHERE  'field name' = ?"
	 * @param dbProperty
	 * @param propertyName
	 * @return query
	 */
	public String getFindByPropertyQuery(DBProperty dbProperty,
			String propertyName) {
		String query = SQL_SELCT_ALL + dbProperty.getNameTable() + SQL_WHERE
				+ dbProperty.getDbNameByAppName(propertyName) + SQL_VALUE;
		return query;
	}

	/**
	 * Find by example 
	 * Create query "SELECT * FROM NameTable WHERE  'column name' = ?"
	 * changeSet - set 
	 * @param entity
	 * @return query
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public String getFindByExampleQuery(Entity entity)
			throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		StringBuilder changeSet = new StringBuilder();
		DBProperty dbProperty = entity.getDbProperty();
		int i = 0;
		for (Map.Entry<String, Class> me : dbProperty.getNameAppFields().entrySet()) {
			Method m = entity.getClass().getDeclaredMethod(
					getMethodGetName(me.getKey(), me.getValue()));
			Object ob = m.invoke(entity, new Object[] {});

			if (ob != null
					&& ((ob instanceof Integer) ? (Integer) ob != 0 : true)) {
				changeSet.append((changeSet.length() > 0) ? " AND " : "")
						.append(dbProperty.getNameFields().get(i) + "=" + "?");
			}
			i++;

		}

		String query = SQL_SELCT_ALL + dbProperty.getNameTable() + SQL_WHERE
				+ changeSet + ";";
		return query;
	}

	/**
	 * Find all.
	 * Create query "SELECT * FROM NameTable".
	 * @param dbProperty
	 * @return query
	 */
	public String getFindAllQuery(DBProperty dbProperty) {
		String query = SQL_SELCT_ALL + dbProperty.getNameTable() + ";";
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

	/**
	 * Find trains by order 
	 * Search parameters NAME_STATION departure, NAME_STATION arrival, DEPARTURE_DATE
	 * @return query 
	 */
	public String getFindTrainsByOrderQuery() {
		StringBuilder sb = new StringBuilder();
		sb.append("select tr.* from rwby.train tr ")
				.append("left join rwby.train_map_station tmsa on tmsa.TRAIN_ID = tr.ID_TRAIN ")
				.append("left join rwby.train_map_station tmsd on tmsd.TRAIN_ID = tr.ID_TRAIN ")
				.append("left join rwby.map_station msa on msa.ID_MAP_STATION = tmsa.MAP_STATION_ID ")
				.append("left join rwby.map_station msd on msd.ID_MAP_STATION = tmsd.MAP_STATION_ID ")
				.append("left join rwby.station sd on sd.ID_STATION = msd.STATION_DEPARTURE ")
				.append("left join rwby.station sa on sa.ID_STATION = msa.STATION_ARRIVAL ")
				.append("right join rwby.train_date td on td.TRAIN_ID = tr.ID_TRAIN ")
				.append("where sd.NAME_STATION = ? and sa.NAME_STATION = ? and td.DEPARTURE_DATE = ? ")
				.append("AND TMSD.SEQ_NO <= TMSA.SEQ_NO	");
		return sb.toString();
	}

	/**
	 * Find train dates by order
	 * Search parameters NAME_STATION departure, NAME_STATION arrival, DEPARTURE_DATE
	 * @return query
	 */
	public String getFindTrainDatesByOrderQuery() {
		StringBuilder sb = new StringBuilder();
		sb.append("select tdr.* from rwby.train_date tdr  ")
		.append("left join rwby.train tr on tr.ID_TRAIN = tdr.TRAIN_ID  ")
		.append("left join rwby.train_map_station tmsa on tmsa.TRAIN_ID = tr.ID_TRAIN  ")
		.append("left join rwby.train_map_station tmsd on tmsd.TRAIN_ID = tr.ID_TRAIN  ")
		.append("left join rwby.map_station msa on msa.ID_MAP_STATION = tmsa.MAP_STATION_ID  ")
		.append("left join rwby.map_station msd on msd.ID_MAP_STATION = tmsd.MAP_STATION_ID  ")
		.append("left join rwby.station sd on sd.ID_STATION = msd.STATION_DEPARTURE  ")
		.append("left join rwby.station sa on sa.ID_STATION = msa.STATION_ARRIVAL  ")
		
		.append("where sd.NAME_STATION = ? and sa.NAME_STATION = ? ")
		.append("and tdr.DEPARTURE_DATE = ? and tmsd.SEQ_NO <= tmsa.SEQ_NO;	 ");
		return sb.toString();
	}
	
	/**
	 * Find ticket between date
	 * Search parameters TIME_DEPARTURE, TIME_ARRIVAL, and user_id
	 * @return
	 */
	public String getFindTicketBetweenDateQuery() {
		StringBuilder sb = new StringBuilder();
		sb.append("select * from rwby.ticket t where t.TIME_DEPARTURE >= ? and t.TIME_ARRIVAL <= ? and user_id = ?");
		return sb.toString();
	}

}
