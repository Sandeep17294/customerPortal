package com.aetins.customerportal.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aetins.customerportal.web.dao.CpRequestMasterDAO;
import com.aetins.customerportal.web.dao.FatcaCountryDAO;
import com.aetins.customerportal.web.dao.FatcaDAO;
import com.aetins.customerportal.web.dao.impl.CpRequestMasterDAOImpl;
import com.aetins.customerportal.web.dao.impl.FatcaCountryDAOImpl;
import com.aetins.customerportal.web.dao.impl.FatcaDAOImpl;
import com.aetins.customerportal.web.dto.FatcaAddCounDTO;
import com.aetins.customerportal.web.dto.FatcaDTO;
import com.aetins.customerportal.web.entity.CpFatca;
import com.aetins.customerportal.web.entity.CpFatcaCountryDet;
import com.aetins.customerportal.web.entity.CpRequestMaster;
import com.aetins.customerportal.web.service.FatcaBL;

@Service
public class FatcaBLImpl implements FatcaBL {
	
	@Autowired
	FatcaDAO fatcaDao;
	
	@Autowired
	CpRequestMasterDAO requestMasterDAO;
	
	@Autowired
	FatcaCountryDAO countryDAO;

	@Override
	public FatcaDTO saveFatcaDetails(FatcaDTO fatcadto) throws Exception {
					
		CpRequestMaster requestMaster = new CpRequestMaster();
		requestMaster.setServiceRequestNo(fatcadto.getServiceRequestNo().getServiceRequestNo());
		
		CpFatca fatca=new CpFatca();
		fatca.setSerialNo(fatcadto.getSeqNo());
		fatca.setServiceRequestNo(requestMaster);
		fatca.setIsUsPerson(fatcadto.getIsUsPerson());
		fatca.setTaxMoreThanOne(fatcadto.getTaxMoreThanOne());
	    fatca.setServiceName(fatcadto.getServiceName());
	    fatca.setTinNo(fatcadto.getTinNo());
	    fatca.setResidentUAE(fatcadto.getResidentUAE());
	    fatca=fatcaDao.insertFatcaDetails(fatca);
	   
	    fatcadto.setSeqNo(fatca.getSerialNo());
	    
	    return fatcadto;
	}

	

	@Override
	public boolean saveCountryDet(List<FatcaAddCounDTO> fatcaAddCounDTO) {
		
		List<CpFatcaCountryDet> cpFatcaCountryDet = new ArrayList<CpFatcaCountryDet>();
		for(FatcaAddCounDTO dto :fatcaAddCounDTO){
			 
			CpFatcaCountryDet  det = new CpFatcaCountryDet();
			det.setSerial(dto.getSerial());			
	     	CpFatca fatca = new CpFatca();
	     	
	     	fatca.setSerialNo(dto.getSeqNo().getSeqNo());
	     	det.setSerialNo(fatca);
	     	det.setCountry(dto.getCountry());
	     	det.setTinOrReason(dto.getTinOrReason());
	     	det.setCountryName(dto.getCountryName());
	     	cpFatcaCountryDet.add(det);
		}
		return countryDAO.insertCountryDetails(cpFatcaCountryDet);
	}
	
	
	

	
	public FatcaDAO getFatcaDao() {
		return fatcaDao;
	}

	

	public void setFatcaDao(FatcaDAO fatcaDao) {
		this.fatcaDao = fatcaDao;
	}


	public FatcaCountryDAO getCountryDAO() {
		return countryDAO;
	}


	public void setCountryDAO(FatcaCountryDAO countryDAO) {
		this.countryDAO = countryDAO;
	}



	
	
	

}
