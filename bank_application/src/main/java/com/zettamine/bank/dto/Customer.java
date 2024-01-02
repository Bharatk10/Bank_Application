package com.zettamine.bank.dto;




public class Customer implements BankDto {

	private int customerId;
	private String customerName;
	private String email;
	private long mobileNum;
	private String gender;
	private String maritalStatus;
	private int addressId;
	private long aadharNum;
	private String panNum;
	
	public Customer() {
		super();
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getMobileNum() {
		return mobileNum;
	}
	public void setMobileNum(long mobileNum) {
		this.mobileNum = mobileNum;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public long getAadharNum() {
		return aadharNum;
	}
	public void setAadharNum(long aadharNum) {
		this.aadharNum = aadharNum;
	}
	public String getPanNum() {
		return panNum;
	}
	public void setPanNum(String panNum) {
		this.panNum = panNum;
	}
	public Customer(String customerName, String email, long mobileNum, String gender,String maritalStatus,
			int addressId, long aadharNum, String panNum) {
		super();
		this.customerName = customerName;
		this.email = email;
		this.mobileNum = mobileNum;
		this.gender = gender;
		this.maritalStatus = maritalStatus;
		this.addressId = addressId;
		this.aadharNum = aadharNum;
		this.panNum = panNum;
	}
	public Customer(String customerName, String email, long mobileNum, String gender, String maritalStatus,
			long aadharNum, String panNum) {
		super();
		this.customerName = customerName;
		this.email = email;
		this.mobileNum = mobileNum;
		this.gender = gender;
		this.maritalStatus = maritalStatus;
		this.aadharNum = aadharNum;
		this.panNum = panNum;
	}
	

}
