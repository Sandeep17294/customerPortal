package com.aetins.customerportal.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.aetins.customerportal.web.common.GenericDaoImpl;
import com.aetins.customerportal.web.dao.ContributionHolidayDAO;
import com.aetins.customerportal.web.entity.CpContributionHoliday;
import com.aetins.customerportal.web.entity.CpRequestMaster;
import com.aetins.customerportal.web.entity.CpSessionSummary;

@Repository
public class ContributionHolidayDAOImpl extends GenericDaoImpl<CpContributionHoliday, Long> implements ContributionHolidayDAO {

	private Logger logger = LoggerFactory.getLogger(ContributionHolidayDAOImpl.class);

	@Resource
	private SessionFactory session;

	@Override
	protected Session getSession() {
		return session.getCurrentSession();
	}

	
	@Override
	protected Class<CpContributionHoliday> getEntityClass() {
		// TODO Auto-generated method stub
		return CpContributionHoliday.class;
	}
	
	@Override
	public boolean insertHolidayDetails(CpContributionHoliday cpContributionHoliday) {
		boolean status = Boolean.TRUE;
		logger.info("Entering into insertHolidayDetails for DAOImpl=============" + cpContributionHoliday);
		try {
			save(cpContributionHoliday);
		} catch (Exception e) {
			status = Boolean.FALSE;
			logger.error("Error occured insertHolidayDetails for DAOImpl==========" + e.getMessage());
			e.printStackTrace();
		}
		return status;
	}


	@Override
	public List<CpContributionHoliday> fetchAllContrHoliday(int serviceReqNo) {
		List<CpContributionHoliday> cpContributionHoliday = new ArrayList<CpContributionHoliday>();
		String[] propNames = { "nServicRequestNo.serviceRequestNo" };
		Object[] values = { serviceReqNo };
		logger.info("List all user cpContributionAlterations retreiving");
		try {
			cpContributionHoliday = findAllByPropertiesEqual(propNames, values);
			logger.info("Size of holidayList in fetchAllContrHoliday() ==============" + cpContributionHoliday.size());
		} catch (Exception e) {
			logger.error("Error occured in fetchAllContrHoliday================ " + e.getMessage());
			e.printStackTrace();
		}
		return cpContributionHoliday;
	}

	public List<CpRequestMaster> getTransactionCount(String userid) {

		String queryString = "from CpRequestMaster master where  master.userId=? and master.requestStatus in('AWAP','SUCC','REJ')";
		return getSession().createQuery(queryString).setString(0, userid).list();
	}

	
	public Long getRowCount(String type, String policyNo) {
		
		String queryString = "select count(*) from CpRequestMaster master where master.serviceRequestType=? and master.policyNo=? and master.requestStatus in('AWIT','AWAP')";
		Query query = getSession().createQuery(queryString);
		query.setString(0, type);
		query.setString(1, policyNo);
		Long count = (Long) query.uniqueResult();
		return count;
	}

	/*
	 * public List<CpRequestMaster> getTransactionCount(String userid) {
	 * 
	 * openSession(); beginTransaction(); String queryString =
	 * "from CpRequestMaster master where  master.userId=? and master.requestStatus in('AWIT','AWAP','SUCC','REJ')"
	 * ; Query query = session.createQuery(queryString); query.setString(0, userid);
	 * List<CpRequestMaster> tranList = query.list();
	 * 
	 * return tranList; }
	 */

	@Override
	public List getTransactionCount(String userName, String fatcaFlag) {
		List list = null;
//		try {
//			String queryString = "from "+getEntityClass().getName()+" master where master.userId=? and master.fatcaFlag=? ";
//			Criteria cr = getSession().createCriteria(CpRequestMaster.class);
//			cr.setProjection(Projections.projectionList().add(Projections.property("serviceRequestDate"), "serviceRequestDate")).setResultTransformer(Transformers.aliasToBean(CpRequestMaster.class));
//			cr.add(Restrictions.eq("userId", userName));
//			cr.add(Restrictions.eq("fatcaFlag", fatcaFlag));
//			cr.addOrder(Order.desc("serviceRequestDate"));
//			list = cr.list();
			
			Criteria criteria = getSession().createCriteria(CpRequestMaster.class);
			criteria.add(Restrictions.eq("userId", userName));
			criteria.add(Restrictions.eq("fatcaFlag", fatcaFlag));
			return(List<CpRequestMaster>)criteria.list();
			
			
				
			
			
//			String queryString = "select * from CpRequestMaster master where master.userId=? and master.fatcaFlag=? ";
//			Criteria cr = getSession().createCriteria(CpRequestMaster.class)
//					.setProjection(Projections.projectionList().add(Projections.property("serviceRequestDate"),
//							"serviceRequestDate"))
//					.setResultTransformer(Transformers.aliasToBean(CpRequestMaster.class));
//			cr.add(Restrictions.eq("userId", userName));
//			cr.add(Restrictions.eq("fatcaFlag", fatcaFlag));
//			cr.addOrder(Order.desc("serviceRequestDate"));

//			logger.info("List size of fatca 90 days validation is ======================" + list.size());
//		} catch (Exception e) {
//			logger.error("Inside catch block of fatca 90 days validation fetch==============" + e.getMessage());
//			e.printStackTrace();
//		}
		//return list;
//		return list;
	}

	public List getLastLoginDetails(String userName) {

		List list = null;
		try {
			// String
			String queryString = "from CpSessionSummary master where master.vuserName=? ";

			Criteria cr = createCriteria(CpSessionSummary.class);
			cr.setProjection(Projections.projectionList().add(Projections.property("dsessionEnd"), "dsessionEnd"));
			cr.setResultTransformer(Transformers.aliasToBean(CpSessionSummary.class));
			cr.add(Restrictions.eq("vuserName", userName));
			cr.addOrder(Order.desc("dsessionEnd"));

			list = cr.list();
			logger.info("getLastLoginDetails =========" + list.size());
		} catch (Exception e) {
			logger.error("Inside catch block of getLastLoginDetails========" + e.getMessage());
			e.printStackTrace();
		}
		return list;
	}

}
