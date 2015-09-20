package com.nearGroup.ui.server;

import java.util.Map;

import com.nearGroup.modal.Groups;
import com.nearGroup.modal.Topic;

public interface HomeServices {

	// Group
	public int getGroupCount(String type,String value);
	public Map<String, Groups> getListOfGroups(String sql,int uid);
	
	public int getTopicCount(String type,String value,String topicType,String ownerName,String gid);
	public Map<String, Topic> getListOfTopic(String sql,int uid,String type,String value,String topicType,String ownerName,String gid);
	
	
	
	
}
