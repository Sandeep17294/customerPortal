package com.aetins.customerportal.web.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aetins.customerportal.web.entity.CpLogsErros;
import com.aetins.customerportal.web.service.CpLogsErrosBL;
import com.aetins.customerportal.web.service.EServiceTransactionService;
import com.aetins.customerportal.web.transaction.dto.TyChangeInContributionDTO;
import com.aetins.customerportal.web.transaction.dto.TyClaimIntimationDTO;
import com.aetins.customerportal.web.transaction.dto.TyContributionHolidayDTO;
import com.aetins.customerportal.web.transaction.dto.TyNfAddressContactsDTO;
import com.aetins.customerportal.web.transaction.dto.TyNfAddressDTO;
import com.aetins.customerportal.web.transaction.dto.TyNfPersonalDetailsDTO;
import com.aetins.customerportal.web.transaction.dto.TyPartialWithdrawDTO;
import com.aetins.customerportal.web.transaction.dto.TyProtectionBenifitDTO;
import com.aetins.customerportal.web.transaction.dto.TyRedirectionSwitchingDTO;
import com.aetins.customerportal.web.transaction.dto.TyReinstatementDTO;
import com.aetins.customerportal.web.utils.AppSettingURL;
import com.aetins.customerportal.web.utils.BSLUtils;
import com.aetins.customerportal.web.utils.DateUtil;

import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.BfnChangeInContribution;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.BfnChangeInContributionResponse;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.BfnClaimIntimation;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.BfnClaimIntimationResponse;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.BfnContributionHoliday;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.BfnContributionHolidayResponse;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.BfnNfAddress;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.BfnNfAddressContacts;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.BfnNfAddressContactsResponse;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.BfnNfAddressResponse;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.BfnNfPersonalDetails;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.BfnNfPersonalDetailsResponse;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.BfnPartialWithdrawalFunds;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.BfnPartialWithdrawalFundsResponse;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.BfnProductionBenefit;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.BfnProductionBenefitResponse;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.BfnRedirectionSwitching;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.BfnRedirectionSwitchingResponse;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.BfnReinStatement;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.BfnReinStatementResponse;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.CpChangeInContributionColc;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.CpChangeInContributionTypeUser;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.CpClaimIntimationColc;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.CpClaimIntimationTypeUser;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.CpContributionHolidayColc;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.CpContributionHolidayTypeUser;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.CpExceptionErrorTypeUser;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.CpExceptionErrorTypeUserArray;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.CpFactaCountryDetTypeUser;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.CpFatcaCountryDetColc;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.CpFatcaTableTypeUser;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.CpFatcaTableTypeUserArray;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.CpNfAddressColc;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.CpNfAddressContactsColc;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.CpNfAddressContactsTypeUser;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.CpNfAddressTypeUser;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.CpNfPersonalDetailsColc;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.CpNfPersonalDetailsTypeUser;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.CpPartialWithdrawFundsColc;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.CpProductionBenefitColc;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.CpProductionBenefitTypeUser;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.CpRedirectionSwitchingColc;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.CpRedirectionSwitchingTypeUser;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.CpReinStatementColc;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.CpReinStatementTypeUser;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.CpServiceRequestMasterColc;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.CpServiceRequestMasterTypeUser;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.TyChangeInContributionUser;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.TyChangeInContributionUserArray;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.TyClaimIntimationUser;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.TyClaimIntimationUserArray;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.TyContributionHolidayUser;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.TyContributionHolidayUserArray;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.TyNfAddressContactsUser;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.TyNfAddressContactsUserArray;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.TyNfAddressUser;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.TyNfAddressUserArray;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.TyNfPersonalDetailsUser;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.TyNfPersonalDetailsUserArray;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.TyPartialWithdrawalFundsUser;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.TyPartialWithdrawalFundsUserArray;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.TyProductionBenefitUser;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.TyProductionBenefitUserArray;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.TyRedirectionSwitchingUser;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.TyRedirectionSwitchingUserArray;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.TyReinStatementUser;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.TyReinStatementUserArray;
import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.CpPartialWithdrawFundsTypeUser;


/**
 * @author Shashi
 * @since 06/07/2017
 *
 */
@SuppressWarnings("rawtypes")
@Service
public class EServiceTransactionServiceImpl implements EServiceTransactionService {
	
	
	private static final Logger logger = Logger.getLogger(EServiceTransactionServiceImpl.class);

	@Autowired
	CpLogsErrosBL cpLogsErrosBL;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.aetins.webservices.transaction.service.EServiceTransactionService#
	 * getServiceProxy()
	 */
	@Override
	public PhaseTwoServiceStub getServiceProxy() throws Exception {
		return new PhaseTwoServiceStub(AppSettingURL.ESERV_TRANSACTION_URL);
	}

	public Calendar getCalendarwithTypeDateTime(Date date) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
		try {
			date = sdf.parse(DateUtil.toString(date, "yyyy-MM-dd HH:mm:ss"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	public Calendar getCalendarwithTypeDate(Date date) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
		try {
			date = sdf.parse(DateUtil.toString(date, "yyyy-MM-dd HH:mm:ss"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date newDate = new Date();
		newDate.setYear(date.getYear());
		newDate.setMonth(date.getMonth());
		newDate.setDate(date.getDate());
		Calendar cal = Calendar.getInstance();
		cal.setTime(newDate);
		return cal;
	}

	@Override
	public String saveReinstatementAlteration(TyReinstatementDTO reinstatementDTO) throws Exception {

        try {
		TyReinStatementUserArray valuesArray = new TyReinStatementUserArray();
		TyReinStatementUser[] tyReinstatementUserArrays = null;

		CpServiceRequestMasterColc requestMasterColc = new CpServiceRequestMasterColc();
		CpReinStatementColc reinstatementColc = new CpReinStatementColc();
		CpReinStatementTypeUser[] cpReinstatementTypeUsers = null;
		CpServiceRequestMasterTypeUser[] cpServiceRequestMasterTypeUsers = null;

		CpFatcaTableTypeUser[] cpFatcaTableTypeUsers = null;
		CpFatcaTableTypeUserArray cpFatcaTableTypeUserArray = null;
		CpFatcaCountryDetColc cpFatcaCountryDetColc = new CpFatcaCountryDetColc();

		if (BSLUtils.isNotNull(reinstatementDTO)) {
			if (BSLUtils.isNotNull(reinstatementDTO.getCpRequestMasters())) {
				cpServiceRequestMasterTypeUsers = new CpServiceRequestMasterTypeUser[reinstatementDTO
						.getCpRequestMasters().size()];
				for (int i = 0; i < reinstatementDTO.getCpRequestMasters().size(); i++) {
					logger.info("Transaction Reinstatement size :" + reinstatementDTO.getCpReinStatement().size());
					CpServiceRequestMasterTypeUser cpServiceRequestMasterTypeUser = new CpServiceRequestMasterTypeUser();
					cpServiceRequestMasterTypeUser.setServiceRequestNo(
							BigDecimal.valueOf(reinstatementDTO.getCpRequestMasters().get(i).getServiceRequestNo()));
					
					if (BSLUtils.isNotNull(reinstatementDTO.getCpRequestMasters().get(i).getSeqno()))
						cpServiceRequestMasterTypeUser
								.setSeqNo(reinstatementDTO.getCpRequestMasters().get(i).getSeqno());
					
					
					if (BSLUtils.isNotNull(reinstatementDTO.getCpRequestMasters().get(i).getServiceRequestDate())) {
						cpServiceRequestMasterTypeUser.setServiceRequestDate(getCalendarwithTypeDateTime(
								reinstatementDTO.getCpRequestMasters().get(i).getServiceRequestDate()));
					}
					if (BSLUtils.isNotNull(reinstatementDTO.getCpRequestMasters().get(i).getServiceRequestType())) {
						if (reinstatementDTO.getCpRequestMasters().get(i).getServiceRequestType()
								.equalsIgnoreCase("reinstatement"))
							cpServiceRequestMasterTypeUser.setServiceRequestType("PR0015");
						else
							cpServiceRequestMasterTypeUser.setServiceRequestType("PR0015");
					}
					if (BSLUtils.isNotNull(reinstatementDTO.getCpRequestMasters().get(i).getPolicyNo()))
						cpServiceRequestMasterTypeUser
								.setPolicyNo(reinstatementDTO.getCpRequestMasters().get(i).getPolicyNo());
					if (BSLUtils.isNotNull(reinstatementDTO.getCpRequestMasters().get(i).getUserId()))
						cpServiceRequestMasterTypeUser
								.setUserid(reinstatementDTO.getCpRequestMasters().get(i).getUserId());
					if (BSLUtils.isNotNull(reinstatementDTO.getCpRequestMasters().get(i).getRequestStatus()))
						cpServiceRequestMasterTypeUser
								.setRequestStatus(reinstatementDTO.getCpRequestMasters().get(i).getRequestStatus());
					if (BSLUtils.isNotNull(reinstatementDTO.getCpRequestMasters().get(i).getProcessedDate())) {
						cpServiceRequestMasterTypeUser.setProcessedDate(getCalendarwithTypeDateTime(
								reinstatementDTO.getCpRequestMasters().get(i).getProcessedDate()));
					}
					if (BSLUtils.isNotNull(reinstatementDTO.getCpRequestMasters().get(i).getProcessedBy()))
						cpServiceRequestMasterTypeUser
								.setProcessedBy(reinstatementDTO.getCpRequestMasters().get(i).getProcessedBy());

					if (BSLUtils.isNotNull(reinstatementDTO.getCpRequestMasters().get(i).getRecentUpdate())) {
						cpServiceRequestMasterTypeUser.setProcessedDate(getCalendarwithTypeDateTime(
								reinstatementDTO.getCpRequestMasters().get(i).getRecentUpdate()));
					}
					cpServiceRequestMasterTypeUsers[i] = cpServiceRequestMasterTypeUser;
				}
				requestMasterColc.setArray(cpServiceRequestMasterTypeUsers);
			}

			if (BSLUtils.isNotNull(reinstatementDTO.getCpReinStatement())) {
				cpReinstatementTypeUsers = new CpReinStatementTypeUser[reinstatementDTO.getCpReinStatement().size()];
				for (int i = 0; i < reinstatementDTO.getCpReinStatement().size(); i++) {
					logger.info("Transaction Reinstatement size :" + reinstatementDTO.getCpReinStatement().size());
					CpReinStatementTypeUser cpReinStatementTypeUser = new CpReinStatementTypeUser();
					cpReinStatementTypeUser.setSerialno(
							BigDecimal.valueOf(reinstatementDTO.getCpReinStatement().get(i).getSerialNo()));
					if (BSLUtils.isNotNull(reinstatementDTO.getCpReinStatement().get(i).getOutstandingAmount()))
						cpReinStatementTypeUser.setOutstandingAmount(BigDecimal
								.valueOf(reinstatementDTO.getCpReinStatement().get(i).getOutstandingAmount()));
					if (BSLUtils.isNotNull(reinstatementDTO.getCpReinStatement().get(i).getProductName()))
						cpReinStatementTypeUser
								.setProductName(reinstatementDTO.getCpReinStatement().get(i).getProductName());
					if (BSLUtils.isNotNull(reinstatementDTO.getCpReinStatement().get(i).getPrumDate())) {
						cpReinStatementTypeUser.setPrumDate(getCalendarwithTypeDateTime(
								reinstatementDTO.getCpReinStatement().get(i).getPrumDate()));
					}
					if (BSLUtils.isNotNull(reinstatementDTO.getCpReinStatement().get(i).getIssueDate())) {
						cpReinStatementTypeUser.setIssueDate(getCalendarwithTypeDateTime(
								reinstatementDTO.getCpReinStatement().get(i).getIssueDate()));
					}
					if (BSLUtils.isNotNull(reinstatementDTO.getCpReinStatement().get(i).getLapseDate())) {
						cpReinStatementTypeUser.setLapseDate(getCalendarwithTypeDateTime(
								reinstatementDTO.getCpReinStatement().get(i).getLapseDate()));
					}
					cpReinstatementTypeUsers[i] = cpReinStatementTypeUser;
				}
				reinstatementColc.setArray(cpReinstatementTypeUsers);
			}

			// Inserting Fatca details --------
			if (BSLUtils.isNotNull(reinstatementDTO.getCpFatca())) {
				cpFatcaTableTypeUsers = new CpFatcaTableTypeUser[reinstatementDTO.getCpFatca().size()];

				for (int i = 0; i < reinstatementDTO.getCpFatca().size(); i++) {

					CpFatcaTableTypeUser cpFatcaTableTypeUser = new CpFatcaTableTypeUser();

					if (BSLUtils.isNotNull(reinstatementDTO.getCpFatca().get(i).getSerialNo())) {
						cpFatcaTableTypeUser
								.setNSerialNo(BigDecimal.valueOf(reinstatementDTO.getCpFatca().get(i).getSerialNo()));
					}

					if (BSLUtils.isNotNull(reinstatementDTO.getCpFatca().get(i).getServiceRequestNo()))

						cpFatcaTableTypeUser.setServiceRequestNo(BigDecimal
								.valueOf(reinstatementDTO.getCpRequestMasters().get(i).getServiceRequestNo()));

					if (BSLUtils.isNotNull(reinstatementDTO.getCpFatca().get(i).getIsUsPerson()))
						cpFatcaTableTypeUser.setVUsPerson(reinstatementDTO.getCpFatca().get(i).getIsUsPerson());

					if (BSLUtils.isNotNull(reinstatementDTO.getCpFatca().get(i).getServiceName())) {
						cpFatcaTableTypeUser.setVServiceName(reinstatementDTO.getCpFatca().get(i).getServiceName());
					}

					if (BSLUtils.isNotNull(reinstatementDTO.getCpFatca().get(i).getTinNo())) {
						cpFatcaTableTypeUser.setVTinNo(reinstatementDTO.getCpFatca().get(i).getTinNo());
					}

					if (BSLUtils.isNotNull(reinstatementDTO.getCpFatca().get(i).getTaxMoreThanOne())) {
						cpFatcaTableTypeUser
								.setVTaxMorethanOnecoun(reinstatementDTO.getCpFatca().get(i).getTaxMoreThanOne());
					}

					CpFactaCountryDetTypeUser[] cpFactaCountryDetTypeUser = new CpFactaCountryDetTypeUser[10];
					if (reinstatementDTO.getCpFatcaCountryDet() != null) {
						if (reinstatementDTO.getCpFatcaCountryDet().size() > 0) {
							cpFactaCountryDetTypeUser = new CpFactaCountryDetTypeUser[reinstatementDTO
									.getCpFatcaCountryDet().size()];
							for (int l = 0; l < reinstatementDTO.getCpFatcaCountryDet().size(); l++) {
								CpFactaCountryDetTypeUser eachRecord = new CpFactaCountryDetTypeUser();

								// eachRecord.setNSeqNo(reinstatementDTO.getCpFatcaCountryDet().get(l).getSerialNo());
								eachRecord.setNSerialNo(
										BigDecimal.valueOf(reinstatementDTO.getCpFatca().get(i).getSerialNo()));
								eachRecord.setVCountryJuridis(
										reinstatementDTO.getCpFatcaCountryDet().get(l).getCountry());
								eachRecord
										.setVTinReason(reinstatementDTO.getCpFatcaCountryDet().get(l).getTinOrReason());
								cpFactaCountryDetTypeUser[l] = eachRecord;
								cpFatcaCountryDetColc.setArray(cpFactaCountryDetTypeUser);
								cpFatcaTableTypeUser.setOcCountry(cpFatcaCountryDetColc);
							}
						}
					}
					cpFatcaTableTypeUsers[i] = cpFatcaTableTypeUser;
				}

			}
			if (BSLUtils.isNotNull(cpFatcaTableTypeUsers)) {
				cpFatcaTableTypeUserArray = new CpFatcaTableTypeUserArray();
				cpFatcaTableTypeUserArray.setCpFatcaTableTypeUser(cpFatcaTableTypeUsers);
			}

			if (reinstatementColc.getArray().length > 0 && requestMasterColc.getArray().length > 0) {
				tyReinstatementUserArrays = new TyReinStatementUser[1];
				TyReinStatementUser tyReinStatementUser = new TyReinStatementUser();
				tyReinStatementUser.setLcServiceColc(requestMasterColc);
				tyReinStatementUser.setLcReinStatementColc(reinstatementColc);
				tyReinstatementUserArrays[0] = tyReinStatementUser;
				valuesArray.setTyReinStatementUser(tyReinstatementUserArrays);
			}

			logger.info("Saving Transaction :Transaction DTO size :" + valuesArray.getTyReinStatementUser().length);
			if (valuesArray.getTyReinStatementUser().length > 0) {
				BfnReinStatement bfnReinStatement = new BfnReinStatement();
				bfnReinStatement.setPReinStatement(valuesArray);
				bfnReinStatement.setPFatca(cpFatcaTableTypeUserArray);
				BfnReinStatementResponse response = getServiceProxy().bfnReinStatement(bfnReinStatement);
				CpExceptionErrorTypeUserArray cpExceptionErrorTypeUserList = response.getResult();
				CpExceptionErrorTypeUser[] cpExceptionErrorTypeUserArray = cpExceptionErrorTypeUserList
						.getCpExceptionErrorTypeUser();

				if (BSLUtils.isNotNull(cpExceptionErrorTypeUserArray)) {

					for (int i = 0; i < cpExceptionErrorTypeUserArray.length; i++) {
						CpExceptionErrorTypeUser cpExceptionErrorTypeUser = cpExceptionErrorTypeUserArray[i];
						
						if (cpExceptionErrorTypeUser.getErrSqlCode().equalsIgnoreCase("PASS")) {
							logger.info("Change in contribution transaction status: "
									+ cpExceptionErrorTypeUser.getErrSqlCode());
							return "PASS";
						} 	else {
							for(int i1 =0;i1<reinstatementDTO.getCpRequestMasters().size();i1++) {
								boolean status;
								CpLogsErros error = new CpLogsErros();
								error.setPolicyno(reinstatementDTO.getCpRequestMasters().get(i1).getPolicyNo());
								error.setReqno(reinstatementDTO.getCpRequestMasters().get(i1).getServiceRequestNo());
								error.setType(reinstatementDTO.getCpRequestMasters().get(i1).getServiceRequestType());
								error.setUserid(reinstatementDTO.getCpRequestMasters().get(i1).getUserId());
								error.setException(cpExceptionErrorTypeUser.getErrSqlErrDesc());
								status= cpLogsErrosBL.insertexception(error);
							
							logger.info("Change in contribution transaction status: "
									+ cpExceptionErrorTypeUser.getErrSqlCode());
							logger.info("Transaction error code: " + cpExceptionErrorTypeUser.getErrSqlCode());
							logger.info("Transaction error Description: " + cpExceptionErrorTypeUser.getErrSqlErrDesc());
							logger.info(
									"Transaction service Request no: " + cpExceptionErrorTypeUser.getServiceRequestNo());
						}
					  }
					}
				
				}

			}
		}
     }catch(Exception e) {
    	 for(int i1 =0;i1<reinstatementDTO.getCpRequestMasters().size();i1++) {
				boolean status;
				CpLogsErros error = new CpLogsErros();
				error.setPolicyno(reinstatementDTO.getCpRequestMasters().get(i1).getPolicyNo());
				error.setReqno(reinstatementDTO.getCpRequestMasters().get(i1).getServiceRequestNo());
				error.setType(reinstatementDTO.getCpRequestMasters().get(i1).getServiceRequestType());
				error.setUserid(reinstatementDTO.getCpRequestMasters().get(i1).getUserId());
				error.setException(e.getMessage());
				status= cpLogsErrosBL.insertexception(error);
    	 }
     }
		return "FAIL";
	
	}

	@Override
	public String saveContributionHoliday(TyContributionHolidayDTO contributionHolidayDTO) throws Exception {

		TyContributionHolidayUserArray valuesArray = new TyContributionHolidayUserArray();
		TyContributionHolidayUser[] tyContributionHolidayUserArrays = null;

		// CpFatcaTableTypeUserArray fatcaUserArray = new
		// CpFatcaTableTypeUserArray();
		// CpFatcaTableTypeUser[] cpFatcaTableTypeUserArrays = null;

		CpServiceRequestMasterColc requestMasterColc = new CpServiceRequestMasterColc();
		CpContributionHolidayColc contributionHolidayColc = new CpContributionHolidayColc();

		CpContributionHolidayTypeUser[] cpContributionHolidayTypeUsers = null;
		CpServiceRequestMasterTypeUser[] cpServiceRequestMasterTypeUsers = null;

		CpFatcaTableTypeUser[] cpFatcaTableTypeUsers = null;
		CpFatcaTableTypeUserArray cpFatcaTableTypeUserArray = null;
		CpFatcaCountryDetColc cpFatcaCountryDetColc = new CpFatcaCountryDetColc();

		if (BSLUtils.isNotNull(contributionHolidayDTO)) {

			// Inserting Master table details --------
			if (BSLUtils.isNotNull(contributionHolidayDTO.getCpRequestMasters())) {
				cpServiceRequestMasterTypeUsers = new CpServiceRequestMasterTypeUser[contributionHolidayDTO
						.getCpRequestMasters().size()];
				for (int i = 0; i < contributionHolidayDTO.getCpRequestMasters().size(); i++) {
					CpServiceRequestMasterTypeUser cpServiceRequestMasterTypeUser = new CpServiceRequestMasterTypeUser();
					cpServiceRequestMasterTypeUser.setServiceRequestNo(BigDecimal
							.valueOf(contributionHolidayDTO.getCpRequestMasters().get(i).getServiceRequestNo()));
					
					if (BSLUtils
							.isNotNull(contributionHolidayDTO.getCpRequestMasters().get(i).getServiceRequestDate())) {
						cpServiceRequestMasterTypeUser.setSeqNo(contributionHolidayDTO.getCpRequestMasters()
								.get(i).getSeqno());
					}
					
					if (BSLUtils
							.isNotNull(contributionHolidayDTO.getCpRequestMasters().get(i).getServiceRequestDate())) {
						cpServiceRequestMasterTypeUser.setServiceRequestDate(getCalendarwithTypeDateTime(
								contributionHolidayDTO.getCpRequestMasters().get(i).getServiceRequestDate()));
					}
					if (BSLUtils
							.isNotNull(contributionHolidayDTO.getCpRequestMasters().get(i).getServiceRequestType())) {
						if (contributionHolidayDTO.getCpRequestMasters().get(i).getServiceRequestType()
								.equalsIgnoreCase("contributionHoliday"))
							cpServiceRequestMasterTypeUser.setServiceRequestType("PREM_HOL");
						else {
							cpServiceRequestMasterTypeUser.setServiceRequestType("PREM_HOL");
						}
					}
					if (BSLUtils.isNotNull(contributionHolidayDTO.getCpRequestMasters().get(i).getPolicyNo()))
						cpServiceRequestMasterTypeUser
								.setPolicyNo(contributionHolidayDTO.getCpRequestMasters().get(i).getPolicyNo());
					if (BSLUtils.isNotNull(contributionHolidayDTO.getCpRequestMasters().get(i).getUserId()))
						cpServiceRequestMasterTypeUser
								.setUserid(contributionHolidayDTO.getCpRequestMasters().get(i).getUserId());
					if (BSLUtils.isNotNull(contributionHolidayDTO.getCpRequestMasters().get(i).getRequestStatus()))
						cpServiceRequestMasterTypeUser.setRequestStatus(
								contributionHolidayDTO.getCpRequestMasters().get(i).getRequestStatus());
					if (BSLUtils.isNotNull(contributionHolidayDTO.getCpRequestMasters().get(i).getProcessedDate())) {
						cpServiceRequestMasterTypeUser.setProcessedDate(getCalendarwithTypeDateTime(
								contributionHolidayDTO.getCpRequestMasters().get(i).getProcessedDate()));
					}
					if (BSLUtils.isNotNull(contributionHolidayDTO.getCpRequestMasters().get(i).getProcessedBy()))
						cpServiceRequestMasterTypeUser
								.setProcessedBy(contributionHolidayDTO.getCpRequestMasters().get(i).getProcessedBy());
					if (BSLUtils.isNotNull(contributionHolidayDTO.getCpRequestMasters().get(i).getRecentUpdate())) {
						cpServiceRequestMasterTypeUser.setProcessedDate(getCalendarwithTypeDateTime(
								contributionHolidayDTO.getCpRequestMasters().get(i).getRecentUpdate()));
					}
					cpServiceRequestMasterTypeUsers[i] = cpServiceRequestMasterTypeUser;
				}
				requestMasterColc.setArray(cpServiceRequestMasterTypeUsers);
			}

			// Inserting Holiday details -------
			if (BSLUtils.isNotNull(contributionHolidayDTO.getCpContributionHoliday())) {
				cpContributionHolidayTypeUsers = new CpContributionHolidayTypeUser[contributionHolidayDTO
						.getCpContributionHoliday().size()];
				for (int i = 0; i < contributionHolidayDTO.getCpContributionHoliday().size(); i++) {
					CpContributionHolidayTypeUser cpContributionHolidayTypeUser = new CpContributionHolidayTypeUser();
					if (BSLUtils.isNotNull(contributionHolidayDTO.getCpContributionHoliday().get(i).getnContribution()))
						cpContributionHolidayTypeUser.setNContribution(BigDecimal
								.valueOf(contributionHolidayDTO.getCpContributionHoliday().get(i).getnContribution()));
					if (BSLUtils.isNotNull(
							contributionHolidayDTO.getCpContributionHoliday().get(i).getnOutstandingAmount()))
						cpContributionHolidayTypeUser.setNOutstandingAmount(BigDecimal.valueOf(
								contributionHolidayDTO.getCpContributionHoliday().get(i).getnOutstandingAmount()));
					if (BSLUtils.isNotNull(contributionHolidayDTO.getCpContributionHoliday().get(i).getdHolidayTo())) {
						cpContributionHolidayTypeUser.setDHolidayTo(getCalendarwithTypeDate(
								contributionHolidayDTO.getCpContributionHoliday().get(i).getdHolidayTo()));
					}
					if (BSLUtils
							.isNotNull(contributionHolidayDTO.getCpContributionHoliday().get(i).getvPaymentFrequency()))
						cpContributionHolidayTypeUser.setVPaymentFrequency(
								contributionHolidayDTO.getCpContributionHoliday().get(i).getvPaymentFrequency());
					if (BSLUtils.isNotNull(contributionHolidayDTO.getCpContributionHoliday().get(i).getnSerialNo()))
						cpContributionHolidayTypeUser.setNSerialNo(BigDecimal
								.valueOf(contributionHolidayDTO.getCpContributionHoliday().get(i).getnSerialNo()));
					if (BSLUtils.isNotNull(contributionHolidayDTO.getCpContributionHoliday().get(i).getvProduct()))
						cpContributionHolidayTypeUser
								.setVProduct(contributionHolidayDTO.getCpContributionHoliday().get(i).getvProduct());
					if (BSLUtils
							.isNotNull(contributionHolidayDTO.getCpContributionHoliday().get(i).getdHolidayFrom())) {
						cpContributionHolidayTypeUser.setDHolidayFrom(getCalendarwithTypeDate(
								contributionHolidayDTO.getCpContributionHoliday().get(i).getdHolidayFrom()));
					}
					if (BSLUtils.isNotNull(
							contributionHolidayDTO.getCpContributionHoliday().get(i).getdContributionDueDate())) {
						cpContributionHolidayTypeUser.setDContributionDueDate(getCalendarwithTypeDate(
								contributionHolidayDTO.getCpContributionHoliday().get(i).getdContributionDueDate()));
					}
					cpContributionHolidayTypeUsers[i] = cpContributionHolidayTypeUser;
				}
				contributionHolidayColc.setArray(cpContributionHolidayTypeUsers);
			}

			// Inserting Fatca details --------
			if (BSLUtils.isNotNull(contributionHolidayDTO.getCpFatca())) {
				cpFatcaTableTypeUsers = new CpFatcaTableTypeUser[contributionHolidayDTO.getCpFatca().size()];

				for (int i = 0; i < contributionHolidayDTO.getCpFatca().size(); i++) {

					CpFatcaTableTypeUser cpFatcaTableTypeUser = new CpFatcaTableTypeUser();

					if (BSLUtils.isNotNull(contributionHolidayDTO.getCpFatca().get(i).getSerialNo())) {
						cpFatcaTableTypeUser.setNSerialNo(
								BigDecimal.valueOf(contributionHolidayDTO.getCpFatca().get(i).getSerialNo()));
					}

					if (BSLUtils.isNotNull(contributionHolidayDTO.getCpFatca().get(i).getServiceRequestNo()))

						cpFatcaTableTypeUser.setServiceRequestNo(BigDecimal
								.valueOf(contributionHolidayDTO.getCpRequestMasters().get(i).getServiceRequestNo()));

					if (BSLUtils.isNotNull(contributionHolidayDTO.getCpFatca().get(i).getIsUsPerson()))
						cpFatcaTableTypeUser.setVUsPerson(contributionHolidayDTO.getCpFatca().get(i).getIsUsPerson());

					if (BSLUtils.isNotNull(contributionHolidayDTO.getCpFatca().get(i).getServiceName())) {
						cpFatcaTableTypeUser
								.setVServiceName(contributionHolidayDTO.getCpFatca().get(i).getServiceName());
					}

					if (BSLUtils.isNotNull(contributionHolidayDTO.getCpFatca().get(i).getResidentUAE())) {
						cpFatcaTableTypeUser
								.setVUaeResidency(contributionHolidayDTO.getCpFatca().get(i).getResidentUAE());
					}

					if (BSLUtils.isNotNull(contributionHolidayDTO.getCpFatca().get(i).getTinNo())) {
						cpFatcaTableTypeUser.setVTinNo(contributionHolidayDTO.getCpFatca().get(i).getTinNo());
					}

					if (BSLUtils.isNotNull(contributionHolidayDTO.getCpFatca().get(i).getTaxMoreThanOne())) {
						cpFatcaTableTypeUser
								.setVTaxMorethanOnecoun(contributionHolidayDTO.getCpFatca().get(i).getTaxMoreThanOne());
					}

					CpFactaCountryDetTypeUser[] cpFactaCountryDetTypeUser = new CpFactaCountryDetTypeUser[10];
					if (contributionHolidayDTO.getCpFatcaCountryDet() != null) {
						if (contributionHolidayDTO.getCpFatcaCountryDet().size() > 0) {
							cpFactaCountryDetTypeUser = new CpFactaCountryDetTypeUser[contributionHolidayDTO
									.getCpFatcaCountryDet().size()];
							for (int l = 0; l < contributionHolidayDTO.getCpFatcaCountryDet().size(); l++) {
								CpFactaCountryDetTypeUser eachRecord = new CpFactaCountryDetTypeUser();

								// eachRecord.setNSeqNo(contributionHolidayDTO.getCpFatcaCountryDet().get(l).getSerialNo());
								eachRecord.setNSerialNo(
										BigDecimal.valueOf(contributionHolidayDTO.getCpFatca().get(i).getSerialNo()));
								eachRecord.setVCountryJuridis(
										contributionHolidayDTO.getCpFatcaCountryDet().get(l).getCountry());
								eachRecord.setVTinReason(
										contributionHolidayDTO.getCpFatcaCountryDet().get(l).getTinOrReason());
								cpFactaCountryDetTypeUser[l] = eachRecord;
								cpFatcaCountryDetColc.setArray(cpFactaCountryDetTypeUser);
								cpFatcaTableTypeUser.setOcCountry(cpFatcaCountryDetColc);
							}
						}
					}
					cpFatcaTableTypeUsers[i] = cpFatcaTableTypeUser;
				}

			}
			if (cpFatcaTableTypeUsers.length > 0) {
				cpFatcaTableTypeUserArray = new CpFatcaTableTypeUserArray();
				cpFatcaTableTypeUserArray.setCpFatcaTableTypeUser(cpFatcaTableTypeUsers);
			}

			if (contributionHolidayColc.getArray().length > 0 && requestMasterColc.getArray().length > 0) {

				tyContributionHolidayUserArrays = new TyContributionHolidayUser[1];
				TyContributionHolidayUser tyContributionHolidayUser = new TyContributionHolidayUser();
				tyContributionHolidayUser.setLcServiceColc(requestMasterColc);
				tyContributionHolidayUser.setLcContributionHolidayColc(contributionHolidayColc);
				tyContributionHolidayUserArrays[0] = tyContributionHolidayUser;
				valuesArray.setTyContributionHolidayUser(tyContributionHolidayUserArrays);

			}
		}
		if (valuesArray.getTyContributionHolidayUser().length > 0) {
			BfnContributionHoliday bfnContributionHoliday = new BfnContributionHoliday();
			bfnContributionHoliday.setPContributionHoliday(valuesArray);
			bfnContributionHoliday.setPFatca(cpFatcaTableTypeUserArray);
			BfnContributionHolidayResponse response = getServiceProxy().bfnContributionHoliday(bfnContributionHoliday);
			CpExceptionErrorTypeUserArray cpExceptionErrorTypeUserList = response.getResult();
			CpExceptionErrorTypeUser[] cpExceptionErrorTypeUserArray = cpExceptionErrorTypeUserList
					.getCpExceptionErrorTypeUser();

			if (BSLUtils.isNotNull(cpExceptionErrorTypeUserArray)) {
				for (int i = 0; i < cpExceptionErrorTypeUserArray.length; i++) {
					CpExceptionErrorTypeUser cpExceptionErrorTypeUser = cpExceptionErrorTypeUserArray[i];
					if (cpExceptionErrorTypeUser.getErrSqlCode().equalsIgnoreCase("PASS")) {
						logger.info("contribution Holiday transaction status:::::::: "
								+ cpExceptionErrorTypeUser.getErrSqlCode());
						return "PASS";
					} else {
						logger.info("contribution Holiday transaction status::::::: "
								+ cpExceptionErrorTypeUser.getErrSqlCode());
						logger.info("Transaction error code::::::::::::: " + cpExceptionErrorTypeUser.getErrSqlCode());
						logger.info("Transaction error Description:::::::::::: "
								+ cpExceptionErrorTypeUser.getErrSqlErrDesc());
						logger.info("Transaction service Request no:::::::::::::: "
								+ cpExceptionErrorTypeUser.getServiceRequestNo());
					}
				}
			}
		}
		return "FAIL";
	}

	@Override
	public String saveUpdateInformationPersonalDeatils(TyNfPersonalDetailsDTO tyNfPersonalDetailsDTO) throws Exception {

		TyNfPersonalDetailsUserArray valuesArray = new TyNfPersonalDetailsUserArray();
		TyNfPersonalDetailsUser[] tyNfPersonalDetailsUserArrays = null;

		CpServiceRequestMasterColc requestMasterColc = new CpServiceRequestMasterColc();
		CpNfPersonalDetailsColc cpNfPersonalDetailsColc = new CpNfPersonalDetailsColc();
		CpNfPersonalDetailsTypeUser[] cpNfPersonalDetailsTypeUsers = null;
		CpServiceRequestMasterTypeUser[] cpServiceRequestMasterTypeUsers = null;

		CpFatcaTableTypeUser[] cpFatcaTableTypeUsers = null;
		CpFatcaTableTypeUserArray cpFatcaTableTypeUserArray = null;
		CpFatcaCountryDetColc cpFatcaCountryDetColc = new CpFatcaCountryDetColc();

		if (BSLUtils.isNotNull(tyNfPersonalDetailsDTO)) {
			if (BSLUtils.isNotNull(tyNfPersonalDetailsDTO.getCpRequestMasters())) {
				cpServiceRequestMasterTypeUsers = new CpServiceRequestMasterTypeUser[tyNfPersonalDetailsDTO
						.getCpRequestMasters().size()];
				for (int i = 0; i < tyNfPersonalDetailsDTO.getCpRequestMasters().size(); i++) {

					CpServiceRequestMasterTypeUser cpServiceRequestMasterTypeUser = new CpServiceRequestMasterTypeUser();
					cpServiceRequestMasterTypeUser.setServiceRequestNo(BigDecimal
							.valueOf(tyNfPersonalDetailsDTO.getCpRequestMasters().get(i).getServiceRequestNo()));
					
					if (BSLUtils.isNotNull(tyNfPersonalDetailsDTO.getCpRequestMasters().get(i).getSeqno()))
						cpServiceRequestMasterTypeUser
								.setSeqNo(tyNfPersonalDetailsDTO.getCpRequestMasters().get(i).getSeqno());
					
					if (BSLUtils
							.isNotNull(tyNfPersonalDetailsDTO.getCpRequestMasters().get(i).getServiceRequestDate())) {
						cpServiceRequestMasterTypeUser.setServiceRequestDate(getCalendarwithTypeDateTime(
								tyNfPersonalDetailsDTO.getCpRequestMasters().get(i).getServiceRequestDate()));
					}
					if (BSLUtils
							.isNotNull(tyNfPersonalDetailsDTO.getCpRequestMasters().get(i).getServiceRequestType())) {
						if (tyNfPersonalDetailsDTO.getCpRequestMasters().get(i).getServiceRequestType()
								.equalsIgnoreCase("updateInformation"))
							cpServiceRequestMasterTypeUser.setServiceRequestType("NONFN");
						else {
							cpServiceRequestMasterTypeUser.setServiceRequestType("NONFN");
						}
					}
					if (BSLUtils.isNotNull(tyNfPersonalDetailsDTO.getCpRequestMasters().get(i).getPolicyNo()))
						cpServiceRequestMasterTypeUser
								.setPolicyNo(tyNfPersonalDetailsDTO.getCpRequestMasters().get(i).getPolicyNo());
					if (BSLUtils.isNotNull(tyNfPersonalDetailsDTO.getCpRequestMasters().get(i).getUserId()))
						cpServiceRequestMasterTypeUser
								.setUserid(tyNfPersonalDetailsDTO.getCpRequestMasters().get(i).getUserId());
					if (BSLUtils.isNotNull(tyNfPersonalDetailsDTO.getCpRequestMasters().get(i).getRequestStatus()))
						cpServiceRequestMasterTypeUser.setRequestStatus(
								tyNfPersonalDetailsDTO.getCpRequestMasters().get(i).getRequestStatus());
					if (BSLUtils.isNotNull(tyNfPersonalDetailsDTO.getCpRequestMasters().get(i).getProcessedDate())) {
						cpServiceRequestMasterTypeUser.setProcessedDate(getCalendarwithTypeDateTime(
								tyNfPersonalDetailsDTO.getCpRequestMasters().get(i).getProcessedDate()));
					}

					if (BSLUtils.isNotNull(tyNfPersonalDetailsDTO.getCpRequestMasters().get(i).getApplicable()))
						cpServiceRequestMasterTypeUser
								.setServiceFlag(tyNfPersonalDetailsDTO.getCpRequestMasters().get(i).getApplicable());
					if (BSLUtils.isNotNull(tyNfPersonalDetailsDTO.getCpRequestMasters().get(i).getProcessedBy()))
						cpServiceRequestMasterTypeUser
								.setProcessedBy(tyNfPersonalDetailsDTO.getCpRequestMasters().get(i).getProcessedBy());
					if (BSLUtils.isNotNull(tyNfPersonalDetailsDTO.getCpRequestMasters().get(i).getRecentUpdate())) {
						cpServiceRequestMasterTypeUser.setProcessedDate(getCalendarwithTypeDateTime(
								tyNfPersonalDetailsDTO.getCpRequestMasters().get(i).getRecentUpdate()));
					}
					cpServiceRequestMasterTypeUsers[i] = cpServiceRequestMasterTypeUser;
				}
				requestMasterColc.setArray(cpServiceRequestMasterTypeUsers);
			}
			if (BSLUtils.isNotNull(tyNfPersonalDetailsDTO.getCpNFPersonalDetails())) {
				cpNfPersonalDetailsTypeUsers = new CpNfPersonalDetailsTypeUser[tyNfPersonalDetailsDTO
						.getCpNFPersonalDetails().size()];
				for (int i = 0; i < tyNfPersonalDetailsDTO.getCpNFPersonalDetails().size(); i++) {

					CpNfPersonalDetailsTypeUser cpNfPersonalDetailsTypeUser = new CpNfPersonalDetailsTypeUser();
					if (BSLUtils.isNotNull(tyNfPersonalDetailsDTO.getCpNFPersonalDetails().get(i).getvChangeField()))
						cpNfPersonalDetailsTypeUser.setVChangeField(
								tyNfPersonalDetailsDTO.getCpNFPersonalDetails().get(i).getvChangeField());
					if (BSLUtils.isNotNull(tyNfPersonalDetailsDTO.getCpNFPersonalDetails().get(i).getnSerialNo()))
						cpNfPersonalDetailsTypeUser.setNSerialNo(BigDecimal
								.valueOf(tyNfPersonalDetailsDTO.getCpNFPersonalDetails().get(i).getnSerialNo()));
					if (BSLUtils.isNotNull(tyNfPersonalDetailsDTO.getCpNFPersonalDetails().get(i).getnServiceReqNo()))
						cpNfPersonalDetailsTypeUser.setNServiceReqNo(BigDecimal.valueOf(tyNfPersonalDetailsDTO
								.getCpNFPersonalDetails().get(i).getnServiceReqNo().getServiceRequestNo()));
					if (BSLUtils.isNotNull(tyNfPersonalDetailsDTO.getCpNFPersonalDetails().get(i).getvNewValue()))
						cpNfPersonalDetailsTypeUser
								.setVNewValue(tyNfPersonalDetailsDTO.getCpNFPersonalDetails().get(i).getvNewValue());
					if (BSLUtils.isNotNull(tyNfPersonalDetailsDTO.getCpNFPersonalDetails().get(i).getvOldValue()))
						cpNfPersonalDetailsTypeUser
								.setVOldValue(tyNfPersonalDetailsDTO.getCpNFPersonalDetails().get(i).getvOldValue());

					cpNfPersonalDetailsTypeUsers[i] = cpNfPersonalDetailsTypeUser;
				}
				cpNfPersonalDetailsColc.setArray(cpNfPersonalDetailsTypeUsers);
			}

			// Inserting Fatca details --------
			if (BSLUtils.isNotNull(tyNfPersonalDetailsDTO.getCpFatca())) {
				cpFatcaTableTypeUsers = new CpFatcaTableTypeUser[tyNfPersonalDetailsDTO.getCpFatca().size()];

				for (int i = 0; i < tyNfPersonalDetailsDTO.getCpFatca().size(); i++) {

					CpFatcaTableTypeUser cpFatcaTableTypeUser = new CpFatcaTableTypeUser();

					if (BSLUtils.isNotNull(tyNfPersonalDetailsDTO.getCpFatca().get(i).getSerialNo())) {
						cpFatcaTableTypeUser.setNSerialNo(
								BigDecimal.valueOf(tyNfPersonalDetailsDTO.getCpFatca().get(i).getSerialNo()));
					}

					if (BSLUtils.isNotNull(tyNfPersonalDetailsDTO.getCpFatca().get(i).getServiceRequestNo()))

						cpFatcaTableTypeUser.setServiceRequestNo(BigDecimal
								.valueOf(tyNfPersonalDetailsDTO.getCpRequestMasters().get(i).getServiceRequestNo()));

					if (BSLUtils.isNotNull(tyNfPersonalDetailsDTO.getCpFatca().get(i).getIsUsPerson()))
						cpFatcaTableTypeUser.setVUsPerson(tyNfPersonalDetailsDTO.getCpFatca().get(i).getIsUsPerson());

					if (BSLUtils.isNotNull(tyNfPersonalDetailsDTO.getCpFatca().get(i).getServiceName())) {
						cpFatcaTableTypeUser
								.setVServiceName(tyNfPersonalDetailsDTO.getCpFatca().get(i).getServiceName());
					}

					if (BSLUtils.isNotNull(tyNfPersonalDetailsDTO.getCpFatca().get(i).getResidentUAE())) {
						cpFatcaTableTypeUser
								.setVUaeResidency(tyNfPersonalDetailsDTO.getCpFatca().get(i).getResidentUAE());
					}

					if (BSLUtils.isNotNull(tyNfPersonalDetailsDTO.getCpFatca().get(i).getTinNo())) {
						cpFatcaTableTypeUser.setVTinNo(tyNfPersonalDetailsDTO.getCpFatca().get(i).getTinNo());
					}

					if (BSLUtils.isNotNull(tyNfPersonalDetailsDTO.getCpFatca().get(i).getTaxMoreThanOne())) {
						cpFatcaTableTypeUser
								.setVTaxMorethanOnecoun(tyNfPersonalDetailsDTO.getCpFatca().get(i).getTaxMoreThanOne());
					}

					CpFactaCountryDetTypeUser[] cpFactaCountryDetTypeUser = new CpFactaCountryDetTypeUser[10];
					if (tyNfPersonalDetailsDTO.getCpFatcaCountryDet() != null) {
						if (tyNfPersonalDetailsDTO.getCpFatcaCountryDet().size() > 0) {
							cpFactaCountryDetTypeUser = new CpFactaCountryDetTypeUser[tyNfPersonalDetailsDTO
									.getCpFatcaCountryDet().size()];
							for (int l = 0; l < tyNfPersonalDetailsDTO.getCpFatcaCountryDet().size(); l++) {
								CpFactaCountryDetTypeUser eachRecord = new CpFactaCountryDetTypeUser();

								// eachRecord.setNSeqNo(contributionHolidayDTO.getCpFatcaCountryDet().get(l).getSerialNo());
								eachRecord.setNSerialNo(
										BigDecimal.valueOf(tyNfPersonalDetailsDTO.getCpFatca().get(i).getSerialNo()));
								eachRecord.setVCountryJuridis(
										tyNfPersonalDetailsDTO.getCpFatcaCountryDet().get(l).getCountry());
								eachRecord.setVTinReason(
										tyNfPersonalDetailsDTO.getCpFatcaCountryDet().get(l).getTinOrReason());
								cpFactaCountryDetTypeUser[l] = eachRecord;
								cpFatcaCountryDetColc.setArray(cpFactaCountryDetTypeUser);
								cpFatcaTableTypeUser.setOcCountry(cpFatcaCountryDetColc);
							}
						}
					}
					cpFatcaTableTypeUsers[i] = cpFatcaTableTypeUser;
				}

			}
			if (cpFatcaTableTypeUsers.length > 0) {
				cpFatcaTableTypeUserArray = new CpFatcaTableTypeUserArray();
				cpFatcaTableTypeUserArray.setCpFatcaTableTypeUser(cpFatcaTableTypeUsers);
			}

			if (cpNfPersonalDetailsColc.getArray().length > 0 && requestMasterColc.getArray().length > 0) {
				tyNfPersonalDetailsUserArrays = new TyNfPersonalDetailsUser[1];
				TyNfPersonalDetailsUser tyNfPersonalDetailsUser = new TyNfPersonalDetailsUser();
				tyNfPersonalDetailsUser.setLcServiceColc(requestMasterColc);
				tyNfPersonalDetailsUser.setLcNfPersonalDtlsColc(cpNfPersonalDetailsColc);
				tyNfPersonalDetailsUserArrays[0] = tyNfPersonalDetailsUser;
				valuesArray.setTyNfPersonalDetailsUser(tyNfPersonalDetailsUserArrays);
			}
		}
		if (valuesArray.getTyNfPersonalDetailsUser().length > 0) {
			BfnNfPersonalDetails bfnNfPersonalDetails = new BfnNfPersonalDetails();
			bfnNfPersonalDetails.setPNfPersonalDetails(valuesArray);
			bfnNfPersonalDetails.setPFatca(cpFatcaTableTypeUserArray);
			BfnNfPersonalDetailsResponse response = getServiceProxy().bfnNfPersonalDetails(bfnNfPersonalDetails);
			CpExceptionErrorTypeUserArray cpExceptionErrorTypeUserList = response.getResult();
			CpExceptionErrorTypeUser[] cpExceptionErrorTypeUserArray = cpExceptionErrorTypeUserList
					.getCpExceptionErrorTypeUser();

			if (BSLUtils.isNotNull(cpExceptionErrorTypeUserArray)) {
				for (int i = 0; i < cpExceptionErrorTypeUserArray.length; i++) {
					CpExceptionErrorTypeUser cpExceptionErrorTypeUser = cpExceptionErrorTypeUserArray[i];
					if (cpExceptionErrorTypeUser.getErrSqlCode().equalsIgnoreCase("PASS")) {
						return "PASS";
					} else {
						for(int i1 =0;i1<tyNfPersonalDetailsDTO.getCpRequestMasters().size();i1++) {
							boolean status;
							CpLogsErros error = new CpLogsErros();
							error.setPolicyno(tyNfPersonalDetailsDTO.getCpRequestMasters().get(i1).getPolicyNo());
							error.setReqno(tyNfPersonalDetailsDTO.getCpRequestMasters().get(i1).getServiceRequestNo());
							error.setType(tyNfPersonalDetailsDTO.getCpRequestMasters().get(i1).getServiceRequestType());
							error.setUserid(tyNfPersonalDetailsDTO.getCpRequestMasters().get(i1).getUserId());
							error.setException(cpExceptionErrorTypeUser.getErrSqlErrDesc());
							status= cpLogsErrosBL.insertexception(error);
						}
						logger.info(cpExceptionErrorTypeUser.getErrSqlCode());
						logger.info(cpExceptionErrorTypeUser.getErrSqlErrDesc());
						logger.info(cpExceptionErrorTypeUser.getServiceRequestNo());
					}
				}
			
			}
		}
		return "FAIL";
	}

	@Override
	public String saveUpdateInformationAddressContact(TyNfAddressContactsDTO tyNfAddressContactsDTO) throws Exception {

		TyNfAddressContactsUserArray valuesArray = new TyNfAddressContactsUserArray();
		TyNfAddressContactsUser[] tyNfAddressContactsUserArrays = null;

		CpServiceRequestMasterColc requestMasterColc = new CpServiceRequestMasterColc();
		CpNfAddressContactsColc cpNfAddressContactsColc = new CpNfAddressContactsColc();
		CpNfAddressContactsTypeUser[] cpNfAddressContactsTypeUsers = null;
		CpServiceRequestMasterTypeUser[] cpServiceRequestMasterTypeUsers = null;

		CpFatcaTableTypeUser[] cpFatcaTableTypeUsers = null;
		CpFatcaTableTypeUserArray cpFatcaTableTypeUserArray = null;
		CpFatcaCountryDetColc cpFatcaCountryDetColc = new CpFatcaCountryDetColc();

		if (BSLUtils.isNotNull(tyNfAddressContactsDTO)) {
			if (BSLUtils.isNotNull(tyNfAddressContactsDTO.getCpRequestMasters())) {
				cpServiceRequestMasterTypeUsers = new CpServiceRequestMasterTypeUser[tyNfAddressContactsDTO
						.getCpRequestMasters().size()];
				for (int i = 0; i < tyNfAddressContactsDTO.getCpRequestMasters().size(); i++) {

					CpServiceRequestMasterTypeUser cpServiceRequestMasterTypeUser = new CpServiceRequestMasterTypeUser();
					cpServiceRequestMasterTypeUser.setServiceRequestNo(BigDecimal
							.valueOf(tyNfAddressContactsDTO.getCpRequestMasters().get(i).getServiceRequestNo()));
				
					if (BSLUtils.isNotNull(tyNfAddressContactsDTO.getCpRequestMasters().get(i).getSeqno()))
						cpServiceRequestMasterTypeUser
								.setSeqNo(tyNfAddressContactsDTO.getCpRequestMasters().get(i).getSeqno());
					
					
					if (BSLUtils
							.isNotNull(tyNfAddressContactsDTO.getCpRequestMasters().get(i).getServiceRequestDate())) {
						cpServiceRequestMasterTypeUser.setServiceRequestDate(getCalendarwithTypeDateTime(
								tyNfAddressContactsDTO.getCpRequestMasters().get(i).getServiceRequestDate()));
					}
					if (BSLUtils
							.isNotNull(tyNfAddressContactsDTO.getCpRequestMasters().get(i).getServiceRequestType())) {
						if (tyNfAddressContactsDTO.getCpRequestMasters().get(i).getServiceRequestType()
								.equalsIgnoreCase("updateInformation"))
							cpServiceRequestMasterTypeUser.setServiceRequestType("NONFN");
						else {
							cpServiceRequestMasterTypeUser.setServiceRequestType("NONFN");
						}
					}
					if (BSLUtils.isNotNull(tyNfAddressContactsDTO.getCpRequestMasters().get(i).getPolicyNo()))
						cpServiceRequestMasterTypeUser
								.setPolicyNo(tyNfAddressContactsDTO.getCpRequestMasters().get(i).getPolicyNo());
					if (BSLUtils.isNotNull(tyNfAddressContactsDTO.getCpRequestMasters().get(i).getUserId()))
						cpServiceRequestMasterTypeUser
								.setUserid(tyNfAddressContactsDTO.getCpRequestMasters().get(i).getUserId());
					if (BSLUtils.isNotNull(tyNfAddressContactsDTO.getCpRequestMasters().get(i).getRequestStatus()))
						cpServiceRequestMasterTypeUser.setRequestStatus(
								tyNfAddressContactsDTO.getCpRequestMasters().get(i).getRequestStatus());
					if (BSLUtils.isNotNull(tyNfAddressContactsDTO.getCpRequestMasters().get(i).getProcessedDate())) {
						cpServiceRequestMasterTypeUser.setProcessedDate(getCalendarwithTypeDateTime(
								tyNfAddressContactsDTO.getCpRequestMasters().get(i).getProcessedDate()));
					}
					if (BSLUtils.isNotNull(tyNfAddressContactsDTO.getCpRequestMasters().get(i).getProcessedBy()))
						cpServiceRequestMasterTypeUser
								.setProcessedBy(tyNfAddressContactsDTO.getCpRequestMasters().get(i).getProcessedBy());

					if (BSLUtils.isNotNull(tyNfAddressContactsDTO.getCpRequestMasters().get(i).getApplicable()))
						cpServiceRequestMasterTypeUser
								.setServiceFlag(tyNfAddressContactsDTO.getCpRequestMasters().get(i).getApplicable());
					if (BSLUtils.isNotNull(tyNfAddressContactsDTO.getCpRequestMasters().get(i).getRecentUpdate())) {
						cpServiceRequestMasterTypeUser.setProcessedDate(getCalendarwithTypeDateTime(
								tyNfAddressContactsDTO.getCpRequestMasters().get(i).getRecentUpdate()));
					}
					cpServiceRequestMasterTypeUsers[i] = cpServiceRequestMasterTypeUser;
				}
				requestMasterColc.setArray(cpServiceRequestMasterTypeUsers);
			}
			if (BSLUtils.isNotNull(tyNfAddressContactsDTO.getCpNFAddressContacts())) {
				cpNfAddressContactsTypeUsers = new CpNfAddressContactsTypeUser[tyNfAddressContactsDTO
						.getCpNFAddressContacts().size()];
				for (int i = 0; i < tyNfAddressContactsDTO.getCpNFAddressContacts().size(); i++) {

					CpNfAddressContactsTypeUser cpNfAddressContactsTypeUser = new CpNfAddressContactsTypeUser();
					if (BSLUtils.isNotNull(tyNfAddressContactsDTO.getCpNFAddressContacts().get(i).getvContactDesc()))
						cpNfAddressContactsTypeUser.setVContactDesc(
								tyNfAddressContactsDTO.getCpNFAddressContacts().get(i).getvContactDesc());
					if (BSLUtils.isNotNull(tyNfAddressContactsDTO.getCpNFAddressContacts().get(i).getvContactCode()))
						cpNfAddressContactsTypeUser.setVContactCode(
								tyNfAddressContactsDTO.getCpNFAddressContacts().get(i).getvContactCode());
					if (BSLUtils.isNotNull(tyNfAddressContactsDTO.getCpNFAddressContacts().get(i).getnSerialNo()))
						cpNfAddressContactsTypeUser
								.setNAddrSeqNo(tyNfAddressContactsDTO.getCpNFAddressContacts().get(i).getnAddrSeqNo());
					if (BSLUtils.isNotNull(tyNfAddressContactsDTO.getCpNFAddressContacts().get(i).getnServiceReqNo()))
						cpNfAddressContactsTypeUser.setNServiceReqNo(BigDecimal.valueOf(tyNfAddressContactsDTO
								.getCpNFAddressContacts().get(i).getnServiceReqNo().getServiceRequestNo()));
					if (BSLUtils.isNotNull(tyNfAddressContactsDTO.getCpNFAddressContacts().get(i).getvNewValue()))
						cpNfAddressContactsTypeUser
								.setVNewValue(tyNfAddressContactsDTO.getCpNFAddressContacts().get(i).getvNewValue());
					if (BSLUtils.isNotNull(tyNfAddressContactsDTO.getCpNFAddressContacts().get(i).getvOldValue()))
						cpNfAddressContactsTypeUser
								.setVOldValue(tyNfAddressContactsDTO.getCpNFAddressContacts().get(i).getvOldValue());
					cpNfAddressContactsTypeUsers[i] = cpNfAddressContactsTypeUser;
				}
				cpNfAddressContactsColc.setArray(cpNfAddressContactsTypeUsers);
			}

			// Inserting Fatca details --------
			if (BSLUtils.isNotNull(tyNfAddressContactsDTO.getCpFatca())) {
				cpFatcaTableTypeUsers = new CpFatcaTableTypeUser[tyNfAddressContactsDTO.getCpFatca().size()];

				for (int i = 0; i < tyNfAddressContactsDTO.getCpFatca().size(); i++) {

					CpFatcaTableTypeUser cpFatcaTableTypeUser = new CpFatcaTableTypeUser();

					if (BSLUtils.isNotNull(tyNfAddressContactsDTO.getCpFatca().get(i).getSerialNo())) {
						cpFatcaTableTypeUser.setNSerialNo(
								BigDecimal.valueOf(tyNfAddressContactsDTO.getCpFatca().get(i).getSerialNo()));
					}

					if (BSLUtils.isNotNull(tyNfAddressContactsDTO.getCpFatca().get(i).getServiceRequestNo()))

						cpFatcaTableTypeUser.setServiceRequestNo(BigDecimal
								.valueOf(tyNfAddressContactsDTO.getCpRequestMasters().get(i).getServiceRequestNo()));

					if (BSLUtils.isNotNull(tyNfAddressContactsDTO.getCpFatca().get(i).getIsUsPerson()))
						cpFatcaTableTypeUser.setVUsPerson(tyNfAddressContactsDTO.getCpFatca().get(i).getIsUsPerson());

					if (BSLUtils.isNotNull(tyNfAddressContactsDTO.getCpFatca().get(i).getServiceName())) {
						cpFatcaTableTypeUser
								.setVServiceName(tyNfAddressContactsDTO.getCpFatca().get(i).getServiceName());
					}

					if (BSLUtils.isNotNull(tyNfAddressContactsDTO.getCpFatca().get(i).getResidentUAE())) {
						cpFatcaTableTypeUser
								.setVUaeResidency(tyNfAddressContactsDTO.getCpFatca().get(i).getResidentUAE());
					}

					if (BSLUtils.isNotNull(tyNfAddressContactsDTO.getCpFatca().get(i).getTinNo())) {
						cpFatcaTableTypeUser.setVTinNo(tyNfAddressContactsDTO.getCpFatca().get(i).getTinNo());
					}

					if (BSLUtils.isNotNull(tyNfAddressContactsDTO.getCpFatca().get(i).getTaxMoreThanOne())) {
						cpFatcaTableTypeUser
								.setVTaxMorethanOnecoun(tyNfAddressContactsDTO.getCpFatca().get(i).getTaxMoreThanOne());
					}

					CpFactaCountryDetTypeUser[] cpFactaCountryDetTypeUser = new CpFactaCountryDetTypeUser[10];
					if (tyNfAddressContactsDTO.getCpFatcaCountryDet() != null) {
						if (tyNfAddressContactsDTO.getCpFatcaCountryDet().size() > 0) {
							cpFactaCountryDetTypeUser = new CpFactaCountryDetTypeUser[tyNfAddressContactsDTO
									.getCpFatcaCountryDet().size()];
							for (int l = 0; l < tyNfAddressContactsDTO.getCpFatcaCountryDet().size(); l++) {
								CpFactaCountryDetTypeUser eachRecord = new CpFactaCountryDetTypeUser();

								// eachRecord.setNSeqNo(contributionHolidayDTO.getCpFatcaCountryDet().get(l).getSerialNo());
								eachRecord.setNSerialNo(
										BigDecimal.valueOf(tyNfAddressContactsDTO.getCpFatca().get(i).getSerialNo()));
								eachRecord.setVCountryJuridis(
										tyNfAddressContactsDTO.getCpFatcaCountryDet().get(l).getCountry());
								eachRecord.setVTinReason(
										tyNfAddressContactsDTO.getCpFatcaCountryDet().get(l).getTinOrReason());
								cpFactaCountryDetTypeUser[l] = eachRecord;
								cpFatcaCountryDetColc.setArray(cpFactaCountryDetTypeUser);
								cpFatcaTableTypeUser.setOcCountry(cpFatcaCountryDetColc);
							}
						}
					}
					cpFatcaTableTypeUsers[i] = cpFatcaTableTypeUser;
				}

			}
			if (cpFatcaTableTypeUsers.length > 0) {
				cpFatcaTableTypeUserArray = new CpFatcaTableTypeUserArray();
				cpFatcaTableTypeUserArray.setCpFatcaTableTypeUser(cpFatcaTableTypeUsers);
			}

			if (cpNfAddressContactsColc.getArray().length > 0 && requestMasterColc.getArray().length > 0) {
				tyNfAddressContactsUserArrays = new TyNfAddressContactsUser[1];
				TyNfAddressContactsUser tyNfAddressContactsUser = new TyNfAddressContactsUser();
				tyNfAddressContactsUser.setLcServiceColc(requestMasterColc);
				tyNfAddressContactsUser.setLcNfAddressContactsColc(cpNfAddressContactsColc);
				tyNfAddressContactsUserArrays[0] = tyNfAddressContactsUser;
				valuesArray.setTyNfAddressContactsUser(tyNfAddressContactsUserArrays);
			}
		}
		if (valuesArray.getTyNfAddressContactsUser().length > 0) {
			BfnNfAddressContacts bfnNfAddressContacts = new BfnNfAddressContacts();
			bfnNfAddressContacts.setPNfAddressContacts(valuesArray);
			bfnNfAddressContacts.setPFatca(cpFatcaTableTypeUserArray);
			BfnNfAddressContactsResponse response = getServiceProxy().bfnNfAddressContacts(bfnNfAddressContacts);
			CpExceptionErrorTypeUserArray cpExceptionErrorTypeUserList = response.getResult();
			CpExceptionErrorTypeUser[] cpExceptionErrorTypeUserArray = cpExceptionErrorTypeUserList
					.getCpExceptionErrorTypeUser();

			if (BSLUtils.isNotNull(cpExceptionErrorTypeUserArray)) {
				for (int i = 0; i < cpExceptionErrorTypeUserArray.length; i++) {
					CpExceptionErrorTypeUser cpExceptionErrorTypeUser = cpExceptionErrorTypeUserArray[i];
					if (cpExceptionErrorTypeUser.getErrSqlCode().equalsIgnoreCase("PASS")) {
						logger.info("Address Contact transaction status:::::::: "
								+ cpExceptionErrorTypeUser.getErrSqlCode());
						return "PASS";
					} else {
						for(int i1 =0;i1<tyNfAddressContactsDTO.getCpRequestMasters().size();i1++) {
							boolean status;
							CpLogsErros error = new CpLogsErros();
							error.setPolicyno(tyNfAddressContactsDTO.getCpRequestMasters().get(i1).getPolicyNo());
							error.setReqno(tyNfAddressContactsDTO.getCpRequestMasters().get(i1).getServiceRequestNo());
							error.setType(tyNfAddressContactsDTO.getCpRequestMasters().get(i1).getServiceRequestType());
							error.setUserid(tyNfAddressContactsDTO.getCpRequestMasters().get(i1).getUserId());
							error.setException(cpExceptionErrorTypeUser.getErrSqlErrDesc());
							status= cpLogsErrosBL.insertexception(error);
						}
						
						logger.info("Address Contact transaction status::::::: "
								+ cpExceptionErrorTypeUser.getErrSqlCode());
						logger.info("Address contact Transaction error code::::::::::::: "
								+ cpExceptionErrorTypeUser.getErrSqlCode());
						logger.info("Address contact Transaction error Description:::::::::::: "
								+ cpExceptionErrorTypeUser.getErrSqlErrDesc());
						logger.info("Address contact Transaction service Request no:::::::::::::: "
								+ cpExceptionErrorTypeUser.getServiceRequestNo());
					}
				}
			
			}
		}
		return "FAIL";
	}

	@Override
	public String saveAddressDetails(TyNfAddressDTO tyNfAddressDTO) throws Exception {

		TyNfAddressUserArray valuesArray = new TyNfAddressUserArray();
		TyNfAddressUser[] tyNFAddressUserArrays = null;

		CpServiceRequestMasterColc requestMasterColc = new CpServiceRequestMasterColc();
		CpNfAddressColc nfAddressColc = new CpNfAddressColc();
		CpNfAddressTypeUser[] cpNFAddressTypeUsers = null;
		CpServiceRequestMasterTypeUser[] cpServiceRequestMasterTypeUsers = null;

		CpFatcaTableTypeUser[] cpFatcaTableTypeUsers = null;
		CpFatcaTableTypeUserArray cpFatcaTableTypeUserArray = null;
		CpFatcaCountryDetColc cpFatcaCountryDetColc = new CpFatcaCountryDetColc();

		if (BSLUtils.isNotNull(tyNfAddressDTO)) {
			if (BSLUtils.isNotNull(tyNfAddressDTO.getCpRequestMasters())) {
				cpServiceRequestMasterTypeUsers = new CpServiceRequestMasterTypeUser[tyNfAddressDTO
						.getCpRequestMasters().size()];
				for (int i = 0; i < tyNfAddressDTO.getCpRequestMasters().size(); i++) {
					logger.info("Transaction Update Address Contact size :" + tyNfAddressDTO.getCpNFAddress().size());

					CpServiceRequestMasterTypeUser cpServiceRequestMasterTypeUser = new CpServiceRequestMasterTypeUser();
					cpServiceRequestMasterTypeUser.setServiceRequestNo(
							BigDecimal.valueOf(tyNfAddressDTO.getCpRequestMasters().get(i).getServiceRequestNo()));
					
					if (BSLUtils.isNotNull(tyNfAddressDTO.getCpRequestMasters().get(i).getSeqno()))
						cpServiceRequestMasterTypeUser
								.setSeqNo(tyNfAddressDTO.getCpRequestMasters().get(i).getSeqno());
					
					
					if (BSLUtils.isNotNull(tyNfAddressDTO.getCpRequestMasters().get(i).getServiceRequestDate())) {
						cpServiceRequestMasterTypeUser.setServiceRequestDate(getCalendarwithTypeDateTime(
								tyNfAddressDTO.getCpRequestMasters().get(i).getServiceRequestDate()));
					}
					if (BSLUtils.isNotNull(tyNfAddressDTO.getCpRequestMasters().get(i).getServiceRequestType())) {
						if (tyNfAddressDTO.getCpRequestMasters().get(i).getServiceRequestType()
								.equalsIgnoreCase("updateInformation"))
							cpServiceRequestMasterTypeUser.setServiceRequestType("NONFN");
						else
							cpServiceRequestMasterTypeUser.setServiceRequestType("NONFN");
					}
					if (BSLUtils.isNotNull(tyNfAddressDTO.getCpRequestMasters().get(i).getApplicable()))
						cpServiceRequestMasterTypeUser
								.setServiceFlag(tyNfAddressDTO.getCpRequestMasters().get(i).getApplicable());
					if (BSLUtils.isNotNull(tyNfAddressDTO.getCpRequestMasters().get(i).getPolicyNo()))
						cpServiceRequestMasterTypeUser
								.setPolicyNo(tyNfAddressDTO.getCpRequestMasters().get(i).getPolicyNo());
					if (BSLUtils.isNotNull(tyNfAddressDTO.getCpRequestMasters().get(i).getUserId()))
						cpServiceRequestMasterTypeUser
								.setUserid(tyNfAddressDTO.getCpRequestMasters().get(i).getUserId());
					if (BSLUtils.isNotNull(tyNfAddressDTO.getCpRequestMasters().get(i).getRequestStatus()))
						cpServiceRequestMasterTypeUser
								.setRequestStatus(tyNfAddressDTO.getCpRequestMasters().get(i).getRequestStatus());
					if (BSLUtils.isNotNull(tyNfAddressDTO.getCpRequestMasters().get(i).getProcessedDate())) {
						cpServiceRequestMasterTypeUser.setProcessedDate(getCalendarwithTypeDateTime(
								tyNfAddressDTO.getCpRequestMasters().get(i).getProcessedDate()));
					}
					if (BSLUtils.isNotNull(tyNfAddressDTO.getCpRequestMasters().get(i).getProcessedBy()))
						cpServiceRequestMasterTypeUser
								.setProcessedBy(tyNfAddressDTO.getCpRequestMasters().get(i).getProcessedBy());
					if (BSLUtils.isNotNull(tyNfAddressDTO.getCpRequestMasters().get(i).getRecentUpdate())) {
						cpServiceRequestMasterTypeUser.setProcessedDate(getCalendarwithTypeDateTime(
								tyNfAddressDTO.getCpRequestMasters().get(i).getRecentUpdate()));
					}
					cpServiceRequestMasterTypeUsers[i] = cpServiceRequestMasterTypeUser;
				}
				requestMasterColc.setArray(cpServiceRequestMasterTypeUsers);
			}

			if (BSLUtils.isNotNull(tyNfAddressDTO.getCpNFAddress())) {
				cpNFAddressTypeUsers = new CpNfAddressTypeUser[tyNfAddressDTO.getCpNFAddress().size()];
				for (int i = 0; i < tyNfAddressDTO.getCpNFAddress().size(); i++) {
					logger.info("Transaction Update Address size :" + tyNfAddressDTO.getCpNFAddress().size());

					CpNfAddressTypeUser cpNfAddressTypeUser = new CpNfAddressTypeUser();
					cpNfAddressTypeUser
							.setNSerialNo(BigDecimal.valueOf(tyNfAddressDTO.getCpNFAddress().get(i).getnSerialNo()));
					if (BSLUtils.isNotNull(tyNfAddressDTO.getCpNFAddress().get(i).getvAddr1()))
						cpNfAddressTypeUser.setVAddr1(tyNfAddressDTO.getCpNFAddress().get(i).getvAddr1());

					if (BSLUtils.isNotNull(tyNfAddressDTO.getCpNFAddress().get(i).getvAddr2()))
						cpNfAddressTypeUser.setVAddr2(tyNfAddressDTO.getCpNFAddress().get(i).getvAddr2());

					if (BSLUtils.isNotNull(tyNfAddressDTO.getCpNFAddress().get(i).getvAddr3()))
						cpNfAddressTypeUser.setVAddr3(tyNfAddressDTO.getCpNFAddress().get(i).getvAddr3());

					if (BSLUtils.isNotNull(tyNfAddressDTO.getCpNFAddress().get(i).getvState()))
						cpNfAddressTypeUser.setVState(tyNfAddressDTO.getCpNFAddress().get(i).getvState());

					if (BSLUtils.isNotNull(tyNfAddressDTO.getCpNFAddress().get(i).getvStateCode()))
						cpNfAddressTypeUser.setVStateCode(tyNfAddressDTO.getCpNFAddress().get(i).getvStateCode());

					if (BSLUtils.isNotNull(tyNfAddressDTO.getCpNFAddress().get(i).getvCountry()))
						cpNfAddressTypeUser.setVCountry(tyNfAddressDTO.getCpNFAddress().get(i).getvCountry());

					if (BSLUtils.isNotNull(tyNfAddressDTO.getCpNFAddress().get(i).getvCountryCode()))
						cpNfAddressTypeUser.setVCountryCode(tyNfAddressDTO.getCpNFAddress().get(i).getvCountryCode());

					if (BSLUtils.isNotNull(tyNfAddressDTO.getCpNFAddress().get(i).getvPOBoxNo()))
						cpNfAddressTypeUser.setVPoBoxNo(tyNfAddressDTO.getCpNFAddress().get(i).getvPOBoxNo());

					if (BSLUtils.isNotNull(tyNfAddressDTO.getCpNFAddress().get(i).getvAddrType()))
						cpNfAddressTypeUser.setVAddrType(tyNfAddressDTO.getCpNFAddress().get(i).getvAddrType());

					if (BSLUtils.isNotNull(tyNfAddressDTO.getCpNFAddress().get(i).getvAddrTypeCode()))
						cpNfAddressTypeUser.setVAddrTypeCode(tyNfAddressDTO.getCpNFAddress().get(i).getvAddrTypeCode());

					if (BSLUtils.isNotNull(tyNfAddressDTO.getCpNFAddress().get(i).getvAddrSeqNo()))
						cpNfAddressTypeUser.setVAddrSeqNo(tyNfAddressDTO.getCpNFAddress().get(i).getvAddrSeqNo());

					cpNFAddressTypeUsers[i] = cpNfAddressTypeUser;
				}
				nfAddressColc.setArray(cpNFAddressTypeUsers);
			}

			// Inserting Fatca details --------
			if (BSLUtils.isNotNull(tyNfAddressDTO.getCpFatca())) {
				cpFatcaTableTypeUsers = new CpFatcaTableTypeUser[tyNfAddressDTO.getCpFatca().size()];

				for (int i = 0; i < tyNfAddressDTO.getCpFatca().size(); i++) {

					CpFatcaTableTypeUser cpFatcaTableTypeUser = new CpFatcaTableTypeUser();

					if (BSLUtils.isNotNull(tyNfAddressDTO.getCpFatca().get(i).getSerialNo())) {
						cpFatcaTableTypeUser
								.setNSerialNo(BigDecimal.valueOf(tyNfAddressDTO.getCpFatca().get(i).getSerialNo()));
					}

					if (BSLUtils.isNotNull(tyNfAddressDTO.getCpFatca().get(i).getServiceRequestNo()))

						cpFatcaTableTypeUser.setServiceRequestNo(
								BigDecimal.valueOf(tyNfAddressDTO.getCpRequestMasters().get(i).getServiceRequestNo()));

					if (BSLUtils.isNotNull(tyNfAddressDTO.getCpFatca().get(i).getIsUsPerson()))
						cpFatcaTableTypeUser.setVUsPerson(tyNfAddressDTO.getCpFatca().get(i).getIsUsPerson());

					if (BSLUtils.isNotNull(tyNfAddressDTO.getCpFatca().get(i).getServiceName())) {
						cpFatcaTableTypeUser.setVServiceName(tyNfAddressDTO.getCpFatca().get(i).getServiceName());
					}

					if (BSLUtils.isNotNull(tyNfAddressDTO.getCpFatca().get(i).getResidentUAE())) {
						cpFatcaTableTypeUser.setVUaeResidency(tyNfAddressDTO.getCpFatca().get(i).getResidentUAE());
					}

					if (BSLUtils.isNotNull(tyNfAddressDTO.getCpFatca().get(i).getTinNo())) {
						cpFatcaTableTypeUser.setVTinNo(tyNfAddressDTO.getCpFatca().get(i).getTinNo());
					}

					if (BSLUtils.isNotNull(tyNfAddressDTO.getCpFatca().get(i).getTaxMoreThanOne())) {
						cpFatcaTableTypeUser
								.setVTaxMorethanOnecoun(tyNfAddressDTO.getCpFatca().get(i).getTaxMoreThanOne());
					}

					CpFactaCountryDetTypeUser[] cpFactaCountryDetTypeUser = new CpFactaCountryDetTypeUser[10];
					if (tyNfAddressDTO.getCpFatcaCountryDet() != null) {
						if (tyNfAddressDTO.getCpFatcaCountryDet().size() > 0) {
							cpFactaCountryDetTypeUser = new CpFactaCountryDetTypeUser[tyNfAddressDTO
									.getCpFatcaCountryDet().size()];
							for (int l = 0; l < tyNfAddressDTO.getCpFatcaCountryDet().size(); l++) {
								CpFactaCountryDetTypeUser eachRecord = new CpFactaCountryDetTypeUser();

								// eachRecord.setNSeqNo(contributionHolidayDTO.getCpFatcaCountryDet().get(l).getSerialNo());
								eachRecord.setNSerialNo(
										BigDecimal.valueOf(tyNfAddressDTO.getCpFatca().get(i).getSerialNo()));
								eachRecord
										.setVCountryJuridis(tyNfAddressDTO.getCpFatcaCountryDet().get(l).getCountry());
								eachRecord.setVTinReason(tyNfAddressDTO.getCpFatcaCountryDet().get(l).getTinOrReason());
								cpFactaCountryDetTypeUser[l] = eachRecord;
								cpFatcaCountryDetColc.setArray(cpFactaCountryDetTypeUser);
								cpFatcaTableTypeUser.setOcCountry(cpFatcaCountryDetColc);
							}
						}
					}
					cpFatcaTableTypeUsers[i] = cpFatcaTableTypeUser;
				}

			}
			if (cpFatcaTableTypeUsers.length > 0) {
				cpFatcaTableTypeUserArray = new CpFatcaTableTypeUserArray();
				cpFatcaTableTypeUserArray.setCpFatcaTableTypeUser(cpFatcaTableTypeUsers);
			}

			if (nfAddressColc.getArray().length > 0 && requestMasterColc.getArray().length > 0) {
				tyNFAddressUserArrays = new TyNfAddressUser[1];
				TyNfAddressUser tyNfAddressUser = new TyNfAddressUser();
				tyNfAddressUser.setLcServiceColc(requestMasterColc);
				tyNfAddressUser.setLcNfAddressColc(nfAddressColc);
				tyNFAddressUserArrays[0] = tyNfAddressUser;
				valuesArray.setTyNfAddressUser(tyNFAddressUserArrays);
			}

			logger.info("Saving Transaction :Transaction DTO size :" + valuesArray.getTyNfAddressUser().length);
			if (valuesArray.getTyNfAddressUser().length > 0) {
				BfnNfAddress bfnNfAddress = new BfnNfAddress();
				bfnNfAddress.setPNfAddress(valuesArray);
				bfnNfAddress.setPFatca(cpFatcaTableTypeUserArray);
				BfnNfAddressResponse response = getServiceProxy().bfnNfAddress(bfnNfAddress);
				CpExceptionErrorTypeUserArray cpExceptionErrorTypeUserList = response.getResult();
				CpExceptionErrorTypeUser[] cpExceptionErrorTypeUserArray = cpExceptionErrorTypeUserList
						.getCpExceptionErrorTypeUser();

				if (BSLUtils.isNotNull(cpExceptionErrorTypeUserArray)) {
					for (int i = 0; i < cpExceptionErrorTypeUserArray.length; i++) {
						CpExceptionErrorTypeUser cpExceptionErrorTypeUser = cpExceptionErrorTypeUserArray[i];
						if (cpExceptionErrorTypeUser.getErrSqlCode().equalsIgnoreCase("PASS")) {
							logger.info("Update Address transaction status:::::::::: "
									+ cpExceptionErrorTypeUser.getErrSqlCode());
							return "PASS";
						} else {
								for(int i1 =0;i1<tyNfAddressDTO.getCpRequestMasters().size();i1++) {
									boolean status;
									CpLogsErros error = new CpLogsErros();
									error.setPolicyno(tyNfAddressDTO.getCpRequestMasters().get(i1).getPolicyNo());
									error.setReqno(tyNfAddressDTO.getCpRequestMasters().get(i1).getServiceRequestNo());
									error.setType(tyNfAddressDTO.getCpRequestMasters().get(i1).getServiceRequestType());
									error.setUserid(tyNfAddressDTO.getCpRequestMasters().get(i1).getUserId());
									error.setException(cpExceptionErrorTypeUser.getErrSqlErrDesc());
									status= cpLogsErrosBL.insertexception(error);
								}	
									
							logger.info(
									"Update Address status::::::::::::: " + cpExceptionErrorTypeUser.getErrSqlCode());
							logger.info(
									"Transaction error code::::::::::: " + cpExceptionErrorTypeUser.getErrSqlCode());
							logger.info("Transaction error Description::::::::::::: "
									+ cpExceptionErrorTypeUser.getErrSqlErrDesc());
							logger.info("Transaction service Request no:::::::::::::::: "
									+ cpExceptionErrorTypeUser.getServiceRequestNo());
						}
					}
				
				}

			}
		}
		return "FAIL";
	}

	@Override
	public String saveProtectionBenifit(TyProtectionBenifitDTO tyProtectionBenifitDTO) throws Exception {

        try {
		TyProductionBenefitUserArray valueArray = new TyProductionBenefitUserArray();
		TyProductionBenefitUser[] tyProductionBenefitUserArray;
		CpServiceRequestMasterColc requestMasterColc = new CpServiceRequestMasterColc();
		CpProductionBenefitColc productionBenefitColc = new CpProductionBenefitColc();
		CpProductionBenefitTypeUser[] cpProductionBenefitTypeUser = null;
		CpServiceRequestMasterTypeUser[] cpServiceRequestMasterTypeUsers = null;

		CpFatcaTableTypeUser[] cpFatcaTableTypeUsers = null;
		CpFatcaTableTypeUserArray cpFatcaTableTypeUserArray = null;
		CpFatcaCountryDetColc cpFatcaCountryDetColc = new CpFatcaCountryDetColc();

		if (BSLUtils.isNotNull(tyProtectionBenifitDTO)) {
			if (BSLUtils.isNotNull(tyProtectionBenifitDTO.getCpRequestMasters())) {
				cpServiceRequestMasterTypeUsers = new CpServiceRequestMasterTypeUser[tyProtectionBenifitDTO
						.getCpRequestMasters().size()];
				for (int i = 0; i < tyProtectionBenifitDTO.getCpRequestMasters().size(); i++) {
					logger.info("Transaction service request  size :"
							+ tyProtectionBenifitDTO.getCpRequestMasters().size());
					CpServiceRequestMasterTypeUser cpServiceRequestMasterTypeUser = new CpServiceRequestMasterTypeUser();
					cpServiceRequestMasterTypeUser.setServiceRequestNo(BigDecimal
							.valueOf(tyProtectionBenifitDTO.getCpRequestMasters().get(i).getServiceRequestNo()));
					
					if (BSLUtils.isNotNull(tyProtectionBenifitDTO.getCpRequestMasters().get(i).getSeqno()))
						cpServiceRequestMasterTypeUser
								.setSeqNo(tyProtectionBenifitDTO.getCpRequestMasters().get(i).getSeqno());
					
					if (BSLUtils
							.isNotNull(tyProtectionBenifitDTO.getCpRequestMasters().get(i).getServiceRequestDate())) {
						cpServiceRequestMasterTypeUser.setServiceRequestDate(getCalendarwithTypeDateTime(
								tyProtectionBenifitDTO.getCpRequestMasters().get(i).getServiceRequestDate()));
					}
					
					if (BSLUtils
							.isNotNull(tyProtectionBenifitDTO.getCpProtectionBenifit().get(i).getAlterationType())) {
						String alterationType = tyProtectionBenifitDTO.getCpProtectionBenifit().get(i)
								.getAlterationType().trim();
						
						switch(alterationType){  
				        case "Increase in Basic Sum Covered": cpServiceRequestMasterTypeUser.setServiceRequestType("AL03"); break;  
				        case "Decrease in Basic Sum Covered": cpServiceRequestMasterTypeUser.setServiceRequestType("AL123"); break; 
				        case "Increase in Rider Sum Covered": cpServiceRequestMasterTypeUser.setServiceRequestType("AL86"); break;           
				        case "Decrease in Rider Sum Covered": cpServiceRequestMasterTypeUser.setServiceRequestType("AL122"); break;      
				        case "Addition of Suplimentary Rider": cpServiceRequestMasterTypeUser.setServiceRequestType("AL26"); break; 
				        case "Deletion of Suplimentary Rider": cpServiceRequestMasterTypeUser.setServiceRequestType("AL27"); break; 
				     } 

					}
					
					if (BSLUtils.isNotNull(tyProtectionBenifitDTO.getCpRequestMasters().get(i).getPolicyNo()))
						cpServiceRequestMasterTypeUser
								.setPolicyNo(tyProtectionBenifitDTO.getCpRequestMasters().get(i).getPolicyNo());
					if (BSLUtils.isNotNull(tyProtectionBenifitDTO.getCpRequestMasters().get(i).getUserId()))
						cpServiceRequestMasterTypeUser
								.setUserid(tyProtectionBenifitDTO.getCpRequestMasters().get(i).getUserId());
					if (BSLUtils.isNotNull(tyProtectionBenifitDTO.getCpRequestMasters().get(i).getRequestStatus()))
						cpServiceRequestMasterTypeUser.setRequestStatus(
								tyProtectionBenifitDTO.getCpRequestMasters().get(i).getRequestStatus());
					if (BSLUtils.isNotNull(tyProtectionBenifitDTO.getCpRequestMasters().get(i).getProcessedDate())) {
						cpServiceRequestMasterTypeUser.setProcessedDate(getCalendarwithTypeDateTime(
								tyProtectionBenifitDTO.getCpRequestMasters().get(i).getProcessedDate()));
					}
					if (BSLUtils.isNotNull(tyProtectionBenifitDTO.getCpRequestMasters().get(i).getProcessedBy()))
						cpServiceRequestMasterTypeUser
								.setProcessedBy(tyProtectionBenifitDTO.getCpRequestMasters().get(i).getProcessedBy());
					if (BSLUtils.isNotNull(tyProtectionBenifitDTO.getCpRequestMasters().get(i).getRecentUpdate())) {
						cpServiceRequestMasterTypeUser.setProcessedDate(getCalendarwithTypeDateTime(
								tyProtectionBenifitDTO.getCpRequestMasters().get(i).getRecentUpdate()));
					}
					cpServiceRequestMasterTypeUsers[i] = cpServiceRequestMasterTypeUser;
				}
				requestMasterColc.setArray(cpServiceRequestMasterTypeUsers);
			}

			if (BSLUtils.isNotNull(tyProtectionBenifitDTO.getCpProtectionBenifit())) {
				cpProductionBenefitTypeUser = new CpProductionBenefitTypeUser[tyProtectionBenifitDTO
						.getCpProtectionBenifit().size()];
				for (int i = 0; i < tyProtectionBenifitDTO.getCpProtectionBenifit().size(); i++) {
					logger.info("Transaction Protection Benifit size :"
							+ tyProtectionBenifitDTO.getCpProtectionBenifit().size());
					CpProductionBenefitTypeUser cpProductionBenefit = new CpProductionBenefitTypeUser();

					cpProductionBenefit.setOnSerialNo(
							(BigDecimal.valueOf(tyProtectionBenifitDTO.getCpProtectionBenifit().get(i).getSerialNo())));

					if (BSLUtils.isNotNull(tyProtectionBenifitDTO.getCpProtectionBenifit().get(i).getPolicyNo()))
						cpProductionBenefit
								.setOvPolicyNo((tyProtectionBenifitDTO.getCpProtectionBenifit().get(i).getPolicyNo()));

					if (BSLUtils.isNotNull(tyProtectionBenifitDTO.getCpProtectionBenifit().get(i).getExistBenifit()))
						cpProductionBenefit.setOvDispalySa(
								(tyProtectionBenifitDTO.getCpProtectionBenifit().get(i).getExistBenifit()));

					if (BSLUtils.isNotNull(tyProtectionBenifitDTO.getCpProtectionBenifit().get(i).getBenifitType()))
						cpProductionBenefit.setOvRiderFlag(
								(tyProtectionBenifitDTO.getCpProtectionBenifit().get(i).getBenifitType()));

					if (BSLUtils.isNotNull(tyProtectionBenifitDTO.getCpProtectionBenifit().get(i).getNewvalue()))
						cpProductionBenefit
								.setOnRiderSa((tyProtectionBenifitDTO.getCpProtectionBenifit().get(i).getNewvalue()));

					if (BSLUtils.isNotNull(tyProtectionBenifitDTO.getCpProtectionBenifit().get(i).getRiderCode()))
						cpProductionBenefit.setOvRiderCode(
								(tyProtectionBenifitDTO.getCpProtectionBenifit().get(i).getRiderCode()));

					cpProductionBenefitTypeUser[i] = cpProductionBenefit;
				}
				productionBenefitColc.setArray(cpProductionBenefitTypeUser);

				// Inserting Fatca details --------
				if (BSLUtils.isNotNull(tyProtectionBenifitDTO.getCpFatca())) {
					cpFatcaTableTypeUsers = new CpFatcaTableTypeUser[tyProtectionBenifitDTO.getCpFatca().size()];

					for (int i = 0; i < tyProtectionBenifitDTO.getCpFatca().size(); i++) {

						CpFatcaTableTypeUser cpFatcaTableTypeUser = new CpFatcaTableTypeUser();

						if (BSLUtils.isNotNull(tyProtectionBenifitDTO.getCpFatca().get(i).getSerialNo())) {
							cpFatcaTableTypeUser.setNSerialNo(
									BigDecimal.valueOf(tyProtectionBenifitDTO.getCpFatca().get(i).getSerialNo()));
						}

						if (BSLUtils.isNotNull(tyProtectionBenifitDTO.getCpFatca().get(i).getServiceRequestNo()))

							cpFatcaTableTypeUser.setServiceRequestNo(BigDecimal.valueOf(
									tyProtectionBenifitDTO.getCpRequestMasters().get(i).getServiceRequestNo()));

						if (BSLUtils.isNotNull(tyProtectionBenifitDTO.getCpFatca().get(i).getIsUsPerson()))
							cpFatcaTableTypeUser
									.setVUsPerson(tyProtectionBenifitDTO.getCpFatca().get(i).getIsUsPerson());

						if (BSLUtils.isNotNull(tyProtectionBenifitDTO.getCpFatca().get(i).getServiceName())) {
							cpFatcaTableTypeUser
									.setVServiceName(tyProtectionBenifitDTO.getCpFatca().get(i).getServiceName());
						}

						if (BSLUtils.isNotNull(tyProtectionBenifitDTO.getCpFatca().get(i).getResidentUAE())) {
							cpFatcaTableTypeUser
									.setVUaeResidency(tyProtectionBenifitDTO.getCpFatca().get(i).getResidentUAE());
						}

						if (BSLUtils.isNotNull(tyProtectionBenifitDTO.getCpFatca().get(i).getTinNo())) {
							cpFatcaTableTypeUser.setVTinNo(tyProtectionBenifitDTO.getCpFatca().get(i).getTinNo());
						}

						if (BSLUtils.isNotNull(tyProtectionBenifitDTO.getCpFatca().get(i).getTaxMoreThanOne())) {
							cpFatcaTableTypeUser.setVTaxMorethanOnecoun(
									tyProtectionBenifitDTO.getCpFatca().get(i).getTaxMoreThanOne());
						}
						
						CpFactaCountryDetTypeUser[] cpFactaCountryDetTypeUser = new CpFactaCountryDetTypeUser[10];
						
						if (tyProtectionBenifitDTO.getCpFatcaCountryDet() != null) {
							if (tyProtectionBenifitDTO.getCpFatcaCountryDet().size() > 0) {
								cpFactaCountryDetTypeUser = new CpFactaCountryDetTypeUser[tyProtectionBenifitDTO
										.getCpFatcaCountryDet().size()];
								for (int l = 0; l < tyProtectionBenifitDTO.getCpFatcaCountryDet().size(); l++) {
									CpFactaCountryDetTypeUser eachRecord = new CpFactaCountryDetTypeUser();

									//eachRecord.setNSeqNo(tyProtectionBenifitDTO.getCpFatcaCountryDet().get(l).getSerialNo());
									eachRecord.setNSerialNo(BigDecimal
											.valueOf(tyProtectionBenifitDTO.getCpFatca().get(i).getSerialNo()));
									eachRecord.setVCountryJuridis(
											tyProtectionBenifitDTO.getCpFatcaCountryDet().get(l).getCountry());
									eachRecord.setVTinReason(
											tyProtectionBenifitDTO.getCpFatcaCountryDet().get(l).getTinOrReason());
									cpFactaCountryDetTypeUser[l] = eachRecord;
									cpFatcaCountryDetColc.setArray(cpFactaCountryDetTypeUser);
									cpFatcaTableTypeUser.setOcCountry(cpFatcaCountryDetColc);
								}
							}
						}
						cpFatcaTableTypeUsers[i] = cpFatcaTableTypeUser;
					}

				}
				
				if (BSLUtils.isNotNull(cpFatcaTableTypeUsers)) {
					cpFatcaTableTypeUserArray = new CpFatcaTableTypeUserArray();
					cpFatcaTableTypeUserArray.setCpFatcaTableTypeUser(cpFatcaTableTypeUsers);
				}

				if (productionBenefitColc.getArray().length > 0 && requestMasterColc.getArray().length > 0) {

					tyProductionBenefitUserArray = new TyProductionBenefitUser[1];
					TyProductionBenefitUser tyProductionBenefitUser = new TyProductionBenefitUser();
					tyProductionBenefitUser.setLcServiceColc(requestMasterColc);
					tyProductionBenefitUser.setLcProdBeneColc(productionBenefitColc);
					tyProductionBenefitUserArray[0] = tyProductionBenefitUser;
					valueArray.setTyProductionBenefitUser(tyProductionBenefitUserArray);
				}
			}
			logger.info("Saving Protection Benifit:Transaction DTO size :"
					+ valueArray.getTyProductionBenefitUser().length);
		}

		if (valueArray.getTyProductionBenefitUser().length > 0) {
			BfnProductionBenefit bfnProductionBenefit = new BfnProductionBenefit();
			bfnProductionBenefit.setPProdBene(valueArray);
			bfnProductionBenefit.setPFatca(cpFatcaTableTypeUserArray);
			BfnProductionBenefitResponse response = getServiceProxy().bfnProductionBenefit(bfnProductionBenefit);
			CpExceptionErrorTypeUserArray cpExceptionErrorTypeUserList = response.getResult();
			CpExceptionErrorTypeUser[] cpExceptionErrorTypeUserArray = cpExceptionErrorTypeUserList
					.getCpExceptionErrorTypeUser();

			if (BSLUtils.isNotNull(cpExceptionErrorTypeUserArray)) {

				for (int i = 0; i < cpExceptionErrorTypeUserArray.length; i++) {
					CpExceptionErrorTypeUser cpExceptionErrorTypeUser = cpExceptionErrorTypeUserArray[i];
					if (cpExceptionErrorTypeUser.getErrSqlCode().equalsIgnoreCase("PASS")) {
						return "PASS";
					} else {
						for(int i1 =0;i1<tyProtectionBenifitDTO.getCpRequestMasters().size();i1++) {
							boolean status;
							CpLogsErros error = new CpLogsErros();
							error.setPolicyno(tyProtectionBenifitDTO.getCpRequestMasters().get(i1).getPolicyNo());
							error.setReqno(tyProtectionBenifitDTO.getCpRequestMasters().get(i1).getServiceRequestNo());
							error.setType(tyProtectionBenifitDTO.getCpRequestMasters().get(i1).getServiceRequestType());
							error.setUserid(tyProtectionBenifitDTO.getCpRequestMasters().get(i1).getUserId());
							error.setException(cpExceptionErrorTypeUser.getErrSqlErrDesc());
							status= cpLogsErrosBL.insertexception(error);
						}
						logger.info(cpExceptionErrorTypeUser.getErrSqlCode());
						logger.info(cpExceptionErrorTypeUser.getErrSqlErrDesc());
						logger.info(cpExceptionErrorTypeUser.getServiceRequestNo());
						
					}
			
				}
			
			}
		}
     }catch(Exception e) {
    	 for(int i1 =0;i1<tyProtectionBenifitDTO.getCpRequestMasters().size();i1++) {
				boolean status;
				CpLogsErros error = new CpLogsErros();
				error.setPolicyno(tyProtectionBenifitDTO.getCpRequestMasters().get(i1).getPolicyNo());
				error.setReqno(tyProtectionBenifitDTO.getCpRequestMasters().get(i1).getServiceRequestNo());
				error.setType(tyProtectionBenifitDTO.getCpRequestMasters().get(i1).getServiceRequestType());
				error.setUserid(tyProtectionBenifitDTO.getCpRequestMasters().get(i1).getUserId());
				error.setException(e.getMessage());
				status= cpLogsErrosBL.insertexception(error);
			}
     }
		return "FAIL";
	
	}

	@Override
	public String saveContributionAlteration(TyChangeInContributionDTO changeInContributionDTO) throws Exception {

	      try {		
			TyChangeInContributionUserArray valuesArray = new TyChangeInContributionUserArray();
			TyChangeInContributionUser[] tyChangeInContributionUserArrays = null;

			CpServiceRequestMasterColc requestMasterColc = new CpServiceRequestMasterColc();
			CpChangeInContributionColc changeInContributionColc = new CpChangeInContributionColc();
			CpChangeInContributionTypeUser[] cpChangeInContributionTypeUsers = null;
			CpServiceRequestMasterTypeUser[] cpServiceRequestMasterTypeUsers = null;

			CpFatcaTableTypeUser[] cpFatcaTableTypeUsers = null;
			CpFatcaTableTypeUserArray cpFatcaTableTypeUserArray = null;
			CpFatcaCountryDetColc cpFatcaCountryDetColc = new CpFatcaCountryDetColc();

			if (BSLUtils.isNotNull(changeInContributionDTO)) {
				if (BSLUtils.isNotNull(changeInContributionDTO.getCpRequestMasters())) {
					cpServiceRequestMasterTypeUsers = new CpServiceRequestMasterTypeUser[changeInContributionDTO
							.getCpRequestMasters().size()];
					for (int i = 0; i < changeInContributionDTO.getCpRequestMasters().size(); i++) {
						logger.info("Transaction service request  size :"
								+ changeInContributionDTO.getCpRequestMasters().size());
						CpServiceRequestMasterTypeUser cpServiceRequestMasterTypeUser = new CpServiceRequestMasterTypeUser();
						cpServiceRequestMasterTypeUser.setServiceRequestNo(BigDecimal
								.valueOf(changeInContributionDTO.getCpRequestMasters().get(i).getServiceRequestNo()));
						
						if (BSLUtils
								.isNotNull(changeInContributionDTO.getCpRequestMasters().get(i).getSeqno())) {
							cpServiceRequestMasterTypeUser.setSeqNo(changeInContributionDTO.getCpRequestMasters().get(i).getSeqno());
						}
						
						
						
						if (BSLUtils
								.isNotNull(changeInContributionDTO.getCpRequestMasters().get(i).getServiceRequestDate())) {
							cpServiceRequestMasterTypeUser.setServiceRequestDate(getCalendarwithTypeDateTime(
									changeInContributionDTO.getCpRequestMasters().get(i).getServiceRequestDate()));
						}
						if (BSLUtils
								.isNotNull(changeInContributionDTO.getCpRequestMasters().get(i).getServiceRequestType())) {
							if (changeInContributionDTO.getCpRequestMasters().get(i).getServiceRequestType()
									.equalsIgnoreCase("contributionALteration"))
								cpServiceRequestMasterTypeUser.setServiceRequestType("AL38");
							else
								cpServiceRequestMasterTypeUser.setServiceRequestType("AL38");
						}
						if (BSLUtils.isNotNull(changeInContributionDTO.getCpRequestMasters().get(i).getPolicyNo()))
							cpServiceRequestMasterTypeUser
									.setPolicyNo(changeInContributionDTO.getCpRequestMasters().get(i).getPolicyNo());
						if (BSLUtils.isNotNull(changeInContributionDTO.getCpRequestMasters().get(i).getUserId()))
							cpServiceRequestMasterTypeUser
									.setUserid(changeInContributionDTO.getCpRequestMasters().get(i).getUserId());
						if (BSLUtils.isNotNull(changeInContributionDTO.getCpRequestMasters().get(i).getRequestStatus()))
							cpServiceRequestMasterTypeUser.setRequestStatus(
									changeInContributionDTO.getCpRequestMasters().get(i).getRequestStatus());
						if (BSLUtils.isNotNull(changeInContributionDTO.getCpRequestMasters().get(i).getProcessedDate())) {
							cpServiceRequestMasterTypeUser.setProcessedDate(getCalendarwithTypeDateTime(
									changeInContributionDTO.getCpRequestMasters().get(i).getProcessedDate()));
						}
						if (BSLUtils.isNotNull(changeInContributionDTO.getCpRequestMasters().get(i).getProcessedBy()))
							cpServiceRequestMasterTypeUser
									.setProcessedBy(changeInContributionDTO.getCpRequestMasters().get(i).getProcessedBy());
						if (BSLUtils.isNotNull(changeInContributionDTO.getCpRequestMasters().get(i).getRecentUpdate())) {
							cpServiceRequestMasterTypeUser.setProcessedDate(getCalendarwithTypeDateTime(
									changeInContributionDTO.getCpRequestMasters().get(i).getRecentUpdate()));
						}
						cpServiceRequestMasterTypeUsers[i] = cpServiceRequestMasterTypeUser;
					}
					requestMasterColc.setArray(cpServiceRequestMasterTypeUsers);
				}
				if (BSLUtils.isNotNull(changeInContributionDTO.getCpContributionAlterations())) {
					cpChangeInContributionTypeUsers = new CpChangeInContributionTypeUser[changeInContributionDTO
							.getCpContributionAlterations().size()];
					for (int i = 0; i < changeInContributionDTO.getCpContributionAlterations().size(); i++) {
						logger.info("Transaction Contribution size :"
								+ changeInContributionDTO.getCpContributionAlterations().size());
						CpChangeInContributionTypeUser cpChangeInContributionTypeUser = new CpChangeInContributionTypeUser();
						cpChangeInContributionTypeUser.setSerialNo(BigDecimal
								.valueOf(changeInContributionDTO.getCpContributionAlterations().get(i).getSerialNo()));
						if (BSLUtils.isNotNull(changeInContributionDTO.getCpContributionAlterations().get(i).getPolicyNo()))
							cpChangeInContributionTypeUser.setProductName(
									changeInContributionDTO.getCpContributionAlterations().get(i).getPolicyNo());
						if (BSLUtils.isNotNull(
								changeInContributionDTO.getCpContributionAlterations().get(i).getOutstandingAmount()))
							cpChangeInContributionTypeUser.setOutstandingAmount(
									changeInContributionDTO.getCpContributionAlterations().get(i).getOutstandingAmount());
						if (BSLUtils.isNotNull(
								changeInContributionDTO.getCpContributionAlterations().get(i).getCurrentContribution()))
							cpChangeInContributionTypeUser.setCurrentContribution(
									changeInContributionDTO.getCpContributionAlterations().get(i).getCurrentContribution());
						if (BSLUtils.isNotNull(
								changeInContributionDTO.getCpContributionAlterations().get(i).getCurrentFrequency()))
							cpChangeInContributionTypeUser.setCurrentFrequency(
									changeInContributionDTO.getCpContributionAlterations().get(i).getCurrentFrequency());
						if (BSLUtils.isNotNull(
								changeInContributionDTO.getCpContributionAlterations().get(i).getNewContribution()))
							cpChangeInContributionTypeUser.setNewContribution(
									changeInContributionDTO.getCpContributionAlterations().get(i).getNewContribution());
						if (BSLUtils
								.isNotNull(changeInContributionDTO.getCpContributionAlterations().get(i).getNewFrequency()))
							cpChangeInContributionTypeUser.setNewFrequency(
									changeInContributionDTO.getCpContributionAlterations().get(i).getNewFrequency());
						// cpChangeInContributionTypeUser.set
						cpChangeInContributionTypeUsers[i] = cpChangeInContributionTypeUser;
					}
					logger.info("Before data insert into cont alt collection ::::::::::::::::");
					changeInContributionColc.setArray(cpChangeInContributionTypeUsers);
					logger.info("After data insert into cont alt collection ::::::::::::::::" +changeInContributionColc.getArray().length);
				}

				if (BSLUtils.isNotNull(changeInContributionDTO.getCpFatca())) {
					
					logger.info("Inside Fatca for COnt ALt ::::::::::::" +changeInContributionDTO.getCpFatca().size());
					cpFatcaTableTypeUsers = new CpFatcaTableTypeUser[changeInContributionDTO.getCpFatca().size()];

					for (int i = 0; i < changeInContributionDTO.getCpFatca().size(); i++) {

						CpFatcaTableTypeUser cpFatcaTableTypeUser = new CpFatcaTableTypeUser();

						if (BSLUtils.isNotNull(changeInContributionDTO.getCpFatca().get(i).getSerialNo())) {
							cpFatcaTableTypeUser.setNSerialNo(
									BigDecimal.valueOf(changeInContributionDTO.getCpFatca().get(i).getSerialNo()));
						}

						if (BSLUtils.isNotNull(changeInContributionDTO.getCpFatca().get(i).getServiceRequestNo()))

							cpFatcaTableTypeUser.setServiceRequestNo(BigDecimal
									.valueOf(changeInContributionDTO.getCpRequestMasters().get(i).getServiceRequestNo()));

						if (BSLUtils.isNotNull(changeInContributionDTO.getCpFatca().get(i).getIsUsPerson()))
							cpFatcaTableTypeUser.setVUsPerson(changeInContributionDTO.getCpFatca().get(i).getIsUsPerson());

						if (BSLUtils.isNotNull(changeInContributionDTO.getCpFatca().get(i).getServiceName())) {
							cpFatcaTableTypeUser
									.setVServiceName(changeInContributionDTO.getCpFatca().get(i).getServiceName());
						}

						if (BSLUtils.isNotNull(changeInContributionDTO.getCpFatca().get(i).getResidentUAE())) {
							cpFatcaTableTypeUser
									.setVUaeResidency(changeInContributionDTO.getCpFatca().get(i).getResidentUAE());
						}

						if (BSLUtils.isNotNull(changeInContributionDTO.getCpFatca().get(i).getTinNo())) {
							cpFatcaTableTypeUser.setVTinNo(changeInContributionDTO.getCpFatca().get(i).getTinNo());
						}

						if (BSLUtils.isNotNull(changeInContributionDTO.getCpFatca().get(i).getTaxMoreThanOne())) {
							cpFatcaTableTypeUser.setVTaxMorethanOnecoun(
									changeInContributionDTO.getCpFatca().get(i).getTaxMoreThanOne());
						}

						CpFactaCountryDetTypeUser[] cpFactaCountryDetTypeUser = new CpFactaCountryDetTypeUser[10];
						if (changeInContributionDTO.getCpFatcaCountryDet() != null) {
							if (changeInContributionDTO.getCpFatcaCountryDet().size() > 0) {
								cpFactaCountryDetTypeUser = new CpFactaCountryDetTypeUser[changeInContributionDTO
										.getCpFatcaCountryDet().size()];
								for (int l = 0; l < changeInContributionDTO.getCpFatcaCountryDet().size(); l++) {
									CpFactaCountryDetTypeUser eachRecord = new CpFactaCountryDetTypeUser();

									// eachRecord.setNSeqNo(contributionHolidayDTO.getCpFatcaCountryDet().get(l).getSerialNo());
									eachRecord.setNSerialNo(
											BigDecimal.valueOf(changeInContributionDTO.getCpFatca().get(i).getSerialNo()));
									eachRecord.setVCountryJuridis(
											changeInContributionDTO.getCpFatcaCountryDet().get(l).getCountry());
									eachRecord.setVTinReason(
											changeInContributionDTO.getCpFatcaCountryDet().get(l).getTinOrReason());
									cpFactaCountryDetTypeUser[l] = eachRecord;
									cpFatcaCountryDetColc.setArray(cpFactaCountryDetTypeUser);
									cpFatcaTableTypeUser.setOcCountry(cpFatcaCountryDetColc);
								}
							}
						}
						cpFatcaTableTypeUsers[i] = cpFatcaTableTypeUser;
					}
					
					if (cpFatcaTableTypeUsers.length > 0) {
						cpFatcaTableTypeUserArray = new CpFatcaTableTypeUserArray();
						cpFatcaTableTypeUserArray.setCpFatcaTableTypeUser(cpFatcaTableTypeUsers);
					}

				}
				logger.info("Size for master list for cont ALt ::::::::::::::::" +requestMasterColc.getArray().length);
				logger.info("Size for Cont alt table  ::::::::::::::::" +changeInContributionColc.getArray().length);
				if (changeInContributionColc.getArray().length > 0 && requestMasterColc.getArray().length > 0) {
					logger.info("Inside 11111111111 ::::::::::");
					tyChangeInContributionUserArrays = new TyChangeInContributionUser[1];
					TyChangeInContributionUser tyChangeInContributionUser = new TyChangeInContributionUser();
					tyChangeInContributionUser.setLcServiceColc(requestMasterColc);
					tyChangeInContributionUser.setLcChangeInContrColc(changeInContributionColc);
					tyChangeInContributionUserArrays[0] = tyChangeInContributionUser;
					valuesArray.setTyChangeInContributionUser(tyChangeInContributionUserArrays);
					logger.info("Inside 2222222222222 ::::::::::" +valuesArray);
				}
			}
			logger.info("Saving Change in contribution:Transaction DTO size :"
					+ valuesArray.getTyChangeInContributionUser().length);
			if (valuesArray.getTyChangeInContributionUser().length > 0) {
				BfnChangeInContribution bfnChangeInContribution = new BfnChangeInContribution();
				bfnChangeInContribution.setPChangeInContribution(valuesArray);
				bfnChangeInContribution.setPFatca(cpFatcaTableTypeUserArray);
				BfnChangeInContributionResponse response = getServiceProxy()
						.bfnChangeInContribution(bfnChangeInContribution);
				CpExceptionErrorTypeUserArray cpExceptionErrorTypeUserList = response.getResult();
				CpExceptionErrorTypeUser[] cpExceptionErrorTypeUserArray = cpExceptionErrorTypeUserList
						.getCpExceptionErrorTypeUser();

				if (BSLUtils.isNotNull(cpExceptionErrorTypeUserArray)) {
					for (int i = 0; i < cpExceptionErrorTypeUserArray.length; i++) {
						CpExceptionErrorTypeUser cpExceptionErrorTypeUser = cpExceptionErrorTypeUserArray[i];
						
						if (cpExceptionErrorTypeUser.getErrSqlCode().equalsIgnoreCase("PASS")) {
							logger.info("Change in contribution transaction status: "
									+ cpExceptionErrorTypeUser.getErrSqlCode());
							return "PASS";
						} 	else {
							for(int i1 =0;i1<changeInContributionDTO.getCpRequestMasters().size();i1++) {
								boolean status;
								CpLogsErros error = new CpLogsErros();
								error.setPolicyno(changeInContributionDTO.getCpRequestMasters().get(i1).getPolicyNo());
								error.setReqno(changeInContributionDTO.getCpRequestMasters().get(i1).getServiceRequestNo());
								error.setType(changeInContributionDTO.getCpRequestMasters().get(i1).getServiceRequestType());
								error.setUserid(changeInContributionDTO.getCpRequestMasters().get(i1).getUserId());
								error.setException(cpExceptionErrorTypeUser.getErrSqlErrDesc());
								status= cpLogsErrosBL.insertexception(error);
							
							logger.info("Change in contribution transaction status: "
									+ cpExceptionErrorTypeUser.getErrSqlCode());
							logger.info("Transaction error code: " + cpExceptionErrorTypeUser.getErrSqlCode());
							logger.info("Transaction error Description: " + cpExceptionErrorTypeUser.getErrSqlErrDesc());
							logger.info(
									"Transaction service Request no: " + cpExceptionErrorTypeUser.getServiceRequestNo());
						}
					  }
					}
				}
			}
	      }catch(Exception e) {
	    	  boolean status;
	    	  for(int i1 =0;i1<changeInContributionDTO.getCpRequestMasters().size();i1++) {
	    		CpLogsErros error = new CpLogsErros();
	  			error.setPolicyno(changeInContributionDTO.getCpRequestMasters().get(i1).getPolicyNo());
	  			error.setReqno(changeInContributionDTO.getCpRequestMasters().get(i1).getServiceRequestNo());
	  			error.setType(changeInContributionDTO.getCpRequestMasters().get(i1).getServiceRequestType());
	  			error.setUserid(changeInContributionDTO.getCpRequestMasters().get(i1).getUserId());
	  			error.setException(e.getMessage());
	  			status= cpLogsErrosBL.insertexception(error);  
	    	  }
	      }
		  return "FAIL";
		
	}

	@Override
	public String saveRedirectionAndSwitching(TyRedirectionSwitchingDTO redirectionSwitchingDTO) throws Exception {

		TyRedirectionSwitchingUserArray valuesArray = new TyRedirectionSwitchingUserArray();
		TyRedirectionSwitchingUser[] tyRedirectionSwitchingUserArrays = null;

		CpServiceRequestMasterColc requestMasterColc = new CpServiceRequestMasterColc();
		CpRedirectionSwitchingColc redirectionSwitchingColc = new CpRedirectionSwitchingColc();
		CpRedirectionSwitchingTypeUser[] cpRedirectionSwitchingTypeUsers = null;
		CpServiceRequestMasterTypeUser[] cpServiceRequestMasterTypeUsers = null;

		CpFatcaTableTypeUser[] cpFatcaTableTypeUsers = null;
		CpFatcaTableTypeUserArray cpFatcaTableTypeUserArray = null;
		CpFatcaCountryDetColc cpFatcaCountryDetColc = new CpFatcaCountryDetColc();

		if (BSLUtils.isNotNull(redirectionSwitchingDTO)) {
			
			if(redirectionSwitchingDTO.getCpRequestMasters().get(0).getServiceRequestType().equalsIgnoreCase("redirection")) {
				for(int k1=0;k1<redirectionSwitchingDTO.getCpRedirectionSwitchings().size();k1++) {
					if(redirectionSwitchingDTO.getCpRedirectionSwitchings().get(k1).getCriteria()!=null) {
					     if(redirectionSwitchingDTO.getCpRedirectionSwitchings().get(k1).getValue()!=null) {
					    	 if(redirectionSwitchingDTO.getCpRedirectionSwitchings().get(k1).getValue().compareTo(BigDecimal.ZERO)==0) {
									BigDecimal exit=new BigDecimal("0");
									redirectionSwitchingDTO.getCpRedirectionSwitchings().get(k1).setCriteria(exit);
							   }else {
								   BigDecimal currentvalue = redirectionSwitchingDTO.getCpRedirectionSwitchings().get(k1).getValue();							
								   redirectionSwitchingDTO.getCpRedirectionSwitchings().get(k1).setCriteria(currentvalue);
							   }
						}
					}else {
						BigDecimal exit=new BigDecimal("0");
						redirectionSwitchingDTO.getCpRedirectionSwitchings().get(k1).setCriteria(exit);
					}
			    }	
			}

			// Inserting Master table details.
			if (BSLUtils.isNotNull(redirectionSwitchingDTO.getCpRequestMasters())) {
				cpServiceRequestMasterTypeUsers = new CpServiceRequestMasterTypeUser[redirectionSwitchingDTO
						.getCpRequestMasters().size()];
				for (int i = 0; i < redirectionSwitchingDTO.getCpRequestMasters().size(); i++) {
					logger.info("Transaction service request  size :"
							+ redirectionSwitchingDTO.getCpRequestMasters().size());
					CpServiceRequestMasterTypeUser cpServiceRequestMasterTypeUser = new CpServiceRequestMasterTypeUser();
					cpServiceRequestMasterTypeUser.setServiceRequestNo(BigDecimal
							.valueOf(redirectionSwitchingDTO.getCpRequestMasters().get(i).getServiceRequestNo()));
					
					if (BSLUtils.isNotNull(redirectionSwitchingDTO.getCpRequestMasters().get(i).getSeqno()))
						cpServiceRequestMasterTypeUser
								.setSeqNo(redirectionSwitchingDTO.getCpRequestMasters().get(i).getSeqno());
					
					if (BSLUtils
							.isNotNull(redirectionSwitchingDTO.getCpRequestMasters().get(i).getServiceRequestDate())) {
						cpServiceRequestMasterTypeUser.setServiceRequestDate(getCalendarwithTypeDateTime(
								redirectionSwitchingDTO.getCpRequestMasters().get(i).getServiceRequestDate()));
					}
					if (BSLUtils
							.isNotNull(redirectionSwitchingDTO.getCpRequestMasters().get(i).getServiceRequestType())) {
						if (redirectionSwitchingDTO.getCpRequestMasters().get(i).getServiceRequestType()
								.equalsIgnoreCase("redirection"))
							cpServiceRequestMasterTypeUser.setServiceRequestType("REDIRECT");
						else if (redirectionSwitchingDTO.getCpRequestMasters().get(i).getServiceRequestType()
								.equalsIgnoreCase("switching"))
							cpServiceRequestMasterTypeUser.setServiceRequestType("SWITCHING");
						else if (redirectionSwitchingDTO.getCpRequestMasters().get(i).getServiceRequestType()
								.equalsIgnoreCase("redirectionandswitching"))
							cpServiceRequestMasterTypeUser.setServiceRequestType("BOTH_SW_RD");
					}
					if (BSLUtils.isNotNull(redirectionSwitchingDTO.getCpRequestMasters().get(i).getPolicyNo()))
						cpServiceRequestMasterTypeUser
								.setPolicyNo(redirectionSwitchingDTO.getCpRequestMasters().get(i).getPolicyNo());
					if (BSLUtils.isNotNull(redirectionSwitchingDTO.getCpRequestMasters().get(i).getUserId()))
						cpServiceRequestMasterTypeUser
								.setUserid(redirectionSwitchingDTO.getCpRequestMasters().get(i).getUserId());
					if (BSLUtils.isNotNull(redirectionSwitchingDTO.getCpRequestMasters().get(i).getRequestStatus()))
						cpServiceRequestMasterTypeUser.setRequestStatus(
								redirectionSwitchingDTO.getCpRequestMasters().get(i).getRequestStatus());
					if (BSLUtils.isNotNull(redirectionSwitchingDTO.getCpRequestMasters().get(i).getProcessedDate())) {
						cpServiceRequestMasterTypeUser.setProcessedDate(getCalendarwithTypeDateTime(
								redirectionSwitchingDTO.getCpRequestMasters().get(i).getProcessedDate()));
					}
					if (BSLUtils.isNotNull(redirectionSwitchingDTO.getCpRequestMasters().get(i).getProcessedBy()))
						cpServiceRequestMasterTypeUser
								.setProcessedBy(redirectionSwitchingDTO.getCpRequestMasters().get(i).getProcessedBy());
					if (BSLUtils.isNotNull(redirectionSwitchingDTO.getCpRequestMasters().get(i).getRecentUpdate())) {
						cpServiceRequestMasterTypeUser.setProcessedDate(getCalendarwithTypeDateTime(
								redirectionSwitchingDTO.getCpRequestMasters().get(i).getRecentUpdate()));
					}
					cpServiceRequestMasterTypeUsers[i] = cpServiceRequestMasterTypeUser;
				}
				requestMasterColc.setArray(cpServiceRequestMasterTypeUsers);
			}

			// Inserting Redirection and Switching details.
			if (BSLUtils.isNotNull(redirectionSwitchingDTO.getCpRedirectionSwitchings())) {
				cpRedirectionSwitchingTypeUsers = new CpRedirectionSwitchingTypeUser[redirectionSwitchingDTO
						.getCpRedirectionSwitchings().size()];
				for (int i = 0; i < redirectionSwitchingDTO.getCpRedirectionSwitchings().size(); i++) {
					logger.info("Transaction Redirection and switching size :"
							+ redirectionSwitchingDTO.getCpRedirectionSwitchings().size());
					CpRedirectionSwitchingTypeUser cpRedirectionSwitchingTypeUser = new CpRedirectionSwitchingTypeUser();
					cpRedirectionSwitchingTypeUser.setNSerialNo(BigDecimal
							.valueOf(redirectionSwitchingDTO.getCpRedirectionSwitchings().get(i).getSerialNo()));
					if (BSLUtils.isNotNull(redirectionSwitchingDTO.getCpRedirectionSwitchings().get(i).getFundType()))
						cpRedirectionSwitchingTypeUser.setVFundType(
								redirectionSwitchingDTO.getCpRedirectionSwitchings().get(i).getFundType());
					if (BSLUtils.isNotNull(redirectionSwitchingDTO.getCpRedirectionSwitchings().get(i).getFundCode()))
						cpRedirectionSwitchingTypeUser.setVFundCode(
								redirectionSwitchingDTO.getCpRedirectionSwitchings().get(i).getFundCode());
					if (BSLUtils.isNotNull(redirectionSwitchingDTO.getCpRedirectionSwitchings().get(i).getFundName()))
						cpRedirectionSwitchingTypeUser.setVFundName(
								redirectionSwitchingDTO.getCpRedirectionSwitchings().get(i).getFundName());
					if (BSLUtils
							.isNotNull(redirectionSwitchingDTO.getCpRedirectionSwitchings().get(i).getFundCurrency()))
						cpRedirectionSwitchingTypeUser.setVFundCurrency(
								redirectionSwitchingDTO.getCpRedirectionSwitchings().get(i).getFundCurrency());
					if (BSLUtils.isNotNull(redirectionSwitchingDTO.getCpRedirectionSwitchings().get(i).getFundValue()))
						cpRedirectionSwitchingTypeUser.setNFundValue(
								redirectionSwitchingDTO.getCpRedirectionSwitchings().get(i).getFundValue());
					if (BSLUtils.isNotNull(redirectionSwitchingDTO.getCpRedirectionSwitchings().get(i).getCriteria()))
						cpRedirectionSwitchingTypeUser.setVCriteria(
								redirectionSwitchingDTO.getCpRedirectionSwitchings().get(i).getCriteria());
					if (BSLUtils.isNotNull(redirectionSwitchingDTO.getCpRedirectionSwitchings().get(i).getValue()))
						cpRedirectionSwitchingTypeUser
								.setNValue(redirectionSwitchingDTO.getCpRedirectionSwitchings().get(i).getValue());
					if (BSLUtils.isNotNull(redirectionSwitchingDTO.getCpRedirectionSwitchings().get(i).getMode()))
						cpRedirectionSwitchingTypeUser
								.setVMode(redirectionSwitchingDTO.getCpRedirectionSwitchings().get(i).getMode());
					cpRedirectionSwitchingTypeUsers[i] = cpRedirectionSwitchingTypeUser;
				}
				redirectionSwitchingColc.setArray(cpRedirectionSwitchingTypeUsers);

			}

			// Inserting Fatca details --------
			if (BSLUtils.isNotNull(redirectionSwitchingDTO.getCpFatca())) {
				cpFatcaTableTypeUsers = new CpFatcaTableTypeUser[redirectionSwitchingDTO.getCpFatca().size()];

				for (int i = 0; i < redirectionSwitchingDTO.getCpFatca().size(); i++) {

					CpFatcaTableTypeUser cpFatcaTableTypeUser = new CpFatcaTableTypeUser();

					if (BSLUtils.isNotNull(redirectionSwitchingDTO.getCpFatca().get(i).getSerialNo())) {
						cpFatcaTableTypeUser.setNSerialNo(
								BigDecimal.valueOf(redirectionSwitchingDTO.getCpFatca().get(i).getSerialNo()));
					}
					if (BSLUtils.isNotNull(redirectionSwitchingDTO.getCpFatca().get(i).getServiceRequestNo()))
						cpFatcaTableTypeUser.setServiceRequestNo(BigDecimal
								.valueOf(redirectionSwitchingDTO.getCpRequestMasters().get(i).getServiceRequestNo()));

					if (BSLUtils.isNotNull(redirectionSwitchingDTO.getCpFatca().get(i).getIsUsPerson()))
						cpFatcaTableTypeUser.setVUsPerson(redirectionSwitchingDTO.getCpFatca().get(i).getIsUsPerson());

					if (BSLUtils.isNotNull(redirectionSwitchingDTO.getCpFatca().get(i).getServiceName())) {
						cpFatcaTableTypeUser
								.setVServiceName(redirectionSwitchingDTO.getCpFatca().get(i).getServiceName());
					}

					if (BSLUtils.isNotNull(redirectionSwitchingDTO.getCpFatca().get(i).getTinNo())) {
						cpFatcaTableTypeUser.setVTinNo(redirectionSwitchingDTO.getCpFatca().get(i).getTinNo());
					}

					if (BSLUtils.isNotNull(redirectionSwitchingDTO.getCpFatca().get(i).getResidentUAE())) {
						cpFatcaTableTypeUser
								.setVUaeResidency(redirectionSwitchingDTO.getCpFatca().get(i).getResidentUAE());
					}

					if (BSLUtils.isNotNull(redirectionSwitchingDTO.getCpFatca().get(i).getTaxMoreThanOne())) {
						cpFatcaTableTypeUser.setVTaxMorethanOnecoun(
								redirectionSwitchingDTO.getCpFatca().get(i).getTaxMoreThanOne());
					}
					CpFactaCountryDetTypeUser[] cpFactaCountryDetTypeUser = new CpFactaCountryDetTypeUser[10];
					if (redirectionSwitchingDTO.getCpFatcaCountryDet() != null) {
						if (redirectionSwitchingDTO.getCpFatcaCountryDet().size() > 0) {
							cpFactaCountryDetTypeUser = new CpFactaCountryDetTypeUser[redirectionSwitchingDTO
									.getCpFatcaCountryDet().size()];
							for (int l = 0; l < redirectionSwitchingDTO.getCpFatcaCountryDet().size(); l++) {
								CpFactaCountryDetTypeUser eachRecord = new CpFactaCountryDetTypeUser();

								// eachRecord.setNSeqNo(contributionHolidayDTO.getCpFatcaCountryDet().get(l).getSerialNo());
								eachRecord.setNSerialNo(
										BigDecimal.valueOf(redirectionSwitchingDTO.getCpFatca().get(i).getSerialNo()));
								eachRecord.setVCountryJuridis(
										redirectionSwitchingDTO.getCpFatcaCountryDet().get(l).getCountry());
								eachRecord.setVTinReason(
										redirectionSwitchingDTO.getCpFatcaCountryDet().get(l).getTinOrReason());
								cpFactaCountryDetTypeUser[l] = eachRecord;
								cpFatcaCountryDetColc.setArray(cpFactaCountryDetTypeUser);
								cpFatcaTableTypeUser.setOcCountry(cpFatcaCountryDetColc);
							}
						}
					}
					cpFatcaTableTypeUsers[i] = cpFatcaTableTypeUser;
				}

			}
			if (cpFatcaTableTypeUsers.length > 0) {
				cpFatcaTableTypeUserArray = new CpFatcaTableTypeUserArray();
				cpFatcaTableTypeUserArray.setCpFatcaTableTypeUser(cpFatcaTableTypeUsers);
			}

			if (redirectionSwitchingColc.getArray().length > 0 && requestMasterColc.getArray().length > 0) {
				tyRedirectionSwitchingUserArrays = new TyRedirectionSwitchingUser[1];
				TyRedirectionSwitchingUser tyRedirectionSwitchingUser = new TyRedirectionSwitchingUser();
				tyRedirectionSwitchingUser.setLcServiceColc(requestMasterColc);
				tyRedirectionSwitchingUser.setLcRedirectionSwitchingColc(redirectionSwitchingColc);
				tyRedirectionSwitchingUserArrays[0] = tyRedirectionSwitchingUser;
				valuesArray.setTyRedirectionSwitchingUser(tyRedirectionSwitchingUserArrays);
			}
			logger.info("Saving Change in contribution:Transaction DTO size :"
					+ valuesArray.getTyRedirectionSwitchingUser().length);
			if (valuesArray.getTyRedirectionSwitchingUser().length > 0) {
				BfnRedirectionSwitching bfnRedirectionSwitching = new BfnRedirectionSwitching();
				bfnRedirectionSwitching.setPRedirectionSwitching(valuesArray);
				bfnRedirectionSwitching.setPFatca(cpFatcaTableTypeUserArray);
				BfnRedirectionSwitchingResponse response = getServiceProxy()
						.bfnRedirectionSwitching(bfnRedirectionSwitching);
				CpExceptionErrorTypeUserArray cpExceptionErrorTypeUserList = response.getResult();
				CpExceptionErrorTypeUser[] cpExceptionErrorTypeUserArray = cpExceptionErrorTypeUserList
						.getCpExceptionErrorTypeUser();

				if (BSLUtils.isNotNull(cpExceptionErrorTypeUserArray)) {

					for (int i = 0; i < cpExceptionErrorTypeUserArray.length; i++) {
						CpExceptionErrorTypeUser cpExceptionErrorTypeUser = cpExceptionErrorTypeUserArray[i];
						if (cpExceptionErrorTypeUser.getErrSqlCode().equalsIgnoreCase("PASS")) {
							logger.info("Redirection and switching transaction status: "
									+ cpExceptionErrorTypeUser.getErrSqlCode());
							return "PASS";
						} else {
							for(int i1 =0;i1<redirectionSwitchingDTO.getCpRequestMasters().size();i1++) {
								boolean status;
								CpLogsErros error = new CpLogsErros();
								error.setPolicyno(redirectionSwitchingDTO.getCpRequestMasters().get(i1).getPolicyNo());
								error.setReqno(redirectionSwitchingDTO.getCpRequestMasters().get(i1).getServiceRequestNo());
								error.setType(redirectionSwitchingDTO.getCpRequestMasters().get(i1).getServiceRequestType());
								error.setUserid(redirectionSwitchingDTO.getCpRequestMasters().get(i1).getUserId());
								error.setException(cpExceptionErrorTypeUser.getErrSqlErrDesc());
								status= cpLogsErrosBL.insertexception(error);
							}
							
							logger.info("Redirection and switching transaction status:::::::::: "
									+ cpExceptionErrorTypeUser.getErrSqlCode());
							logger.info("Redirection and switching Transaction error code::::::::::::: "
									+ cpExceptionErrorTypeUser.getErrSqlCode());
							logger.info("Redirection and switching Transaction error Description:::::::::: "
									+ cpExceptionErrorTypeUser.getErrSqlErrDesc());
							logger.info("Redirection and switching Transaction service Request no:::::::::: "
									+ cpExceptionErrorTypeUser.getServiceRequestNo());
						}
					}
				
				}
			}
		}
		return "Fail";
	}

	
	public static Calendar toCalendar(Date date){ 
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(date);
		  return cal;
		}

	@Override
	public String saveclaimintimation(TyClaimIntimationDTO tyClaimIntimationDTO) throws Exception {
		
		String intimation=null;
		
		TyClaimIntimationUserArray valuesArray = new TyClaimIntimationUserArray();
		TyClaimIntimationUser[] tyClaimIntimationUser = null;
				
		CpServiceRequestMasterColc requestMasterColc = new CpServiceRequestMasterColc();
		CpClaimIntimationColc cpClaimIntimationColc = new CpClaimIntimationColc();
		CpClaimIntimationTypeUser[] cpClaimIntimationTypeUser = null;
		CpServiceRequestMasterTypeUser[] cpServiceRequestMasterTypeUsers = null;
		
		CpFatcaTableTypeUser[] cpFatcaTableTypeUsers = null;
		CpFatcaTableTypeUserArray cpFatcaTableTypeUserArray = null;
		CpFatcaCountryDetColc cpFatcaCountryDetColc = new CpFatcaCountryDetColc();
		
		if (BSLUtils.isNotNull(tyClaimIntimationDTO)) {
			if (BSLUtils.isNotNull(tyClaimIntimationDTO.getCpRequestMasters())) {
				cpServiceRequestMasterTypeUsers = new CpServiceRequestMasterTypeUser[tyClaimIntimationDTO.getCpRequestMasters().size()];
				
				for (int i = 0; i < tyClaimIntimationDTO.getCpRequestMasters().size(); i++) {
					logger.info("Transaction service request  size :"
							+ tyClaimIntimationDTO.getCpRequestMasters().size());
					
					CpServiceRequestMasterTypeUser cpServiceRequestMasterTypeUser = new CpServiceRequestMasterTypeUser();
					String serviceRequestNo = tyClaimIntimationDTO.getCpRequestMasters().get(i).getServiceRequestNo()+"";
					//String serviceCode = "11";					
					cpServiceRequestMasterTypeUser.setServiceRequestNo(BigDecimal.valueOf(Integer.parseInt(serviceRequestNo)));
					logger.info("Service Request: {}+ "+Integer.parseInt(serviceRequestNo)+"");
					
					
					if (BSLUtils.isNotNull(tyClaimIntimationDTO.getCpRequestMasters().get(i).getSeqno()))
						cpServiceRequestMasterTypeUser
								.setSeqNo(tyClaimIntimationDTO.getCpRequestMasters().get(i).getSeqno());
					
					
					
					if (BSLUtils
							.isNotNull(tyClaimIntimationDTO.getCpRequestMasters().get(i).getServiceRequestDate())) {
						cpServiceRequestMasterTypeUser.setServiceRequestDate(getCalendarwithTypeDateTime(
								tyClaimIntimationDTO.getCpRequestMasters().get(i).getServiceRequestDate()));
					}
					if (BSLUtils
							.isNotNull(tyClaimIntimationDTO.getCpRequestMasters().get(i).getServiceRequestType())) {
						if (tyClaimIntimationDTO.getCpRequestMasters().get(i).getServiceRequestType()
								.equalsIgnoreCase("claimintimation"))
							cpServiceRequestMasterTypeUser.setServiceRequestType("CLAIM INTIMATION");
						else
							cpServiceRequestMasterTypeUser.setServiceRequestType("CLAIM INTIMATION");
					}
					if (BSLUtils.isNotNull(tyClaimIntimationDTO.getCpRequestMasters().get(i).getPolicyNo()))
						cpServiceRequestMasterTypeUser
								.setPolicyNo(tyClaimIntimationDTO.getCpRequestMasters().get(i).getPolicyNo());
				
					if (BSLUtils.isNotNull(tyClaimIntimationDTO.getCpRequestMasters().get(i).getUserId()))
						cpServiceRequestMasterTypeUser.setUserid(tyClaimIntimationDTO.getCpRequestMasters().get(i).getUserId());
								
					if (BSLUtils.isNotNull(tyClaimIntimationDTO.getCpRequestMasters().get(i).getRequestStatus()))
						cpServiceRequestMasterTypeUser.setRequestStatus(
								tyClaimIntimationDTO.getCpRequestMasters().get(i).getRequestStatus());
					if (BSLUtils.isNotNull(tyClaimIntimationDTO.getCpRequestMasters().get(i).getProcessedDate())) {
						cpServiceRequestMasterTypeUser.setProcessedDate(getCalendarwithTypeDateTime(
								tyClaimIntimationDTO.getCpRequestMasters().get(i).getProcessedDate()));
					}
					if (BSLUtils.isNotNull(tyClaimIntimationDTO.getCpRequestMasters().get(i).getProcessedBy()))
						cpServiceRequestMasterTypeUser
								.setProcessedBy(tyClaimIntimationDTO.getCpRequestMasters().get(i).getProcessedBy());
					if (BSLUtils.isNotNull(tyClaimIntimationDTO.getCpRequestMasters().get(i).getRecentUpdate())) {
						cpServiceRequestMasterTypeUser.setProcessedDate(getCalendarwithTypeDateTime(
								tyClaimIntimationDTO.getCpRequestMasters().get(i).getRecentUpdate()));
					}
					cpServiceRequestMasterTypeUsers[i] = cpServiceRequestMasterTypeUser;
				}
				requestMasterColc.setArray(cpServiceRequestMasterTypeUsers);	
			}
			
			if (BSLUtils.isNotNull(tyClaimIntimationDTO.getCpClaimIntimation())){
				cpClaimIntimationTypeUser = new CpClaimIntimationTypeUser[tyClaimIntimationDTO.getCpClaimIntimation().size()];
				for (int i = 0; i < tyClaimIntimationDTO.getCpClaimIntimation().size(); i++) {
					logger.info("Transaction Contribution size :"
							+ tyClaimIntimationDTO.getCpClaimIntimation().size());
					
					CpClaimIntimationTypeUser claimIntimationTypeUser = new CpClaimIntimationTypeUser();
					claimIntimationTypeUser.setNIntNo(BigDecimal.valueOf(tyClaimIntimationDTO.getCpClaimIntimation().get(i).getNserialno()));
	
					int w1=tyClaimIntimationDTO.getCpClaimIntimation().get(i).getCustrefno();
					String s1=Integer.toString(w1);
					BigDecimal customerrefno =new BigDecimal(s1);
					
					if (BSLUtils.isNotNull(tyClaimIntimationDTO.getCpClaimIntimation().get(i).getCustrefno()))
						claimIntimationTypeUser.setNCustRefNo(customerrefno);
					
					if (BSLUtils.isNotNull(tyClaimIntimationDTO.getCpClaimIntimation().get(i).getItitdtype()))
						claimIntimationTypeUser.setVIdenCode(
								tyClaimIntimationDTO.getCpClaimIntimation().get(i).getItitdtype());
					
					if (BSLUtils.isNotNull(tyClaimIntimationDTO.getCpClaimIntimation().get(i).getItiidno()))
						claimIntimationTypeUser.setVIdenNo(
								tyClaimIntimationDTO.getCpClaimIntimation().get(i).getItiidno());
					
					if (BSLUtils.isNotNull(tyClaimIntimationDTO.getCpClaimIntimation().get(i).getIntimatorcustrefno()))
						claimIntimationTypeUser.setNIntCustRefNo(
								tyClaimIntimationDTO.getCpClaimIntimation().get(i).getIntimatorcustrefno());
					
					
					if (BSLUtils.isNotNull(tyClaimIntimationDTO.getCpClaimIntimation().get(i).getIticustype()))
						claimIntimationTypeUser.setVCustType(
								tyClaimIntimationDTO.getCpClaimIntimation().get(i).getIticustype());
					
					
					if (BSLUtils.isNotNull(tyClaimIntimationDTO.getCpClaimIntimation().get(i).getItifstname()))
						claimIntimationTypeUser.setVFirstName(
								tyClaimIntimationDTO.getCpClaimIntimation().get(i).getItifstname());
					
					if (BSLUtils.isNotNull(tyClaimIntimationDTO.getCpClaimIntimation().get(i).getItimdtname()))
						claimIntimationTypeUser.setVMiddleName(
								tyClaimIntimationDTO.getCpClaimIntimation().get(i).getItimdtname());
					
					if (BSLUtils.isNotNull(tyClaimIntimationDTO.getCpClaimIntimation().get(i).getItilstname()))
						claimIntimationTypeUser.setVLastName(
								tyClaimIntimationDTO.getCpClaimIntimation().get(i).getItilstname());
					
					if (BSLUtils.isNotNull(tyClaimIntimationDTO.getCpClaimIntimation().get(i).getItiname()))
						claimIntimationTypeUser.setVName(
								tyClaimIntimationDTO.getCpClaimIntimation().get(i).getItiname());
					
					
					Calendar valeie = null;
					if (BSLUtils.isNotNull(tyClaimIntimationDTO.getCpClaimIntimation().get(i).getItidob()))
						valeie=toCalendar(tyClaimIntimationDTO.getCpClaimIntimation().get(i).getItidob());
					    	claimIntimationTypeUser.setDBirthDate(valeie);
					
					if (BSLUtils.isNotNull(tyClaimIntimationDTO.getCpClaimIntimation().get(i).getItitilee()))
						  claimIntimationTypeUser.setVTitleCode(
										tyClaimIntimationDTO.getCpClaimIntimation().get(i).getItitilee());
							
					if (BSLUtils.isNotNull(tyClaimIntimationDTO.getCpClaimIntimation().get(i).getItigender()))
						  claimIntimationTypeUser.setVSex(
										tyClaimIntimationDTO.getCpClaimIntimation().get(i).getItigender());
					
					if (BSLUtils.isNotNull(tyClaimIntimationDTO.getCpClaimIntimation().get(i).getItiemail()))
						  claimIntimationTypeUser.setVContact(
										tyClaimIntimationDTO.getCpClaimIntimation().get(i).getItiemail());
					
					if (BSLUtils.isNotNull(tyClaimIntimationDTO.getCpClaimIntimation().get(i).getEventgrp()))
						  claimIntimationTypeUser.setVEventGroup(
										tyClaimIntimationDTO.getCpClaimIntimation().get(i).getEventgrp());
					
					Calendar valeie1 = null;
					if (BSLUtils.isNotNull(tyClaimIntimationDTO.getCpClaimIntimation().get(i).getEventdate()))
						valeie1=toCalendar(tyClaimIntimationDTO.getCpClaimIntimation().get(i).getEventdate()); 
						claimIntimationTypeUser.setDEventDate(valeie1);
					
					Calendar valeie2 = null;
					if (BSLUtils.isNotNull(tyClaimIntimationDTO.getCpClaimIntimation().get(i).getCreatedate()))
						valeie2=toCalendar(tyClaimIntimationDTO.getCpClaimIntimation().get(i).getCreatedate());	  
						claimIntimationTypeUser.setDCreateDate(valeie2);	
						
					if (BSLUtils.isNotNull(tyClaimIntimationDTO.getCpClaimIntimation().get(i).getRemarks()))
						claimIntimationTypeUser.setVRemarks(
										tyClaimIntimationDTO.getCpClaimIntimation().get(i).getRemarks());	
					
					
					if (BSLUtils.isNotNull(tyClaimIntimationDTO.getCpClaimIntimation().get(i).getCauseloss()))
						claimIntimationTypeUser.setVCauseCode(
										tyClaimIntimationDTO.getCpClaimIntimation().get(i).getCauseloss());
					
					if (BSLUtils.isNotNull(tyClaimIntimationDTO.getCpClaimIntimation().get(i).getEventplace()))
						claimIntimationTypeUser.setVEventPlace(
										tyClaimIntimationDTO.getCpClaimIntimation().get(i).getEventplace());
					
					Date d2 = new Date();
					int S1 = d2.getHours();
					int S2=d2.getMinutes();
					int S3=d2.getSeconds();
					String S4=Integer.toString(S1);
					String S5=Integer.toString(S2);
					String S6=Integer.toString(S3);
					String datas=S4+":"+S5+":"+S6;
					
					if (BSLUtils.isNotNull(tyClaimIntimationDTO.getCpClaimIntimation().get(i).getIntimatetime()))
						claimIntimationTypeUser.setVIntimationTime(datas);
					
					cpClaimIntimationTypeUser[i] = claimIntimationTypeUser;
					
				}
				cpClaimIntimationColc.setArray(cpClaimIntimationTypeUser);			
		    }
			
			
			// Inserting Fatca details --------
						if (BSLUtils.isNotNull(tyClaimIntimationDTO.getCpFatca())) {
							cpFatcaTableTypeUsers = new CpFatcaTableTypeUser[tyClaimIntimationDTO.getCpFatca().size()];

							for (int i = 0; i < tyClaimIntimationDTO.getCpFatca().size(); i++) {

								CpFatcaTableTypeUser cpFatcaTableTypeUser = new CpFatcaTableTypeUser();

								if (BSLUtils.isNotNull(tyClaimIntimationDTO.getCpFatca().get(i).getSerialNo())) {
									cpFatcaTableTypeUser.setNSerialNo(
											BigDecimal.valueOf(tyClaimIntimationDTO.getCpFatca().get(i).getSerialNo()));
								}
								if (BSLUtils.isNotNull(tyClaimIntimationDTO.getCpFatca().get(i).getServiceRequestNo()))
									cpFatcaTableTypeUser.setServiceRequestNo(BigDecimal
											.valueOf(tyClaimIntimationDTO.getCpRequestMasters().get(i).getServiceRequestNo()));

								if (BSLUtils.isNotNull(tyClaimIntimationDTO.getCpFatca().get(i).getIsUsPerson()))
									cpFatcaTableTypeUser.setVUsPerson(tyClaimIntimationDTO.getCpFatca().get(i).getIsUsPerson());

								if (BSLUtils.isNotNull(tyClaimIntimationDTO.getCpFatca().get(i).getServiceName())) {
									cpFatcaTableTypeUser
											.setVServiceName(tyClaimIntimationDTO.getCpFatca().get(i).getServiceName());
								}

								if (BSLUtils.isNotNull(tyClaimIntimationDTO.getCpFatca().get(i).getTinNo())) {
									cpFatcaTableTypeUser.setVTinNo(tyClaimIntimationDTO.getCpFatca().get(i).getTinNo());
								}

								if (BSLUtils.isNotNull(tyClaimIntimationDTO.getCpFatca().get(i).getResidentUAE())) {
									cpFatcaTableTypeUser
											.setVUaeResidency(tyClaimIntimationDTO.getCpFatca().get(i).getResidentUAE());
								}

								if (BSLUtils.isNotNull(tyClaimIntimationDTO.getCpFatca().get(i).getTaxMoreThanOne())) {
									cpFatcaTableTypeUser.setVTaxMorethanOnecoun(
											tyClaimIntimationDTO.getCpFatca().get(i).getTaxMoreThanOne());
								}
								CpFactaCountryDetTypeUser[] cpFactaCountryDetTypeUser = new CpFactaCountryDetTypeUser[10];
								if (tyClaimIntimationDTO.getCpFatcaCountryDet() != null) {
									if (tyClaimIntimationDTO.getCpFatcaCountryDet().size() > 0) {
										cpFactaCountryDetTypeUser = new CpFactaCountryDetTypeUser[tyClaimIntimationDTO
												.getCpFatcaCountryDet().size()];
										for (int l = 0; l < tyClaimIntimationDTO.getCpFatcaCountryDet().size(); l++) {
											CpFactaCountryDetTypeUser eachRecord = new CpFactaCountryDetTypeUser();

											// eachRecord.setNSeqNo(contributionHolidayDTO.getCpFatcaCountryDet().get(l).getSerialNo());
											eachRecord.setNSerialNo(
													BigDecimal.valueOf(tyClaimIntimationDTO.getCpFatca().get(i).getSerialNo()));
											eachRecord.setVCountryJuridis(
													tyClaimIntimationDTO.getCpFatcaCountryDet().get(l).getCountry());
											eachRecord.setVTinReason(
													tyClaimIntimationDTO.getCpFatcaCountryDet().get(l).getTinOrReason());
											cpFactaCountryDetTypeUser[l] = eachRecord;
											cpFatcaCountryDetColc.setArray(cpFactaCountryDetTypeUser);
											cpFatcaTableTypeUser.setOcCountry(cpFatcaCountryDetColc);
										}
									}
								}
								cpFatcaTableTypeUsers[i] = cpFatcaTableTypeUser;
							}

						}
						if (cpFatcaTableTypeUsers.length > 0) {
							cpFatcaTableTypeUserArray = new CpFatcaTableTypeUserArray();
							cpFatcaTableTypeUserArray.setCpFatcaTableTypeUser(cpFatcaTableTypeUsers);
						}
			
			if (cpClaimIntimationColc.getArray().length > 0 && requestMasterColc.getArray().length > 0) {
				tyClaimIntimationUser = new TyClaimIntimationUser[1];
				TyClaimIntimationUser tyClaimIntimationUser1 = new TyClaimIntimationUser();
				tyClaimIntimationUser1.setLcServiceColc(requestMasterColc);
				tyClaimIntimationUser1.setLcClaimIntimationColc(cpClaimIntimationColc);;
				tyClaimIntimationUser[0] = tyClaimIntimationUser1;
				valuesArray.setTyClaimIntimationUser(tyClaimIntimationUser);
			}
			
			if (valuesArray.getTyClaimIntimationUser().length > 0) {
				BfnClaimIntimation bfnClaimIntimation = new BfnClaimIntimation();
				bfnClaimIntimation.setPClaimIntimation(valuesArray);
				bfnClaimIntimation.setPFatca(cpFatcaTableTypeUserArray);
				
				BfnClaimIntimationResponse response = getServiceProxy()
						.bfnClaimIntimation(bfnClaimIntimation);
				CpExceptionErrorTypeUserArray cpExceptionErrorTypeUserList = response.getResult();
				CpExceptionErrorTypeUser[] cpExceptionErrorTypeUserArray = cpExceptionErrorTypeUserList
						.getCpExceptionErrorTypeUser();
				
				if (BSLUtils.isNotNull(cpExceptionErrorTypeUserArray)) {
					for (int i = 0; i < cpExceptionErrorTypeUserArray.length; i++) {
						CpExceptionErrorTypeUser cpExceptionErrorTypeUser = cpExceptionErrorTypeUserArray[i];
						if (cpExceptionErrorTypeUser.getErrSqlCode().equalsIgnoreCase("PASS")) {
							logger.info("Redirection and switching transaction status: "
									+ cpExceptionErrorTypeUser.getErrSqlCode());
							intimation=cpExceptionErrorTypeUser.getErrSqlCode();
							return intimation;
						} else {
							for(int i1 =0;i1<tyClaimIntimationDTO.getCpRequestMasters().size();i1++) {
								boolean status;
								CpLogsErros error = new CpLogsErros();
								error.setPolicyno(tyClaimIntimationDTO.getCpRequestMasters().get(i1).getPolicyNo());
								error.setReqno(tyClaimIntimationDTO.getCpRequestMasters().get(i1).getServiceRequestNo());
								error.setType(tyClaimIntimationDTO.getCpRequestMasters().get(i1).getServiceRequestType());
								error.setUserid(tyClaimIntimationDTO.getCpRequestMasters().get(i1).getUserId());
								error.setException(cpExceptionErrorTypeUser.getErrSqlErrDesc());
								intimation=cpExceptionErrorTypeUser.getErrSeverity();
								status= cpLogsErrosBL.insertexception(error);
							}
							
							logger.info("Claim Intimation:::::::::: "
									+ cpExceptionErrorTypeUser.getErrSqlCode());
							logger.info("Claim Intimation Transaction error code::::::::::::: "
									+ cpExceptionErrorTypeUser.getErrSqlCode());
							logger.info("Claim Intimation Transaction error Description:::::::::: "
									+ cpExceptionErrorTypeUser.getErrSqlErrDesc());
							logger.info("Claim Intimation Transaction service Request no:::::::::: "
									+ cpExceptionErrorTypeUser.getServiceRequestNo());
						}
					}
				}
			}
		}
		return intimation;
	}
		
	
	@Override
	public String savepartialwithdraw(TyPartialWithdrawDTO tyPartialWithdrawDTO) throws Exception {
		
		TyPartialWithdrawalFundsUserArray valuesArray = new TyPartialWithdrawalFundsUserArray();
		TyPartialWithdrawalFundsUser[] tyPartialWithdrawalFundsUser = null;
				
		CpServiceRequestMasterColc requestMasterColc = new CpServiceRequestMasterColc();
		CpPartialWithdrawFundsColc partialWithdrawFundsColc = new CpPartialWithdrawFundsColc();
		CpPartialWithdrawFundsTypeUser[] cpPartialWithdrawFundsTypeUser = null;
		CpServiceRequestMasterTypeUser[] cpServiceRequestMasterTypeUsers = null;
		
		CpFatcaTableTypeUser[] cpFatcaTableTypeUsers = null;
		CpFatcaTableTypeUserArray cpFatcaTableTypeUserArray = null;
		CpFatcaCountryDetColc cpFatcaCountryDetColc = new CpFatcaCountryDetColc();
		
		
		if (BSLUtils.isNotNull(tyPartialWithdrawDTO)) {
			if (BSLUtils.isNotNull(tyPartialWithdrawDTO.getCpRequestMasters())) {
				cpServiceRequestMasterTypeUsers = new CpServiceRequestMasterTypeUser[tyPartialWithdrawDTO.getCpRequestMasters().size()];
			
				for (int i = 0; i < tyPartialWithdrawDTO.getCpRequestMasters().size(); i++) {
					logger.info("Transaction service request  size :"
							+ tyPartialWithdrawDTO.getCpRequestMasters().size());
					
					CpServiceRequestMasterTypeUser cpServiceRequestMasterTypeUser = new CpServiceRequestMasterTypeUser();
					String serviceRequestNo = tyPartialWithdrawDTO.getCpRequestMasters().get(i).getServiceRequestNo()+"";
					//String serviceCode = "11";					
					cpServiceRequestMasterTypeUser.setServiceRequestNo(BigDecimal.valueOf(Integer.parseInt(serviceRequestNo)));
					logger.info("Service Request: {}+ "+Integer.parseInt(serviceRequestNo)+"");
					if (BSLUtils
							.isNotNull(tyPartialWithdrawDTO.getCpRequestMasters().get(i).getServiceRequestDate())) {
						cpServiceRequestMasterTypeUser.setServiceRequestDate(getCalendarwithTypeDateTime(
								tyPartialWithdrawDTO.getCpRequestMasters().get(i).getServiceRequestDate()));
					}
					if (BSLUtils
							.isNotNull(tyPartialWithdrawDTO.getCpRequestMasters().get(i).getServiceRequestType())) {
						if (tyPartialWithdrawDTO.getCpRequestMasters().get(i).getServiceRequestType()
								.equalsIgnoreCase("partialwithdraw"))
							cpServiceRequestMasterTypeUser.setServiceRequestType("PARTIAL WITHDRAWAL");
						else
							cpServiceRequestMasterTypeUser.setServiceRequestType("PARTIAL WITHDRAWAL");
					}
					if (BSLUtils.isNotNull(tyPartialWithdrawDTO.getCpRequestMasters().get(i).getPolicyNo()))
						cpServiceRequestMasterTypeUser
								.setPolicyNo(tyPartialWithdrawDTO.getCpRequestMasters().get(i).getPolicyNo());
					
					if (BSLUtils.isNotNull(tyPartialWithdrawDTO.getCpRequestMasters().get(i).getSeqno()))
						cpServiceRequestMasterTypeUser
								.setSeqNo(tyPartialWithdrawDTO.getCpRequestMasters().get(i).getSeqno());
					
					if (BSLUtils.isNotNull(tyPartialWithdrawDTO.getCpRequestMasters().get(i).getUserId()))
						cpServiceRequestMasterTypeUser
								.setUserid(tyPartialWithdrawDTO.getCpRequestMasters().get(i).getUserId());

					if (BSLUtils.isNotNull(tyPartialWithdrawDTO.getCpRequestMasters().get(i).getRequestStatus()))
						cpServiceRequestMasterTypeUser.setRequestStatus(
								tyPartialWithdrawDTO.getCpRequestMasters().get(i).getRequestStatus());
					
					if (BSLUtils.isNotNull(tyPartialWithdrawDTO.getCpRequestMasters().get(i).getProcessedDate())) {
						cpServiceRequestMasterTypeUser.setProcessedDate(getCalendarwithTypeDateTime(
								tyPartialWithdrawDTO.getCpRequestMasters().get(i).getProcessedDate()));
					}
					if (BSLUtils.isNotNull(tyPartialWithdrawDTO.getCpRequestMasters().get(i).getProcessedBy()))
						cpServiceRequestMasterTypeUser
								.setProcessedBy(tyPartialWithdrawDTO.getCpRequestMasters().get(i).getProcessedBy());
					if (BSLUtils.isNotNull(tyPartialWithdrawDTO.getCpRequestMasters().get(i).getRecentUpdate())) {
						cpServiceRequestMasterTypeUser.setProcessedDate(getCalendarwithTypeDateTime(
								tyPartialWithdrawDTO.getCpRequestMasters().get(i).getRecentUpdate()));
					}
					cpServiceRequestMasterTypeUsers[i] = cpServiceRequestMasterTypeUser;
				}
				requestMasterColc.setArray(cpServiceRequestMasterTypeUsers);
			}
			
			if (BSLUtils.isNotNull(tyPartialWithdrawDTO.getCpPartialWithdrawalFunds())) {
				cpPartialWithdrawFundsTypeUser = new CpPartialWithdrawFundsTypeUser[tyPartialWithdrawDTO.getCpPartialWithdrawalFunds().size()];
				for (int i = 0; i < tyPartialWithdrawDTO.getCpPartialWithdrawalFunds().size(); i++) {
					logger.info("Transaction Contribution size :"
							+ tyPartialWithdrawDTO.getCpPartialWithdrawalFunds().size());
					
					CpPartialWithdrawFundsTypeUser cppartialWithdrawFundsTypeUser = new CpPartialWithdrawFundsTypeUser();
					cppartialWithdrawFundsTypeUser.setNSerialNo(BigDecimal.valueOf(tyPartialWithdrawDTO.getCpPartialWithdrawalFunds().get(i).getnSerialNo()));
	
					if (BSLUtils.isNotNull(tyPartialWithdrawDTO.getCpPartialWithdrawalFunds().get(i).getvFundType()))
						cppartialWithdrawFundsTypeUser.setVFundType(
								tyPartialWithdrawDTO.getCpPartialWithdrawalFunds().get(i).getvFundType());
					
					if (BSLUtils.isNotNull(tyPartialWithdrawDTO.getCpPartialWithdrawalFunds().get(i).getvFundCode()))
						cppartialWithdrawFundsTypeUser.setVFundCode(
								tyPartialWithdrawDTO.getCpPartialWithdrawalFunds().get(i).getvFundCode());
					
					if (BSLUtils.isNotNull(tyPartialWithdrawDTO.getCpPartialWithdrawalFunds().get(i).getvFundName()))
						cppartialWithdrawFundsTypeUser.setVFundName(
								tyPartialWithdrawDTO.getCpPartialWithdrawalFunds().get(i).getvFundName());
					
					if (BSLUtils.isNotNull(tyPartialWithdrawDTO.getCpPartialWithdrawalFunds().get(i).getnFundValue()))
						cppartialWithdrawFundsTypeUser.setNFundValue(
								tyPartialWithdrawDTO.getCpPartialWithdrawalFunds().get(i).getnFundValue());
					
					if (BSLUtils.isNotNull(tyPartialWithdrawDTO.getCpPartialWithdrawalFunds().get(i).getvCriteria()))
						cppartialWithdrawFundsTypeUser.setVCriteria(
								tyPartialWithdrawDTO.getCpPartialWithdrawalFunds().get(i).getvCriteria());
					
					int creatiea = tyPartialWithdrawDTO.getCpPartialWithdrawalFunds().get(i).getnCriteriaValue();
					BigDecimal bigDecimalCurrency=new BigDecimal(creatiea);
							
					if (BSLUtils.isNotNull(tyPartialWithdrawDTO.getCpPartialWithdrawalFunds().get(i).getnCriteriaValue()))
						cppartialWithdrawFundsTypeUser
								.setNCriteriaValue(bigDecimalCurrency);
					
					if (BSLUtils.isNotNull(tyPartialWithdrawDTO.getCpPartialWithdrawalFunds().get(i).getVinvestementsource()))
						cppartialWithdrawFundsTypeUser.setVInvestmentSource(
								tyPartialWithdrawDTO.getCpPartialWithdrawalFunds().get(i).getVinvestementsource());
					
					if (BSLUtils.isNotNull(tyPartialWithdrawDTO.getCpPartialWithdrawalFunds().get(i).getRemarks()))
						cppartialWithdrawFundsTypeUser.setVRemarks(
								tyPartialWithdrawDTO.getCpPartialWithdrawalFunds().get(i).getRemarks());
					
					int sequncenumber = tyPartialWithdrawDTO.getCpPartialWithdrawalFunds().get(i).getSeqno();
					BigDecimal sequncenumbering=new BigDecimal(sequncenumber);
					if (BSLUtils.isNotNull(tyPartialWithdrawDTO.getCpPartialWithdrawalFunds().get(i).getSeqno()))
						cppartialWithdrawFundsTypeUser.setSeqNo(sequncenumbering);
					
					if (BSLUtils.isNotNull(tyPartialWithdrawDTO.getCpPartialWithdrawalFunds().get(i).getvFundCurrency()))
						cppartialWithdrawFundsTypeUser.setVFundCurrency(
								tyPartialWithdrawDTO.getCpPartialWithdrawalFunds().get(i).getvFundCurrency());
					
					cpPartialWithdrawFundsTypeUser[i] = cppartialWithdrawFundsTypeUser;
					
				}
				partialWithdrawFundsColc.setArray(cpPartialWithdrawFundsTypeUser);
					
		    }
			
			
			// Inserting Fatca details --------
			if (BSLUtils.isNotNull(tyPartialWithdrawDTO.getCpFatca())) {
				cpFatcaTableTypeUsers = new CpFatcaTableTypeUser[tyPartialWithdrawDTO.getCpFatca().size()];

				for (int i = 0; i < tyPartialWithdrawDTO.getCpFatca().size(); i++) {

					CpFatcaTableTypeUser cpFatcaTableTypeUser = new CpFatcaTableTypeUser();

					if (BSLUtils.isNotNull(tyPartialWithdrawDTO.getCpFatca().get(i).getSerialNo())) {
						cpFatcaTableTypeUser.setNSerialNo(
								BigDecimal.valueOf(tyPartialWithdrawDTO.getCpFatca().get(i).getSerialNo()));
					}
					if (BSLUtils.isNotNull(tyPartialWithdrawDTO.getCpFatca().get(i).getServiceRequestNo()))
						cpFatcaTableTypeUser.setServiceRequestNo(BigDecimal
								.valueOf(tyPartialWithdrawDTO.getCpRequestMasters().get(i).getServiceRequestNo()));

					if (BSLUtils.isNotNull(tyPartialWithdrawDTO.getCpFatca().get(i).getIsUsPerson()))
						cpFatcaTableTypeUser.setVUsPerson(tyPartialWithdrawDTO.getCpFatca().get(i).getIsUsPerson());

					if (BSLUtils.isNotNull(tyPartialWithdrawDTO.getCpFatca().get(i).getServiceName())) {
						cpFatcaTableTypeUser
								.setVServiceName(tyPartialWithdrawDTO.getCpFatca().get(i).getServiceName());
					}

					if (BSLUtils.isNotNull(tyPartialWithdrawDTO.getCpFatca().get(i).getTinNo())) {
						cpFatcaTableTypeUser.setVTinNo(tyPartialWithdrawDTO.getCpFatca().get(i).getTinNo());
					}

					if (BSLUtils.isNotNull(tyPartialWithdrawDTO.getCpFatca().get(i).getResidentUAE())) {
						cpFatcaTableTypeUser
								.setVUaeResidency(tyPartialWithdrawDTO.getCpFatca().get(i).getResidentUAE());
					}

					if (BSLUtils.isNotNull(tyPartialWithdrawDTO.getCpFatca().get(i).getTaxMoreThanOne())) {
						cpFatcaTableTypeUser.setVTaxMorethanOnecoun(
								tyPartialWithdrawDTO.getCpFatca().get(i).getTaxMoreThanOne());
					}
					CpFactaCountryDetTypeUser[] cpFactaCountryDetTypeUser = new CpFactaCountryDetTypeUser[10];
					if (tyPartialWithdrawDTO.getCpFatcaCountryDet() != null) {
						if (tyPartialWithdrawDTO.getCpFatcaCountryDet().size() > 0) {
							cpFactaCountryDetTypeUser = new CpFactaCountryDetTypeUser[tyPartialWithdrawDTO
									.getCpFatcaCountryDet().size()];
							for (int l = 0; l < tyPartialWithdrawDTO.getCpFatcaCountryDet().size(); l++) {
								CpFactaCountryDetTypeUser eachRecord = new CpFactaCountryDetTypeUser();

								// eachRecord.setNSeqNo(contributionHolidayDTO.getCpFatcaCountryDet().get(l).getSerialNo());
								eachRecord.setNSerialNo(
										BigDecimal.valueOf(tyPartialWithdrawDTO.getCpFatca().get(i).getSerialNo()));
								eachRecord.setVCountryJuridis(
										tyPartialWithdrawDTO.getCpFatcaCountryDet().get(l).getCountry());
								eachRecord.setVTinReason(
										tyPartialWithdrawDTO.getCpFatcaCountryDet().get(l).getTinOrReason());
								cpFactaCountryDetTypeUser[l] = eachRecord;
								cpFatcaCountryDetColc.setArray(cpFactaCountryDetTypeUser);
								cpFatcaTableTypeUser.setOcCountry(cpFatcaCountryDetColc);
							}
						}
					}
					cpFatcaTableTypeUsers[i] = cpFatcaTableTypeUser;
				}

			}
			if (cpFatcaTableTypeUsers.length > 0) {
				cpFatcaTableTypeUserArray = new CpFatcaTableTypeUserArray();
				cpFatcaTableTypeUserArray.setCpFatcaTableTypeUser(cpFatcaTableTypeUsers);
			}

			
			
			if (partialWithdrawFundsColc.getArray().length > 0 && requestMasterColc.getArray().length > 0) {
				tyPartialWithdrawalFundsUser = new TyPartialWithdrawalFundsUser[1];
				TyPartialWithdrawalFundsUser tyPartialWithdrawalFundsUser1 = new TyPartialWithdrawalFundsUser();
				tyPartialWithdrawalFundsUser1.setLcServiceColc(requestMasterColc);
				tyPartialWithdrawalFundsUser1.setLcPartialWdFundsColc(partialWithdrawFundsColc);
				tyPartialWithdrawalFundsUser[0] = tyPartialWithdrawalFundsUser1;
				valuesArray.setTyPartialWithdrawalFundsUser(tyPartialWithdrawalFundsUser);
			}
			
			logger.info("Saving Change in partialwithdrawn:Transaction DTO size :"+ valuesArray.getTyPartialWithdrawalFundsUser().length);
			if (valuesArray.getTyPartialWithdrawalFundsUser().length > 0) {
				BfnPartialWithdrawalFunds bfnPartialWithdrawalFunds = new BfnPartialWithdrawalFunds();
				bfnPartialWithdrawalFunds.setPPartialWithdrawalFunds(valuesArray);
				bfnPartialWithdrawalFunds.setPFatca(cpFatcaTableTypeUserArray);
				
				BfnPartialWithdrawalFundsResponse response = getServiceProxy()
						.bfnPartialWithdrawalFunds(bfnPartialWithdrawalFunds);
				CpExceptionErrorTypeUserArray cpExceptionErrorTypeUserList = response.getResult();
				CpExceptionErrorTypeUser[] cpExceptionErrorTypeUserArray = cpExceptionErrorTypeUserList
						.getCpExceptionErrorTypeUser();
				
				if (BSLUtils.isNotNull(cpExceptionErrorTypeUserArray)) {
					for (int i = 0; i < cpExceptionErrorTypeUserArray.length; i++) {
						CpExceptionErrorTypeUser cpExceptionErrorTypeUser = cpExceptionErrorTypeUserArray[i];
						if (cpExceptionErrorTypeUser.getErrSqlCode().equalsIgnoreCase("PASS")) {
							logger.info("Redirection and switching transaction status: "
									+ cpExceptionErrorTypeUser.getErrSqlCode());
							return "PASS";
						} else {
							for(int i1 =0;i1<tyPartialWithdrawDTO.getCpRequestMasters().size();i1++) {
								boolean status;
								CpLogsErros error = new CpLogsErros();
								error.setPolicyno(tyPartialWithdrawDTO.getCpRequestMasters().get(i1).getPolicyNo());
								error.setReqno(tyPartialWithdrawDTO.getCpRequestMasters().get(i1).getServiceRequestNo());
								error.setType(tyPartialWithdrawDTO.getCpRequestMasters().get(i1).getServiceRequestType());
								error.setUserid(tyPartialWithdrawDTO.getCpRequestMasters().get(i1).getUserId());
								error.setException(cpExceptionErrorTypeUser.getErrSqlErrDesc());
								status= cpLogsErrosBL.insertexception(error);
							}
							
							logger.info("Redirection and switching transaction status:::::::::: "
									+ cpExceptionErrorTypeUser.getErrSqlCode());
							logger.info("Redirection and switching Transaction error code::::::::::::: "
									+ cpExceptionErrorTypeUser.getErrSqlCode());
							logger.info("Redirection and switching Transaction error Description:::::::::: "
									+ cpExceptionErrorTypeUser.getErrSqlErrDesc());
							logger.info("Redirection and switching Transaction service Request no:::::::::: "
									+ cpExceptionErrorTypeUser.getServiceRequestNo());
						}
					}
				}
			}
		}
		return "Fail";
	}
	
}
