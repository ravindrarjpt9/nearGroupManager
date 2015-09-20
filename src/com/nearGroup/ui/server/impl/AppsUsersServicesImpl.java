package com.nearGroup.ui.server.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.impl.schema.StscChecker;

import XcoreXipworkssslX90X4675.lo;

import com.nearGroup.db.DBManager;
import com.nearGroup.modal.AppUsers;
import com.nearGroup.ui.server.AppsUsersServices;
import com.nearGroup.util.Constants;
import com.nearGroup.util.Helper;
import com.nearGroup.util.NearGroupDaoUtil;

public class AppsUsersServicesImpl implements AppsUsersServices {
	private static final Logger logger = Logger.getLogger(AppsUsersServicesImpl.class);

	@Override
	public int getCountOfAppsUser(String type, String value, String joinDate) {
		StringBuilder sql = new StringBuilder("select count(id) from users");
		if (!value.trim().endsWith(""))
			sql.append(" where ").append(type).append(" like '").append(value.trim()).append("%' ");
		if (!joinDate.trim().equals("") && value.trim().endsWith(""))
			sql.append(" and date(USER_CREATION_TIME)='").append(joinDate.trim()).append("'");
		else if (joinDate.trim().equals("") && !value.trim().endsWith(""))
			sql.append(" where date(USER_CREATION_TIME)='").append(joinDate.trim()).append("'");
		int count = 0;
		try {
			count = NearGroupDaoUtil.getTotalRecord(sql.toString(), Constants.ALPHA_DS);
		} catch (Exception e) {
			logger.error("Error while getting count of apps users count :", e);
		}
		return count;

	}

	@Override
	public List<AppUsers> getListOfUsers(String sql) {
		List<AppUsers> list = new ArrayList<AppUsers>();
		try (Connection connection = DBManager.getConnection(Constants.ALPHA_DS); PreparedStatement ps = connection.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new AppUsers(rs.getString("ID"), rs.getString("MODIFIED_TIME"), rs.getString("CITY"), rs.getString("DEVICE_TYPE"), rs.getString("DOB"), rs.getString("EMAIL"), rs
						.getString("FB_RELATIONSHIP_STATUS"), Constants.FACEBOOK_URL + rs.getString("FB_USER_ID"), rs.getString("FIRST_NAME"), rs.getString("INDUSTRY"), rs.getString("JOB_TYPE"), rs
						.getString("LOCATION"), rs.getString("LOCALITY"), rs.getString("NUM_OF_FRIENDS"), rs.getString("PUSH_ID"), rs.getString("USER_CREATION_TIME"), rs.getString("USER_LIKE_COUNT"),
						rs.getString("USER_SPAM_COUNT"), rs.getString("USER_SCORE"), rs.getString("USER_STATUS")));
			}

		} catch (Exception e) {
			logger.error("Error while getting list of app users list[" + sql + "]", e);
		}
		return list;
	}

	@Override
	public String getUpdateUserStatus(String id, String loginUser, String status, String pushId, String messages) {
		String sql = "update users set USER_STATUS = ? where id = ?";
		String sqlLog = "insert into USERS_STATUS_LOG(U_ID,MODIFY_BY,STATUS,MESSAGE,GCM_MSG_ID) value(?,?,?,?,?)";
		try (Connection alphaConnection = DBManager.getConnection(Constants.ALPHA_DS);
				Connection managerConnection = DBManager.getConnection(Constants.NEAR_GROUP_DS);
				PreparedStatement psUser = alphaConnection.prepareStatement(sql);
				PreparedStatement psLog = managerConnection.prepareStatement(sqlLog)) {

			psUser.setString(1, status.trim());
			psUser.setString(2, id.trim());
			psLog.setString(1, id.trim());
			psLog.setString(2, loginUser.trim());
			psLog.setString(3, status.trim());
			psLog.setString(4, messages.trim());
			if (psUser.executeUpdate() > 0) {
				String msgStatus = Helper.sendUserNotification(pushId, status + ":" + messages);
				psLog.setString(5, msgStatus);
				psLog.executeUpdate();
				logger.info("Update user [" + id + "] status [" + status + "] with message [" + messages + "]");
				return "succes";
			}
		} catch (Exception e) {
			logger.error("Error while updateing users [" + id + "] status [" + status + "] with message [" + messages + "]", e);
		}
		return "fail";
	}

	public String getDeletedUserFromDb(String id) {
		String sqlGroupUser = "delete  from groups_users where user_id = ?";
		String sqlRegistrations = "delete from `registrations` where user_id = ?";
		String sqlUsers = "delete from users where id = ?";
		// String groupId =
		// "select group_id from groups_users where user_id = ?";
		// String groupIdGroupCount =
		// "select id,users_count from groups where id in";
		// String updateGroupUserCount =
		// "update `groups` set USERS_COUNT = ? where id = ?";
		try (Connection connection = DBManager.getConnection(Constants.ALPHA_DS);
				PreparedStatement psSqlGroupUser = connection.prepareStatement(sqlGroupUser);
				PreparedStatement psSqlRegistrations = connection.prepareStatement(sqlRegistrations);
				PreparedStatement psSqlUsers = connection.prepareStatement(sqlUsers)) {
			psSqlGroupUser.setString(1, id);
			psSqlGroupUser.executeUpdate();
			psSqlRegistrations.setString(1, id);
			psSqlRegistrations.executeUpdate();
			psSqlUsers.setString(1, id);
			psSqlUsers.executeUpdate();
			return "succes";
		} catch (Exception e) {
			logger.error("Error while delete user id [" + id + "]", e);
		}
		return "fail";
	}
}
