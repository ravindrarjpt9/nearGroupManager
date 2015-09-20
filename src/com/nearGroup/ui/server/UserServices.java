package com.nearGroup.ui.server;

import java.util.ArrayList;
import java.util.List;

import com.nearGroup.modal.UserLog;
import com.nearGroup.modal.Users;

public interface UserServices {

	public String insertNewTechProfile(String techProfileType,String firstName,String middelName,String lastName,
			String password,String email,String mobile,String status,String loginUser);
	
	public String updateTechProfile(String id,String techProfileType,String firstName,String middelName,String lastName
			,String passworsStatus,String password,String email,String mobile,String status,String loginUser);
	public int getTotalTechProfiles(String type,String value);
	
	public ArrayList<Users> getAllTechProfiles(String sql);
	
	
	public String isValidUser(String email , String password);
	public Users getTechProfile(String id);
	
	public String insertUserLoginLog(String fName,String lName,String role);
	public String resetPassword(int id,String newPassword,String firstName);
	
	public int getTotalUserLogs(String type,String value ,String loginDate);
	
	public List<UserLog> getAllUserLoginLog(String sql);
	
	public String getUserProfile(int id);
	
	
	
}
