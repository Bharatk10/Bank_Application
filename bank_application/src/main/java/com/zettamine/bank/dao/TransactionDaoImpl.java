package com.zettamine.bank.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.zettamine.bank.config.ConnectionFactory;
import com.zettamine.bank.dto.SearchCriteria;
import com.zettamine.bank.dto.Transaction;

public class TransactionDaoImpl implements BankDao<Transaction, SearchCriteria> {
	
	private Connection connection = null;

	public TransactionDaoImpl() {
		connection = ConnectionFactory.getDBConnection();
	}

	private PreparedStatement stmt = null;


	@Override
	public Optional<Transaction> get(int id) {
		
		return Optional.empty();
	}

	@Override
	public List<Transaction> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> getBySearchCriteria(SearchCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(Transaction transaction) {
		
		try {
			stmt = connection.prepareStatement("insert into bank.bank_tx(account_no,amount,transaction_type,date_of_transaction,admin_id,balance_amount)"
					+ "values(?,?,?,?,?,?)");
			stmt.setInt(1,transaction.getAccountNum());
			stmt.setDouble(2, transaction.getAmount());
			stmt.setString(3,transaction.getTransactionType());
			Date date = Date.valueOf(transaction.getDateOfTransaction());
			stmt.setDate(4, date);
			stmt.setInt(5, transaction.getAdminId());
			stmt.setDouble(6, transaction.getUpdtaedBal());
			
			int transactStatus = stmt.executeUpdate();
			if(transactStatus>0) {
				return true;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return false;
		
		
	}

	@Override
	public int update(Transaction t, String... params) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Transaction t) {
		// TODO Auto-generated method stub
		
	}

	

}
