
/**
 * SynapseWsCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package synapse.ws;

    /**
     *  SynapseWsCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class SynapseWsCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public SynapseWsCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public SynapseWsCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for submitCampaigns method
            * override this method for handling normal response from submitCampaigns operation
            */
           public void receiveResultsubmitCampaigns(
                    synapse.ws.SynapseWsStub.SubmitCampaignsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from submitCampaigns operation
           */
            public void receiveErrorsubmitCampaigns(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for submitMessage method
            * override this method for handling normal response from submitMessage operation
            */
           public void receiveResultsubmitMessage(
                    synapse.ws.SynapseWsStub.SubmitMessageResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from submitMessage operation
           */
            public void receiveErrorsubmitMessage(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getUserExpiryDate method
            * override this method for handling normal response from getUserExpiryDate operation
            */
           public void receiveResultgetUserExpiryDate(
                    synapse.ws.SynapseWsStub.GetUserExpiryDateResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getUserExpiryDate operation
           */
            public void receiveErrorgetUserExpiryDate(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getCreditsExpiryDate method
            * override this method for handling normal response from getCreditsExpiryDate operation
            */
           public void receiveResultgetCreditsExpiryDate(
                   synapse.ws.SynapseWsStub.GetCreditsExpiryDateResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getCreditsExpiryDate operation
           */
            public void receiveErrorgetCreditsExpiryDate(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getAvailableCredits method
            * override this method for handling normal response from getAvailableCredits operation
            */
           public void receiveResultgetAvailableCredits(
                    synapse.ws.SynapseWsStub.GetAvailableCreditsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getAvailableCredits operation
           */
            public void receiveErrorgetAvailableCredits(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getCampaignStatus method
            * override this method for handling normal response from getCampaignStatus operation
            */
           public void receiveResultgetCampaignStatus(
                 synapse.ws.SynapseWsStub.GetCampaignStatusResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getCampaignStatus operation
           */
            public void receiveErrorgetCampaignStatus(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getMessageStatus method
            * override this method for handling normal response from getMessageStatus operation
            */
           public void receiveResultgetMessageStatus(
                    synapse.ws.SynapseWsStub.GetMessageStatusResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getMessageStatus operation
           */
            public void receiveErrorgetMessageStatus(java.lang.Exception e) {
            }
                


    }
    