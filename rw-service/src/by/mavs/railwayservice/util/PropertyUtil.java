package by.mavs.railwayservice.util;



import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertyUtil {
	private static Properties properties = new Properties();
	private static final Logger LOG = Logger.getLogger(PropertyUtil.class);

	static {
		try {
			properties.load(PropertyUtil.class.getClassLoader()
					.getResourceAsStream(PropertyConst.APPLICATION_PROPERTIES));
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
