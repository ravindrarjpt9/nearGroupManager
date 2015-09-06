package com.nearGroup.modal;

public class Users {

	private int id;
	private String userName;
	private String techPofileType;
	private String firstName;
	private String lastName;
	private String middleName;
	private String password;
	private String email;
	private String mobile;
	private String createdBy;
	private String createdOn;
	private String modifyBy;
	private String modifyOn;
	private String status;
	
	public Users() {
		// TODO Auto-generated constructor stub
	}
	
	public Users(int id, String techPofileType,
			String firstName, String lastName, String middleName,
			String password, String email, String mobile, String createdBy,
			String createdOn, String modifyBy, String modifyOn, String status) {
		
		this.id = id;
		
		this.techPofileType = techPofileType;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.password = password;
		this.email = email;
		this.mobile = mobile;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.modifyBy = modifyBy;
		this.modifyOn = modifyOn;
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTechPofileType() {
		return techPofileType;
	}
	public void setTechPofileType(String techPofileType) {
		this.techPofileType = techPofileType;
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
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	public String getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}
	public String getModifyOn() {
		return modifyOn;
	}
	public void setModifyOn(String modifyOn) {
		this.modifyOn = modifyOn;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
