package com.zettamine.bank.service;

import com.zettamine.bank.dto.Transaction;

public interface TransactionService {
	
	boolean saveTransaction(int accountNum, double amount, String tranxType, double updatedBalance, int admin_id);

}
