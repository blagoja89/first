package by.mavs.railwayservice.bean;

import java.util.List;

import org.apache.log4j.Logger;

import by.mavs.railwayservice.dao.UserDao;
import by.mavs.railwayservice.entity.User;
import by.mavs.railwayservice.exception.DAOTechnicalException;

public class UserBean {
	private static Logger LOG = Logger.getLogger(UserBean.class);

	private List<User> userList;

	public List<User> getUserList() {

		UserDao userDao = new UserDao();
		try {
			userList = userDao.findAll();
		} catch (DAOTechnicalException e) {
			LOG.error(e.getMessage());
		}

		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

}
