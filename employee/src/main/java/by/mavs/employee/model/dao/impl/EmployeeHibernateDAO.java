package by.mavs.employee.model.dao.impl;


import java.util.List;

import javax.transaction.Transactional;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;

import by.mavs.employee.model.beans.Employee;
import by.mavs.employee.model.dao.IEmployeeDAO;

@Transactional
public final class EmployeeHibernateDAO implements IEmployeeDAO {

	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<Employee> getEmployeeList(int firstResult, int resultsPerPage) {
		List<Employee> list = null;
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(resultsPerPage);
		list = criteria.list();
		session.getTransaction().commit();
		return list;
	}
	
	public int getRowsNumber() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		int rowsNumber = ((Long) sessionFactory.getCurrentSession().createCriteria(Employee.class).setProjection(Projections.rowCount()).uniqueResult()).intValue();
		session.getTransaction().commit();
		return rowsNumber;
		
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
