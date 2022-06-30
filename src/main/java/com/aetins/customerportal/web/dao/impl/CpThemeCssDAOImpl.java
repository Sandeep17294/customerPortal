package com.aetins.customerportal.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aetins.customerportal.web.common.GenericDaoImpl;
import com.aetins.customerportal.web.dao.CpThemeCssDAO;
import com.aetins.customerportal.web.entity.CpCustomerDetail;
import com.aetins.customerportal.web.entity.CpRequestMaster;
import com.aetins.customerportal.web.entity.CpTermAndCondition;
import com.aetins.customerportal.web.entity.CpThemeCss;

@Repository
public class CpThemeCssDAOImpl extends GenericDaoImpl<CpThemeCss, Integer> implements CpThemeCssDAO {

private Logger logger = LoggerFactory.getLogger(CpThemeCssDAOImpl.class);
	
	@Resource
	private SessionFactory session;
	

	@Override
	protected Session getSession() {
		return session.getCurrentSession();
	}
	
	@Override
	protected Class<CpThemeCss> getEntityClass() {
		// TODO Auto-generated method stub
		return CpThemeCss.class;
	}


	
	@Override
	public boolean updatetheme(CpThemeCss cpThemeCss) {
		boolean status=Boolean.TRUE;
		  logger.info("save Documents Start");
		try{
			update(cpThemeCss);
		}catch (Exception e) {
			 status=Boolean.FALSE;
			logger.error("Error occured "+e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	
	@Override
	public boolean inserttheme(CpThemeCss CpThemeCss) {
		boolean status=Boolean.TRUE;
		  logger.info("save Documents Start");
		  List<CpThemeCss> cpThemeCss=new  ArrayList<CpThemeCss>();
		  int custrefno=CpThemeCss.getCustrefno();
		  try{ 
			  String[] propertyName = {"custrefno"};
			   Object[] value={custrefno};
					cpThemeCss=findAllByPropertiesEqual(propertyName,value);
			        if(cpThemeCss.size()>0) {
						String propertyValues = "custrefno";
			        	int refno=cpThemeCss.get(0).getCustrefno();
						deleteByProperty(propertyValues,refno); 
						CpThemeCss.setId(0);
				        saveOrUpdate(CpThemeCss);
			        }else {
			        	saveOrUpdate(CpThemeCss);
			        }
		  }catch (Exception e) {
		   status=Boolean.FALSE;
		   logger.error("Error occured "+e.getMessage());
		   e.printStackTrace();
		  }
		  return status;
		 }

	@Override
	public List<CpThemeCss> fetch(CpThemeCss themeinfo) {
			
		int custrefno=themeinfo.getCustrefno();
		
			List<CpThemeCss> cpThemeCss=new  ArrayList<CpThemeCss>();
			String[] propertyName = {"custrefno"};
			Object[] value={custrefno};
			try{
				cpThemeCss=findAllByPropertiesEqual(propertyName,value);
			}catch (Exception e) {
				
				logger.error("Error occured fetchtransactionDAOImpl class ::::::"+e.getMessage());
				e.printStackTrace();
			}
			return cpThemeCss;
		}
	}





