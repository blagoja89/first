package by.mavs.railwayservice.util;



import java.util.Properties;

import org.apache.log4j.Logger;

public class MySQLProperty {
	private static Properties properties = new Properties();
	private static final Logger LOG = Logger.getLogger(MySQLProperty.class);

	static {
		try {
			properties.load(MySQLProperty.class.getClassLoader()
					.getResourceAsStream(PropertyConst.MYSQL_PROPERTIES));
		} catch (Exception ex) {
			LOG.error(ex.getMessage());
		}
	}
	

	public static String getProperty(String key) {
		String value = null;
			value = properties.getProperty(key);
		return value;
	}

}
