package com.aetins.customerportal.web.dao;

import java.util.List;

import com.aetins.customerportal.web.common.IGenericDao;
import com.aetins.customerportal.web.entity.CpSecurityImgMaster;



public interface SecurityImageMasterDAO extends IGenericDao<CpSecurityImgMaster, Long>{

	 public List<CpSecurityImgMaster> listSecurityImgMaster();
	    public boolean updateSecurityImages1(CpSecurityImgMaster cpSecurityImgMaster);
	    public boolean updateSecurityImages(List<CpSecurityImgMaster> cpSecurityImgMaster);
}
