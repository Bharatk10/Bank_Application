package com.zettamine.bank.dto;

public enum Gender {
     MALE("M"),
	 FEMALE("F"),
	 OTHER("O");
	 
	
	private final String displayValue;
	
	private Gender(String displayValue) {
		this.displayValue = displayValue;
	}
	
	public static String getValue(String name) {
		String value = null;		
		for(Gender code : Gender.values()) {
			if(code.name().equals(name)) {
				value = code.displayValue;
				break;
			}
		}
		return value;
	}
	public static Gender fromValue(String displayValue) {
	    for (Gender gender : Gender.values()) {
	        if (gender.displayValue.equals(displayValue)) {
	            return gender;
	        }
	    }
	    throw new IllegalArgumentException("Invalid Gender display value: " + displayValue);
	}

}
