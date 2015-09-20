package com.nearGroup.ui.server.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

import com.nearGroup.db.DBManager;
import com.nearGroup.ui.server.GroupChatServices;
import com.nearGroup.util.Constants;
import com.nearGroup.util.XMPPUtill;

public class GroupChatServicesImpl implements GroupChatServices {

	private static final Logger logger = Logger.getLogger(GroupChatServicesImpl.class);

	
	@Override
	public String joinGroupChat(String gid, int loginId,String fName) {
		String sql = "insert into GROUP_JOIN(GID,U_ID) value(?,?)";
		String status = "fail";
		logger.info("Going to join Group [+"+gid+"]" +" with u_id "+loginId);
		try(Connection connection = DBManager.getConnection(Constants.NEAR_GROUP_DS);
				PreparedStatement ps = connection.prepareStatement(sql))
				{
			   ps.setString(1, gid);
			   ps.setInt(2, loginId);
			   if(XMPPUtill.joinGroupChat(gid, loginId,fName))
			   ps.executeUpdate();
				   return "succes";
				}catch (Exception e) {
					logger.error("Error while joing group ["+gid+"]",e);
				}
		return status;
	}
	@Override
	public String joinTopicChat(String tid, int loginId, String fName) {
		String sql = "insert into TOPIC_JOIN(TID,U_ID) value(?,?)";
		String status = "fail";
		logger.info("Going to join TOPIC [+"+tid+"]" +" with u_id "+loginId);
		try(Connection connection = DBManager.getConnection(Constants.NEAR_GROUP_DS);
				PreparedStatement ps = connection.prepareStatement(sql))
				{
			   ps.setString(1, tid);
			   ps.setInt(2, loginId);
			   if(XMPPUtill.getJoinTopicChat(tid, loginId,fName))
			   ps.executeUpdate();
				   return "succes";
				}catch (Exception e) {
					logger.error("Error while joing group ["+tid+"]",e);
				}
		return status;
	}
	

}
