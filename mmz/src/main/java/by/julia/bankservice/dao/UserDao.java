package by.julia.bankservice.dao;


import by.julia.bankservice.entity.User;
import by.julia.bankservice.exception.DAOTechnicalException;

import java.util.ArrayList;
import java.util.List;

public class UserDao extends AbstractDao<User> {

	@Override
	public Class<User> getEntityClass() {
		return User.class;
	}

	public User findByLogin(String login) {
		List<User> userList = new ArrayList<>();
		try {
			userList = findByProperty("login", login);
		} catch (DAOTechnicalException e) {
			LOG.error(e.getMessage());
		}
		return (userList != null && !userList.isEmpty()) ? userList.get(0): null;
	}
}
