package com.aetins.customerportal.web.common;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;






/**
 * Provides generic common implementation for {@link IGenericDao} interface persistence methods
 * Extend this abstract class to implement DAO.
 * @author avinash
 */
@Transactional
public abstract class GenericDaoImpl<T, ID extends Serializable> implements IGenericDao<T, ID>{

	private Logger logger = LoggerFactory.getLogger(getEntityClass());
			
	@Resource
	private SessionFactory sessionfactory;
	
	protected Session getSession() {

		return sessionfactory.getCurrentSession();
	}
	
	//protected abstract Session getSession();

	/**
	 * 
	 * @return
	 */
	protected abstract Class<T> getEntityClass();

	/**
	 * 
	 * @return
	 */
	protected DetachedCriteria createDetachedCriteria() {
		return DetachedCriteria.forClass(getEntityClass());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.customer.portal.GenericDAO#save(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false)
	public void save(T newInstance) {
		getSession().save(newInstance);
	}
	
	public void update(T transientObject) {
		getSession().update(transientObject);
	}

	public void saveOrUpdate(T transientObject) {
		getSession().saveOrUpdate(transientObject);
	}

	protected Criteria createCriteria() {
		return getSession().createCriteria(getEntityClass().getName());
	}
	
	protected Criteria createCriteria(Class<?> entityName) {
		return getSession().createCriteria(entityName);
	}

	@Override
	public void merge(T entity) throws HibernateException {
		getSession().merge(entity);
	}

	
	public void remove(T entity) throws HibernateException {
		
		getSession().delete(entity);
	}

	
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		
		return createCriteria().list();
	}

	
	public List<T> findByColumn(String column) {

		logger.info("List Start");
		Criteria crt = createCriteria();
		crt.setProjection(Projections.property(column));

		return crt.list();
	}

	@Override
	public List<T> findByColumnName(String[] columnName, int[] columnData) {
		
		logger.info("List Start");
		Criteria crt = createCriteria();
		for (int i = 0; i < columnName.length; i++) {
			crt.add(Restrictions.eq(columnName[i], columnData[i]));
		}

		return crt.list();
	

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.customer.portal.GenericDAO#findById(java.io.Serializable)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T findById(ID id) {
		return (T) getSession().get(getEntityClass(), id);
	}

	public void deleteById(ID id) {
		T entity  =  findById(id);
		getSession().get(entity.getClass(), id);

	}

	public void deleteByProperty(String propertyName, ID id) {

		logger.info("deleteByProperty Start");
		List<T> entity = findAllByProperty(propertyName, id);
		for (T t : entity)
			getSession().delete(t);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.customer.portal.GenericDAO#findAllByProperty(java.lang.String,
	 * java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAllByProperty(String propertyName, Object value) {

		logger.info("findAllByProperty Start");
		Criteria criteria = createCriteria();
		if (value instanceof Date) {
			Date date = (Date) value;
			criteria.add(Restrictions.eq(propertyName, new java.sql.Timestamp(date.getTime())));
		} else
			criteria.add(Restrictions.like(propertyName, /* Tag#01 Starts "%"+ */ value/* +"%" Tag#01 Ends */));

		return criteria.list();

	}

	@SuppressWarnings("unchecked")
	@Override
	public java.util.List<T> findAllByProperties(String[] propertyName, Object[] value) {

		logger.info("findAllByProperties Start");
		Criteria criteria = createCriteria();
		Disjunction disjunction = Restrictions.disjunction();
		for (int index = 0; index < propertyName.length; index++) {
			if (value[index] != null) {
				if (value[index] instanceof Date) {
					Date date = (Date) value[index];
					disjunction.add(Restrictions.eq(propertyName[index], new java.sql.Timestamp(date.getTime())));
				} else if (value[index] instanceof Integer) {
					disjunction.add(Restrictions.eq(propertyName[index], Integer.parseInt(value[index].toString())));
				} else
					disjunction.add(Restrictions.like(propertyName[index], value[index]));
			}

		}
		criteria.add(disjunction);

		return criteria.list();

	}

	@SuppressWarnings("unchecked")
	@Override
	public java.util.List<T> findAllByPropertiesEqual(String[] propertyName, Object[] value) {
		
			logger.info("findAllByPropertiesEqual Start");
			Criteria criteria = createCriteria();
			
			for (int index = 0; index < propertyName.length; index++) {
				if (value[index] != null) {
					if (value[index] instanceof Date) {
						Date date = (Date) value[index];
						criteria.add(Restrictions.eq(propertyName[index],new java.sql.Timestamp(date.getTime())));
					} else if (value[index] instanceof Integer) {
						criteria.add(Restrictions.eq(propertyName[index],Integer.parseInt(value[index].toString())));
					} else {
						criteria.add(Restrictions.eq(propertyName[index],value[index]));
					}
				}
				// criteria.addOrder(Order.asc(propertyName));
			}

			return criteria.list();
		

	}
	
	
	public int fetchByQuery(String[] propertyName, Object[] value) {

		int columnCount = 0;

		logger.info("findAllByPropertiesEqual Start");
		Criteria criteria = createCriteria();

		for (int index = 0; index < propertyName.length; index++) {
			if (value[index] != null) {
				if (value[index] instanceof Date) {
					Date date = (Date) value[index];
					criteria.add(Restrictions.eq(propertyName[index], new java.sql.Timestamp(date.getTime())));
				} else if (value[index] instanceof Integer) {
					criteria.add(Restrictions.eq(propertyName[index], Integer.parseInt(value[index].toString())));
				} else {
					criteria.add(Restrictions.eq(propertyName[index], value[index]));
				}
			}
		}

		columnCount = criteria.hashCode();
		return columnCount;
	}

	@SuppressWarnings("unchecked")
	@Override
	public java.util.List<T> findAllByPropertiesEqualWithOrder(String[] propertyName, Object[] value, String pptyName) {

		logger.info("findAllByPropertiesEqual Start");
		Criteria criteria = createCriteria();
		for (int index = 0; index < propertyName.length; index++) {

			criteria.add(Restrictions.eq(propertyName[index], value[index]));
			criteria.addOrder(Order.desc(pptyName));
		}

		return criteria.list();

	}
	
	@Override
	public java.util.List<T> findAllWithAscending(String[] propertyName, Object[] value, String pptyName) {

		logger.info("findAllByPropertiesEqual Start");
		Criteria criteria = createCriteria();
		for (int index = 0; index < propertyName.length; index++) {

			criteria.add(Restrictions.eq(propertyName[index], value[index]));
			criteria.addOrder(Order.asc(pptyName));
		}

		return criteria.list();

	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.customer.portal.GenericDAO#findAllByCriteria(org.hibernate.criterion.
	 * DetachedCriteria)
	 */
	/*
	 * @Override public List<T> findAllByCriteria(DetachedCriteria criteria) { //
	 * return getHibernateTemplate().findByCriteria(criteria); }
	 */
	
	@Override
	public void updateWithQuery(String query,String[] colName,String[] colData){
		//"update Persons set recievedDate=:recievedDate where Id=:Id"
		
		Query q = getSession().createQuery(query);
		for (int i = 0; i < colName.length; i++) {
			q.setString(colName[i], colData[i]);
		}
		q.executeUpdate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.customer.portal.GenericDAO#findWithCustomQuery(java.lang.String)
	 */
	/*
	 * @Override public List<T> findWithCustomQuery(String q) { // return
	 * getHibernateTemplate().find(q); }
	 */

	

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.customer.portal.GenericDAO#findAllByOrder(java.lang.String)
	 */
	@Override
	public List<T> findAllByOrder(String propertyName) {

		Criteria criteria = createCriteria();

		criteria.addOrder(Order.asc(propertyName));

		return criteria.list();

	}
	
	
	 /*
	  * (non-Javadoc)
	  * 
	  * @see com.customer.portal.GenericDAO#findAllByOrder(java.lang.String)
	  */
	@Override
	public List<T> findAllByOrderDesc(String propertyName) {

		logger.info("findAllByOrder Start");
		Criteria criteria = createCriteria();

		criteria.addOrder(Order.desc(propertyName));

		return criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.customer.portal.GenericDAO#findAllByOrder(java.lang.String)
	 */
	@Override
	public List<T> findAllByOrderProperty(String orderPropertyName, String propertyName, Object value) {

		logger.info("findAllByOrderProperty Start");
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.like(propertyName, value));
		criteria.addOrder(Order.asc(orderPropertyName));
		return criteria.list();
	}

	@Override
	public List<T> findByColumeName(String[] coloumnName, String[] coloumnData) {
		
			logger.info("List Start");
			// list =
			// session.createCriteria(getEntityClass()).(Projections.property("firstname"));
			Criteria crt = createCriteria();
			for (int i = 0; i < coloumnName.length; i++) {
				crt.add(Restrictions.eq(coloumnName[i], coloumnData[i]));
			}
		
		return crt.list();
	}

		@Override
	@SuppressWarnings("unchecked")
	public List<T> findAllCustomQuery(String query, Object[] params) {

	
		
			Query q = getSession().createQuery(query);
			for (int index = 0; index < params.length; index++) {
				if (params[index] instanceof Date) {
					Date date = (Date) params[index];
					q.setParameter(index, new java.sql.Timestamp(date.getTime()));
				} else if (params[index] instanceof Integer) {
					q.setParameter(index, Integer.parseInt(params[index].toString()));
				} else
					q.setParameter(index, params[index]);
			}

	
		return q.list();
	}

		/*
		 * (non-Javadoc)
		 * 
		 * Update specific columns only...
		 * @author viswakarthick
		 */
	public void merge(T entity, ID id) throws HibernateException {
		//
		// getHibernateTemplate().merge(entity);
		// session.saveOrUpdate(entity);
		
			T en = (T) getSession().get(getEntityClass(), id);
			logger.info("merge Start");
			getSession().merge(entity);
			
	}

		
	public void updateByColumnName(String query) {

		int updatedCount = 0;

		logger.info("updateByColumnName() Start");
		Query q = getSession().createQuery(query);
		updatedCount = q.executeUpdate();

	}
		
		
	public void updateByColumnNameWithDate(String query) {

		int updatedCount = 0;

		logger.info("updateByColumnName() Start");
		Query q = getSession().createQuery(query);
		updatedCount = q.executeUpdate();

	}
		
		
		
	@Override
	public int countNoOFuniqueRecord(String columnNameNotNull) throws HibernateException {
		Long data = 0l;

		logger.info("Update start");
		Criteria crit = createCriteria();
		crit.add(Restrictions.isNotNull(columnNameNotNull));
		crit.setProjection(Projections.rowCount());
		data = (Long) crit.uniqueResult();

		return data.intValue();
	}
		
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findWithCustomQuery(String query) {
		logger.info("Custom query");
		List<T> list = getSession().createSQLQuery(query).list();
		return list;
	}
	
		
	/*
	 * protected void openSession() throws HibernateException { session =
	 * HibernateUtil.getTrasactionSessionFactory().openSession(); transaction =
	 * session.getTransaction();
	 * 
	 * }
	 * 
	 * 
	 * protected void closeSession() throws HibernateException { session.close(); }
	 * 
	 * protected void beginTransaction() throws HibernateException {
	 * transaction.begin(); }
	 * 
	 * protected void rollBackTransaction() throws HibernateException {
	 * transaction.rollback(); }
	 * 
	 * protected void commitTransaction() throws HibernateException {
	 * transaction.commit(); }
	 */
	
	
}
