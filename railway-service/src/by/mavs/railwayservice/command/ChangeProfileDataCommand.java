package by.mavs.railwayservice.command;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

import by.mavs.railwayservice.dao.UserDao;
import by.mavs.railwayservice.entity.User;
import by.mavs.railwayservice.exception.DAOTechnicalException;
import by.mavs.railwayservice.util.ConfigurationManager;
import by.mavs.railwayservice.util.MessageManager;

public class ChangeProfileDataCommand implements ActionCommand {

	private static final Logger LOG = Logger
			.getLogger(ChangeProfileDataCommand.class);
	private static final String EMAIL = "email";
	private static final String USER = "user";
	private static final String PASSWORD = "password";
	private static final String ERROR_MESSAGE = "errorMessage";
	private static final String MESSAGE = "message";

	@Override
	public String execute(HttpServletRequest request) {

		String page;
		User newUser = new User();
		UserDao userDao = new UserDao();

		String password = request.getParameter(PASSWORD);
		String mail = request.getParameter(EMAIL);

		User oldUser = (User) request.getSession().getAttribute(USER);

		newUser.setId(oldUser.getId());
		newUser.setLogin(oldUser.getLogin());
		newUser.setRole(oldUser.getRole());
		newUser.setFirstName(oldUser.getFirstName());
		newUser.setLastName(oldUser.getLastName());
		newUser.setPassword(oldUser.getPassword());
		newUser.setAccount(oldUser.getAccount());

		if (mail != null && mail.trim().length() != 0) {
			newUser.setEmail(mail);
		} else {
			newUser.setEmail(oldUser.getEmail());
		}
		if (password == null || password.trim().length() == 0) {
			newUser.setPassword(oldUser.getPassword());

		} else {
			if (password.trim().length() < 2) {

				request.setAttribute(ERROR_MESSAGE, MessageManager.getInstance().getProperty(
										MessageManager.PASSWORD_TOO_SMALL_ERROR_MESSAGE));
				page = ConfigurationManager.getInstance().getProperty(
						ConfigurationManager.PROFILE_PAGE_PATH);
				return page;
			} else {
				newUser.setPassword(password);
			}
		}
		try {
			userDao.update(newUser);
		} catch (DAOTechnicalException e) {
			LOG.debug(e.getMessage());
		}
		LOG.debug(oldUser.toString());
		LOG.debug(newUser.toString());

		request.setAttribute(
				MESSAGE,
				MessageManager.getInstance().getProperty(
						MessageManager.CHANGE_PROFILE_DATA_WAS_SUCCESS));

		page = ConfigurationManager.getInstance().getProperty(
				ConfigurationManager.PROFILE_PAGE_PATH);
		request.getSession().setAttribute(USER, newUser);

		return page;
	}

}
