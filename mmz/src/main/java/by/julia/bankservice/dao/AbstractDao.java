package by.julia.bankservice.dao;

import by.julia.bankservice.dbutil.ConnectionManager;
import by.julia.bankservice.dbutil.ConnectionPool;
import by.julia.bankservice.dbutil.property.DBQueryUtil;
import by.julia.bankservice.dbutil.property.DBUtil;
import by.julia.bankservice.entity.Entity;
import by.julia.bankservice.exception.DAOTechnicalException;
import by.julia.bankservice.exception.PoolTechnicalException;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<T extends Entity> implements IDao<T> {

	protected static final Logger LOG = Logger.getLogger(AbstractDao.class);
	protected DBUtil dbUtil = new DBUtil();
	protected DBQueryUtil dbQueryUtil = new DBQueryUtil();

	@Override
	public boolean save(T obj) throws DAOTechnicalException {
		
		String query = dbQueryUtil.getInsertQuery(obj.getDbProperty());
		boolean flag = false;

		try (ConnectionManager cm = ConnectionPool.getInstanceConnectionPool()
				.getConnection();
				PreparedStatement prstmt = cm.prepareStatement(query,
						PreparedStatement.RETURN_GENERATED_KEYS);) {
			try {
				LOG.info("sql insert - " + prstmt);
				flag = dbUtil.executeMerge(obj, prstmt, true);
			} catch (NoSuchMethodException | SecurityException
					| IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				LOG.debug(e.getMessage());
			}
			LOG.info("save "+ obj.getClass().getSimpleName() + " instance");
		} catch (SQLException | PoolTechnicalException e) {
			LOG.error("catch exception in save"
					+ " throwing DAOTechnicalException", e);
			throw new DAOTechnicalException(e);
		}
		return flag;
	}

	@Override
	public void update(T obj) throws DAOTechnicalException {

		String query = dbQueryUtil.getUpdateQuery(obj.getDbProperty());

		try (ConnectionManager cm = ConnectionPool.getInstanceConnectionPool()
				.getConnection();
				PreparedStatement prstmt = cm.prepareStatement(query);) {
			LOG.info("sql update - " + prstmt);
			try {
				dbUtil.executeMerge(obj, prstmt, false);
			} catch (NoSuchMethodException | SecurityException
					| IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				LOG.debug(e.getMessage());
			}
		} catch (SQLException | PoolTechnicalException e) {
			LOG.error("catch exception in " + getEntityClass().getSimpleName() 
					+ " throwing DAOTechnicalException", e);
			throw new DAOTechnicalException(e);
		}
	}

	@Override
	public void delete(T obj) throws DAOTechnicalException {

		String query = dbQueryUtil.getDeleteQuery(obj.getDbProperty());

		try (ConnectionManager cm = ConnectionPool.getInstanceConnectionPool()
				.getConnection();
				PreparedStatement prstmt = cm.prepareStatement(query);) {
			LOG.info("sql delete - " + prstmt);
			try {
				dbUtil.delete(obj, prstmt);
			} catch (NoSuchMethodException | SecurityException
					| IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				LOG.debug(e.getMessage());
				}
		} catch (SQLException | PoolTechnicalException e) {
			LOG.error("catch exception in " + getEntityClass().getSimpleName()
					+ " throwing DAOTechnicalException", e);
			throw new DAOTechnicalException(e);
		}
	}

	@Override
	public T findById(int id) throws DAOTechnicalException {
		T entity = null;
		try {
			entity = getEntityClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e1) {
			LOG.debug(e1.getMessage());
		}
		entity.setId(id);
		String query = dbQueryUtil.getFindByIdQuery(entity.getDbProperty());

		try (ConnectionManager cm = ConnectionPool.getInstanceConnectionPool()
				.getConnection();
				PreparedStatement prstmt = cm.prepareStatement(query);) {
			LOG.info("sql find By id - " + prstmt);
			try {
				ResultSet resultSet = dbUtil.findById(entity, prstmt);
				entity =  dbUtil.getObjectByResultSet(entity, resultSet);
			} catch (NoSuchMethodException | SecurityException
					| IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | InstantiationException e) {
				LOG.debug(e.getMessage());
			}
		} catch (SQLException | PoolTechnicalException e) {
			LOG.error("catch exception in " + getEntityClass().getSimpleName() 
					+ " throwing DAOTechnicalException", e);
			throw new DAOTechnicalException(e);
		}
		return entity;
	}

	@Override
	public List<T> findAll() throws DAOTechnicalException {
		List<T> list = new ArrayList<>();
		T entity = null;
		try {
			entity = getEntityClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e1) {
			e1.printStackTrace();
		}
		String query =dbQueryUtil.getFindAllQuery(entity.getDbProperty());

		try (ConnectionManager cm = ConnectionPool.getInstanceConnectionPool()
				.getConnection();
				PreparedStatement prstmt = cm.prepareStatement(query);) {
			LOG.info("sql query find all - " + prstmt);
			try {
				ResultSet resultSet = prstmt.executeQuery();
				list =  dbUtil.getListObjectByResultSet(entity, resultSet);
			} catch (NoSuchMethodException | SecurityException
					| IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | InstantiationException e) {
				LOG.debug(e.getMessage());
			}
		} catch (SQLException | PoolTechnicalException e) {
			LOG.error("catch exception in " + getEntityClass().getSimpleName()
					+ " throwing DAOTechnicalException", e);
			throw new DAOTechnicalException(e);
		}
		return list;
	}

	public List<T> findByProperty(String name, Object value) throws DAOTechnicalException {
		List<T> list = new ArrayList<>();
		T entity = null;
		try {
			entity = getEntityClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e1) {
			LOG.debug(e1.getMessage());
		}
		String query = dbQueryUtil.getFindByPropertyQuery(entity.getDbProperty(), name);

		try (ConnectionManager cm = ConnectionPool.getInstanceConnectionPool()
				.getConnection();
				PreparedStatement prstmt = cm.prepareStatement(query);) {
			LOG.info("sql query by prop - " + prstmt);
			try {
				ResultSet resultSet = dbUtil.findByProperty(prstmt, value);
				list =  dbUtil.getListObjectByResultSet(entity, resultSet);
			} catch (NoSuchMethodException | SecurityException
					| IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | InstantiationException e) {
				LOG.debug(e.getMessage());
			}
		} catch (SQLException | PoolTechnicalException e) {
			LOG.error("catch exception in " + getEntityClass().getSimpleName() 
					+ " throwing DAOTechnicalException", e);
			throw new DAOTechnicalException(e);
		}
		return list;
	}
	

	public List<T> findByExample(T entity) throws DAOTechnicalException {
		List<T> list = new ArrayList<>();
		String query = "";
		try {
			query = dbQueryUtil.getFindByExampleQuery(entity);
		} catch (NoSuchMethodException | SecurityException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e1) {
			LOG.debug(e1.getMessage());
			}

		try (ConnectionManager cm = ConnectionPool.getInstanceConnectionPool()
				.getConnection();
				PreparedStatement prstmt = cm.prepareStatement(query);) {
			LOG.info("sql query find by example - " + prstmt);
			try {
				LOG.info("sql query - " + prstmt);
				ResultSet resultSet = dbUtil.findByExample(entity, prstmt);
				list =  dbUtil.getListObjectByResultSet(entity, resultSet);
			} catch (NoSuchMethodException | SecurityException
					| IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | InstantiationException e) {
				LOG.debug(e.getMessage());
			}
		} catch (SQLException | PoolTechnicalException e) {
			LOG.error("catch exception in " + getEntityClass().getSimpleName() 
					+ " throwing DAOTechnicalException", e);
			throw new DAOTechnicalException(e);
		}
		return list;
	}
	
	public abstract Class<T> getEntityClass();

}
