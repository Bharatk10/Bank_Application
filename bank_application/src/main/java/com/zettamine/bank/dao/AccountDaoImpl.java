package com.zettamine.bank.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.zettamine.bank.config.ConnectionFactory;
import com.zettamine.bank.dto.Account;
import com.zettamine.bank.dto.Address;
import com.zettamine.bank.dto.Customer;
import com.zettamine.bank.dto.SearchCriteria;

public class AccountDaoImpl implements BankDao<Account,SearchCriteria> {
	
private Connection connection = null;
	
	public AccountDaoImpl() {
		connection = ConnectionFactory.getDBConnection();
	}
	
	private PreparedStatement stmt = null;

	@Override
	public Optional<Account> get(int id) {
		
		Account account = new Account();		
		 try {
			stmt = connection.prepareStatement("select * from bank.account where account_no = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				account.setAccountNum(id);
				account.setBalance(rs.getDouble("balance"));
				
				Date sqlDate = rs.getDate("date_of_open");
				 
				LocalDate localDate = sqlDate.toLocalDate();
				account.setDateOfOpening(localDate);
				account.setStatus(rs.getBoolean("status"));
				account.setCustomerid(rs.getInt("customer_id"));
				
				return Optional.of(account);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return Optional.empty();
	}

	@Override
	public List<Account> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> getBySearchCriteria(SearchCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(Account t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int update(Account account, String...params) {
		int accountNum = account.getAccountNum();
		double amount = account.getBalance();
		try {
			stmt = connection.prepareStatement("UPDATE bank.account SET balance = ? WHERE account_no = ?");
			stmt.setDouble(1, amount);
			stmt.setInt(2, accountNum);
			return stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
		
	}
	
	public Boolean transferAmount(Account senderAccount,Account recieverAccount,double amount,double senderBalance,double recieverBalance,int adminId) {
		
		int  senderAccountNum = senderAccount.getAccountNum();
		int recieverAccountNum = recieverAccount.getAccountNum();
		int transDr,transCr;
		String transactionInsertQuery ="insert into bank.bank_tx(account_no,amount,transaction_type,date_of_transaction,admin_id,balance_amount)"
				+ "values(?,?,?,?,?,?)";
		String tranxFlowInsertQuery ="insert into bank.transaction_flow(debit_tx,credit_tx) values(?,?)";
				
		try {
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement("UPDATE bank.account SET balance = ? WHERE account_no = ?");
			stmt.setDouble(1,senderBalance);
			stmt.setInt(2, senderAccountNum);
			
			if(stmt.executeUpdate()>0) {
				
				stmt = connection.prepareStatement(transactionInsertQuery,PreparedStatement.RETURN_GENERATED_KEYS);
				
				stmt.setInt(1,senderAccountNum);
				stmt.setDouble(2, amount);
				stmt.setString(3,"DR");
				LocalDate currentDate = LocalDate.now();
		        Date date = Date.valueOf(currentDate);
				stmt.setDate(4, date);
				stmt.setInt(5, adminId);
				stmt.setDouble(6, senderBalance);
				
				stmt.executeUpdate();
				
				ResultSet rs = stmt.getGeneratedKeys();
				
				if(rs.next()) {
					transDr = rs.getInt("transaction_id");

					stmt = connection.prepareStatement("UPDATE bank.account SET balance = ? WHERE account_no = ?");
					stmt.setDouble(1, recieverBalance);
					stmt.setInt(2, recieverAccountNum);
					if(stmt.executeUpdate()>0) {
						stmt = connection.prepareStatement(transactionInsertQuery,PreparedStatement.RETURN_GENERATED_KEYS);
						
						stmt.setInt(1,recieverAccountNum);
						stmt.setDouble(2, amount);
						stmt.setString(3,"CR");
						

						stmt.setDate(4, date);
						stmt.setInt(5, adminId);
						stmt.setDouble(6, recieverBalance);
						stmt.executeUpdate();
						rs = stmt.getGeneratedKeys();
						if(rs.next()) {
							transCr = rs.getInt("transaction_id");
							stmt = connection.prepareStatement(tranxFlowInsertQuery);
							stmt.setInt(1, transDr);
							stmt.setInt(2, transCr);
							int transFlowStatus = stmt.executeUpdate();
							if(transFlowStatus>0) {
								connection.commit();
								return true;
							}
							else {
								System.out.println("Transaction flow insertion failed");
							}
						}else {
							System.out.println("Reciever Insert Transaction Failed");
						}
						
					}
					else {
						System.out.println("Reciver Deposit is failed");
					}
				}
				else {
					System.out.println("Sender Insert Transaction Failed");
				}
			}
			else {
				System.out.println("Sender Withdrawl is failed");
			}

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return false;
	}
	
	public Boolean createAccount(Customer cobj, Account aobj, Address obj ) {
		
		String addressQuery = "insert into bank.address (door_no, street, city, state_name, pincode) values (?, ?, ?, ?, ?)";
		String customerQuery = "insert into bank.customer (customer_name, email, gender, martial_status, address_id, aadhar_no, pan_no, phone_no) values (?,?,?,?,?,?,?,?)";
		String accountQuery = "insert into bank.account (balance, date_of_open, status, customer_id) values (?, ?, ?, ?)";
		String userQuery = "insert into bank.user (role_type, password, customer_id) values (?, ?, ?)";
		
		try {
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(addressQuery,PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, obj.getDoorNo());
			stmt.setString(2, obj.getStreetName());
			stmt.setString(3, obj.getCityName());
			stmt.setString(4, obj.getStateName());
			stmt.setString(5, obj.getPincode());
			stmt.executeUpdate();
			int addressid  = 0;
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next()) {
				addressid = rs.getInt(1);
				stmt = null;
				stmt = connection.prepareStatement(customerQuery,PreparedStatement.RETURN_GENERATED_KEYS);
				stmt.setString(1, cobj.getCustomerName());
				stmt.setString(2, cobj.getEmail());
				stmt.setString(3, cobj.getGender());
//				System.out.println(cobj.getGender().name());
//				psmt.setString(3, cobj.getGender().name());
				stmt.setString(4, cobj.getMaritalStatus());
				stmt.setInt(5, addressid);
				stmt.setString(6, String.valueOf(cobj.getAadharNum()));
				stmt.setString(7, cobj.getPanNum());
				stmt.setString(8, String.valueOf(cobj.getMobileNum()));
				stmt.executeUpdate();
				ResultSet cust = stmt.getGeneratedKeys();
				if(cust.next()) {
					int customerid = cust.getInt(1);
					stmt = null;
					stmt = connection.prepareStatement(accountQuery,PreparedStatement.RETURN_GENERATED_KEYS);
					stmt.setDouble(1, aobj.getBalance());
					LocalDate localDate = aobj.getDateOfOpening();
					java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
					stmt.setDate(2, sqlDate);
					stmt.setBoolean(3, aobj.isStatus());
					stmt.setInt(4, customerid);
					int res = stmt.executeUpdate();
				    ResultSet acc = stmt.getGeneratedKeys();
				    if(acc.next()) {
				    	stmt = null;
						stmt = connection.prepareStatement(userQuery,PreparedStatement.RETURN_GENERATED_KEYS);
						stmt.setString(1, "C");
						stmt.setString(2, customerid+"@123");
						stmt.setInt(3, customerid);
						stmt.executeUpdate();
						ResultSet user = stmt.getGeneratedKeys();
						if(user.next()) {
				    	connection.commit();
				    	return true;
						}else {
							throw new SQLException("user authentication not created");
						}
				    }else {
				    	throw new SQLException("account not created");
				    }
					
				}else {
					throw new SQLException("customer id not found");
				}
			}else {
				throw new SQLException("address id not found");
			}
			
			
		}catch(SQLException ex) {
			try {
				connection.rollback();
			} catch (SQLException e) {
				System.out.println("roll back executing");
				e.printStackTrace();
				return false;
			}
			System.out.println("insertion failed ----> " + ex.getMessage());
		}
		return false;
	}

	@Override
	public void delete(Account t) {
		// TODO Auto-generated method stub
		
	}

}
