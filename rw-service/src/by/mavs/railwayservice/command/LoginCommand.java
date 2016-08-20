package by.mavs.railwayservice.command;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

import by.mavs.railwayservice.util.ConfigurationManager;
import by.mavs.railwayservice.util.MessageManager;

public class LoginCommand implements ActionCommand {

	private static Logger LOG = Logger.getLogger(LoginCommand.class);
	private static String PARAM_NAME_LOGIN = "login";
	private static String PARAM_NAME_PASSWORD = "password";

	@Override
	public String execute(HttpServletRequest request) {

		String page = null;

		VerifyAuthorization va = new VerifyAuthorization();

		String login = request.getParameter(PARAM_NAME_LOGIN);
		String password = request.getParameter(PARAM_NAME_PASSWORD);
		LOG.debug("login = " + login + ", password = " + password);

		if (login == null || password == null || login.trim().length() == 0
				|| password.trim().length() == 0) {
			request.setAttribute("errorMessage", MessageManager.getInstance()
					.getProperty(MessageManager.NULL_DATA_ERROR_MESSAGES));
			LOG.debug("Login is not entered");
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.AUTHORIZATION_PAGE_PATH);
		} else if (!va.verifyAuthorization(login, password)) {
			request.setAttribute(
					"errorMessage",
					MessageManager.getInstance().getProperty(
							MessageManager.AUTHORIZATION_FALSE_ERROR_MESSAGES));
			LOG.debug("Login or password is uncorrect");
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.AUTHORIZATION_PAGE_PATH);
		} else if (va.getLevel() == 0) {
			request.setAttribute("errorMessage", MessageManager.getInstance()
					.getProperty(MessageManager.USER_BLOCKED_ERROR_MESSAGES));
			LOG.debug("User is blocked");
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.HOME_PAGE);
		} else {
			request.setAttribute("message", MessageManager.getInstance()
					.getProperty(MessageManager.AUTHORIZATION_WAS_SUCCESS));
			LOG.debug("Authorization is success");
			request.getSession().setAttribute("user", va.getUser());
			request.getSession().setAttribute("userLogin", login);
			request.getSession().setAttribute("accessLevel", va.getLevel());

			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.HOME_PAGE);
		}
		return page;
	}
}
