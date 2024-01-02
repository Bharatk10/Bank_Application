package com.zettamine.bank.service;

import java.time.LocalDate;

import com.zettamine.bank.dao.BankDao;
import com.zettamine.bank.dao.TransactionDaoImpl;
import com.zettamine.bank.dto.Transaction;

public class TransactionServiceImpl implements TransactionService {
	
	private BankDao transRepo;
	
	public TransactionServiceImpl() {
		transRepo = new TransactionDaoImpl();
	}

	@Override
	public boolean saveTransaction(int accountNum, double amount, String tranxType, double updatedBalance, int admin_id) {
		
		Transaction transaction = new Transaction();
		
		transaction.setAccountNum(accountNum);
		transaction.setAmount(amount);
		transaction.setTransactionType(tranxType);
		transaction.setDateOfTransaction(LocalDate.now());
		transaction.setUpdtaedBal(updatedBalance);
		transaction.setAdminId(admin_id);
		
		Boolean transactionStatus = transRepo.save(transaction);
		
		if(transactionStatus) {
			return true;
		}
		
		return false;
	}

	

}
