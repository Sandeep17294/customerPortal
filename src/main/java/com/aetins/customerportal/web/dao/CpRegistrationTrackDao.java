package com.aetins.customerportal.web.dao;

import java.util.List;

import com.aetins.customerportal.web.common.IGenericDao;
import com.aetins.customerportal.web.entity.CpRegistrationTrack;


/**
 * This DAO interface for all Registration track table 
 * 21/06/2017
 * @author Viswakarthick
 */
public interface CpRegistrationTrackDao extends IGenericDao<CpRegistrationTrack, Long>{
	
	public boolean saveErrTrackDetails(CpRegistrationTrack cpRegistrationTrack) throws Exception;
	List<CpRegistrationTrack> listAllRegistTrack();
	
}
