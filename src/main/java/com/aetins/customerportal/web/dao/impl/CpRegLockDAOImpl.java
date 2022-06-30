package com.aetins.customerportal.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.aetins.customerportal.web.common.GenericDaoImpl;
import com.aetins.customerportal.web.dao.CpRegLockDAO;
import com.aetins.customerportal.web.entity.CpRegLock;
import com.aetins.customerportal.web.entity.CpRequestMaster;
import com.aetins.customerportal.web.entity.CpThemeCss;

@Repository
public class CpRegLockDAOImpl extends GenericDaoImpl<CpRegLock, Integer> implements CpRegLockDAO{

	@Resource
	private SessionFactory session;
	
	@Override
	public boolean inserttable(CpRegLock cpRegLock) {
		boolean status = false;
		try{
			save(cpRegLock);
			status = true;
		}catch(Exception e) {
			status = false;
			e.printStackTrace();
		}
		return status;
	}
	
	@Override
	public CpRegLock fetchpolicy(String idenno) {	
		List<CpRegLock> cpRegLock = new  ArrayList<CpRegLock>();
		CpRegLock list = new CpRegLock();
		String[] propertyName = {"idenno"};
		Object[] value={idenno};
		try{
			cpRegLock=findAllByPropertiesEqual(propertyName,value);
			if(cpRegLock.size()>=1) {
				for(CpRegLock obj : cpRegLock) {
					list=obj;	
				}		
			}
		}catch (Exception e) {	
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<CpRegLock> fetchtable() {
		List<CpRegLock> listing = new ArrayList<CpRegLock>();
		try {
			listing =  findAll();
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
		 return listing;
	}

	@Override
	public boolean updatettable(CpRegLock cpRegLock) {
		boolean status=Boolean.TRUE;
		try {
			CpRegLock entity = findById(cpRegLock.getSno());			
			entity.setNoofattempts(cpRegLock.getNoofattempts());
			entity.setStatus(cpRegLock.getStatus());
			entity.setModel(cpRegLock.getModel());
			update(entity);
		}catch(Exception e) {
			status=Boolean.FALSE;
			e.printStackTrace();
		}
		return status;
	}

	@Override
	protected Class<CpRegLock> getEntityClass() {
		// TODO Auto-generated method stub
		return CpRegLock.class;
	}

	

}
