package by.mavs.news.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import by.mavs.news.exception.DaoException;
import by.mavs.news.model.News;

public final class JpaNewsDao implements IDao<News> {
	private final static String QUERY_GET_LIST = "getList";
	private final static String QUERY_DELETE_LIST = "deleteList";
	private final static String IDS = "ids";
	
	
	private EntityManagerFactory entityManagerFactory;

	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public void setEntityManagerFactory(
			EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	@Override
	public boolean save(News obj) throws DaoException {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			entityManager.persist(obj);
			entityManager.flush();
			transaction.commit();
		} catch (PersistenceException e) {
			transaction.rollback();
		} finally {
			entityManager.close();
		}
		return true;
	}

	@Override
	public void update(News obj) throws DaoException {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			entityManager.merge(obj);
			entityManager.flush();
			transaction.commit();
		} catch (PersistenceException e) {
			transaction.rollback();
		} finally {
			entityManager.close();
		}
	}

	@Override
	public News findById(int id) throws DaoException {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		return entityManager.find(News.class, id);
	}

	@Override
	public List<News> findAll() throws DaoException {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		List<News> newsList = entityManager.createNamedQuery(QUERY_GET_LIST,
				News.class).getResultList();
		return newsList;
	}

	@Override
	public void deleteList(int[] ids) throws DaoException {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		List<Integer> idsList = new ArrayList<Integer>();
		for (int index = 0; index < ids.length; index++) {
			idsList.add(ids[index]);
		}
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			
			Query query = entityManager.createNamedQuery(QUERY_DELETE_LIST);
			query.setParameter(IDS, idsList);
			query.executeUpdate();
			
			entityManager.flush();
			transaction.commit();
		} catch (PersistenceException e) {
			transaction.rollback();
		} finally {
			entityManager.close();
		}

	}

}
