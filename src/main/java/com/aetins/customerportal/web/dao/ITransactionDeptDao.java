package com.aetins.customerportal.web.dao;

import java.util.List;

import com.aetins.customerportal.web.common.IGenericDao;
import com.aetins.customerportal.web.entity.CPTransactionDept;

public interface ITransactionDeptDao extends IGenericDao<CPTransactionDept, Long>{

	public List<CPTransactionDept> findByDeptName(String deptName);
	
	public List<CPTransactionDept> findByDeptCode(String deptCode);
	
	public CPTransactionDept findByTransaction(String transaction);
	
	public void createTransactionDept(CPTransactionDept transactionDept);
}
