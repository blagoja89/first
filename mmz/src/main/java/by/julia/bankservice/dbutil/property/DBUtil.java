package by.julia.bankservice.dbutil.property;

import by.julia.bankservice.dao.AbstractDao;
import by.julia.bankservice.entity.Entity;
import by.julia.bankservice.exception.DAOTechnicalException;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBUtil {
	private static Logger LOG = Logger.getLogger(DBUtil.class);
	private DBQueryUtil dbQueryUtil = new DBQueryUtil();
	private static final Object NO_PARAMS_OB[] = {};
	private static final Class NO_PARAMS[] = {};
	private static Class RESULT_SET ;
	
	static{
		try {
			RESULT_SET = Class.forName("java.sql.ResultSet");
		} catch (ClassNotFoundException e) {
			LOG.debug(e.getMessage());
		}
	}
/**
 * Get value by get method call
 * @param entity
 * @param fieldName
 * @param fieldType
 * @return
 * @throws NoSuchMethodException
 * @throws SecurityException
 * @throws IllegalAccessException
 * @throws IllegalArgumentException
 * @throws InvocationTargetException
 */
	private Object getValueByGetMethodCall(Entity entity, String fieldName, Class<?> fieldType) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		return getValueByGetMethodCall(entity, fieldName, fieldType, NO_PARAMS);
	}
	
	/**
	 * Get value by get method call
	 * Using Reflection API
	 * @param entity
	 * @param fieldName
	 * @param fieldType
	 * @param params
	 * @return method
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private Object getValueByGetMethodCall(Entity entity, String fieldName, Class<?> fieldType, Class [] params) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Method m = entity.getClass().getMethod(dbQueryUtil.getMethodGetName(fieldName, fieldType), params);
		return m.invoke(entity, NO_PARAMS_OB);
	}
	
	/**
	 * Save/update value in BD
	 * @param entity
	 * @param pstmt
	 * @param isNew -- write new object to DB
	 * @return 
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws SQLException
	 */
	public boolean executeMerge(Entity entity, PreparedStatement pstmt, boolean isNew) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, SQLException {
		DBProperty dbProperty = entity.getDbProperty();
		int i = 0;
		for (DBSimplePropertyItem item : dbProperty.getDbSimplePropertyItems()) {
			pstmt.setObject(++i, getValueByGetMethodCall(entity, item.getAppName(), item.getType()));
		}
		for (DBManyToOnePropertyItem item : dbProperty.getDbManyToOnePropertyItems()) {
			Entity ob = (Entity) getValueByGetMethodCall(entity, item.getAppName(), item.getClassName());
			if(ob != null)
				pstmt.setObject(++i, getValueByGetMethodCall(ob, item.getAppFkName(), item.getClassFkName()));
			else
				pstmt.setObject(++i, null);
		}
		if (!isNew) {
			pstmt = addByIdProperty(entity, pstmt, ++i);
		}
		return pstmt.execute();
	}

	/**
	 * Delete entity from table
	 * @param entity
	 * @param pstmt
	 * @return deleted
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws SQLException
	 */
	public boolean delete(Entity entity, PreparedStatement pstmt)
			throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, SQLException {
		return addByIdProperty(entity, pstmt, 1).execute();
	}

	/**
	 * Find by id
	 * 
	 * @param entity
	 * @param pstmt
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws SQLException
	 */
	public ResultSet findById(Entity entity, PreparedStatement pstmt)
			throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, SQLException {
		return addByIdProperty(entity, pstmt, 1).executeQuery();
	}
	
	/**
	 * Find by property
	 * 
	 * @param pstmt
	 * @param value
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws SQLException
	 */
	public ResultSet findByProperty(PreparedStatement pstmt, Object value)
			throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, SQLException {
		
		pstmt.setObject(1, value);
		return pstmt.executeQuery();
	}
	
/**
 * Add by id property
 * @param entity
 * @param pstmt
 * @param indexProperty
 * @return preparedStatement
 * @throws NoSuchMethodException
 * @throws SecurityException
 * @throws IllegalAccessException
 * @throws IllegalArgumentException
 * @throws InvocationTargetException
 * @throws SQLException
 */
	private PreparedStatement addByIdProperty(Entity entity, PreparedStatement pstmt, int indexProperty)
			throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, SQLException {
		pstmt.setObject(indexProperty, 
				getValueByGetMethodCall(entity, entity.getDbProperty().getDbSimplePropertyItems().get(0).getAppName(),
				entity.getDbProperty().getDbSimplePropertyItems().get(0).getType()));
		return pstmt;
	}

	/**
	 * Find by example
	 * @param entity
	 * @param pstmt
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws SQLException
	 */
	public ResultSet findByExample(Entity entity, PreparedStatement pstmt)
			throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, SQLException {
		
		DBProperty dbProperty = entity.getDbProperty();
		int i = 0;
		for (DBSimplePropertyItem item : dbProperty.getDbSimplePropertyItems()) {
			Object ob = getValueByGetMethodCall(entity, item.getAppName(), item.getType());
			if(ob != null && ((ob instanceof Integer)? (Integer)ob != 0:true))
				pstmt.setObject(++i, ob);
		}
		for (DBManyToOnePropertyItem item : dbProperty.getDbManyToOnePropertyItems()) {
			Entity ob = (Entity) getValueByGetMethodCall(entity, item.getAppName(), item.getClassName());
			if(ob != null){
				Object obT = getValueByGetMethodCall(ob, item.getAppFkName(), item.getClassFkName());
				if(obT != null && ((obT instanceof Integer)? (Integer)obT != 0:true))				
					pstmt.setObject(++i, obT);
			}
		}
		return pstmt.executeQuery();
	}
	
	/**
	 * Get object by ResultSet for AbstractDao.findById()
	 * @param entity
	 * @param rs
	 * @return entity
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws SQLException
	 * @throws InstantiationException
	 */
	public <T extends Entity> T getObjectByResultSet(T entity, ResultSet rs) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException, InstantiationException{
		List<T> list = getListObjectByResultSet(entity, rs);
		return (list != null && !list.isEmpty())?list.get(0):null;
	}
	

	/**
	 * Get list object by ResultSet
	 * @param entityT
	 * @param rs
	 * @return entityList
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws SQLException
	 * @throws InstantiationException
	 */
	public <T extends Entity> List<T> getListObjectByResultSet(T entityT, ResultSet rs) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException, InstantiationException{
		DBProperty dbProperty = entityT.getDbProperty();
		List<T> list = new ArrayList<T>();
		while(rs.next())
		{
			T entity = (T) entityT.getClass().newInstance();
			for (DBSimplePropertyItem item : dbProperty.getDbSimplePropertyItems()) {
				Method mRs = RESULT_SET.getMethod(
						dbQueryUtil.getMethodGetName(item.getType().getSimpleName(), item.getType()), new Class[]{String.class});
				Object obRs = mRs.invoke(rs, item.getDbName());
				Class type = (item.getType().equals(Time.class))?Date.class:item.getType();
				Method m = entity.getClass().getMethod(dbQueryUtil.getMethodSetName(item.getAppName()), new Class[]{type});
				m.invoke(entity, obRs);
			}
			
			for (DBManyToOnePropertyItem item : dbProperty
					.getDbManyToOnePropertyItems()) {
				//obrs = rs.getInt("ID_FK")
				Method mRs = RESULT_SET.getMethod(dbQueryUtil.getMethodGetName(item.getClassFkName().getSimpleName(), 
						item.getClassFkName()), new Class[]{String.class});
				Object obRs = mRs.invoke(rs, item.getDbName());
	
				Object ob= item.getClassName().newInstance();
				
				//ob.setId(obRs)
				Method mFk = ob.getClass().getMethod(dbQueryUtil.getMethodSetName(item.getAppFkName()), new Class[]{item.getClassFkName()});
				mFk.invoke(ob, obRs);
				if(!item.isLazy()){
					ob = getObjectByObject((Entity) ob);
				}
				//entity.setXXX(ob)
				Method m = entity.getClass().getMethod(dbQueryUtil.getMethodSetName(item.getAppName()), new Class[]{item.getClassName()});
				m.invoke(entity, ob);
			}

			for (DBOneToManyPropertyItem item : dbProperty.getDbOneToManyPropertyItems()) {
				List<Entity> result = null;
				if(!item.isLazy()){
					Method mRs = RESULT_SET.getMethod(
							dbQueryUtil.getMethodGetName(item.getClassFkName().getSimpleName(),item.getClassFkName()), new Class[]{String.class});
					Object obRs = mRs.invoke(rs, item.getDbName());
					
					Object ob= dbProperty.getClassName().newInstance();
					Method mFk = ob.getClass().getMethod(
								dbQueryUtil.getMethodSetName(item.getAppFkName()), new Class[]{item.getClassFkName()});
					mFk.invoke(ob, obRs);
					
					Entity obTemp= (Entity)item.getClassName().newInstance();
					Method mTemp = obTemp.getClass().getMethod(
								dbQueryUtil.getMethodSetName(obTemp.getDbProperty().getAppNameByDbName(item.getDbFkName())), new Class[]{dbProperty.getClassName()});
					mTemp.invoke(obTemp, ob);
				
					result = getListObjectByObject(obTemp);
					
					Method m = entity.getClass().getMethod(dbQueryUtil.getMethodSetName(item.getAppName()), new Class[]{List.class});
					m.invoke(entity, result);
				}
			}
			list.add(entity);
		}
		return list;
	}
	
	/**
	 * Get object by object
	 * @param entity
	 * @return entity
	 */
	private Entity getObjectByObject(final Entity entity){
		DBProperty dbProperty = entity.getDbProperty();	
		Entity result = entity;
		AbstractDao<Entity> aDao = new AbstractDao<Entity>() {
				@Override
				public Class getEntityClass() {
					return entity.getClass();
				}
			};
			try {
				result = aDao.findById(entity.getId());
			} catch (DAOTechnicalException e) {
				LOG.debug(e.getMessage());
			}
			return result;
		}
	

	/**
	 * Get list object by object
	 * @param entity
	 * @return List<Entity>
	 */
	private List<Entity> getListObjectByObject(final Entity entity){
		List<Entity> result = null;
		AbstractDao<Entity> aDao = new AbstractDao<Entity>() {
				@Override
				public Class getEntityClass() {
					return entity.getClass();
				}
			};
			try {
				result = aDao.findByExample(entity);
			} catch (DAOTechnicalException e) {
				LOG.debug(e.getMessage());
			}
			return result;
		}
		

}
