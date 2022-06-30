/*package com.aetins.customerportal.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aetins.customerportal.web.dao.ITransactionDeptDao;
import com.aetins.customerportal.web.entity.CPTransactionDept;
import com.aetins.customerportal.web.service.TransactionDeptService;

@Service
public class TransactionDeptServiceImpl implements TransactionDeptService{

	@Autowired
	private ITransactionDeptDao transactionDept;
	
	
	@Override
	public List<CPTransactionDept> findByDeptName(String deptName) {
		
		return transactionDept.findByDeptName(deptName);
	}

	@Override
	public List<CPTransactionDept> findByDeptCode(String deptCode) {
		return transactionDept.findByDeptCode(deptCode);
	}
}
*/