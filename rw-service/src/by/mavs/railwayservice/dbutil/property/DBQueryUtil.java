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

	public String getMethodGetName(String name, Class type) {
		StringBuilder result = new StringBuilder();
		result.append(
				(type.getSimpleName().equalsIgnoreCase("Boolean") && !name
						.equalsIgnoreCase("Boolean")) ? "is" : "get")
				.append(name.substring(0, 1).toUpperCase())
				.append(name.substring(1));
		return result.toString();
	}

	public String getMethodSetName(String name) {
		StringBuilder result = new StringBuilder();
		result.append("set").append(name.substring(0, 1).toUpperCase())
				.append(name.substring(1));
		return result.toString();
	}

	public String getInsertQuery(DBProperty dbProperty) {
		String query = "INSERT INTO " + dbProperty.getNameTable()
				+ getParamByFields(dbProperty) + " VALUES"
				+ getValueBySize(dbProperty.getFieldSize()) + ";";
		return query;
	}

	// TODO For extended class's
	// private List<String> getInsertQueryListTemp(List<String> list, DBProperty
	// dbProperty) throws InstantiationException, IllegalAccessException {
	// String query = "INSERT INTO " + dbProperty.getNameTable()
	// + getParamByFields(dbProperty) + " VALUES"
	// + getValueBySize(dbProperty.getFieldSize()) + ";";
	// list.add(query);
	// if(dbProperty.getDbExtendsPropertyItem() == null){
	// Collections.reverse(list);
	// return list;
	// }
	// return
	// getInsertQueryListTemp(list,dbProperty.getDbExtendsPropertyItem().getSuperClass().newInstance().getDbProperty());
	// }
	//
	// public List<String> getInsertQueryList(DBProperty dbProperty)
	// throws InstantiationException, IllegalAccessException {
	// List<String> list = new ArrayList<>();
	// return getInsertQueryListTemp(list, dbProperty);
	// }

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

	public String getDeleteQuery(DBProperty dbProperty) {
		String query = SQL_DELETE + dbProperty.getNameTable() + SQL_WHERE
				+ dbProperty.getNameFields().get(0) + SQL_VALUE;
		return query;
	}

	public String getFindByIdQuery(DBProperty dbProperty) {
		String query = SQL_SELCT_ALL + dbProperty.getNameTable() + SQL_WHERE
				+ dbProperty.getNameFields().get(0) + SQL_VALUE;
		return query;
	}

	public String getFindByPropertyQuery(DBProperty dbProperty,
			String propertyName) {
		String query = SQL_SELCT_ALL + dbProperty.getNameTable() + SQL_WHERE
				+ dbProperty.getDbNameByAppName(propertyName) + SQL_VALUE;
		return query;
	}

	public String getFindByExampleQuery(Entity entity)
			throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		StringBuilder changeSet = new StringBuilder();
		DBProperty dbProperty = entity.getDbProperty();
		Class noparams[] = {};
		int i = 0;
		int size = dbProperty.getNameAppFields().entrySet().size();
		for (Map.Entry<String, Class> me : dbProperty.getNameAppFields()
				.entrySet()) {
			Method m = entity.getClass().getDeclaredMethod(
					getMethodGetName(me.getKey(), me.getValue()), noparams);
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

	public String getFindAllQuery(DBProperty dbProperty) {
		String query = SQL_SELCT_ALL + dbProperty.getNameTable() + ";";
		return query;
	}

	private String getValueBySize(int size) {
		String values = "";
		for (int i = 0; i < size; i++) {
			values += "?" + ((i > size - 2) ? ")" : ", ");
		}
		String part = " (" + values;
		return part;
	}

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

	public String getfindTrainsByOrderQuery() {
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

	public String getfindTrainDatesByOrderQuery() {
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
}
