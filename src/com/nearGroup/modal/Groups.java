package com.nearGroup.modal;

public class Groups {

	private String id;
	private String name;
	private String category;
	private String iconCategory;
	private String status;
	private String systemGroupName;
	private String userCount;
	private String creationTime;
	
	private String modifiedTime;
	private int joinFlag = 0;

	
	public Groups(String id, String name, String category, String iconCategory, String status, String systemGroupName, String userCount, String creationTime, String modifiedTime) {
		
		this.id = id;
		this.name = name;
		this.category = category;
		this.iconCategory = iconCategory;
		this.status = status;
		this.systemGroupName = systemGroupName;
		this.userCount = userCount;
		this.creationTime = creationTime;
		this.modifiedTime = modifiedTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getIconCategory() {
		return iconCategory;
	}

	public void setIconCategory(String iconCategory) {
		this.iconCategory = iconCategory;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSystemGroupName() {
		return systemGroupName;
	}

	public void setSystemGroupName(String systemGroupName) {
		this.systemGroupName = systemGroupName;
	}

	public String getUserCount() {
		return userCount;
	}

	public void setUserCount(String userCount) {
		this.userCount = userCount;
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

	public int getJoinFlag() {
		return joinFlag;
	}

	public void setJoinFlag(int joinFlag) {
		this.joinFlag = joinFlag;
	}
	 
}
