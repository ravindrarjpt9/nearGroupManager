package com.nearGroup.ui.server;

import java.util.List;

import com.nearGroup.modal.AppUsers;

public interface AppsUsersServices {

	public int getCountOfAppsUser(String type,String value,String joinDate);
	public List<AppUsers> getListOfUsers(String sql);
	public String getUpdateUserStatus(String id,String loginUser,String status,String pushId,String messages);
	
	public String getDeletedUserFromDb(String id);
	
}
