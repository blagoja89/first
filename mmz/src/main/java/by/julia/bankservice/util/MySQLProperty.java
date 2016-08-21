package by.julia.bankservice.util;


import org.apache.log4j.Logger;

import java.util.Properties;

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
