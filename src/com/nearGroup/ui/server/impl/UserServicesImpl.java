package com.nearGroup.ui.server.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.nearGroup.db.DBManager;
import com.nearGroup.modal.Users;
import com.nearGroup.ui.server.UserServices;
import com.nearGroup.util.Constants;
import com.nearGroup.util.Helper;
import com.nearGroup.util.NearGroupDaoUtil;

public class UserServicesImpl implements UserServices {

	
	private static final Logger logger = Logger.getLogger(UserServicesImpl.class);
	
	
	@Override
	public String insertNewTechProfile(String techProfileType,
			String firstName, String middelName, String lastName,
			 String password, String email, String mobile,
			String userStatus,String loginUser) {
		logger.info("Going to create new user ["+email+"]");
		String status = "";
		String sql = "insert into USERS(FIRST_NAME,MIDDLE_NAME,LAST_NAME,PASSWORD,EMAIL,MOBILE_NUM,CREATED_BY,USER_STATUS,ROLE) "
				+ "value(?,?,?,?,?,?,?,?,?)";
		//Connection connection = null;
		//PreparedStatement psTechProfile = null;
		//connection = DBManager.getConnection(Constants.NEAR_GROUP_DS);
		try(Connection connection = DBManager.getConnection(Constants.NEAR_GROUP_DS);
				PreparedStatement psTechProfile = connection.prepareStatement(sql)
				)
		{
			
			
			psTechProfile.setString(1, firstName);
			psTechProfile.setString(2, middelName);
			psTechProfile.setString(3, lastName);
			psTechProfile.setString(4, Helper.encrypt(password,Constants.ENCRYPTED_KEY));
			psTechProfile.setString(5, email);
			psTechProfile.setString(6, mobile);
			psTechProfile.setString(7, loginUser);
			psTechProfile.setString(8, userStatus);
			psTechProfile.setString(9, techProfileType);
			psTechProfile.executeUpdate();
			logger.info("Successfully inserted new Tech profile Record with email ["+email+"]");
			
			status = "insert";
		}catch(SQLException e)
		{
			logger.error("Error while createing new user ["+email+"]",e);
		}
		return status;
	}
	
	@Override
	public int getTotalTechProfiles(String type, String value) {
		
		int count = 0;
		 StringBuilder sql =new StringBuilder("select count(id) from USERS ");
		 if(!value.trim().equals(""))
			 sql.append(" where ").append(type).append(" like '").append(value.trim()).append("%' ");
        try
        {
       	 count = NearGroupDaoUtil.getTotalRecord(sql.toString(),Constants.NEAR_GROUP_DS);
		} catch (SQLException e) {
			logger.error("Error while getting count of users : ",e);
		
		}

		return count;
	}
	@Override
	public ArrayList<Users> getAllTechProfiles(String sql) {
		
		//Connection connection = null;
		
		ResultSet rs = null;
		ArrayList<Users> list = new ArrayList<>();
		
		try(Connection connection = DBManager.getConnection(Constants.NEAR_GROUP_DS);
				PreparedStatement ps = connection.prepareStatement(sql))
		{
			
			rs = ps.executeQuery();
			while(rs.next())
			{
				list.add(new Users(rs.getInt("ID"), Helper.chkNull(rs.getString("ROLE")), Helper.chkNull(rs.getString("FIRST_NAME")),
						Helper.chkNull(rs.getString("LAST_NAME")), Helper.chkNull(rs.getString("MIDDLE_NAME")), Helper.chkNull(rs.getString("PASSWORD")), Helper.chkNull(rs.getString("EMAIL")),
								Helper.chkNull(rs.getString("MOBILE_NUM")), Helper.chkNull(rs.getString("CREATED_BY")), Helper.chkNull(rs.getString("CREATED_TIME")),
										Helper.chkNull(rs.getString("MODIFY_BY")), Helper.chkNull(rs.getString("MODIFIED_TIME")), Helper.chkNull(rs.getString("USER_STATUS"))));
			}
		}catch (Exception e) {
			logger.error("Error while getting list of Tech Users sql ["+sql+"]",e);
		}
		return list;
	}
}
