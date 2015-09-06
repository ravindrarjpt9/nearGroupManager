package com.nearGroup.ui.server;

import java.util.ArrayList;

import com.nearGroup.modal.Users;

public interface UserServices {

	public String insertNewTechProfile(String techProfileType,String firstName,String middelName,String lastName,
			String password,String email,String mobile,String status,String loginUser);
	public int getTotalTechProfiles(String type,String value);
	
	public ArrayList<Users> getAllTechProfiles(String sql);
}
