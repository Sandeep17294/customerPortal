package com.aetins.customerportal.web.common;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Constants
{

	public static final String SUCCESS = "success";

    public static final String ADMIN_SUCCESS = "adminSuccess";

    public static final String BUSINESS_SUCCESS = "businessSuccess";

    public static final String USER_GROUP = "U";

    public static final String ADMIN_GROUP = "A";

    public static final String BUSINESS_GROUP = "B";

  //added by vikas
    public static final String CHANGE_STATUS = "C";
    public static final String ACTIVE_STATUS = "A";
	
	public static final String POLICYDETAILS="searchpolicy";
 
    public static final String FAIL = "fail";

    public static final String Fail = "fail";

    public static final String FEEDBACKINFO = "ViewFeedbackDetails";

    public static final String DOWNTIMESTATUS = "Down";

    public static final String DOWNSTATUS = "Up";

    public static final String DOCUMENTUPLOAD = "Document Upload";

    public static final String REPLYFEEDBACK = "Reply Feedback";

    public static final String IDENTIFICATION = "IDENTIFICATION";
    public static final String POLICY_INFORCE_STATUS = "NB010";
    public static final String POLICY_OUTSTANDING_STATUS = "PENDING";
    public static final String CLSTATUS = "CLSTATUS";
    public static final String BASECURRENCY = "BASECR";
    public static final String TITLE = "TITLE";
    public static final String DEPT_CODE= "DEPT_CODE";
    
    public static final String MTITLE = "MTITLE";
    public static final String FTITLE = "FTITLE";
       public static final String IDENTYPE = "IDENTYPE";
    
    public static final String EN = "EN";
    
    
    
    //Malik 
    public static final String ADMNUSERLISTLOCKSTATUSYES = "Lock Status Successfully Changed to Active";
    public static final String ADMNUSERLISTLOCKSTATUSNO = "Lock Status Successfully Changed to In-Active";
    
    public static final String ADMNUSERLISTSTATUSACTIVE = "User Status Successfully Changed to Active";
    public static final String ADMNUSERLISTSTATUSINACTIVE = "User Status Successfully Changed to In-Active";
    
    public static final String ADMNSUBMIT = "Data Processed Successfully";
    public static final String ADMNFAIL = "Please be informed that data processing failed. Kindly try again";

    

   // Encryption Key
   // public static final String KEY = "q3s6v9y$B&E)H@Mc";
    public static final String KEY = "abcdefgh";

    public static final String INIT_VECTOR = "44e9942f5036dca3";
    
    public static final String CUSTOMER_PORTAL="Customer Portal";
    public static final String APP_URL="/activateUser.jsf?token=";
    public static final String REGISTRATION_SUBJECT = "AL HILAL Customer Portal Registration Activation Link";
   
    public static final String FEEDBACK_STATUS = "Status";
    public static final String FEEDBACK_CLOSED = "Closed";
    public static final String FEEDBACK_CLOSE_STATUS = "y";
    public static final String FEEDBACK_TYPE_F = "f";
    public static final String FEEDBACK_TYPE_C = "c";  
    public static final String ESTSTEMENT_REDIRECTION = "e-statementSent";
    public static final String FEEDBACK_YES = "Y";
    public static final String FEEDBACK_NO = "N";
    
    public static final String preventDoubleSubmitFlag = "preventDoubleSubmitFlag";
    public static final String SESSION_EXPIRY_PAGE = "/sessionExpire.jsf";
    public static final String FORGOT_PASSWORD_PAGE = "/forgetPassword.jsf";
    public static final String LOGIN_REDIRECT_PAGE = "login.jsf";
    public static final String LOGIN_PAGE = "/login.jsf";
    public static final String PATH_SEPARATOR = "/";
    public static final String DASHBOARD_HOME="/home.jsf";
    public static final String USER_NAME="USERNAME";
    public static final String CLIENT_IP_ADDRESS="CLIENT_IP_ADDRESS";
    public static final String POLICY_STATUS="IN-FORCE";
public static final String CUSTOMER_REDIRECTION="REDIRECTION";
    public static final String MANDATORY="Y";    
    public static final String REQUIRED="Y";
    public static final String MANDATORYNO="N";
    public static final String REQUIREDYES="Y";
 public static final String PARTIAL_WITHDRAWAL_SERVICE_REQUEST_TYPE = "PARTIAL WITHDRAWAL";
    public static final String CONTRIBUTION_HOLIDAY_SERVICE_REQUEST_TYPE = "CONTRIBUTION HOLIDAY";
    public static final String PROTECTION_BENIFIT_SERVICE_REQUEST_TYPE = "PROTECTION BENEFIT";
    public static final String LANGUAGE_EN = "EN";
    public static final String BANK = "CUST_BANK";
   // public static final String INACTIVE = "I";
    //public static final String PRINTFORM = "P";
    public static final String PRINTFORM = "AWIT";
    public static final String INACTIVE = "PEND";
    //added by harmain
    public static final String ADD_BENEFICIARY = "BENEFICIARY";
	public static final String REINSTATEMENT_SERVICE_REQUEST_TYPE="REINSTATEMENT";
    public static final String CUSTOMER_SWITCHING="SWITCHING"; 
    public static final String CUSTOMER_SWITCHING_BOTH="SWITCHING_";  
    public static final String SWITCHING_SERVICE_REQUEST_TYPE ="SWITCHING";
    
    public static final String ADDTRANSACTION="A";
    public static final String DELETETRANSACTION="R";
    public static final String EDITTRANSACTION="M";
    public static final String RELATIONSHIP="RELATION";
    public static final String CITY="STATE";
    public static final String COUNTRY="COUNTRY";
    public static final String POSTALCODE="POST";
    public static final String CONTRIBUTION_ALTERATION_SERVICE_REQUEST_TYPE = "CONTRIBUTION ALTERATION";
                                                                               
    public static final String SUM_ASSURED_ALTERATION_SERVICE_REQUEST_TYPE = "SUM ASSURED ALTERATION";
    public static final String CONTRIBUTION_ALTERATION="CONTRIBUTION ALTERATION";
    public static final String SUM_ASSURED_ALTERATION="SUM ASSURED ALTERATION";
    public static final String TERM_ALTERATION="TERM ALTERATION";
     public static final String AUTO_DEBIT_CHANGE_REQUEST_TYPE = "AUTO DEBIT CHANGE";
    //update information    
    public static final String NF_STATE = "STATE";
    public static final String NF_COUNTRY = "COUNTRY";
    public static final String NF_TITLE = "TITLE";
    public static final String NF_COUNTRY_CODE = "DIAL_CODE";
    public static final String NF_MARITAL = "MARITAL";
    public static final String NF_RELIGION = "RELIGION";
    public static final String NF_RACE = "RACE";
    public static final String NF_LANGUAGE = "LANGUAGE";
    public static final String NF_NATION = "NATIONALITY";
    public static final String UPDATE_INFORMATION = "UPDATE INFORMATION";	
    public static final String UPDATE_INFO_CONTINUE = "PEND";
    public static final String UPDATE_INFO_PRINT = "AWIT";

    public static final String DELETERIDER="R";
    public static final String EDITRIDER = "M";
    public static final String NEWRIDER = "N";
    public static final String RIDER = "RIDER";
    public static final String MEND = "Y";
    public static final String MODIFIEDRIDER = "M";
    
    
    public static final String PARTIAL_WITHDRAW = "PARTIAL WITHDRAW";
    
    public static final String MEND1 = "Y";
    
    public static final String REGTRACK_STATUS_SUCCESS = "S";
    public static final String REGTRACK_STATUS_FAILURE = "F";
    public static final String TRANSACTION_SUCCESS = "AWAP";
    public static final String REDIRECTION_SWITCHING_SERVICE_REQ ="REDIRECTIONANDSWITCHING";
    
    // For CC Email
    
    public static final String FORGET_PASSWORD = "FORGETPASSWORD";
    public static final String CHANGE_PASSWORD = "CHANGEPASSWORD";
    public static final String REGISTRATION = "REGISTRATION";
    public static final String LOGIN = "LOGIN";
    public static final String UNIT_STATEMENT = "UNITSTATEMENT";
    public static final String ADMIN_MODULE = "USERACCOUNTSTATUS";

    public static final String CUSTOMER_REDIRECTIONANDSWITCHING="REDIRECTIONANDSWITCHING";
    public static final String CUSTOMER_PROTECTION_BENIFIT_INC_BASIC_SUM_COVERED="INCREASE BASIC SUM COVERED";
    public static final String CUSTOMER_PROTECTION_BENIFIT_INC_RIDER_SUM_COVERED="INCREASE RIDER SUM COVERED";
    public static final String CUSTOMER_PROTECTION_BENIFIT_DEC_BASIC_SUM_COVERED="DECREASE BASIC SUM COVERED";
    public static final String CUSTOMER_PROTECTION_BENIFIT_DEC_RIDER_SUM_COVERED="DECREASE RIDER SUM COVERED";
    public static final String CUSTOMER_PROTECTION_BENIFIT_ADD_SUP_RIDER="ADD SUPPLEMENTARY RIDER";
    public static final String CUSTOMER_PROTECTION_BENIFIT_DELETE_SUP_RIDER="DELETE SUPPLEMENTARY RIDER";
    public static final String CUSTOMER_CONTRIBUTION_ALTERATION_INC_REGULATION="INCREASE REGULATION CONTRIBUTION";
    public static final String CUSTOMER_CONTRIBUTION_ALTERATION_DEC_REGULATION="DECREASE REGULATION CONTRIBUTION";
   
    
    // for service types
    public static final Map<String, String> SERVICE_TYPES = new LinkedHashMap<String, String>()
    {
    	{
			put("Switching", "S");
			put("Redirection", "R");
			put("Switching and Redirection", "RS");
			put("Reinstatement", "Reinstatement");
			put("Update Information", "Update Information");
			put("Contribution Alteration", "CA");
			put("Increase in Basic Sum covered", "I");
			put("Decrease in Basic Sum covered", "D");
			put("Increase in Rider Sum covered", "IR");
			put("Decrease in Rider Sum covered", "DR");
			put("Addition of Supplimentory Rider", "ARB");
			put("Deletion of Supplimentory Rider", "DRB");			
		}
    };
    
    public static final String RE_INS = "Reinstatement";
    public static final String UPDATE_INFO = "Update Information";
    public static final String CONTRIB_ALT = "CA";
    public static final String QUERY_LATEST_RECORD_QUES = "SELECT * FROM CP_QUESTIONNAIRE A WHERE n_service_id IN (SELECT MAX(n_service_id) FROM CP_QUESTIONNAIRE)";
  
    public static final String QUERY_FETCH_POLICY_DEFERMENT_BY_POLICY_NO="select * from WNMT_DEFER_PREMIUM_REQUEST where V_POLICY_NO=?";
    public static final String QUERY_INSERT_POLICY_DEFERMENT="insert into wnmt_defer_premium_request (N_TRANS_NO,V_POLICY_NO,V_IDEN_CODE,V_IDEN_NO,D_DEFER_START_DT,N_DEFEMENT_PERIOD,V_RECORD_STATUS,V_REQUEST_STATUS,V_REMARKS) values(?,?,?,?,?,?,?,?,?)";
    public static final String QUERY_FETCH_LATEST_TRANS_NO="SELECT A.N_TRANS_NO  FROM WNMT_DEFER_PREMIUM_REQUEST A WHERE n_trans_no IN (SELECT MAX(n_trans_no) FROM WNMT_DEFER_PREMIUM_REQUEST)";
    public static final String _TRANS_NO="N_TRANS_NO";
    public static final String _POLICY_NO="V_POLICY_NO";
    public static final String _IDEN_CODE="V_IDEN_CODE";
    public static final String _IDEN_NO="V_IDEN_NO";
    public static final String _DEFERMENT_START_DATE="D_DEFER_START_DT";
    public static final String _DEFERMENT_PERIOD="N_DEFEMENT_PERIOD";
    public static final String _RECORD_STATUS="V_RECORD_STATUS";
    public static final String _REQUEST_STATUS="V_REQUEST_STATUS";
    public static final String _DEFERMENT_REMARKS="V_REMARKS";
    
    public static final String CLAIM_INTIMATION="CLAIM_INTIMATION";
    
    public static final String CLAIM_INTIMATION_1 = "CLAIM INTIMATION";
    
    /**
     * policy deferment transaction status
     * @return
     */
    public static final Map<String, String> getDefermentLOV(){
    	
    	Map<String, String> map = new HashMap<String, String>();
    	map.put("I", "Intiated");
    	map.put("U", "Under Progress");
    	map.put("A", "Accepted");
    	map.put("R", "Rejected");
    	return map;
    }

}