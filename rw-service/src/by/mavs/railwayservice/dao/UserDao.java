package by.mavs.railwayservice.dao;

import java.util.ArrayList;
import java.util.List;

import by.mavs.railwayservice.entity.User;
import by.mavs.railwayservice.exception.DAOTechnicalException;

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
			e.printStackTrace();
		}
		return (userList != null && !userList.isEmpty()) ? userList.get(0)
				: null;
	}
}
