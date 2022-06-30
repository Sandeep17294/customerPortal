/*
 * package com.aetins.customerportal.web.dao.impl;
 * 
 * import org.springframework.stereotype.Repository;
 * 
 * import com.aetins.customerportal.web.common.GenericDaoImpl; import
 * com.aetins.customerportal.web.dao.IUserServiceDao; import
 * com.aetins.customerportal.web.entity.CpUserInfo;
 * 
 *//**
	 * Data access object Impl to work with {@link CpUserInfo} entity database
	 * operations
	 * 
	 * @author avinash
	 *
	 *//*
		 * @Repository public class UserServiceDaoImpl extends
		 * GenericDaoImpl<CpUserInfo, Long> implements IUserServiceDao{
		 * 
		 * 
		 * 
		 * 
		 * 
		 * public UserServiceDaoImpl() { super(CpUserInfo.class); }
		 * 
		 * @Override public void delete(CpUserInfo persistentObject) {
		 * 
		 * }
		 * 
		 * @Override public boolean checkAvaliable(String username) {
		 * 
		 * CpUserInfo loadUserByUserName = loadUserByUserName(username);
		 * 
		 * return loadUserByUserName !=null; }
		 * 
		 * @Override public CpUserInfo loadUserByUserName(String username) {
		 * 
		 * return (CpUserInfo)
		 * getEntityManager().createQuery("select count(*) from "+getPersistentClass().
		 * getSimpleName()+" u where u.vuserName = :vuserName").setParameter(
		 * "vuserName", username); }
		 * 
		 * }
		 */