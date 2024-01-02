package com.zettamine.bank.dto;

public class Admin implements BankDto {
	
	private int adminId;
	private String adminName;
	private String email;
	private long phoneNum;
	private int addressId;
	
	public Admin() {
		super();
	}

	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(long phoneNum) {
		this.phoneNum = phoneNum;
	}
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public Admin(String adminName, String email, long phoneNum, int addressId) {
		super();
		this.adminName = adminName;
		this.email = email;
		this.phoneNum = phoneNum;
		this.addressId = addressId;
	}

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminName=" + adminName + ", email=" + email + ", phoneNum=" + phoneNum
				+ ", addressId=" + addressId + "]";
	}
	
	

}
