package com.nearGroup.util;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.nearGroup.modal.XmppUser;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class XMPPUtill {

private static Logger logger = Logger.getLogger(XMPPUtill.class);
	
	
	public static void getRegisterdXmppUser(XmppUser xmppUser,String status)
	{
		String path = "";
		Gson gson = new Gson();
		String jsonInput = gson.toJson(xmppUser);
//
//		// Setting the request path
		if(status.equalsIgnoreCase("Add"))
		{
		 path = Constants.XMPP_REST_API_USERS_URL;
		}else
		{
			path = Constants.XMPP_REST_API_USERS_URL+xmppUser.getUsername();
		}
//
//		// Receiving clientResponse from post
//		ClientResponse clientResponse = xmppHttpService.getPostResponse(path,
//				jsonInput);
		Client client = Client.create();
		// Creating a webResource with the Base URL
		WebResource webResource = client
				.resource(Constants.XMPP_PROTOCOL+"://"+Constants.XMPP_SERVER_URL+":"+Constants.XMPP_HTTP_PORT+Constants.XMPP_REST_API_URL);

		// Fetching response after sending request on the specified path
		ClientResponse clientResponse = webResource.path(path)
				.type(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.header(Constants.AUTHORIZATION, Constants.XMPP_SECRET_KEY)
				.post(ClientResponse.class, jsonInput);

		

		// Throwing ApplicationException if success response was not received
		if (clientResponse.getStatus() != 201) {
			logger.warn("Unable to perform rest job "+clientResponse.getStatus());
		}
		else
		{
			logger.info("XMPP user successfully ["+status+"] into server ["+jsonInput+"]");
		}
	
	}
	public static void updateRegisterdXmppUser(XmppUser xmppUser,String status)
	{
		String path = "";
		Gson gson = new Gson();
		String jsonInput = gson.toJson(xmppUser);
//
//		// Setting the request path
		if(status.equalsIgnoreCase("Add"))
		{
		 path = Constants.XMPP_REST_API_USERS_URL;
		}else
		{
			path = Constants.XMPP_REST_API_USERS_URL+xmppUser.getUsername();
		}
//
//		// Receiving clientResponse from post
//		ClientResponse clientResponse = xmppHttpService.getPostResponse(path,
//				jsonInput);
		Client client = Client.create();
		// Creating a webResource with the Base URL
		WebResource webResource = client
				.resource(Constants.XMPP_PROTOCOL+"://"+Constants.XMPP_SERVER_URL+":"+Constants.XMPP_HTTP_PORT+Constants.XMPP_REST_API_URL);

		// Fetching response after sending request on the specified path
		ClientResponse clientResponse = webResource.path(path)
				.type(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.header(Constants.AUTHORIZATION, Constants.XMPP_SECRET_KEY)
				.put(ClientResponse.class, jsonInput);

		

		// Throwing ApplicationException if success response was not received
		if (clientResponse.getStatus() != 201) {
			logger.warn("Unable to perform rest job "+clientResponse.getStatus());
		}
		else
		{
			logger.info("XMPP user successfully ["+status+"] into server ["+jsonInput+"]");
		}
	
	}
	
	public static boolean joinGroupChat(String gid,int loginId,String fName)
	{
		StringBuilder path = new StringBuilder();
		// Appending CHAT_ROOMS_URL
		path.append(Constants.XMPP_REST_API_CHAT_ROOMS_URL);
		// Appending roomName
		path.append(gid);
		path.append(Constants.SLASH);
		// Appending role
		path.append("members");
		path.append(Constants.SLASH);
		// Appending userName
		path.append(fName+"_"+loginId+Constants.AT_THE_RATE_NEARGROUP);
		path.append(Constants.SLASH);

		// Receiving clientResponse from post
		ClientResponse clientResponse = getPostResponse(path
				.toString());

		// Throwing ApplicationException if success response was not received
		if (clientResponse.getStatus() != 201) {
			logger.error("Error while joing grouo chat room ["+gid+"] user id ["+loginId+"] Response ["+clientResponse.getStatus()+"}");
			return false;
		}
		return true;
		}
	
	public static boolean getJoinTopicChat(String tid,int loginId,String fName)
	{
		// Setting the request path
				StringBuilder path = new StringBuilder();
				// Appending CHAT_ROOMS_URL
				path.append(Constants.XMPP_REST_API_CHAT_ROOMS_URL);
				// Appending topicChatName
				path.append(tid);
				path.append(Constants.SLASH);
				// Appending role
				path.append("members");
				path.append(Constants.SLASH);
				// Appending userName
				path.append(fName+"_"+loginId+Constants.AT_THE_RATE_NEARGROUP);
				path.append(Constants.SLASH);

				// Setting Query Params
				String queryParamKey = Constants.SERVICE_NAME;
				String queryParamValue = Constants.TOPICS_SERVICE_NAME;

				// Receiving clientResponse from post
				ClientResponse clientResponse = getPostResponseWithQueryParam(path.toString(), queryParamKey,
								queryParamValue);

				// Throwing ApplicationException if success response was not received
				if (clientResponse.getStatus() != 201) {
					logger.error("Error while joing topic chat room ["+tid+"] user id ["+loginId+"] Response ["+clientResponse.getStatus()+"}");
					return false;
				}
				return true;
	}
	private static ClientResponse getPostResponseWithQueryParam(String path, String queryParamKey, String queryParamValue) {
		
		Client client = Client.create();

		// Creating a webResource with the Base URL
		WebResource webResource = client
				.resource(Constants.XMPP_PROTOCOL+"://"+Constants.XMPP_SERVER_URL+":"+Constants.XMPP_HTTP_PORT+Constants.XMPP_REST_API_URL);

		// Fetching response after sending request on the specified path
		ClientResponse clientResponse = webResource.path(path)
				.queryParam(queryParamKey, queryParamValue)
				.type(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.header(Constants.AUTHORIZATION, Constants.XMPP_SECRET_KEY)
				.post(ClientResponse.class);

		return clientResponse;
	}
	public static ClientResponse getPostResponse(String path)
			 {
		// Instantiating the jersey client
		Client client = Client.create();

		// Creating a webResource with the Base URL
		WebResource webResource = client
				.resource(Constants.XMPP_PROTOCOL+"://"+Constants.XMPP_SERVER_URL+":"+Constants.XMPP_HTTP_PORT+Constants.XMPP_REST_API_URL);

		// Fetching response after sending request on the specified path
		ClientResponse clientResponse = webResource.path(path)
				.type(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.header(Constants.AUTHORIZATION, Constants.XMPP_SECRET_KEY)
				.post(ClientResponse.class);

		return clientResponse;
	}

/*	public static boolean isSubscribeUser(String suId, String xmpp_server) {
		Roster roster = OneClickConstants.connection.getRoster();
    	Collection<RosterEntry> entries = roster.getEntries();
    	        for(RosterEntry entry : entries) {
    	            
		            if((suId+"@"+xmpp_server).equals(entry.getUser()))
		            		{
		            	System.out.println((suId+"@"+xmpp_server).equals(entry.getUser()));
		            	return false;
		            	
		            		}
		
	}
    	        return true;
	}
	*/
/*	public static void subscrib(String to)
    {
		Presence subscribe = new Presence(Presence.Type.subscribe);
    	subscribe.setTo(to+"@"+OneClickConstants.xmpp_server);
    	OneClickConstants.connection.sendPacket(subscribe);
    }
	public static void unSubscrib(String to)
    {
		Presence subscribe = new Presence(Presence.Type.unsubscribe);
    	subscribe.setTo(to+"@"+OneClickConstants.xmpp_server);
    	OneClickConstants.connection.sendPacket(subscribe);
    }
*/	/* public class MyMessageListener implements MessageListener {
		 
	        @Override
	        public void processMessage(Chat chat, Message message) {
	            String from = message.getFrom();
	            String body = message.getBody();
	            System.out.println(String.format("Received message '%1$s' from %2$s", body, from));
	        }
	         
	    }*/
	
//	public static void enableXmppFlagInPractice(String emgrUid,String password,String xmpp_Server,String port,String domain,String xmppBoshPort,String XmppStandardPort) {
//	
//		java.sql.Connection connection = null;
//		StringBuilder sql = new StringBuilder("update ers_practice set XMPP_FLAG = 'Y',XMPP_USER_PASSWORD = ?,XMPP_SERVER = ? ,XMPP_PORT = ? , XMPP_DOMIN = ? ,XMPP_BOSH_PORT = ? ,XMPP_STANDARD_PORT = ? where E_MANAGER_PRACTICE_ID = ?");
//		java.sql.PreparedStatement ps = null;
//		try
//		{
//			
//			connection = DBManager.getConnection(OneClickConstants.ERS_DS);
//			ps = connection.prepareStatement(sql.toString());
//			ps.setString(1, password);
//			ps.setString(2, xmpp_Server);
//			ps.setString(3, port);
//			ps.setString(4, domain);
//			ps.setString(5, xmppBoshPort);
//			ps.setString(6, XmppStandardPort);
//			ps.setInt(7, Integer.parseInt(emgrUid));
//			ps.executeUpdate();
//		} catch (Exception e) {
//			logger.info("Error While updateing xmpp flag:",e);
//			
//		} finally {
//			try {
//				
//				if (ps != null) {
//					ps.close();
//					ps = null;
//				}
//				if (connection != null) {
//					connection.close();
//					connection = null;
//				}
//			} catch (SQLException e) {
//				logger.info("Error While Closing Resources:", e);
//			}
//		}
//	}
//	public static int[] getBulkUpdateXmppFlag(List<PracticeEntity> plist,String status) {
//
//		java.sql.Connection  connection = null;
//		java.sql.PreparedStatement ps = null;
//		int count[] = null;
//		StringBuilder sql = new StringBuilder("update ers_practice set XMPP_FLAG = ?,XMPP_USER_PASSWORD = ?,XMPP_SERVER = ?,XMPP_PORT = ?,XMPP_DOMIN = ?,XMPP_BOSH_PORT = ?,XMPP_STANDARD_PORT = ? where E_MANAGER_PRACTICE_ID = ?");
//		try
//		{
//			connection = DBManager.getConnection(OneClickConstants.ERS_DS);
//			connection.setAutoCommit(false);
//			ps = connection.prepareStatement(sql.toString());
//			 
//			for(PracticeEntity obj : plist)
//			{
//				if(obj.getXmpp_flag().equalsIgnoreCase("N"))
//				{
//					ps.setString(1, status);
//					ps.setString(2, obj.getXmpp_user_password());
//					ps.setString(3, obj.getXmpp_Server());
//					ps.setString(4, obj.getXmpp_port());
//					ps.setString(5, obj.getXmpp_domain());
//					ps.setString(6, obj.getXmpp_bosh_port());
//					ps.setString(7, obj.getXmpp_standard_port());
//					ps.setInt(8, Integer.parseInt(obj.geteManagerPracticeId()));
//					ps.addBatch();
//				}
//				
//			}
//			 count = ps.executeBatch();
//			connection.commit();
//		} catch (Exception e) {
//			logger.info("Error While updateing bulk xmpp flag:",e);
//			
//		} finally {
//			try {
//				
//				if (ps != null) {
//					ps.close();
//					ps = null;
//				}
//				if (connection != null) {
//					connection.close();
//					connection = null;
//				}
//			} catch (SQLException e) {
//				logger.info("Error While Closing Resources:", e);
//			}
//		}
//		return count;
//	}
//	public static int[] getBulkUpdateXmppTechFlag(
//			List<TechProfileEntity> techList, String status) {
//		
//		java.sql.Connection  connection = null;
//		java.sql.PreparedStatement ps = null;
//		int count[] = null;
//		StringBuilder sql = new StringBuilder("update ers_tech_profile set XMPP_FLAG = ? where E_MGR_U_ID = ?");
//		try
//		{
//			connection = DBManager.getConnection(OneClickConstants.ERS_DS);
//			connection.setAutoCommit(false);
//			ps = connection.prepareStatement(sql.toString());
//			 
//			for(TechProfileEntity obj : techList)
//			{
//				if(obj.getXmpp_status().equalsIgnoreCase("N"))
//				{
//					ps.setString(1, status);
//					ps.setInt(2, Integer.parseInt(obj.geteManagerPracticeId()));
//					ps.addBatch();
//				}
//				
//			}
//			 count = ps.executeBatch();
//			connection.commit();
//		} catch (Exception e) {
//			logger.info("Error While updateing bulk Tech Profile xmpp flag:",e);
//			
//		} finally {
//			try {
//				
//				if (ps != null) {
//					ps.close();
//					ps = null;
//				}
//				if (connection != null) {
//					connection.close();
//					connection = null;
//				}
//			} catch (SQLException e) {
//				logger.info("Error While Closing Resources:", e);
//			}
//		}
//		return count;
//	}
//	
//	public static int getUpdateSingleTechFlag(String eManagerUserId,String password)
//	{
//
//		java.sql.Connection  connection = null;
//		java.sql.PreparedStatement ps = null;
//		int count = -1;
//		StringBuilder sql = new StringBuilder("update ers_tech_profile set XMPP_FLAG = 'Y',XMPP_PASSWORD = ? where E_MGR_U_ID = ?");
//		try
//		{
//			
//			connection = DBManager.getConnection(OneClickConstants.ERS_DS);
//			ps = connection.prepareStatement(sql.toString());
//			ps.setString(1, password);
//			ps.setString(2, eManagerUserId);
//			count = ps.executeUpdate();
//			
//		} catch (Exception e) {
//			logger.info("Error While updateing xmpp single user flag:",e);
//			
//		} finally {
//			try {
//				
//				if (ps != null) {
//					ps.close();
//					ps = null;
//				}
//				if (connection != null) {
//					connection.close();
//					connection = null;
//				}
//			} catch (SQLException e) {
//				logger.info("Error While Closing Resources:", e);
//			}
//		}
//		return count;
//	}
//	
//
	
//	public static void sendSchudelMessage(String ersId,PracticeEntity xmppInfo,TechProfileEntity techInfo)
//	{
//		
//	        if(xmppInfo.getXmpp_standard_port() != null && xmppInfo.getXmpp_standard_port().length() > 1){
//	        XmppManager xmppm = new XmppManager(techInfo.geteMUserId(),techInfo.getXmpp_password(),xmppInfo.getXmpp_domain(),xmppInfo.getXmpp_standard_port(),xmppInfo.getXmpp_domain());
//	        xmppm.init();
//	        xmppm.performLogin();
//	        for(String info : ersId.split("#"))
//			{
//		        if(OneClickConstants.XMPP_MODE.equalsIgnoreCase("Smack"))
//		        {
//			xmppm.sendMessage(info.split(",")[0]+":"+"RetriveJobs", xmppInfo.geteManagerPracticeId().trim()+"@"+xmppInfo.getXmpp_domain()+"/"+info.split(",")[0]+"/"+info.split(",")[1]);
//		        }
//			}
//			xmppm.destroy();
//	        }
//	        else if(OneClickConstants.XMPP_MODE.equalsIgnoreCase("IPWork"))
//	        {
//	        	if(xmppInfo.getXmpp_standard_port() != null && xmppInfo.getXmpp_standard_port().length() > 1){
//	        	XmppIpWorkManager obji = new XmppIpWorkManager(techInfo.geteMUserId(),techInfo.getXmpp_password(),xmppInfo.getXmpp_domain(),Integer.parseInt(xmppInfo.getXmpp_standard_port()),xmppInfo.getXmpp_domain());
//	        	obji.init();
//	        	 for(String info : ersId.split("#"))
//	 			{
//	 		        if(OneClickConstants.XMPP_MODE.equalsIgnoreCase("Smack"))
//	 		        {
//	        	obji.sendMessage(info.split(",")[0]+":"+"RetriveJobs", xmppInfo.geteManagerPracticeId().trim()+"@"+xmppInfo.getXmpp_domain()+"/"+info.split(",")[0]+"/"+info.split(",")[1]);
//	 		        }
//	 			}
//	        	obji.destroy();}
//	        }
//
//	
//	return;	
//	}
}
