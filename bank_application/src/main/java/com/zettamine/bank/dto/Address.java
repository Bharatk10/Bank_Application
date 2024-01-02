package com.zettamine.bank.dto;

import lombok.Getter;

@Getter
public class Address implements BankDto{
	
//	private int addressId;
//	private String doorNo; 
//	private String street;
//	private String city;
//	private String state;
//	private int pincode;
//	
//	
//	
//	public Address() {
//		super();
//		
//	}
//	public int getAddressId() {
//		return addressId;
//	}
//	public void setAddressId(int addressId) {
//		this.addressId = addressId;
//	}
//	public String getDoorNo() {
//		return doorNo;
//	}
//	public void setDoorNo(String doorNo) {
//		this.doorNo = doorNo;
//	}
//	public String getStreet() {
//		return street;
//	}
//	public void setStreet(String street) {
//		this.street = street;
//	}
//	public String getCity() {
//		return city;
//	}
//	public void setCity(String city) {
//		this.city = city;
//	}
//	public String getState() {
//		return state;
//	}
//	public void setState(String state) {
//		this.state = state;
//	}
//	public int getPincode() {
//		return pincode;
//	}
//	public void setPincode(int pincode) {
//		this.pincode = pincode;
//	}
//	public Address(String doorNo, String street, String city, String state, int pincode) {
//		super();
//		this.doorNo = doorNo;
//		this.street = street;
//		this.city = city;
//		this.state = state;
//		this.pincode = pincode;
//	}
//	
//	
	
	private int addressId;
	private String doorNo;
	private String streetName;
	private String cityName;
	private String stateName;
	private String pincode;
	
	public Address(String doorNo, String streetName, String cityName,String state, String pincode) {
		super();
		this.doorNo = doorNo;
		this.streetName = streetName;
		this.cityName = cityName;
		this.pincode = pincode;
		this.stateName = state;
	}
}
