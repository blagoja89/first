package by.julia.bankservice.util;


import org.apache.log4j.Logger;

import java.util.ResourceBundle;


public class ConfigurationManager {
	private static final Logger LOG = Logger.getLogger(ConfigurationManager.class);
	private static ConfigurationManager instance;
	private ResourceBundle resourceBundle;

	private static final String BUNDLE_NAME = "property.config";
	public static final String DB_DRIVER_NAME="DATABASE_DRIVER_NAME";
	public static final String DB_URL="DATABASE_URL";
	public static final String ERROR_PAGE_PATH = "ERROR_PAGE_PATH";
	public static final String ABOUT_US_PAGE_PATH = "ABOUT_US_PAGE_PATH";
	public static final String PRODUCTS_PAGE_PATH = "PRODUCTS_PAGE_PATH";
	public static final String AUTHORIZATION_PAGE_PATH = "AUTHORIZATION_PAGE_PATH";
	public static final String HOME_PAGE = "HOME_PAGE";
	public static final String CHOOSE_TICKET_WAGON_PAGE = "CHOOSE_TICKET_WAGON_PAGE";
	public static final String PAYMENT_TICKET_PAGE = "PAYMENT_TICKET_PAGE";
	public static final String REGISTRATION_PAGE_PATH = "REGISTRATION_PAGE_PATH";
	public static final String CHANGE_PROFILE_PATH = "CHANGE_PROFILE_PATH";
	public static final String PROFILE_PAGE_PATH = "PROFILE_PAGE_PATH";
	public static final String ADMIN_PAGE_PATH = "ADMIN_PAGE_PATH";
	public static final String ORDER_PAGE = "ORDER_PAGE";
	public static final String ACCOUNT_PAGE = "ACCOUNT_PAGE";
	public static final String ADMIN_USER_PAGE_PATH = "ADMIN_USER_PAGE_PATH";
	public static final String ADMIN_MESSAGE_PAGE_PATH = "ADMIN_MESSAGE_PAGE_PATH";

	private ConfigurationManager() {
	}

	public static ConfigurationManager getInstance(){
		if (instance == null){
			instance = new ConfigurationManager();
			instance.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
		}
			return instance;
	}
	
	public String getProperty(String key){
		LOG.debug("key = " + key);
		LOG.debug("property = " + resourceBundle.getObject(key));
		return (String)resourceBundle.getObject(key);
	}

}