package com.nearGroup.modal;

public class Topic {

	private int id;
	private String topicnName;
	private String despcription;
	private String topicType;
	private String groupName;
	private String ownerFbId;
	private String ownerName;
	private String like;
	private String spam;
	private String creationTime;
	private String modifiedTime;
	private String gid;
	private int joinFlag = 0;
	
	public Topic(int id, String topicnName, String despcription, String topicType, String groupName, String ownerFbId, String ownerName, String like, String spam, String creationTime,
			String modifiedTime, String gid) {
		
		this.id = id;
		this.topicnName = topicnName;
		this.despcription = despcription;
		this.topicType = topicType;
		this.groupName = groupName;
		this.ownerFbId = ownerFbId;
		this.ownerName = ownerName;
		this.like = like;
		this.spam = spam;
		this.creationTime = creationTime;
		this.modifiedTime = modifiedTime;
		this.gid = gid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTopicnName() {
		return topicnName;
	}
	public void setTopicnName(String topicnName) {
		this.topicnName = topicnName;
	}
	public String getDespcription() {
		return despcription;
	}
	public void setDespcription(String despcription) {
		this.despcription = despcription;
	}
	public String getTopicType() {
		return topicType;
	}
	public void setTopicType(String topicType) {
		this.topicType = topicType;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getOwnerFbId() {
		return ownerFbId;
	}
	public void setOwnerFbId(String ownerFbId) {
		this.ownerFbId = ownerFbId;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getLike() {
		return like;
	}
	public void setLike(String like) {
		this.like = like;
	}
	public String getSpam() {
		return spam;
	}
	public void setSpam(String spam) {
		this.spam = spam;
	}
	public String getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}
	public String getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public String getGid() {
		return gid;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}
	public int getJoinFlag() {
		return joinFlag;
	}
	public void setJoinFlag(int joinFlag) {
		this.joinFlag = joinFlag;
	}
	
	
}
