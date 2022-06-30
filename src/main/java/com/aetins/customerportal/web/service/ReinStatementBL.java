package com.aetins.customerportal.web.service;
import java.util.List;

import com.aetins.customerportal.web.dto.ReinStatementsDTO;
import com.aetins.customerportal.web.dto.ServiceRequestMasterDTO;
import com.aetins.customerportal.web.transaction.dto.TyReinstatementDTO;


public interface ReinStatementBL {

	public ReinStatementsDTO insertReinStatementDetails(ReinStatementsDTO reinStatementsDTO);
	
	public boolean saveReinstatmentBenefit(List<ReinStatementsDTO> cpReinStatementBenefitDTO);
	
	public TyReinstatementDTO fetchRequestAndReinstatements(ServiceRequestMasterDTO serviceRequestMasterDTO);
}
