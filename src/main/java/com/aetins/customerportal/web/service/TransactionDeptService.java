package com.aetins.customerportal.web.service;

import java.util.List;

import com.aetins.customerportal.web.entity.CPTransactionDept;

/**
 * @author avinash
 *
 */
public interface TransactionDeptService {

    public List<CPTransactionDept> findByDeptName(String deptName);
	
	public List<CPTransactionDept> findByDeptCode(String deptCode);
	
	//public List<CPTransactionDept> findByTransaction(String transaction);
	
	//public void saveTransactionDept(CPTransactionDept transactionDept);
}
