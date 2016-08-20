package by.julia.bankservice.util;


import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import java.io.File;

public class ConnectLog4jWithWeb {
	private static final String LOG4J_PROPERTIES = "log4j-properties-location";

	public static void connectLog4j(ServletConfig config){
		String log4jLocation = config.getInitParameter(LOG4J_PROPERTIES);
        ServletContext sc = config.getServletContext();
        if (log4jLocation == null) {
                BasicConfigurator.configure();
        } else {
                String webAppPath = sc.getRealPath("/");
                String log4jProp = webAppPath + log4jLocation;
                File prop = new File(log4jProp);
                if (prop.exists()) {
                        PropertyConfigurator.configure(log4jProp);
                } else {
                        BasicConfigurator.configure();
                }
        }
	}
}
