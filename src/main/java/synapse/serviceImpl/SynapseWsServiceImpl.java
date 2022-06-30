package synapse.serviceImpl;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.aetins.customerportal.web.entity.CpLogsErros;
import com.aetins.customerportal.web.service.CpLogsErrosBL;
import com.aetins.customerportal.web.utils.AppSettingURL;

import synapse.service.SynapseWsService;
import synapse.ws.SynapseWsStub;
import synapse.ws.SynapseWsStub.GetAvailableCredits;
import synapse.ws.SynapseWsStub.GetAvailableCreditsResponse;
import synapse.ws.SynapseWsStub.GetCampaignStatus;
import synapse.ws.SynapseWsStub.GetCampaignStatusResponse;
import synapse.ws.SynapseWsStub.GetCreditsExpiryDate;
import synapse.ws.SynapseWsStub.GetCreditsExpiryDateResponse;
import synapse.ws.SynapseWsStub.GetMessageStatus;
import synapse.ws.SynapseWsStub.GetMessageStatusResponse;
import synapse.ws.SynapseWsStub.GetUserExpiryDate;
import synapse.ws.SynapseWsStub.GetUserExpiryDateResponse;
import synapse.ws.SynapseWsStub.SubmitCampaigns;
import synapse.ws.SynapseWsStub.SubmitCampaignsResponse;
import synapse.ws.SynapseWsStub.SubmitMessage;
import synapse.ws.SynapseWsStub.SubmitMessageResponse;



/**
 * @author satendra
 * @since Mar 2017
 *
 */
public class SynapseWsServiceImpl implements SynapseWsService {

	private SynapseWsStub synapseWsStub;
	private static final Logger logger = Logger.getLogger(SynapseWsServiceImpl.class);

	public SynapseWsServiceImpl() {
		logger.info("SynapseWsServiceImpl class instantiated");
		try {
			synapseWsStub = new SynapseWsStub(AppSettingURL.SYNAPSE_WS);
		} catch (AxisFault e) {
			logger.error("Axis webservice not available" + e.getMessage());
		}
	}

	@Override
	public String getAvailableCredits(String strUser, String strPwd) throws AxisFault, RemoteException {
		GetAvailableCredits availableCredits = new GetAvailableCredits();
		availableCredits.setStrUser(strUser);
		availableCredits.setStrPwd(strPwd);
		GetAvailableCreditsResponse availableCreditsResponse = synapseWsStub.getAvailableCredits(availableCredits);
		String creditResult = availableCreditsResponse.getGetAvailableCreditsResult();
		return creditResult;
	}

	@Override
	public String getCampaignStatus(String strUser, String strPwd, String messageID) throws AxisFault, RemoteException {
		GetCampaignStatus campaignStatus = new GetCampaignStatus();
		campaignStatus.setStrUser(strUser);
		campaignStatus.setStrPwd(strPwd);
		campaignStatus.setMessageID(messageID);
		GetCampaignStatusResponse campaignStatusResponse = synapseWsStub.getCampaignStatus(campaignStatus);
		String campaignStatusResult = campaignStatusResponse.getGetCampaignStatusResult();
		return campaignStatusResult;
	}

	@Override
	public String getCreditsExpiryDate(String strUser, String strPwd) throws AxisFault, RemoteException {
		GetCreditsExpiryDate creditsExpiryDate = new GetCreditsExpiryDate();
		creditsExpiryDate.setStrUser(strUser);
		creditsExpiryDate.setStrPwd(strPwd);
		GetCreditsExpiryDateResponse creditsExpiryDateResponse = synapseWsStub.getCreditsExpiryDate(creditsExpiryDate);
		String creditsExpiryDateResult = creditsExpiryDateResponse.getGetCreditsExpiryDateResult();
		return creditsExpiryDateResult;
	}

	@Override
	public String getMessageStatus(String strUser, String strPwd, String messageID) throws AxisFault, RemoteException {
		GetMessageStatus messageStatus = new GetMessageStatus();
		messageStatus.setStrUser(strUser);
		messageStatus.setStrPwd(strPwd);
		messageStatus.setMessageID(messageID);
		GetMessageStatusResponse messageStatusResponse = synapseWsStub.getMessageStatus(messageStatus);
		String messageStatusResult = messageStatusResponse.getGetMessageStatusResult();
		return messageStatusResult;
	}

	@Override
	public String getUserExpiryDate(String strUser, String strPwd) throws AxisFault, RemoteException {

		GetUserExpiryDate userExpiryDate = new GetUserExpiryDate();
		userExpiryDate.setStrUser(strUser);
		userExpiryDate.setStrPwd(strPwd);
		GetUserExpiryDateResponse userExpiryDateResponse = synapseWsStub.getUserExpiryDate(userExpiryDate);
		String userExpiryDateResult = userExpiryDateResponse.getGetUserExpiryDateResult();
		return userExpiryDateResult;
	}

	@Override
	public String submitCampaigns(String userName, String password, String message, String oMesgType, String senderID,
			String msgDestinations, String strSchedule, String scheduleTime, String dlrEnable)
			throws AxisFault, RemoteException {
		SubmitCampaigns submitCampaigns = new SubmitCampaigns();
		submitCampaigns.setUserName(userName);
		submitCampaigns.setPassword(password);
		submitCampaigns.setMessage(message);
		submitCampaigns.setOMesgType(oMesgType);
		submitCampaigns.setSenderID(senderID);
		submitCampaigns.setMsgDestinations(msgDestinations);
		submitCampaigns.setStrSchedule(strSchedule);
		submitCampaigns.setScheduleTime(scheduleTime);
		submitCampaigns.setDLREnable(dlrEnable);
		SubmitCampaignsResponse submitCampaignsResponse = synapseWsStub.submitCampaigns(submitCampaigns);
		String submitCampaignsResult = submitCampaignsResponse.getSubmitCampaignsResult();
		return submitCampaignsResult;
	}

	@Autowired
	CpLogsErrosBL cpLogsErrosBL;
	
	@Override
	public String submitMessage(String userName, String password, String message, String oMesgType, String senderID,
			String msgDestinations, String dlrEnable) throws AxisFault, RemoteException {
		SubmitMessage submitMessage = new SubmitMessage();
		submitMessage.setUserName(userName);
		submitMessage.setPassword(password);
		submitMessage.setMessage(message);
		submitMessage.setOMesgType(oMesgType);
		submitMessage.setSenderID(senderID);
		submitMessage.setMsgDestinations(msgDestinations);
		submitMessage.setDLREnable(dlrEnable);
		logger.info(":::::SMS MESSAGE CONTENT:::::"+msgDestinations);
		logger.info(":::::SMS MESSAGE CONTENT:::::"+msgDestinations);
		SubmitMessageResponse submitMessageResponse = synapseWsStub.submitMessage(submitMessage);
		String submitMessageResult = submitMessageResponse.getSubmitMessageResult();
        logger.info(":::::SMS MESSAGE RESPONSE:::::"+submitMessageResult);
        logger.info(":::::SMS MESSAGE RESPONSE:::::"+submitMessageResult);
		return submitMessageResult;
	}

}
