
package by.julia.bankservice.util;

import org.apache.log4j.Logger;

import java.util.ResourceBundle;


public class MessageManager {
	static final Logger LOG = Logger.getLogger(MessageManager.class);
	private static MessageManager instance;
	private ResourceBundle resourceBundle;
	
	private static final String BUNDLE_NAME = "property.messages";
	public static final String NULL_LOGIN_ERROR_MESSAGE = "NULL_LOGIN_ERROR_MESSAGE";
	public static final String NULL_PASSWORD_ERROR_MESSAGE = "NULL_PASSWORD_ERROR_MESSAGE";
	public static final String PASSWORD_TOO_SMALL_ERROR_MESSAGE = "PASSWORD_TOO_SMALL_ERROR_MESSAGE";
	public static final String LOGIN_EXIST_ERROR_MESSAGE = "LOGIN_EXIST_ERROR_MESSAGE";
	public static final String PASSWORD_ERROR_MESSAGE = "PASSWORD_ERROR_MESSAGE";
	public static final String REGISTRATION_WAS_SUCCESS = "REGISTRATION_WAS_SUCCESS";
	public static final String LOGIN_ERROR_MESSAGE = "LOGIN_ERROR_MESSAGE";
	public static final String LOGOUT_MESSAGE = "LOGOUT_MESSAGE";
	public static final String ERROR_MESSAGE = "ERROR_MESSAGE";
	public static final String NULL_DATA_ERROR_MESSAGES = "NULL_DATA_ERROR_MESSAGES";
	public static final String AUTHORIZATION_FALSE_ERROR_MESSAGES = "AUTHORIZATION_FALSE_ERROR_MESSAGES";
	public static final String USER_BLOCKED_ERROR_MESSAGES = "USER_BLOCKED_ERROR_MESSAGES";
	public static final String AUTHORIZATION_WAS_SUCCESS = "AUTHORIZATION_WAS_SUCCESS";
	public static final String CHANGE_PROFILE_DATA_WAS_SUCCESS = "CHANGE_PROFILE_DATA_WAS_SUCCESS";
	public static final String MESSAGE_SENT = "MESSAGE_SENT";
	public static final String ERROR_SENDING_MESSAGE = "ERROR_SENDING_MESSAGE";
	public static final String USER_WAS_NOT_DEFINE_ERROR_MESSAGE = "USER_WAS_NOT_DEFINE_ERROR_MESSAGE";
	public static final String PASSWORD_WAS_CHANGED = "PASSWORD_WAS_CHANGED";
	public static final String LEVEL_WAS_CHANGED = "LEVEL_WAS_CHANGED";
	public static final String NULL_ID_PRODUCT_ERROR_MESSAGES = "NULL_ID_PRODUCT_ERROR_MESSAGES";	
	public static final String PRODUCT_WAS_ADDED = "PRODUCT_WAS_ADDED";
	public static final String PRODUCT_WAS_REMOVED = "PRODUCT_WAS_REMOVED";
	public static final String PRODUCT_WAS_UPDATE = "PRODUCT_WAS_UPDATE";
	public static final String MESSAGE_RESPONSE_WAS_UPDATE = "MESSAGE_RESPONSE_WAS_UPDATE";
	public static final String IGNORE_DELETE = "IGNORE_DELETE";
	public static final String NULL_MAIL_ERROR_MESSAGE = "NULL_MAIL_ERROR_MESSAGE";
	public static final String UNKNOWN_TYPE_OF_SELECT = "UNKNOWN_TYPE_OF_SELECT";
	public static final String EMPTY_SEARCH_ERROR_MESSAGES = "EMPTY_SEARCH_ERROR_MESSAGES";
	public static final String BAD_COMMAND_ERROR_MESSAGES = "BAD_COMMAND_ERROR_MESSAGES";
	public static final String CART_IS_EMPTY_ERROR_MESSAGE = "CART_IS_EMPTY_ERROR_MESSAGE";
	public static final String NOT_ENOUGH_PRODUCT_ERROR_MESSAGES = "NOT_ENOUGH_PRODUCT_ERROR_MESSAGES";
	public static final String NULL_ADDRESS_FORM_ERROR_MESSAGES = "NULL_ADDRESS_FORM_ERROR_MESSAGES";
	public static final String NULL_PHONE_ERROR_MESSAGES = "NULL_PHONE_ERROR_MESSAGES";
	public static final String NULL_DELIVERY_ERROR_MESSAGES = "NULL_DELIVERY_ERROR_MESSAGES";
	public static final String NULL_PAYMENT_ERROR_MESSAGES = "NULL_PAYMENT_ERROR_MESSAGES";
	public static final String YOU_SENT_MESSAGE = "YOU_SENT_MESSAGE";
	public static final String INCORRECT_DATE_ERROR_MESSAGES = "INCORRECT_DATE_ERROR_MESSAGES";
	public static final String TRAIN_ADD = "TRAIN_ADD";
	public static final String ENTER_ONLY_NUMBERS = "ENTER_ONLY_NUMBERS";
	public static final String MONEY_SUCCESSFULLY_ADDED = "MONEY_SUCCESSFULLY_ADDED";
	public static final String ENTER_DATE_CORRECT_FORMAT = "ENTER_DATE_CORRECT_FORMAT";
	
	
	public static MessageManager getInstance(){
		if (instance == null){
			instance = new MessageManager();
			instance.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
		}
			return instance;
	}

	public String getProperty(String key) {
		LOG.debug("key =" + key);
		LOG.debug("property = " + resourceBundle.getObject(key));
		return (String) resourceBundle.getObject(key);
	}
}