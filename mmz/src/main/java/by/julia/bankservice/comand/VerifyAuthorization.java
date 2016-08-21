package by.julia.bankservice.comand;

import by.julia.bankservice.dao.ClientInformationDao;
import by.julia.bankservice.dao.UserDao;
import by.julia.bankservice.entity.ClientInformation;
import by.julia.bankservice.entity.User;
import by.julia.bankservice.exception.DAOTechnicalException;
import org.apache.log4j.Logger;

public class VerifyAuthorization {

	private static Logger LOG = Logger.getLogger(VerifyAuthorization.class);
	private User user;

	public boolean verifyAuthorization(String login, String password) {
		UserDao daoUser = new UserDao();
		user = daoUser.findByLogin(login);
		if (user.getLogin().equals(login)
				&& user.getPassword().equals(password)) {
			return true;
		} else {
			return false;
		}
	}

	public int getLevel() {
		int level = 0;
		level = user.getRole();
		LOG.debug(level);
		return level;
	}

	public User getUser() {
		LOG.debug(user.getLogin());
		return user;
	}

	public void setUser(User User) {
		this.user = User;
	}

}
