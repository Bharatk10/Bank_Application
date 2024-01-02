package com.zettamine.bank.service;

import com.zettamine.bank.dto.CreateAccount;

public interface AccountService {
	
	Boolean checkValidAccountNumber(int accountNum);
	
	Boolean checkAmountToWithdraw(int accountNum,double amount);
	
	Boolean createAccount(CreateAccount createAccount);
	
	Boolean withdraw(int accountNum,double amount,int adminIid);
	
	Boolean deposit(int accountNum,double amount,int adminId);
	
	Boolean transferAmount(int senderaAccountNum,int recieverAccountNum,double amount,int adminId);
	
	

}
