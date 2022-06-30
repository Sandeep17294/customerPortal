package com.aetins.customerportal.web.common;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;

/**
 * Generic Interface for Data Access Objects. To be extended or Implemented
 * 
 * @author avinash
 *
 */
public interface IGenericDao<E, I extends Serializable> {

	/**
	 * 
	 * @param entity
	 * @return
	 * @throws HibernateException
	 */
	void save(E entity) throws HibernateException;

	/**
	 * 
	 * @param entity
	 * @return
	 * @throws HibernateException
	 */
	void update(E entity) throws HibernateException;

	/**
	 * 
	 * @param entity
	 * @return
	 * @throws HibernateException
	 */
	void merge(E entity) throws HibernateException;

	/**
	 * 
	 * @param entity
	 * @throws HibernateException
	 */
	void remove(E entity) throws HibernateException;

	/**
	 * 
	 * @return
	 */
	List<E> findAll();

	/**
	 * 
	 * @param id
	 * @return
	 */
	E findById(I id);

	/**
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	List<E> findAllByProperty(String propertyName, Object value);

	/**
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	List<E> findAllByProperties(String[] propertyName, Object[] value);

	/**
	 * 
	 * @param property name
	 * @return
	 */
	List<E> findAllByOrder(String propertyName);

	List<E> findAllByOrderProperty(String orderPropertyName, String propertyName, Object value);

	List<E> findAllByPropertiesEqual(String[] propertyName, Object[] value);

	List<E> findAllByPropertiesEqualWithOrder(String[] propertyName, Object[] value, String pptyName);

	public List<E> findByColumeName(String[] coloumnName, String[] coloumnData);

	public List<E> findAllCustomQuery(String query, Object[] params);

	public List<E> findByColumnName(String[] columnName, int[] columnData);

	void updateWithQuery(String query, String[] colName, String[] colData);

	public int countNoOFuniqueRecord(String columnNameNotNull);

	public java.util.List<E> findAllWithAscending(String[] propertyName, Object[] value, String pptyName);

	/**
	 * descending
	 * 
	 * @param property name
	 * @return
	 */
	public List<E> findAllByOrderDesc(String propertyName);

	List<E> findWithCustomQuery(String query);

}