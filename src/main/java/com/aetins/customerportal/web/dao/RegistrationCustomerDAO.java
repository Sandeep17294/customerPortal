package com.aetins.customerportal.web.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.aetins.customerportal.web.common.IGenericDao;
import com.aetins.customerportal.web.entity.CpUserInfo;

/**
 * @author satendra
 *
 * @param <T>
 * @param <ID>
 */
public interface RegistrationCustomerDAO  extends IGenericDao<CpUserInfo, Long>{
    
	  public void save(CpUserInfo cpUserInfo) throws HibernateException;

	    public List<CpUserInfo> findAll(CpUserInfo clazz);

	    public void update(CpUserInfo entity) throws HibernateException;

	    public List<CpUserInfo> findByColumn(CpUserInfo info, String coloumn);

	    public CpUserInfo findById(CpUserInfo info, Long id);
    
   /* public void saveRegistrationQuestions(List<CpResetSecurityAnswer> securityQuestionsDAO) throws HibernateException;*/
}
