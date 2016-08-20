package by.mavs.news.dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.mavs.news.dbutil.ConnectionManager;
import by.mavs.news.dbutil.ConnectionPool;
import by.mavs.news.dbutil.property.DBQueryUtil;
import by.mavs.news.dbutil.property.DBUtil;
import by.mavs.news.exception.DaoException;
import by.mavs.news.exception.PoolException;
import by.mavs.news.model.MyEntity;

public abstract class AbstractDao<T extends MyEntity> implements IDao<T> {

	protected static final Logger LOG = Logger.getLogger(AbstractDao.class);
	protected DBUtil dbUtil = new DBUtil();
	protected DBQueryUtil dbQueryUtil = new DBQueryUtil();
	protected ConnectionPool pool;

	@Override
	public boolean save(T obj) throws DaoException {

		String query = dbQueryUtil.getInsertQuery(obj.getDbProperty());
		boolean flag = false;

		try (ConnectionManager cm = pool.getConnection();
				PreparedStatement prstmt = cm.prepareStatement(query,
						PreparedStatement.RETURN_GENERATED_KEYS);) {
			try {
				LOG.info("sql insert - " + prstmt);
				flag = dbUtil.executeMerge(obj, prstmt, true);
			} catch (NoSuchMethodException | SecurityException
					| IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				LOG.debug(e.getMessage());
				cm.setPool(pool);
			}
			cm.setPool(pool);
			LOG.info("save " + obj.getClass().getSimpleName() + " instance");
		} catch (SQLException | PoolException e) {
			LOG.error("catch exception in save"
					+ " throwing DAOTechnicalException", e);
			throw new DaoException(e);
		}
		return flag;
	}

	@Override
	public void update(T obj) throws DaoException {

		String query = dbQueryUtil.getUpdateQuery(obj.getDbProperty());

		try (ConnectionManager cm = pool.getConnection();
				PreparedStatement prstmt = cm.prepareStatement(query);) {
			LOG.info("sql update - " + prstmt);
			try {
				dbUtil.executeMerge(obj, prstmt, false);
				cm.setPool(pool);
			} catch (NoSuchMethodException | SecurityException
					| IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				LOG.debug(e.getMessage());
			}
			
		} catch (SQLException | PoolException e) {
			LOG.error("catch exception in " + getEntityClass().getSimpleName()
					+ " throwing DAOTechnicalException", e);
			throw new DaoException(e);
		}
	}


	@Override
	public T findById(int id) throws DaoException {
		T entity = null;
		try {
			entity = getEntityClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e1) {
			LOG.debug(e1.getMessage());
		}
		entity.setId(id);
		String query = dbQueryUtil.getFindByIdQuery(entity.getDbProperty());

		try (ConnectionManager cm = pool.getConnection();
				PreparedStatement prstmt = cm.prepareStatement(query);) {
			LOG.info("sql find By id - " + prstmt);
			try {
				ResultSet resultSet = dbUtil.findById(entity, prstmt);
				entity = dbUtil.getObjectByResultSet(entity, resultSet);
			} catch (NoSuchMethodException | SecurityException
					| IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | InstantiationException e) {
				LOG.debug(e.getMessage());
			}
			cm.setPool(pool);
		} catch (SQLException | PoolException e) {
			LOG.error("catch exception in " + getEntityClass().getSimpleName()
					+ " throwing DAOTechnicalException", e);
			throw new DaoException(e);
		}
		return entity;
	}

	@Override
	public List<T> findAll() throws DaoException {
		List<T> list = new ArrayList<>();
		T entity = null;
		try {
			entity = getEntityClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e1) {
			e1.printStackTrace();
		}
		String query = dbQueryUtil.getFindAllQuery(entity.getDbProperty());

		try (ConnectionManager cm = pool.getConnection();
				PreparedStatement prstmt = cm.prepareStatement(query);) {
			LOG.info("sql query find all - " + prstmt);
			try {
				ResultSet resultSet = prstmt.executeQuery();
				list = dbUtil.getListObjectByResultSet(entity, resultSet);
			} catch (NoSuchMethodException | SecurityException
					| IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | InstantiationException e) {
				LOG.error(e.getMessage());
			}
			cm.setPool(pool);
		} catch (SQLException | PoolException e) {
			LOG.error("catch exception in " + getEntityClass().getSimpleName()
					+ " throwing DAOTechnicalException", e);
			throw new DaoException(e);
		}
		return list;
	}


	
	@Override
	public void deleteList(int[] ids) throws DaoException {
		List<MyEntity> entityList = new ArrayList<>();

		T entity = null;
		try {
			entity = getEntityClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e1) {
			e1.printStackTrace();
		}

		for (Integer id : ids) {
			T entityId = null;
			try {
				entityId = getEntityClass().newInstance();
				entityId.setId(id);
			} catch (InstantiationException | IllegalAccessException e1) {
				e1.printStackTrace();
			}
			entityList.add(entityId);
		}

		String query = dbQueryUtil.getDeleteListQuery(entity.getDbProperty(),
				ids.length);

		try (ConnectionManager cm = pool.getConnection();
				PreparedStatement prstmt = cm.prepareStatement(query);) {
			LOG.info("sql delete list - " + prstmt);
			try {
				dbUtil.deleteList(entityList, prstmt);
			} catch (NoSuchMethodException | SecurityException
					| IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				LOG.debug(e.getMessage());
			}
			cm.setPool(pool);
		} catch (SQLException | PoolException e) {
			LOG.error("catch exception in " + getEntityClass().getSimpleName()
					+ " throwing DAOTechnicalException", e);
			throw new DaoException(e);
		}

	}
	
	
	public abstract ConnectionPool getPool();

	public abstract void setPool(ConnectionPool pool);

	public abstract Class<T> getEntityClass();

}
