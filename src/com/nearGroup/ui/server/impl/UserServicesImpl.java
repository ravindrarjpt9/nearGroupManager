package com.nearGroup.ui.server.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import XcoreXipworkssslX90X4675.lo;

import com.nearGroup.db.DBManager;
import com.nearGroup.modal.UserLog;
import com.nearGroup.modal.Users;
import com.nearGroup.ui.server.UserServices;
import com.nearGroup.util.Constants;
import com.nearGroup.util.Helper;
import com.nearGroup.util.NearGroupDaoUtil;

public class UserServicesImpl implements UserServices {

	private static final Logger logger = Logger.getLogger(UserServicesImpl.class);

	@Override
	public String insertNewTechProfile(String techProfileType, String firstName, String middelName, String lastName, String password, String email, String mobile, String userStatus, String loginUser) {
		logger.info("Going to create new user [" + email + "]");
		String status = "";
		String sql = "insert into USERS(FIRST_NAME,MIDDLE_NAME,LAST_NAME,PASSWORD,EMAIL,MOBILE_NUM,CREATED_BY,USER_STATUS,ROLE) " + "value(?,?,?,?,?,?,?,?,?)";
		// Connection connection = null;
		// PreparedStatement psTechProfile = null;
		// connection = DBManager.getConnection(Constants.NEAR_GROUP_DS);
		try (Connection connection = DBManager.getConnection(Constants.NEAR_GROUP_DS); PreparedStatement psTechProfile = connection.prepareStatement(sql)) {

			psTechProfile.setString(1, firstName);
			psTechProfile.setString(2, middelName);
			psTechProfile.setString(3, lastName);
			psTechProfile.setString(4, Helper.encrypt(password, Constants.ENCRYPTED_KEY));
			psTechProfile.setString(5, email);
			psTechProfile.setString(6, mobile);
			psTechProfile.setString(7, loginUser);
			psTechProfile.setString(8, userStatus);
			psTechProfile.setString(9, techProfileType);
			psTechProfile.executeUpdate();
			logger.info("Successfully inserted new Tech profile Record with email [" + email + "]");

			status = "insert";
		} catch (SQLException e) {
			logger.error("Error while createing new user [" + email + "]", e);
		}
		return status;
	}

	@Override
	public int getTotalTechProfiles(String type, String value) {

		int count = 0;
		StringBuilder sql = new StringBuilder("select count(id) from USERS ");
		if (!value.trim().equals(""))
			sql.append(" where ").append(type).append(" like '").append(value.trim()).append("%' ");
		try {
			count = NearGroupDaoUtil.getTotalRecord(sql.toString(), Constants.NEAR_GROUP_DS);
		} catch (SQLException e) {
			logger.error("Error while getting count of users : ", e);

		}

		return count;
	}

	@Override
	public ArrayList<Users> getAllTechProfiles(String sql) {

		// Connection connection = null;

		ResultSet rs = null;
		ArrayList<Users> list = new ArrayList<>();

		try (Connection connection = DBManager.getConnection(Constants.NEAR_GROUP_DS); PreparedStatement ps = connection.prepareStatement(sql)) {

			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Users(rs.getInt("ID"), Helper.chkNull(rs.getString("ROLE")), Helper.chkNull(rs.getString("FIRST_NAME")), Helper.chkNull(rs.getString("LAST_NAME")), Helper.chkNull(rs
						.getString("MIDDLE_NAME")), Helper.decrypt(Helper.chkNull(rs.getString("PASSWORD")), Constants.ENCRYPTED_KEY).equalsIgnoreCase("Default@123") ? "Default@123" : Helper
						.chkNull(rs.getString("PASSWORD")), Helper.chkNull(rs.getString("EMAIL")), Helper.chkNull(rs.getString("MOBILE_NUM")), Helper.chkNull(rs.getString("CREATED_BY")), Helper
						.chkNull(rs.getString("CREATED_TIME")), Helper.chkNull(rs.getString("MODIFY_BY")), Helper.chkNull(rs.getString("MODIFIED_TIME")), Helper.chkNull(rs.getString("USER_STATUS"))));
			}
		} catch (Exception e) {
			logger.error("Error while getting list of Tech Users sql [" + sql + "]", e);
		}
		return list;
	}

	@Override
	public String updateTechProfile(String id, String techProfileType, String firstName, String middelName, String lastName, String passworsStatus, String password, String email, String mobile,
			String status, String loginUser) {

		StringBuilder sql = new StringBuilder("update users set FIRST_NAME=?,MIDDLE_NAME=?,LAST_NAME=?");
		if (passworsStatus != null && passworsStatus.equalsIgnoreCase("true"))
			sql.append(",PASSWORD = ?");
		else
			sql.append(",EMAIL = ?,MOBILE_NUM=?,USER_STATUS=?,ROLE=? ,MODIFIED_TIME = NOW(),MODIFY_BY =? where id=?");

		try (Connection connection = DBManager.getConnection(Constants.NEAR_GROUP_DS); PreparedStatement ps = connection.prepareStatement(sql.toString())) {
			int i = 1;
			ps.setString(i++, firstName);
			ps.setString(i++, middelName);
			ps.setString(i++, lastName);
			if (passworsStatus != null && passworsStatus.equalsIgnoreCase("true"))
				ps.setString(i++, "Default@123");
			ps.setString(i++, email);
			ps.setString(i++, mobile);
			ps.setString(i++, status);
			ps.setString(i++, techProfileType);
			ps.setString(i++, loginUser);
			ps.setString(i++, id);
			if (ps.executeUpdate() != 0)
				;
			return "update";

		} catch (Exception e) {
			logger.error("Error while update user profile [" + id + "," + firstName + "," + email + "]", e);
		}
		return null;
	}

	public String isValidUser(String email, String password) {
		String sql = "select id from users where email=? and password = ?";
		String status = "false";
		String id = "";
		try (Connection connection = DBManager.getConnection(Constants.NEAR_GROUP_DS); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, email.trim().toLowerCase());
			ps.setString(2, Helper.encrypt(password.trim(), Constants.ENCRYPTED_KEY));
			ResultSet rs = ps.executeQuery();
			while (rs.next())
				id = rs.getString("id");
			if (id != null && id.length() > 0)
				return id;
		} catch (Exception e) {
			logger.error("Error while validatin user login [" + email + "]", e);
		}
		return status;
	}

	public Users getTechProfile(String id) {
		Users user = null;
		String sql = "select * from users where id=?";
		try (Connection connection = DBManager.getConnection(Constants.NEAR_GROUP_DS); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, id.trim());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				user = new Users(Integer.parseInt(rs.getString("ID")), rs.getString("ROLE"), rs.getString("FIRST_NAME"), rs.getString("LAST_NAME"), rs.getString("MIDDLE_NAME"), Helper.decrypt(
						rs.getString("PASSWORD"), Constants.ENCRYPTED_KEY), rs.getString("EMAIL"), rs.getString("MOBILE_NUM"), rs.getString("CREATED_BY"), rs.getString("CREATED_TIME"),
						rs.getString("MODIFY_BY"), rs.getString("MODIFIED_TIME"), rs.getString("USER_STATUS"));
			}
		} catch (Exception e) {
			logger.error("Error while getting user information baesd on id [" + id + "]", e);
		}
		return user;
	}

	@Override
	public String insertUserLoginLog(String fName, String lName, String role) {
		String sql = "insert into users_log(FIRST_NAME,LAST_NAME,ROLE) value(?,?,?)";
		int id = 0;
		try (Connection connection = DBManager.getConnection(Constants.NEAR_GROUP_DS); PreparedStatement ps = connection.prepareStatement(sql); Statement psSelect = connection.createStatement()) {
			ps.setString(1, fName.trim());
			ps.setString(2, lName.trim());
			ps.setString(3, role.trim());
			ps.executeUpdate();

			ResultSet rs = psSelect.executeQuery("SELECT LAST_INSERT_ID()");
			while (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (Exception e) {
			logger.error("Error while inserting user loging details :[" + fName + "],[" + lName + "]", e);
		}
		return String.valueOf(id);
	}

	public void getLogOut(String id) {
		String sql = "update users_log set LOGOUT_TIME=CURRENT_TIMESTAMP where id = ?";
		try (Connection connection = DBManager.getConnection(Constants.NEAR_GROUP_DS); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, id);
			ps.executeUpdate();
			logger.info("successfully updateing use logout time [" + id + "]");
		} catch (Exception e) {
			logger.error("Error while updateing logout time of users [" + id + "]", e);
		}
	}

	@Override
	public String resetPassword(int id, String newPassword) {
		logger.info("Updateing password for ID [" + id + "]");
		String sql = "update USERS set PASSWORD = ? where id = ?";
		String status = "fail";
		try (Connection connection = DBManager.getConnection(Constants.NEAR_GROUP_DS); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, Helper.encrypt(newPassword, Constants.ENCRYPTED_KEY));
			ps.setInt(2, id);
			if (ps.executeUpdate() > 0) {
				return "success";
			}
		} catch (Exception e) {
			logger.error("Error while updateing user password :[" + id + "]", e);
		}
		return status;
	}

	@Override
	public int getTotalUserLogs(String type, String value, String loginDate) {

		StringBuilder sql = new StringBuilder("select count(ID) from USERS_LOG");
		if (!value.trim().endsWith(""))
			sql.append(" where ").append(type).append(" like '").append(value.trim()).append("%' ");
		if (!loginDate.trim().equals("") && value.trim().endsWith(""))
			sql.append(" and date(LOGIN_TIME)='").append(loginDate.trim()).append("'");
		else if (loginDate.trim().equals("") && !value.trim().endsWith(""))
			sql.append(" where date(LOGIN_TIME)='").append(loginDate.trim()).append("'");

		int count = 0;
		try {
			count = NearGroupDaoUtil.getTotalRecord(sql.toString(), Constants.NEAR_GROUP_DS);
		} catch (Exception e) {
			logger.error("Error while getting count of user log :", e);
		}
		return count;

	}

	@Override
	public List<UserLog> getAllUserLoginLog(String sql) {

		List<UserLog> list = new ArrayList<>();
		try (Connection connection = DBManager.getConnection(Constants.NEAR_GROUP_DS); PreparedStatement ps = connection.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new UserLog(rs.getString("ID"), rs.getString("FIRST_NAME"), rs.getString("LAST_NAME"), rs.getString("ROLE"), Helper.chkNull(rs.getString("LOGIN_TIME")), Helper.chkNull(rs
						.getString("LOGOUT_TIME"))));
			}
		} catch (Exception e) {
			logger.error("Error whle getting  list of user log :[" + sql + "]", e);
		}
		return list;
	}
}
