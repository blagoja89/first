package by.mavs.railwayservice.command;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

import by.mavs.railwayservice.dao.UserDao;
import by.mavs.railwayservice.entity.User;
import by.mavs.railwayservice.exception.DAOTechnicalException;
import by.mavs.railwayservice.util.ConfigurationManager;
import by.mavs.railwayservice.util.MessageManager;




public class RegistrationCommand implements ActionCommand {


	private static final Logger LOG = Logger
			.getLogger(RegistrationCommand.class);

	@Override
	public String execute(HttpServletRequest request) {

		String page = null;
		
				
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		

		if (login == null || login.trim().length() == 0) {
			request.setAttribute("errorMessage", MessageManager.getInstance()
					.getProperty(MessageManager.NULL_LOGIN_ERROR_MESSAGE));
			LOG.debug("Login is not entered");
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.REGISTRATION_PAGE_PATH);
			return page;
		}
		else if (isExist(login)){
			request.setAttribute("errorMessage", MessageManager.getInstance()
			.getProperty(MessageManager.LOGIN_EXIST_ERROR_MESSAGE));
			LOG.debug("Login is exist");
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.REGISTRATION_PAGE_PATH);
		}

		else if (password == null || password.trim().length() == 0) {
			request.setAttribute("errorMessage", MessageManager.getInstance()
					.getProperty(MessageManager.NULL_PASSWORD_ERROR_MESSAGE));
			LOG.debug("Password is not entered");
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.REGISTRATION_PAGE_PATH);
			return page;
		}
		else if (password.trim().length() < 8) {
			request.setAttribute(
					"errorMessage",
					MessageManager.getInstance().getProperty(
							MessageManager.PASSWORD_TOO_SMALL_ERROR_MESSAGE));
			LOG.debug("Password is too small");
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.REGISTRATION_PAGE_PATH);
			return page;
		}
		
		else if (!password.equals(repassword)) {
			request.setAttribute(
					"errorMessage",
					MessageManager.getInstance().getProperty(
							MessageManager.PASSWORD_ERROR_MESSAGE));
			LOG.debug("Password not equal repassword");
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.REGISTRATION_PAGE_PATH);
			return page;
		}


		else {
			
			String firstname = request.getParameter("firstname");
			String lastname = request.getParameter("lastname");
			String email = request.getParameter("email");

			LOG.debug(login + ", " + password + ", " + firstname + "," + lastname);

			User newUser = new User();
			Date date = new Date(System.currentTimeMillis());
			long d = date.getTime();
			java.sql.Date sqlDate = new java.sql.Date(d);
			LOG.debug("sqlDate = " + sqlDate);

			newUser.setLogin(login);
			newUser.setPassword(password);
			newUser.setRole(1);

			if (firstname != null && firstname.trim().length() != 0) {
				newUser.setFirstName(firstname);
			}
			if (lastname != null && lastname.trim().length() != 0) {
				newUser.setLastName(lastname);
			}
			if (email != null && email.trim().length() != 0) {
				newUser.setEmail(email);
			}
			try {
				new UserDao().save(newUser);
			} catch (DAOTechnicalException e) {
				e.printStackTrace();
			}
			
			request.setAttribute("message", MessageManager.getInstance()
				.getProperty(MessageManager.REGISTRATION_WAS_SUCCESS));
		
			request.getSession().setAttribute("user", newUser);
			request.getSession().setAttribute("userLogin", login);
			request.getSession().setAttribute("accessLevel",
					newUser.getRole());
			
		LOG.debug("Registration was success");
		page = ConfigurationManager.getInstance().getProperty(
				ConfigurationManager.REGISTRATION_PAGE_PATH);
	
		}
		return page;
	}
	
	public boolean isExist(String login) {
		
		User user = new UserDao().findByLogin(login);
		String log = user.getLogin();
		LOG.debug("Enter login = " + login + ", equal login = " + log);
		
		if (log != null){
			return true;
		}
		
		return false;
	}

}
