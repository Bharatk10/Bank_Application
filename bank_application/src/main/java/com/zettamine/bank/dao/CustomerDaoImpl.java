package com.zettamine.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.zettamine.bank.config.ConnectionFactory;
import com.zettamine.bank.dto.Customer;
import com.zettamine.bank.dto.Gender;
import com.zettamine.bank.dto.MaritalStatus;
import com.zettamine.bank.dto.SearchCriteria;

public class CustomerDaoImpl implements BankDao<Customer,SearchCriteria> {

	private Connection connection = null;

	public CustomerDaoImpl() {
		connection = ConnectionFactory.getDBConnection();
	}

	private PreparedStatement stmt = null;

	@Override
	public Optional<Customer> get(int id) {
		
		Customer customer = new Customer(); 
		
		try {
			stmt = connection.prepareStatement("select * from bank.customer where customer_id=?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				customer.setCustomerId(id);
				customer.setCustomerName(rs.getString("customer_name"));
				customer.setEmail(rs.getString("email"));
				customer.setGender(rs.getString("gender"));
				customer.setMaritalStatus(rs.getString("marital_status"));
			    customer.setAadharNum(Long.valueOf(rs.getString("aadhar_no")));
			    customer.setPanNum(rs.getString("pan_num"));
			    customer.setMobileNum(Long.valueOf(rs.getString("phone_no")));
			    customer.setAddressId(rs.getInt("address_id"));
			    
			    return Optional.of(customer);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return Optional.empty();
	}

	@Override
	public List<Customer> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> getBySearchCriteria(SearchCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(Customer t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int update(Customer t, String[] params) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Customer t) {
		// TODO Auto-generated method stub
		
	}

	

}
