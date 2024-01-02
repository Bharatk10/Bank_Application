package com.zettamine.bank.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account implements BankDto{

	private int accountNum;
    private double balance;
    private LocalDate dateOfOpening;
    private boolean status;
    
    private int customerid;
    public Account() {
    	
    }
    public Account(double balance, LocalDate now, boolean status) {
		this.balance = balance;
		this.dateOfOpening = now;
		this.status = status;
	}
	public Account(double balance, LocalDate dateOfOpening, boolean status, int customerid) {
		super();
		this.balance = balance;
		this.dateOfOpening = dateOfOpening;
		this.status = status;
		this.customerid = customerid;
	}

	
}

