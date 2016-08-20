package by.julia.bankservice.bean;

import by.julia.bankservice.dao.UserDao;
import by.julia.bankservice.entity.User;
import by.julia.bankservice.exception.DAOTechnicalException;
import org.apache.log4j.Logger;

import java.util.List;

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
