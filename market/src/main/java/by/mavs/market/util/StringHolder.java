package by.mavs.market.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public final class StringHolder implements ServletContextListener  {

	public static final String MARKET_XML = "/market.xml";
	public static final String CAT_NAME = "catName";
	public static final String SUBCAT_NAME = "subcatName";
	public static final String MODEL = "model";
	public static final String COLOR = "color";
	public static final String DATE_OF_ISSUE = "dateOfIssue";
	public static final String PRICE = "price";
	public static final String PRODUCER = "producer";
	public static final String NOT_IN_STOCK = "notInStock";
	public static final String VALIDATOR = "validator";
	public static final String NAME = "name";
	public static String MARKET_REAL_PATH = null;
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		MARKET_REAL_PATH = sce.getServletContext().getRealPath(
				"WEB-INF/classes/market.xml");
		
	}
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}
	
	

}
