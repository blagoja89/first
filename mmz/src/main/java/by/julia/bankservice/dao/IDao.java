package by.julia.bankservice.dao;


import by.julia.bankservice.entity.Entity;
import by.julia.bankservice.exception.DAOTechnicalException;

import java.util.List;

public interface IDao<T extends Entity> {
	boolean save(T obj) throws DAOTechnicalException;

	void delete(T obj) throws DAOTechnicalException;

	void update(T obj) throws DAOTechnicalException;

	T findById(int id) throws DAOTechnicalException;

	List<T> findAll() throws DAOTechnicalException;

}
