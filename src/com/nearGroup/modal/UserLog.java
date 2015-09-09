package com.nearGroup.modal;

public class UserLog {

	private String id;
	private String firstName;
	private String lastName;
	private String role;
	private String loginTime;
	private String logoutTime;
	
	
	public UserLog(String id, String firstName, String lastName, String role, String loginTime, String logoutTime) {
		
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.loginTime = loginTime;
		this.logoutTime = logoutTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public String getLogoutTime() {
		return logoutTime;
	}
	public void setLogoutTime(String logoutTime) {
		this.logoutTime = logoutTime;
	}
	
	
}
