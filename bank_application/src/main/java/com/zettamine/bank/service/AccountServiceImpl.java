package com.zettamine.bank.service;

import java.time.LocalDate;
import java.util.Optional;

import com.zettamine.bank.dao.AccountDaoImpl;
import com.zettamine.bank.dao.TransactionDaoImpl;
import com.zettamine.bank.dto.Account;
import com.zettamine.bank.dto.Address;
import com.zettamine.bank.dto.CreateAccount;
import com.zettamine.bank.dto.Customer;
import com.zettamine.bank.dto.Gender;
import com.zettamine.bank.dto.Transaction;

public class AccountServiceImpl implements AccountService {

	private AccountDaoImpl accountRepo;
	private TransactionService transactionService;
	
	public AccountServiceImpl(){
		accountRepo = new AccountDaoImpl();
		transactionService = new TransactionServiceImpl();
	}

	@Override
	public Boolean createAccount(CreateAccount createAccount) {

		String[] address = createAccount.getAddress().split(",");
		System.out.println("-".repeat(20));
		for (String s : address) {
			System.out.println(s);
		}

		String customerName = createAccount.getFirstName() + " " + createAccount.getLastName();
		String email = createAccount.getEmail().toUpperCase();
		long mobileNum = Long.valueOf(createAccount.getMobile());
		String input_gender = createAccount.getGender();
		String gender = Gender.getValue(input_gender);
		String maritalStatus = createAccount.getMaritalStatus();
		long aadharNum = Long.valueOf(createAccount.getAadhar());
		String pan = createAccount.getPan();

		Address addressObj = new Address(address[0], address[1], address[2], address[3], address[4]);
		Customer customer = new Customer(customerName, email, mobileNum, gender, maritalStatus, aadharNum, pan);
		Account ac = new Account(0.0, LocalDate.now(), true);

		return accountRepo.createAccount(customer, ac, addressObj);

	}

	@Override
	public Boolean checkValidAccountNumber(int accountNum) {

		Optional<Account> account = accountRepo.get(accountNum);

		if (account.isPresent()) {
			return true;
		}

		return false;
	}

	@Override
	public Boolean checkAmountToWithdraw(int accountNum, double amount) {

		Optional<Account> account = accountRepo.get(accountNum);

		if (account.isPresent()) {
			double dbAmount = account.get().getBalance();
			if (dbAmount >= amount) {
				return true;
			}
		}

		return false;
	}

	@Override
	public Boolean withdraw(int accountNum, double amount,int adminId) {

		Optional<Account> account = accountRepo.get(accountNum);

		if (account.isPresent()) {
			double updatedBalance = account.get().getBalance() - amount;
			account.get().setBalance(updatedBalance);
			int withdrawlStatus = accountRepo.update(account.get());
			if (withdrawlStatus > 0) {
				Boolean transactionStatus = transactionService.saveTransaction(accountNum, amount,"DR",updatedBalance,adminId);
				System.out.println(transactionStatus);
				return transactionStatus;
			}
		}

		return false;
	}

	@Override
	public Boolean deposit(int accountNum, double amount,int adminId) {

		Optional<Account> account = accountRepo.get(accountNum);

		if (account.isPresent()) {
			double updatedBalance = account.get().getBalance() + amount;
			account.get().setBalance(updatedBalance);
			int depositStatus = accountRepo.update(account.get());
			
			if (depositStatus > 0) {
				
				Boolean transactionStatus = transactionService.saveTransaction(accountNum, amount,"CR",updatedBalance,adminId);
		
				return transactionStatus;
			}
		}

		return false;
	}

	@Override
	public Boolean transferAmount(int senderAccountNum, int recieverAccountNum, double amount, int adminId) {
		
		
		
		Optional<Account> senderAccount = accountRepo.get(senderAccountNum);
		
		double senderUpdatedBalance = senderAccount.get().getBalance()-amount;
		
		Optional<Account> recieverAccount = accountRepo.get(recieverAccountNum);
		
		double recieverUpdatedBalance = recieverAccount.get().getBalance()+amount;
		
		Boolean transferStatus = accountRepo.transferAmount(senderAccount.get(),recieverAccount.get(),amount,senderUpdatedBalance,recieverUpdatedBalance,adminId);
		if(transferStatus) {
			return true;
		}
		
		return false;
	}

}
