package synapse.service;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;

/**
 * @author satendra
 * @since Mar 2017
 *
 */
public interface SynapseWsService {

	public String getAvailableCredits(String strUser, String strPwd) throws AxisFault, RemoteException;

	public String getCampaignStatus(String strUser, String strPwd, String messageID) throws AxisFault, RemoteException;

	public String getCreditsExpiryDate(String strUser, String strPwd) throws AxisFault, RemoteException;

	public String getMessageStatus(String strUser, String strPwd, String messageID) throws AxisFault, RemoteException;

	public String getUserExpiryDate(String strUser, String strPwd) throws AxisFault, RemoteException;

	public String submitCampaigns(String userName, String password, String message, String oMesgType, String senderID,
			String msgDestinations, String strSchedule, String scheduleTime, String dlrEnable)
			throws AxisFault, RemoteException;

	public String submitMessage(String userName, String password, String message, String oMesgType, String senderID,
			String msgDestinations, String dlrEnable) throws AxisFault, RemoteException;
}
