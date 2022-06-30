package com.aetins.customerportal.web.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.aetins.customerportal.web.common.GenericDaoImpl;
import com.aetins.customerportal.web.dao.ResetSecurityAnswerDAO;
import com.aetins.customerportal.web.entity.CpResetSecurityAnswer;

@Repository
public class ResetSecurityAnswerDAOImpl extends GenericDaoImpl<CpResetSecurityAnswer, Long> implements ResetSecurityAnswerDAO {
	
	private Logger logger = LoggerFactory.getLogger(ResetSecurityAnswerDAOImpl.class);
	
	@Resource
	SessionFactory sessionFactory;
	
	@Override
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	protected Class<CpResetSecurityAnswer> getEntityClass() {
		return CpResetSecurityAnswer.class;
	}

	@Override
	public List<CpResetSecurityAnswer> fetchUserSecurityQuestion(String userName ,String questionStatus) {

		List<CpResetSecurityAnswer> questionList = new ArrayList<CpResetSecurityAnswer>();
		try {
			String colName[] = { "userName","quesStatus" };
			String colData[] = { userName,questionStatus };
			
			questionList = findByColumeName(colName, colData);
		} catch (Exception e) {

			logger.error("Error occured " + e.getMessage());
			e.printStackTrace();
		}
		return questionList;
	}

	@Override
	public boolean updateSecurityQuestion(List<CpResetSecurityAnswer> cpQuestionDetail) {
		// TODO Auto-generated method stub
		boolean status = Boolean.TRUE;
		logger.info("save Documents Start");
		try {
			for (CpResetSecurityAnswer details : cpQuestionDetail) {
				if (details != null) {
					if (details.getQuesStatus() != null) {
						if (details.getQuesStatus().equalsIgnoreCase("A")) {
							save(details);
						}
						if (details.getQuesStatus().equalsIgnoreCase("D")) {
							String updateQuery = "UPDATE CpResetSecurityAnswer SET quesStatus='I' WHERE id='"
									+ details.getId() + "'";
							System.out.println(updateQuery);
							updateByColumnName(updateQuery);
						}
						if (details.getQuesStatus().equalsIgnoreCase("E")) {
							String updateQuery = "UPDATE CpResetSecurityAnswer SET quesStatus='I' WHERE id='"
									+ details.getId() + "'";
							System.out.println(updateQuery);
							updateByColumnName(updateQuery);
							details.setQuesStatus("A");
							save(details);
						}
					}
				}
			}
		} catch (Exception e) {
			status = Boolean.FALSE;
			logger.error("Error occured " + e.getMessage());
			e.printStackTrace();
		}
		return status;
	}


	@Override
	public void saveRegistrationQuestions(List<CpResetSecurityAnswer> securityQuestionsDAO) {
		try{
			for (CpResetSecurityAnswer cpResetSecurityAnswer : securityQuestionsDAO){
				save(cpResetSecurityAnswer);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	

}
