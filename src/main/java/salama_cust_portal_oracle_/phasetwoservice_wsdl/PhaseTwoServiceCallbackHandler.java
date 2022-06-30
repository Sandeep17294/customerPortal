
/**
 * PhaseTwoServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package salama_cust_portal_oracle_.phasetwoservice_wsdl;

    /**
     *  PhaseTwoServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class PhaseTwoServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public PhaseTwoServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public PhaseTwoServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for bfnContributionHoliday method
            * override this method for handling normal response from bfnContributionHoliday operation
            */
           public void receiveResultbfnContributionHoliday(
                    salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.BfnContributionHolidayResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bfnContributionHoliday operation
           */
            public void receiveErrorbfnContributionHoliday(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bfnNfAddress method
            * override this method for handling normal response from bfnNfAddress operation
            */
           public void receiveResultbfnNfAddress(
                    salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.BfnNfAddressResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bfnNfAddress operation
           */
            public void receiveErrorbfnNfAddress(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bfnNfAddressContacts method
            * override this method for handling normal response from bfnNfAddressContacts operation
            */
           public void receiveResultbfnNfAddressContacts(
                    salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.BfnNfAddressContactsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bfnNfAddressContacts operation
           */
            public void receiveErrorbfnNfAddressContacts(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bfnClaimIntimation method
            * override this method for handling normal response from bfnClaimIntimation operation
            */
           public void receiveResultbfnClaimIntimation(
                    salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.BfnClaimIntimationResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bfnClaimIntimation operation
           */
            public void receiveErrorbfnClaimIntimation(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bpcFatca method
            * override this method for handling normal response from bpcFatca operation
            */
           public void receiveResultbpcFatca(
                    salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.BpcFatcaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bpcFatca operation
           */
            public void receiveErrorbpcFatca(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bfnReinStatement method
            * override this method for handling normal response from bfnReinStatement operation
            */
           public void receiveResultbfnReinStatement(
                    salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.BfnReinStatementResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bfnReinStatement operation
           */
            public void receiveErrorbfnReinStatement(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bfnNfPersonalDetails method
            * override this method for handling normal response from bfnNfPersonalDetails operation
            */
           public void receiveResultbfnNfPersonalDetails(
                    salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.BfnNfPersonalDetailsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bfnNfPersonalDetails operation
           */
            public void receiveErrorbfnNfPersonalDetails(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bfnServiceRequestCount method
            * override this method for handling normal response from bfnServiceRequestCount operation
            */
           public void receiveResultbfnServiceRequestCount(
                    salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.BfnServiceRequestCountResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bfnServiceRequestCount operation
           */
            public void receiveErrorbfnServiceRequestCount(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bfnNfPersonalContacts method
            * override this method for handling normal response from bfnNfPersonalContacts operation
            */
           public void receiveResultbfnNfPersonalContacts(
                    salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.BfnNfPersonalContactsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bfnNfPersonalContacts operation
           */
            public void receiveErrorbfnNfPersonalContacts(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bfnRedirectionSwitching method
            * override this method for handling normal response from bfnRedirectionSwitching operation
            */
           public void receiveResultbfnRedirectionSwitching(
                    salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.BfnRedirectionSwitchingResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bfnRedirectionSwitching operation
           */
            public void receiveErrorbfnRedirectionSwitching(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bfnOtpSettings method
            * override this method for handling normal response from bfnOtpSettings operation
            */
           public void receiveResultbfnOtpSettings(
                    salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.BfnOtpSettingsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bfnOtpSettings operation
           */
            public void receiveErrorbfnOtpSettings(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bfnChangeInContribution method
            * override this method for handling normal response from bfnChangeInContribution operation
            */
           public void receiveResultbfnChangeInContribution(
                    salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.BfnChangeInContributionResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bfnChangeInContribution operation
           */
            public void receiveErrorbfnChangeInContribution(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bfnPartialWithdrawalFunds method
            * override this method for handling normal response from bfnPartialWithdrawalFunds operation
            */
           public void receiveResultbfnPartialWithdrawalFunds(
                    salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.BfnPartialWithdrawalFundsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bfnPartialWithdrawalFunds operation
           */
            public void receiveErrorbfnPartialWithdrawalFunds(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bfnProductionBenefit method
            * override this method for handling normal response from bfnProductionBenefit operation
            */
           public void receiveResultbfnProductionBenefit(
                    salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub.BfnProductionBenefitResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bfnProductionBenefit operation
           */
            public void receiveErrorbfnProductionBenefit(java.lang.Exception e) {
            }
                


    }
    