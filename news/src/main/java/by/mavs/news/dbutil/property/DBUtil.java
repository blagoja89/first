package by.mavs.news.dbutil.property;



import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import org.apache.log4j.Logger;

import by.mavs.news.model.MyEntity;


public final class DBUtil {
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
 * @param myEntity
 * @param fieldName
 * @param fieldType
 * @return
 * @throws NoSuchMethodException
 * @throws SecurityException
 * @throws IllegalAccessException
 * @throws IllegalArgumentException
 * @throws InvocationTargetException
 */
	private Object getValueByGetMethodCall(MyEntity myEntity, String fieldName, Class<?> fieldType) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		return getValueByGetMethodCall(myEntity, fieldName, fieldType, NO_PARAMS);
	}
	
	/**
	 * Get value by get method call
	 * @param myEntity
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
	private Object getValueByGetMethodCall(MyEntity myEntity, String fieldName, Class<?> fieldType, Class [] params) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Method m = myEntity.getClass().getMethod(dbQueryUtil.getMethodGetName(fieldName, fieldType), params);
		return m.invoke(myEntity, NO_PARAMS_OB);
	}
	
	/**
	 * Save/update value in BD
	 * @param myEntity
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
	public boolean executeMerge(MyEntity myEntity, PreparedStatement pstmt, boolean isNew) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, SQLException {
		DBProperty dbProperty = myEntity.getDbProperty();
		int i = 0;
		for (DBSimplePropertyItem item : dbProperty.getDbSimplePropertyItems()) {
			pstmt.setObject(++i, getValueByGetMethodCall(myEntity, item.getAppName(), item.getType()));
		}
		if (!isNew) {
			pstmt = addByIdProperty(myEntity, pstmt, ++i);
		}
		return pstmt.execute();
	}

	/**
	 * Delete entity from table
	 * @param myEntity
	 * @param pstmt
	 * @return deleted
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws SQLException
	 */
	public boolean delete(MyEntity myEntity, PreparedStatement pstmt)
			throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, SQLException {
		return addByIdProperty(myEntity, pstmt, 1).execute();
	}
	
	
	public boolean deleteList(List<MyEntity> entityList, PreparedStatement pstmt)
			throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, SQLException {
		
		boolean flag = false;
		
		for(MyEntity myEntity: entityList){
			flag = addByIdProperty(myEntity, pstmt, 1).execute();
			if(flag == false){
				return flag;
			}
		}
		return flag;
	}
	
	/**
	 * Find by id
	 * 
	 * @param myEntity
	 * @param pstmt
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws SQLException
	 */
	public ResultSet findById(MyEntity myEntity, PreparedStatement pstmt)
			throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, SQLException {
		return addByIdProperty(myEntity, pstmt, 1).executeQuery();
	}
	
	
/**
 * Add by id property
 * @param myEntity
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
	private PreparedStatement addByIdProperty(MyEntity myEntity, PreparedStatement pstmt, 
			int indexProperty) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, SQLException {
		
		pstmt.setObject(indexProperty, getValueByGetMethodCall(myEntity, 
				myEntity.getDbProperty().getDbSimplePropertyItems().get(0).getAppName(),
				myEntity.getDbProperty().getDbSimplePropertyItems().get(0).getType()));
		return pstmt;
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
	public <T extends MyEntity> T getObjectByResultSet(T entity, ResultSet rs) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException, InstantiationException{
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
	public <T extends MyEntity> List<T> getListObjectByResultSet(T entityT, ResultSet rs) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException, InstantiationException{
		DBProperty dbProperty = entityT.getDbProperty();
		List<T> list = new ArrayList<>();
		while(rs.next())
		{
			T entity = (T) entityT.getClass().newInstance();
			for (DBSimplePropertyItem item : dbProperty.getDbSimplePropertyItems()) {
				Method mRs = RESULT_SET.getMethod(
						dbQueryUtil.getMethodGetName(item.getType().getSimpleName(), item.getType()), 
							new Class[]{String.class});
				Object obRs = mRs.invoke(rs, item.getDbName());
				Class type = (item.getType().equals(Time.class))?Date.class:item.getType();
				Method m = entity.getClass().getMethod(dbQueryUtil.getMethodSetName(item.getAppName()), new Class[]{type});
				m.invoke(entity, obRs);
			}
			
			list.add(entity);
		}
		return list;
	}
	

}
