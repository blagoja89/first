package by.julia.bankservice.comand;

import by.julia.bankservice.bean.MessageBean;
import by.julia.bankservice.bean.UserBean;
import by.julia.bankservice.dao.UserDao;
import by.julia.bankservice.entity.Message;
import by.julia.bankservice.entity.User;
import by.julia.bankservice.exception.DAOTechnicalException;
import by.julia.bankservice.util.ConfigurationManager;
import by.julia.bankservice.util.MessageManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class AdminCommand implements ActionCommand {

	private static final String SELECTED_CURRENT_USER_ATRIBUTE = "userList";
	private static final String LOGIN = "login";
	private static final String PASSWORD = "password";
	private static final String ERROR_MESSAGE = "errorMessage";
	private static final String MESSAGE = "message";
	private static final String TYPE = "type";
	private static final String SELECT = "select";
	private static final String USER_LIST = "userList";
	private static final String CHANGE = "change";
	private static final String LEVEL = "level";
	private static final String ADMIN = "admin";
	private static final String MESSAGES = "messages";
	
	private static Logger LOG = Logger.getLogger(AdminCommand.class);
	private UserDao userDao = new UserDao();
	private User user = null;

	private String passwordCommand(HttpServletRequest request) {
		String page = null;
		String login = request.getParameter(LOGIN);
		String pass = request.getParameter(PASSWORD);

		user = userDao.findByLogin(login);
		if (user != null) {
			user.setPassword(pass);
			try {
				userDao.update(user);
			} catch (DAOTechnicalException e) {
				LOG.debug(e.getMessage());
			}
			LOG.debug("password was changed");
			request.setAttribute(MESSAGE, MessageManager.getInstance()
					.getProperty(MessageManager.PASSWORD_WAS_CHANGED));
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.ADMIN_PAGE_PATH);

		} else {
			request.setAttribute(ERROR_MESSAGE, MessageManager.getInstance()
					.getProperty(MessageManager.NULL_LOGIN_ERROR_MESSAGE));
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.ADMIN_PAGE_PATH);
		}

		return page;
	}

	private String userCommand(HttpServletRequest request) {
		String page = null;
		String type = request.getParameter(TYPE);

		if (type.equals(SELECT)) {

			UserBean userBean = new UserBean();
			List<User> userList = new ArrayList<>();
				userList = userBean.getUserList();

			request.setAttribute(USER_LIST, userList);
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.ADMIN_USER_PAGE_PATH);
			return page;
			// *****
		} else if (type.equals(CHANGE)) {
			String login = request.getParameter(LOGIN);
			String role = request.getParameter(LEVEL);
			User userChange = userDao.findByLogin(login);
			userChange.setRole(Integer.parseInt(role));
			try {
				userDao.update(userChange);
			} catch (DAOTechnicalException e) {
				LOG.debug(e.getMessage());
			}

			request.setAttribute(MESSAGE, MessageManager.getInstance()
					.getProperty(MessageManager.LEVEL_WAS_CHANGED));
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.ADMIN_PAGE_PATH);
			return page;
		} else {
			request.setAttribute(ERROR_MESSAGE, MessageManager.getInstance()
					.getProperty(MessageManager.BAD_COMMAND_ERROR_MESSAGES));
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.ADMIN_PAGE_PATH);
			return page;
		}
	}

	private String messageCommand(HttpServletRequest request) {
		String page = null;
		MessageBean mesBean = new MessageBean();
		List<Message> messageList = new ArrayList<Message>();
			messageList = mesBean.getMessageList();

		request.setAttribute(MESSAGES, messageList);
		page = ConfigurationManager.getInstance().getProperty(
				ConfigurationManager.ADMIN_MESSAGE_PAGE_PATH);
		return page;
	}

	private String initClientCommand(HttpServletRequest request) {
		String page = ConfigurationManager.getInstance().getProperty(
				ConfigurationManager.ADMIN_PAGE_PATH);

		return page;
	}


	@Override
	public String execute(HttpServletRequest request) {

		String page = null;
		String command = request.getParameter(ADMIN);
		AdminCommandEnum commandEnum = AdminCommandEnum.valueOf(command
				.toUpperCase());

		switch (commandEnum) {
		case PASSWORD:
			page = passwordCommand(request);
			break;
		case USER:
			page = userCommand(request);
			break;
		case MESSAGE:
			page = messageCommand(request);
			break;
			case INIT:
				page = initClientCommand(request);
				break;
		default:
			request.setAttribute(ERROR_MESSAGE, MessageManager.getInstance()
					.getProperty(MessageManager.BAD_COMMAND_ERROR_MESSAGES));
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.ADMIN_PAGE_PATH);
		}
		return page;

	}
}
