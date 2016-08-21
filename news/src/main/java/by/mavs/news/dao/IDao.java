package by.mavs.news.dao;


import java.util.List;

import by.mavs.news.exception.DaoException;
import by.mavs.news.model.MyEntity;


public interface IDao<T extends MyEntity> {
	boolean save(T obj) throws DaoException;

	void update(T obj) throws DaoException;

	T findById(int id) throws DaoException;

	List<T> findAll() throws DaoException;
	
	void deleteList(int[] ids) throws DaoException;


}
