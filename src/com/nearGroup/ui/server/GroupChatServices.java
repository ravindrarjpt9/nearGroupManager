package com.nearGroup.ui.server;

public interface GroupChatServices {

	public String joinGroupChat(String gid,int loginId,String fName);
	public String joinTopicChat(String gid,int loginId,String fName);
}
