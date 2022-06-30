package com.aetins.customerportal.web.dao;
import java.util.List;

import com.aetins.customerportal.web.common.IGenericDao;
import com.aetins.customerportal.web.entity.CpReinStatement;

public interface ReinStatementDAO{
	
	
	public CpReinStatement insertReinStatementDetails(CpReinStatement  cpReinStatement);
	public List<CpReinStatement> fetchAllReinstatement(int serviceReqNo);
	public Long getRowCount(String type, String policyNo);
	
}