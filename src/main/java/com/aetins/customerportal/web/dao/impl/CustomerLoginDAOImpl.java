package com.aetins.customerportal.web.dao.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
//import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.aetins.customerportal.web.common.GenericDaoImpl;
import com.aetins.customerportal.web.dao.CustomerLoginDAO;
import com.aetins.customerportal.web.entity.CpUserInfo;

@Repository
public class CustomerLoginDAOImpl extends GenericDaoImpl<CpUserInfo, Serializable> implements CustomerLoginDAO {

	
	private static final Logger logger = Logger.getLogger(CustomerLoginDAOImpl.class);
	
	
	@Resource
	SessionFactory sessionFactory;
	
	

	@Override
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	@Override
	protected Class<CpUserInfo> getEntityClass() {
		
		return CpUserInfo.class;
	}
	
	
	@Override
	public boolean saveUserDetails(CpUserInfo cpUserLogin) {
		boolean status = Boolean.TRUE;
		logger.info("saveUserDetails Start");
		try {
			save(cpUserLogin);
		} catch (Exception e) {
			status = Boolean.FALSE;
			logger.error("Error occured " + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	

	// added by Harmain login verfication
	
	public List<CpUserInfo> fetchUserDetails(CpUserInfo verifyUserLogin) {
		List<CpUserInfo> cpUserInfoList = new ArrayList<CpUserInfo>();
		logger.info("verifyUserDetails Start");

		try {
			String coluName[] = { "vuserName" };
			String coluData[] = { verifyUserLogin.getVuserName() };
			cpUserInfoList = findByColumeName(coluName, coluData);
		} catch (Exception e) {
			logger.error("Error occured " + e.getMessage());

		

		try{
			cpUserInfoList=findByColumn(verifyUserLogin.getVuserName());
			}catch (Exception ex) {
			logger.error("Error occured "+e.getMessage());

			e.printStackTrace();
		}
		
	}
		return cpUserInfoList;
	
	}



	
	//added by Nithiya
	@Override
	public List<CpUserInfo> listUserDetailsForgetPassword(CpUserInfo verifyUserLogin) {
		// TODO Auto-generated method stub
		List<CpUserInfo> userLists =new ArrayList<CpUserInfo>();
		try{
			String colName[] = {"vuserName"}; 
			String colData[] = {verifyUserLogin.getVuserName()};
			userLists=findByColumeName(colName,colData);
		}catch (Exception e) {
			
			logger.error("Error occured "+e.getMessage());
			e.printStackTrace();
		}
		return userLists;
	}

	
	@Override
	public boolean updateColumns(CpUserInfo cpUserInfo) {
		// TODO Auto-generated method stub
		boolean status=Boolean.TRUE;
		logger.info("Update Start");
		try{
			CpUserInfo entity = findById(cpUserInfo.getNid());
			entity.setVpassword(cpUserInfo.getVpassword());
			entity.setVactive(cpUserInfo.getVactive());
			entity.setVpwMustChange("N");
			//merge(entity,entity.getNid());
			update(entity);
		}catch (Exception e) {
			status=Boolean.FALSE;
			logger.error("Error occured "+e.getMessage());
			e.printStackTrace();
		}	
		return status;
	}
	
/*added by vishal*/
	@Override
	public boolean updateUserDetails(CpUserInfo cpUser) {
		boolean status = Boolean.TRUE;
		logger.info("update UserDetails Start");
		try {
			update(cpUser);
		} catch (Exception e) {
			status = Boolean.FALSE;
			logger.error("Error occured " + e.getMessage());
			e.printStackTrace();
		}
		return status;
	}



	@Override
	public boolean updatePassword(CpUserInfo cpUserInfo) {
		// TODO Auto-generated method stub
		boolean status=Boolean.TRUE;
		logger.info("Update Start");
		try{
			update(cpUserInfo);
		}catch (Exception e) {
			status=Boolean.FALSE;
			logger.error("Error occured "+e.getMessage());
			e.printStackTrace();
		}	
		return status;
	}
	//BLOCK USER
	
	@Override
	public boolean lockUser(CpUserInfo lockUserLogin) {
		// TODO Auto-generated method stub
		boolean status=Boolean.TRUE;
		logger.info("Update Start");
		try{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			String today = formatter.format(date);
		    
			String lockedQuery = "UPDATE CpUserInfo SET vLockedTime= '"+today+"' WHERE vuserName='"+lockUserLogin.getVuserName()+"'";
			updateByColumnName(lockedQuery);
			
			String query = "UPDATE CpUserInfo SET vlocked= 'Y' WHERE vuserName='"+lockUserLogin.getVuserName()+"'";
			updateByColumnName(query);
		
		}catch (Exception e) {
			status=Boolean.FALSE;
			logger.error("Error occured "+e.getMessage());
			e.printStackTrace();
		}	
		return status;
	}

	@Override
	public List<CpUserInfo> getUserDetails(CpUserInfo verifyUserLogin) {

		String colCustRefNo[] = { "ncustRefNo" };
		int colData[] = { verifyUserLogin.getNcustRefNo() };

		return findByColumnName(colCustRefNo, colData);
	}
	

     public static void main(String[] args) {
    	 
    	 
    	 CustomerLoginDAOImpl d = new CustomerLoginDAOImpl();
    	 
    	 CpUserInfo ds = new CpUserInfo();
    	 ds.setVuserName("Vinod");
    	 d.fetchUserDetails(ds);
    	 System.out.println();
     }
	@Override
	public CpUserInfo findByUserName(String username) {
		String[] propertyValues = {"V_USER_NAME"};
		Object[] value = {username};
		List<CpUserInfo> findAllByPropertiesEqual = findAllByPropertiesEqual(propertyValues, value);
		return findAllByPropertiesEqual.get(0);
	}

	
	

}

