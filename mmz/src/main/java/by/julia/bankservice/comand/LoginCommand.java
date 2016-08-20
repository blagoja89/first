package by.julia.bankservice.comand;

import by.julia.bankservice.util.ConfigurationManager;
import by.julia.bankservice.util.MessageManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements ActionCommand {

	private static Logger LOG = Logger.getLogger(LoginCommand.class);
	private static String PARAM_NAME_LOGIN = "login";
	private static String PARAM_NAME_PASSWORD = "password";
	private static final String ERROR_MESSAGE = "errorMessage";
	private static final String MESSAGE = "message";
	private static final String USER = "user";
	private static final String USER_LOGIN = "userLogin";
	private static final String ACCESS_LEVEL = "accessLevel";

	@Override
	public String execute(HttpServletRequest request) {

		String page = null;

		VerifyAuthorization va = new VerifyAuthorization();

		String login = request.getParameter(PARAM_NAME_LOGIN);
		String password = request.getParameter(PARAM_NAME_PASSWORD);
		LOG.debug("login = " + login + ", password = " + password);

		if (login == null || password == null || login.trim().length() == 0
				|| password.trim().length() == 0) {
			request.setAttribute(ERROR_MESSAGE, MessageManager.getInstance()
					.getProperty(MessageManager.NULL_DATA_ERROR_MESSAGES));
			LOG.debug("Login is not entered");
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.AUTHORIZATION_PAGE_PATH);
		} else if (!va.verifyAuthorization(login, password)) {
			request.setAttribute(
					ERROR_MESSAGE,
					MessageManager.getInstance().getProperty(
							MessageManager.AUTHORIZATION_FALSE_ERROR_MESSAGES));
			LOG.debug("Login or password is uncorrect");
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.AUTHORIZATION_PAGE_PATH);
		} else if (va.getLevel() == 0) {
			request.setAttribute(ERROR_MESSAGE, MessageManager.getInstance()
					.getProperty(MessageManager.USER_BLOCKED_ERROR_MESSAGES));
			LOG.debug("User is blocked");
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.HOME_PAGE);
		} else {
			request.setAttribute(MESSAGE, MessageManager.getInstance()
					.getProperty(MessageManager.AUTHORIZATION_WAS_SUCCESS));
			LOG.debug("Authorization is success");
			request.getSession().setAttribute(USER, va.getUser());
			request.getSession().setAttribute(USER_LOGIN, login);
			request.getSession().setAttribute(ACCESS_LEVEL, va.getLevel());

			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.HOME_PAGE);
		}
		return page;
	}
}
