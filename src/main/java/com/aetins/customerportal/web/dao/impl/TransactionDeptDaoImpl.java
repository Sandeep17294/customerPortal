package com.aetins.customerportal.web.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.aetins.customerportal.web.common.GenericDaoImpl;
import com.aetins.customerportal.web.dao.ITransactionDeptDao;
import com.aetins.customerportal.web.entity.CPTransactionDept;
import com.aetins.customerportal.web.entity.Role;

/**
 * @author avinash
 *
 */
@Repository
public class TransactionDeptDaoImpl extends GenericDaoImpl<CPTransactionDept, Long> implements ITransactionDeptDao{

   private Logger _LOGGER = LoggerFactory.getLogger(TransactionDeptDaoImpl.class);
	
	@Resource
	private SessionFactory session;
	
	@Override
	protected Session getSession() {
		return session.getCurrentSession();
	}
	@Override
	protected Class<CPTransactionDept> getEntityClass() {
		return CPTransactionDept.class;
	}
	
	@Override
	public List<CPTransactionDept> findByDeptName(String deptName) {
		
		String[] propertyValues = {"dept_name"};
		Object[] value = {deptName};
		
		List<CPTransactionDept> findAllByPropertiesEqual = findAllByPropertiesEqual(propertyValues, value);
		return findAllByPropertiesEqual;
	}

	@Override
	public List<CPTransactionDept> findByDeptCode(String deptCode) {
		String[] propertyValues = {"dept_code"};
		Object[] value = {deptCode};
		
		List<CPTransactionDept> findAllByPropertiesEqual = findAllByPropertiesEqual(propertyValues, value);
		return findAllByPropertiesEqual;
	}

	@Override
	public CPTransactionDept findByTransaction(String transaction) {
		/*String[] propertyValues = {"transaction_name"};
		Object[] value = {transaction};
		
		List<CPTransactionDept> findAllByPropertiesEqual = findAllByPropertiesEqual(propertyValues, value);
		return findAllByPropertiesEqual.get(0);*/
		Criteria criteria = getSession().createCriteria(CPTransactionDept.class);
		return (CPTransactionDept) criteria.add(Restrictions.eq("transactionName", transaction).ignoreCase()).uniqueResult();
	}
	@Override
	public void createTransactionDept(CPTransactionDept transactionDept) {

		save(transactionDept);
	}

	

}
