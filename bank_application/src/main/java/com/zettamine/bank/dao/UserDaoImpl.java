package com.zettamine.bank.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import com.zettamine.bank.config.ConnectionFactory;

public class UserDaoImpl implements UserDao{
	
	private Connection connection = null;
	
	public UserDaoImpl() {
		connection = ConnectionFactory.getDBConnection();
	}
	
	private Statement stmt = null;
	
	@Override
	public Optional<String> getPassword(int id, char userType) {
		
		
		try {
			stmt = connection.createStatement();
			if(userType == 'C') {
				
				String query = "select password from bank.user where customer_id = "+id+" and role_type = '" +userType+"'";
				ResultSet rs = stmt.executeQuery(query);
				if(rs.next()) {
					String password = rs.getString("password");
					return Optional.of(password);
				}
				
			}
			else if(userType == 'A'){
				String query = "select password from bank.user where admin_id = "+id+" and role_type = '" +userType+"'";
				
				ResultSet rs = stmt.executeQuery(query);
				if(rs.next()) {
					String password = rs.getString("password");
					return Optional.of(password);
				}
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

}
