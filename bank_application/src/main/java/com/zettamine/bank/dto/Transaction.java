package com.zettamine.bank.dto;

import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Transaction implements BankDto {
	
	private int transactionId;
	private int accountNum;
	private double amount;
	private String transactionType;
	private LocalDate  dateOfTransaction;
	private double updtaedBal;
	private int adminId;
	
	
	public Transaction(int accountNum, double amount, String transactionType, LocalDate dateOfTransaction,
			double updtaedBal, int adminId) {
		super();
		this.accountNum = accountNum;
		this.amount = amount;
		this.transactionType = transactionType;
		this.dateOfTransaction = dateOfTransaction;
		this.updtaedBal = updtaedBal;
		this.adminId = adminId;
	}
	
	

}
