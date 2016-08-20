package by.mavs.news.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import by.mavs.news.exception.DaoException;
import by.mavs.news.model.News;

@Repository
@Transactional
public final class HibernateDao implements IDao<News> {
	private final static String QUERY_GET_LIST = "getList";
	private final static String QUERY_DELETE_LIST = "deleteList";
	private final static String IDS = "ids";
	
	private SessionFactory sessionFactory;

	@Override
	public boolean save(News obj) throws DaoException {
		Transaction t = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			t = session.beginTransaction();
			session.save(obj);
			t.commit();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			t.rollback();
		}
		return false;
	}

	@Override
	public void update(News obj) throws DaoException {
		Transaction t = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			t = session.beginTransaction();
			session.merge(obj);
			t.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			t.rollback();
		}
	}

	@Override
	public News findById(int id) throws DaoException {
		Session session = sessionFactory.getCurrentSession();
		News news = null;
		Transaction t = null;
		try {
			t = session.beginTransaction();
			news = (News) session.get(News.class, new Integer(id));
			t.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			t.rollback();
		}
		return news;
	}

	@Override
	public List<News> findAll() throws DaoException {
		Session session = sessionFactory.getCurrentSession();
		List<News> newsList = null;
		Transaction t = null;
		try {
			t = session.beginTransaction();
			newsList = session.getNamedQuery(QUERY_GET_LIST).list();
			t.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			t.rollback();
		}
		return newsList;
	}

	@Override
	public void deleteList(int[] ids) throws DaoException {
		Session session = sessionFactory.getCurrentSession();
		List<Integer> idsList = new ArrayList<Integer>();
		for (int index = 0; index < ids.length; index++) {
			idsList.add(ids[index]);
		}
		Transaction t = null;
		try {
			t = session.beginTransaction();
			Query query = session.getNamedQuery(QUERY_DELETE_LIST);
			query.setParameterList(IDS, idsList);
			query.executeUpdate();
			t.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			t.rollback();
		}

	}

	public SessionFactory getSession() {
		return sessionFactory;
	}

	public void setSession(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
