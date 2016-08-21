package by.julia.bankservice.comand;

import by.julia.bankservice.dao.*;
import by.julia.bankservice.entity.*;
import by.julia.bankservice.exception.DAOTechnicalException;
import by.julia.bankservice.util.ConfigurationManager;
import by.julia.bankservice.util.MessageManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

public class RegistrationCommand implements ActionCommand {
	private static final String ERROR_MESSAGE = "errorMessage";
	private static final String MESSAGE = "message";

	private static final String LOGIN = "login";
	private static final String PASSWORD = "password";
	private static final String REPASSWORD = "repassword";
	private static final String FIRSTNAME = "firstname";
	private static final String LASTNAME = "lastname";
	private static final String EMAIL = "email";

	private static final Logger LOG = Logger
			.getLogger(RegistrationCommand.class);
	
	private AccountDao accountDao = new AccountDao();
	private UserDao userDao = new UserDao();
	private ClientAdditionalInformationDao additionalInformationDao = new ClientAdditionalInformationDao();
	private PassportDataDao passportDataDao = new PassportDataDao();
	private ClientInformationDao clientInformationDao = new ClientInformationDao();

	@Override
	public String execute(HttpServletRequest request) {

		String page = null;

		String login = request.getParameter(LOGIN);
		String password = request.getParameter(PASSWORD);
		String repassword = request.getParameter(REPASSWORD);

		if (login == null || login.trim().length() == 0) {
			request.setAttribute(ERROR_MESSAGE, MessageManager.getInstance()
					.getProperty(MessageManager.NULL_LOGIN_ERROR_MESSAGE));
			LOG.debug("Login is not entered");
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.REGISTRATION_PAGE_PATH);
			return page;
		} else if (isExist(login)) {
			request.setAttribute(ERROR_MESSAGE, MessageManager.getInstance()
					.getProperty(MessageManager.LOGIN_EXIST_ERROR_MESSAGE));
			LOG.debug("Login is exist");
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.REGISTRATION_PAGE_PATH);
		}

		else if (password == null || password.trim().length() == 0) {
			request.setAttribute(ERROR_MESSAGE, MessageManager.getInstance()
					.getProperty(MessageManager.NULL_PASSWORD_ERROR_MESSAGE));
			LOG.debug("Password is not entered");
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.REGISTRATION_PAGE_PATH);
			return page;
		} else if (password.trim().length() < 2) {
			request.setAttribute(
					ERROR_MESSAGE,
					MessageManager.getInstance().getProperty(
							MessageManager.PASSWORD_TOO_SMALL_ERROR_MESSAGE));
			LOG.debug("Password is too small");
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.REGISTRATION_PAGE_PATH);
			return page;
		}

		else if (!password.equals(repassword)) {
			request.setAttribute(ERROR_MESSAGE, MessageManager.getInstance()
					.getProperty(MessageManager.PASSWORD_ERROR_MESSAGE));
			LOG.debug("Password not equal repassword");
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.REGISTRATION_PAGE_PATH);
			return page;
		}

		else {

			String firstname = request.getParameter(FIRSTNAME);
			String lastname = request.getParameter(LASTNAME);
			String email = request.getParameter(EMAIL);

			LOG.debug(login + ", " + password + ", " + firstname + ","
					+ lastname);

			// Create account
			Account account = new Account();
			account.setId(Math.abs(UUID.randomUUID().hashCode()));
			account.setNumberAccount(Math.abs(UUID.randomUUID().hashCode()));
			account.setBill(0);
			try {
				accountDao.save(account);
			} catch (DAOTechnicalException e1) {
				LOG.error(e1.getMessage());
			}

			// Create passport data
			PassportData passportData = new PassportData();
			passportData.setId(Math.abs(UUID.randomUUID().hashCode()));
			try {
				passportDataDao.save(passportData);
			} catch (DAOTechnicalException e) {
				e.printStackTrace();
			}

			//Create client additional inf
			ClientAdditionalInformation clientAdditionalInformation = new ClientAdditionalInformation();
			clientAdditionalInformation.setId(Math.abs(UUID.randomUUID().hashCode()));
			try {
				additionalInformationDao.save(clientAdditionalInformation);
			} catch (DAOTechnicalException e) {
				e.printStackTrace();
			}

			// Create client inf
			ClientInformation clientInformation = new ClientInformation();
			clientInformation.setId(Math.abs(UUID.randomUUID().hashCode()));
			clientInformation.setPassportData(passportData);
			clientInformation.setClientAdditionalInformation(clientAdditionalInformation);
			try {
				clientInformationDao.save(clientInformation);
			} catch (DAOTechnicalException e) {
				e.printStackTrace();
			}

			// Create new user
			User newUser = new User();
			Date date = new Date(System.currentTimeMillis());
			long d = date.getTime();
			java.sql.Date sqlDate = new java.sql.Date(d);
			LOG.debug("sqlDate = " + sqlDate);

			newUser.setId(Math.abs(UUID.randomUUID().hashCode()));
			newUser.setLogin(login);
			newUser.setPassword(password);
			newUser.setRole(1);
			newUser.setAccount(account);
			newUser.setUserInf(clientInformation);

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
				userDao.save(newUser);
			} catch (DAOTechnicalException e) {
				LOG.debug(e.getMessage());
			}

			LOG.debug("Registration was success");
			request.setAttribute(MESSAGE, MessageManager.getInstance()
					.getProperty(MessageManager.REGISTRATION_WAS_SUCCESS));

			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.HOME_PAGE);

		}
		return page;
	}

	private boolean isExist(String login) {

		User user = new UserDao().findByLogin(login);

		if (user != null) {
			return true;
		}

		return false;
	}

}
