package com.aetins.customerportal.core.listeners.startup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.aetins.customerportal.core.enums.PRIVILEGES;
import com.aetins.customerportal.core.enums.ROLES;
import com.aetins.customerportal.core.enums.TRANSACTIONDEPARTMENT;
import com.aetins.customerportal.core.enums.TRANSACTIONDEPARTMENTCODES;
import com.aetins.customerportal.core.enums.TRANSACTIONS;
import com.aetins.customerportal.web.dao.ITransactionDeptDao;
import com.aetins.customerportal.web.dao.PrivilegeDao;
import com.aetins.customerportal.web.dao.RolesDao;
import com.aetins.customerportal.web.entity.CPTransactionDept;
import com.aetins.customerportal.web.entity.Privilege;
import com.aetins.customerportal.web.entity.Role;

/**
 * These listener is to set up initial data at the time of application initialization 
 * @author avinash
 *
 */
@Component
public class SetupDataLoaderListeners implements ApplicationListener<ContextRefreshedEvent> {
	
	private Logger logger = LoggerFactory.getLogger(SetupDataLoaderListeners.class);

    private boolean alreadySetup = false;

    @Autowired
    private RolesDao roleDao;

    @Autowired
    private PrivilegeDao privilegeDao;
    
    @Autowired
    private ITransactionDeptDao transactionDeptDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    
    /* (non-Javadoc)
     * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
     */
    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

        logger.info("=================================================================================");
        logger.info("   ===========================================================================   ");
        logger.info("  ============================= DATA SET UP INITIALIZATION =====================  ");
        logger.info("   ====================================== STARTED ==============================  ");
        logger.info("=================================================================================");
        
		/* == create initial privileges == */
        
		/* ADMIN USER PRIVILEGES */
        final Privilege _portal_Mangement_Privilege = createPrivilegeIfNotFound(PRIVILEGES.PORTAL_MANAGEMENT_PRIVILEGE.toString());
        final Privilege _user_Status_Change_Privilege = createPrivilegeIfNotFound(PRIVILEGES.USER_STATUS_CHANGE_PRIVILEGE.toString());
        final Privilege _feedback_Close_Privilege = createPrivilegeIfNotFound(PRIVILEGES.FEEDBACK_CLOSE_PRIVILEGE.toString());
           
		/* USER PRIVILEGES */
        final Privilege _transaction_Privilege = createPrivilegeIfNotFound(PRIVILEGES.TRANSACTION_PRIVILEGE.toString());
        final Privilege _change_Security_Ques_Privilege = createPrivilegeIfNotFound(PRIVILEGES.CHANGE_SECURITY_QUES_PRIVILEGE.toString());
        final Privilege _e_Statement_Privilege = createPrivilegeIfNotFound(PRIVILEGES.E_STATEMENT_PRIVILEGE.toString());
        
		/* BUSINESS USER PRIVILEGES */
        final Privilege _feedback_Reply_Privilege = createPrivilegeIfNotFound(PRIVILEGES.FEEDBACK_REPLY_PRIVILEGE.toString());
        
		/* ASSIGN PRIVILEGES TO ROLES */
        final List<Privilege> _admin_Privileges = new ArrayList<Privilege>(Arrays.asList(_portal_Mangement_Privilege, _user_Status_Change_Privilege, _feedback_Close_Privilege));
        final List<Privilege> _user_Privileges = new ArrayList<Privilege>(Arrays.asList(_transaction_Privilege, _change_Security_Ques_Privilege,_e_Statement_Privilege));
        final List<Privilege> _business_User_Privileges = new ArrayList<Privilege>(Arrays.asList(_feedback_Reply_Privilege));
        
        /* TRANSACTIONS,TRANSACTIONS DEPARTMENT,TRANSACTIONS DEPT CODES */
        CPTransactionDept _UPDATE_TRANSACTION = new CPTransactionDept(TRANSACTIONDEPARTMENT.UNDERWRITING.toString(),TRANSACTIONS.UPDATEINFORMATION_TRANSACTION.toString(),TRANSACTIONDEPARTMENTCODES.UW.toString());
        CPTransactionDept _REDIRECTION_TRANSACTION = new CPTransactionDept(TRANSACTIONDEPARTMENT.POLICYSERVICING.toString(),TRANSACTIONS.REDIRECTION_TRANSACTION.toString(),TRANSACTIONDEPARTMENTCODES.PS.toString());
        CPTransactionDept _SWITCHING_TRANSACTION = new CPTransactionDept(TRANSACTIONDEPARTMENT.POLICYSERVICING.toString(),TRANSACTIONS.SWITCHING_TRANSACTION.toString(),TRANSACTIONDEPARTMENTCODES.PS.toString());
        CPTransactionDept _REDIRECTION_SWITCHING_TRANSACTION = new CPTransactionDept(TRANSACTIONDEPARTMENT.POLICYSERVICING.toString(),TRANSACTIONS.REDIRECTION_SWITCHING_TRANSACTION.toString(),TRANSACTIONDEPARTMENTCODES.PS.toString());
        CPTransactionDept _CONTRIBUTION_ALTERATION_TRANSACTION = new CPTransactionDept(TRANSACTIONDEPARTMENT.POLICYSERVICING.toString(),TRANSACTIONS.CONTRIBUTION_ALTERATION_TRANSACTION.toString(),TRANSACTIONDEPARTMENTCODES.PS.toString());
        CPTransactionDept _PROTECTION_BENEFIT_TRANSACTION = new CPTransactionDept(TRANSACTIONDEPARTMENT.POLICYSERVICING.toString(),TRANSACTIONS.PROTECTION_BENEFIT_TRANSACTION.toString(),TRANSACTIONDEPARTMENTCODES.PS.toString());
        CPTransactionDept _PARTIALWITHDRAWAL_TRANSACTION = new CPTransactionDept(TRANSACTIONDEPARTMENT.POLICYSERVICING.toString(),TRANSACTIONS.PARTIALWITHDRAWAL_TRANSACTION.toString(),TRANSACTIONDEPARTMENTCODES.PS.toString());
        CPTransactionDept _REINSTATEMENT_TRANSACTION = new CPTransactionDept(TRANSACTIONDEPARTMENT.POLICYSERVICING.toString(),TRANSACTIONS.REINSTATEMENT_TRANSACTION.toString(),TRANSACTIONDEPARTMENTCODES.PS.toString());
        CPTransactionDept _CLAIMINTIMATION_TRANSACTION = new CPTransactionDept(TRANSACTIONDEPARTMENT.CLAIMS.toString(),TRANSACTIONS.CLAIMINTIMATION_TRANSACTION.toString(),TRANSACTIONDEPARTMENTCODES.CL.toString());
        
        /* == create transaction dept == */
        checkTransactionDeptIfNotFound(_UPDATE_TRANSACTION);
        checkTransactionDeptIfNotFound(_REDIRECTION_TRANSACTION);
        checkTransactionDeptIfNotFound(_SWITCHING_TRANSACTION);
        checkTransactionDeptIfNotFound(_REDIRECTION_SWITCHING_TRANSACTION);
        checkTransactionDeptIfNotFound(_PARTIALWITHDRAWAL_TRANSACTION);
        checkTransactionDeptIfNotFound(_REINSTATEMENT_TRANSACTION);
        checkTransactionDeptIfNotFound(_CLAIMINTIMATION_TRANSACTION);
        checkTransactionDeptIfNotFound(_CONTRIBUTION_ALTERATION_TRANSACTION);
        checkTransactionDeptIfNotFound(_PROTECTION_BENEFIT_TRANSACTION);
        
		/* == create initial roles == */
        createRoleIfNotFound(ROLES.ROLE_ADMIN.name(), _admin_Privileges);
        createRoleIfNotFound(ROLES.ROLE_USER.name(), _user_Privileges);
        createRoleIfNotFound(ROLES.ROLE_BUSINESS_USER.name(), _business_User_Privileges);
        
        
        

        alreadySetup = true;
        
        logger.info("=================================================================================");
        logger.info("   ===========================================================================   ");
        logger.info("  ============================= DATA SET UP INITIALIZATION =====================  ");
        logger.info("   ====================================== ENDED ==============================  ");
        logger.info("=================================================================================");
    }

    /**
     * create a privilege if not found
     * @param name
     * @return
     */
    @Transactional
    private final Privilege createPrivilegeIfNotFound(final String name) {
        Privilege privilege = privilegeDao.findByName(name);
        
        if (privilege == null) {
        	logger.info("New Privilege is creating with name: {} as its not found",name);
            privilege = new Privilege(name);
            privilege = privilegeDao.savePrivilege(privilege);
            logger.info("PRIVILEGE CREATED: {} ",privilege);
        }
         else {
        	 logger.info("Privilege is already exists with name: {}",name);
        }
        
        return privilege;
    }

    /**
     * create role if not found
     * @param name
     * @param privileges
     * @return
     */
    @Transactional
    private final Role createRoleIfNotFound(final String name, final Collection<Privilege> privileges) {
        Role role = roleDao.findByName(name);
        if (role == null) {
            role = new Role(name);
            role.setPrivileges(privileges);
            role = roleDao.saveRoles(role);
            logger.info("ROLE CREATED: {} ",role);
        }
         else {
        	 logger.info("ROLE is already exists : {} ",name);
        }
        return role;
    }
    
	  
   /**
     * Check transaction dept if not found
     * @param transactionName
     * @return
     */
    @Transactional
    private void checkTransactionDeptIfNotFound(final CPTransactionDept transactionDept){ 
    
    	
    	CPTransactionDept findByTransaction = transactionDeptDao.findByTransaction(transactionDept.getTransactionName());
    	
    	if(findByTransaction==null){
    		logger.info("TRANSACTION DEPT NOT FOUND FOR TRANSACTION: {} ",transactionDept.getTransactionName());
    		transactionDeptDao.save(transactionDept);
    		logger.info("TRANSACTION DEPT CREATED: {} ",transactionDept);
    	}
    }

}