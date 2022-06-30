
/**
 * PhaseOneServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package salama_cust_portal_oracle_.phaseoneservice_wsdl;

    /**
     *  PhaseOneServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class PhaseOneServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public PhaseOneServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public PhaseOneServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for bfnGetPolOutsummary method
            * override this method for handling normal response from bfnGetPolOutsummary operation
            */
           public void receiveResultbfnGetPolOutsummary(
                    salama_cust_portal_oracle_.phaseoneservice_wsdl.PhaseOneServiceStub.BfnGetPolOutsummaryResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bfnGetPolOutsummary operation
           */
            public void receiveErrorbfnGetPolOutsummary(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bfnGetCustomerNfaInfo method
            * override this method for handling normal response from bfnGetCustomerNfaInfo operation
            */
           public void receiveResultbfnGetCustomerNfaInfo(
                    salama_cust_portal_oracle_.phaseoneservice_wsdl.PhaseOneServiceStub.BfnGetCustomerNfaInfoResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bfnGetCustomerNfaInfo operation
           */
            public void receiveErrorbfnGetCustomerNfaInfo(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bfnCustomerSummary method
            * override this method for handling normal response from bfnCustomerSummary operation
            */
           public void receiveResultbfnCustomerSummary(
                    salama_cust_portal_oracle_.phaseoneservice_wsdl.PhaseOneServiceStub.BfnCustomerSummaryResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bfnCustomerSummary operation
           */
            public void receiveErrorbfnCustomerSummary(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bfnSaValidation method
            * override this method for handling normal response from bfnSaValidation operation
            */
           public void receiveResultbfnSaValidation(
                    salama_cust_portal_oracle_.phaseoneservice_wsdl.PhaseOneServiceStub.BfnSaValidationResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bfnSaValidation operation
           */
            public void receiveErrorbfnSaValidation(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bfnCustomerPolPayouts method
            * override this method for handling normal response from bfnCustomerPolPayouts operation
            */
           public void receiveResultbfnCustomerPolPayouts(
                    salama_cust_portal_oracle_.phaseoneservice_wsdl.PhaseOneServiceStub.BfnCustomerPolPayoutsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bfnCustomerPolPayouts operation
           */
            public void receiveErrorbfnCustomerPolPayouts(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bfnChkIuAu method
            * override this method for handling normal response from bfnChkIuAu operation
            */
           public void receiveResultbfnChkIuAu(
                    salama_cust_portal_oracle_.phaseoneservice_wsdl.PhaseOneServiceStub.BfnChkIuAuResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bfnChkIuAu operation
           */
            public void receiveErrorbfnChkIuAu(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bfnGetMasterLov method
            * override this method for handling normal response from bfnGetMasterLov operation
            */
           public void receiveResultbfnGetMasterLov(
                    salama_cust_portal_oracle_.phaseoneservice_wsdl.PhaseOneServiceStub.BfnGetMasterLovResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bfnGetMasterLov operation
           */
            public void receiveErrorbfnGetMasterLov(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bfnGetAutodebitList method
            * override this method for handling normal response from bfnGetAutodebitList operation
            */
           public void receiveResultbfnGetAutodebitList(
                    salama_cust_portal_oracle_.phaseoneservice_wsdl.PhaseOneServiceStub.BfnGetAutodebitListResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bfnGetAutodebitList operation
           */
            public void receiveErrorbfnGetAutodebitList(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bfnGetPlanRider method
            * override this method for handling normal response from bfnGetPlanRider operation
            */
           public void receiveResultbfnGetPlanRider(
                    salama_cust_portal_oracle_.phaseoneservice_wsdl.PhaseOneServiceStub.BfnGetPlanRiderResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bfnGetPlanRider operation
           */
            public void receiveErrorbfnGetPlanRider(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bfnCustomerPolicyDetail method
            * override this method for handling normal response from bfnCustomerPolicyDetail operation
            */
           public void receiveResultbfnCustomerPolicyDetail(
                    salama_cust_portal_oracle_.phaseoneservice_wsdl.PhaseOneServiceStub.BfnCustomerPolicyDetailResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bfnCustomerPolicyDetail operation
           */
            public void receiveErrorbfnCustomerPolicyDetail(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bfnGetPolicyRiders method
            * override this method for handling normal response from bfnGetPolicyRiders operation
            */
           public void receiveResultbfnGetPolicyRiders(
                    salama_cust_portal_oracle_.phaseoneservice_wsdl.PhaseOneServiceStub.BfnGetPolicyRidersResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bfnGetPolicyRiders operation
           */
            public void receiveErrorbfnGetPolicyRiders(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bfnCustomerPolList method
            * override this method for handling normal response from bfnCustomerPolList operation
            */
           public void receiveResultbfnCustomerPolList(
                    salama_cust_portal_oracle_.phaseoneservice_wsdl.PhaseOneServiceStub.BfnCustomerPolListResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bfnCustomerPolList operation
           */
            public void receiveErrorbfnCustomerPolList(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bfnGetPolicyPlan method
            * override this method for handling normal response from bfnGetPolicyPlan operation
            */
           public void receiveResultbfnGetPolicyPlan(
                    salama_cust_portal_oracle_.phaseoneservice_wsdl.PhaseOneServiceStub.BfnGetPolicyPlanResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bfnGetPolicyPlan operation
           */
            public void receiveErrorbfnGetPolicyPlan(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bfnCustomerPolOuts method
            * override this method for handling normal response from bfnCustomerPolOuts operation
            */
           public void receiveResultbfnCustomerPolOuts(
                    salama_cust_portal_oracle_.phaseoneservice_wsdl.PhaseOneServiceStub.BfnCustomerPolOutsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bfnCustomerPolOuts operation
           */
            public void receiveErrorbfnCustomerPolOuts(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bpcCpErrorMessage method
            * override this method for handling normal response from bpcCpErrorMessage operation
            */
           public void receiveResultbpcCpErrorMessage(
                    salama_cust_portal_oracle_.phaseoneservice_wsdl.PhaseOneServiceStub.BpcCpErrorMessageResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bpcCpErrorMessage operation
           */
            public void receiveErrorbpcCpErrorMessage(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bfnCustomerLov method
            * override this method for handling normal response from bfnCustomerLov operation
            */
           public void receiveResultbfnCustomerLov(
                    salama_cust_portal_oracle_.phaseoneservice_wsdl.PhaseOneServiceStub.BfnCustomerLovResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bfnCustomerLov operation
           */
            public void receiveErrorbfnCustomerLov(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bfnCustomerPolFunds method
            * override this method for handling normal response from bfnCustomerPolFunds operation
            */
           public void receiveResultbfnCustomerPolFunds(
                    salama_cust_portal_oracle_.phaseoneservice_wsdl.PhaseOneServiceStub.BfnCustomerPolFundsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bfnCustomerPolFunds operation
           */
            public void receiveErrorbfnCustomerPolFunds(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bfnGetReinstateBenefits method
            * override this method for handling normal response from bfnGetReinstateBenefits operation
            */
           public void receiveResultbfnGetReinstateBenefits(
                    salama_cust_portal_oracle_.phaseoneservice_wsdl.PhaseOneServiceStub.BfnGetReinstateBenefitsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bfnGetReinstateBenefits operation
           */
            public void receiveErrorbfnGetReinstateBenefits(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bfnCustomerPolDeposit method
            * override this method for handling normal response from bfnCustomerPolDeposit operation
            */
           public void receiveResultbfnCustomerPolDeposit(
                    salama_cust_portal_oracle_.phaseoneservice_wsdl.PhaseOneServiceStub.BfnCustomerPolDepositResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bfnCustomerPolDeposit operation
           */
            public void receiveErrorbfnCustomerPolDeposit(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bpcInsertDownTime method
            * override this method for handling normal response from bpcInsertDownTime operation
            */
           public void receiveResultbpcInsertDownTime(
                    salama_cust_portal_oracle_.phaseoneservice_wsdl.PhaseOneServiceStub.BpcInsertDownTimeResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bpcInsertDownTime operation
           */
            public void receiveErrorbpcInsertDownTime(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bfnGetCustClaim method
            * override this method for handling normal response from bfnGetCustClaim operation
            */
           public void receiveResultbfnGetCustClaim(
                    salama_cust_portal_oracle_.phaseoneservice_wsdl.PhaseOneServiceStub.BfnGetCustClaimResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bfnGetCustClaim operation
           */
            public void receiveErrorbfnGetCustClaim(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bfnGetNominee method
            * override this method for handling normal response from bfnGetNominee operation
            */
           public void receiveResultbfnGetNominee(
                    salama_cust_portal_oracle_.phaseoneservice_wsdl.PhaseOneServiceStub.BfnGetNomineeResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bfnGetNominee operation
           */
            public void receiveErrorbfnGetNominee(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bpcExceptionHandler method
            * override this method for handling normal response from bpcExceptionHandler operation
            */
           public void receiveResultbpcExceptionHandler(
                    salama_cust_portal_oracle_.phaseoneservice_wsdl.PhaseOneServiceStub.BpcExceptionHandlerResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bpcExceptionHandler operation
           */
            public void receiveErrorbpcExceptionHandler(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bfnGetReceiptSummary method
            * override this method for handling normal response from bfnGetReceiptSummary operation
            */
           public void receiveResultbfnGetReceiptSummary(
                    salama_cust_portal_oracle_.phaseoneservice_wsdl.PhaseOneServiceStub.BfnGetReceiptSummaryResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bfnGetReceiptSummary operation
           */
            public void receiveErrorbfnGetReceiptSummary(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bfnGetRiderList method
            * override this method for handling normal response from bfnGetRiderList operation
            */
           public void receiveResultbfnGetRiderList(
                    salama_cust_portal_oracle_.phaseoneservice_wsdl.PhaseOneServiceStub.BfnGetRiderListResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bfnGetRiderList operation
           */
            public void receiveErrorbfnGetRiderList(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bfnGetServiceNo method
            * override this method for handling normal response from bfnGetServiceNo operation
            */
           public void receiveResultbfnGetServiceNo(
                    salama_cust_portal_oracle_.phaseoneservice_wsdl.PhaseOneServiceStub.BfnGetServiceNoResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bfnGetServiceNo operation
           */
            public void receiveErrorbfnGetServiceNo(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bfnChkPwAllow method
            * override this method for handling normal response from bfnChkPwAllow operation
            */
           public void receiveResultbfnChkPwAllow(
                    salama_cust_portal_oracle_.phaseoneservice_wsdl.PhaseOneServiceStub.BfnChkPwAllowResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bfnChkPwAllow operation
           */
            public void receiveErrorbfnChkPwAllow(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bfnUnitStatement method
            * override this method for handling normal response from bfnUnitStatement operation
            */
           public void receiveResultbfnUnitStatement(
                    salama_cust_portal_oracle_.phaseoneservice_wsdl.PhaseOneServiceStub.BfnUnitStatementResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bfnUnitStatement operation
           */
            public void receiveErrorbfnUnitStatement(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bfnVerifyCustomer method
            * override this method for handling normal response from bfnVerifyCustomer operation
            */
           public void receiveResultbfnVerifyCustomer(
                    salama_cust_portal_oracle_.phaseoneservice_wsdl.PhaseOneServiceStub.BfnVerifyCustomerResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bfnVerifyCustomer operation
           */
            public void receiveErrorbfnVerifyCustomer(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bfnAllowTransaction method
            * override this method for handling normal response from bfnAllowTransaction operation
            */
           public void receiveResultbfnAllowTransaction(
                    salama_cust_portal_oracle_.phaseoneservice_wsdl.PhaseOneServiceStub.BfnAllowTransactionResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bfnAllowTransaction operation
           */
            public void receiveErrorbfnAllowTransaction(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bfnCustomerPolicyLov method
            * override this method for handling normal response from bfnCustomerPolicyLov operation
            */
           public void receiveResultbfnCustomerPolicyLov(
                    salama_cust_portal_oracle_.phaseoneservice_wsdl.PhaseOneServiceStub.BfnCustomerPolicyLovResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bfnCustomerPolicyLov operation
           */
            public void receiveErrorbfnCustomerPolicyLov(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bfnCustomerPolClaims method
            * override this method for handling normal response from bfnCustomerPolClaims operation
            */
           public void receiveResultbfnCustomerPolClaims(
                    salama_cust_portal_oracle_.phaseoneservice_wsdl.PhaseOneServiceStub.BfnCustomerPolClaimsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bfnCustomerPolClaims operation
           */
            public void receiveErrorbfnCustomerPolClaims(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bfnGetReinstate method
            * override this method for handling normal response from bfnGetReinstate operation
            */
           public void receiveResultbfnGetReinstate(
                    salama_cust_portal_oracle_.phaseoneservice_wsdl.PhaseOneServiceStub.BfnGetReinstateResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bfnGetReinstate operation
           */
            public void receiveErrorbfnGetReinstate(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bfnCustomerPolReceipts method
            * override this method for handling normal response from bfnCustomerPolReceipts operation
            */
           public void receiveResultbfnCustomerPolReceipts(
                    salama_cust_portal_oracle_.phaseoneservice_wsdl.PhaseOneServiceStub.BfnCustomerPolReceiptsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bfnCustomerPolReceipts operation
           */
            public void receiveErrorbfnCustomerPolReceipts(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bfnGetCompany method
            * override this method for handling normal response from bfnGetCompany operation
            */
           public void receiveResultbfnGetCompany(
                    salama_cust_portal_oracle_.phaseoneservice_wsdl.PhaseOneServiceStub.BfnGetCompanyResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bfnGetCompany operation
           */
            public void receiveErrorbfnGetCompany(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bfnGetPortalMode method
            * override this method for handling normal response from bfnGetPortalMode operation
            */
           public void receiveResultbfnGetPortalMode(
                    salama_cust_portal_oracle_.phaseoneservice_wsdl.PhaseOneServiceStub.BfnGetPortalModeResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bfnGetPortalMode operation
           */
            public void receiveErrorbfnGetPortalMode(java.lang.Exception e) {
            }
                


    }
    