package com.aetins.customerportal.web.dao;

import java.util.List;

import com.aetins.customerportal.web.common.IGenericDao;
import com.aetins.customerportal.web.entity.CpReinStatementBenefit;

public interface ReinStatementBenefitDAO {
	
	public boolean insertreinstatement(List<CpReinStatementBenefit> cpReinStatementBenefit);
	
	public List<CpReinStatementBenefit> fetchReinstatementBenefit(int serialNo);
}
