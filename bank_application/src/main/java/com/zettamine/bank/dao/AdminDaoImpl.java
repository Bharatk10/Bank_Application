package com.zettamine.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.zettamine.bank.config.ConnectionFactory;
import com.zettamine.bank.dto.Account;
import com.zettamine.bank.dto.Address;
import com.zettamine.bank.dto.Admin;
import com.zettamine.bank.dto.Customer;
import com.zettamine.bank.dto.SearchCriteria;

public class AdminDaoImpl implements BankDao<Admin, SearchCriteria> {
	
	private Connection connection = null;

	public AdminDaoImpl() {
		connection = ConnectionFactory.getDBConnection();
	}

	private PreparedStatement stmt = null;
	
	@Override
	public Optional<Admin> get(int id) {
		
		Admin admin = new Admin();		
		 try {
			stmt = connection.prepareStatement("select * from bank.admin where admin_id = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				admin.setAdminId(id);
				admin.setAdminName(rs.getString("admin_name"));
				admin.setEmail(rs.getString("email"));
				admin.setPhoneNum(Long.valueOf(rs.getString("phone_no")));
				admin.setAddressId(rs.getInt("address_id"));
				
				return Optional.of(admin);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return Optional.empty();
	}

	@Override
	public List<Admin> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Admin> getBySearchCriteria(SearchCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(Admin t) {
		
		return false;
		
	}

	@Override
	public int update(Admin t, String[] params) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Admin t) {
		// TODO Auto-generated method stub
		
	}
	

	

}
