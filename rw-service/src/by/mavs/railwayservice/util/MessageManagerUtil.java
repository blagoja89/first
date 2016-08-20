package by.mavs.railwayservice.util;



import java.util.Properties;

import org.apache.log4j.Logger;

public class MessageManagerUtil {

	private static final Properties properties = new Properties();
	private static final Logger LOG = Logger.getLogger(MessageManagerUtil.class);

	static {
		try {
			properties.load(MessageManagerUtil.class.getClassLoader()
					.getResourceAsStream(PropertyConst.MESSAGES_PROPERTIES));
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

