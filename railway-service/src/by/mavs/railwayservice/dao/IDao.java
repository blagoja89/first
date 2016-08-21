package by.mavs.railwayservice.dao;

import java.util.List;

import by.mavs.railwayservice.entity.Entity;
import by.mavs.railwayservice.exception.DAOTechnicalException;

public interface IDao<T extends Entity> {
	boolean save(T obj) throws DAOTechnicalException;

	void delete(T obj) throws DAOTechnicalException;

	void update(T obj) throws DAOTechnicalException;

	T findById(int id) throws DAOTechnicalException;

	List<T> findAll() throws DAOTechnicalException;

}
