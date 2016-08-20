package by.mavs.railwayservice.command;

import org.apache.log4j.Logger;

import by.mavs.railwayservice.dao.UserDao;
import by.mavs.railwayservice.entity.User;

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
