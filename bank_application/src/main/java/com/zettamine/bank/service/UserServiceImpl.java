package com.zettamine.bank.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.zettamine.bank.dao.AdminDaoImpl;
import com.zettamine.bank.dao.BankDao;
import com.zettamine.bank.dao.UserDao;
import com.zettamine.bank.dao.UserDaoImpl;

public class UserServiceImpl implements UserService {

	private UserDao userRepo = new UserDaoImpl();

	@Override
	public Map<String, String> checkLogin(int id, String password, char userType) {

		Map<String, String> loginStatus = new HashMap<>();

		Optional<String> optionalPass = userRepo.getPassword(id, userType);

		if (optionalPass.isPresent()) {
			String pass = optionalPass.get();
			if (pass.equals(password)) {
				loginStatus.put("status", "true");
				loginStatus.put("id", Integer.toString(id));
				loginStatus.put("type", Character.toString(userType));

				return loginStatus;
			} else {
				loginStatus.put("status", "false");
				loginStatus.put("id", Integer.toString(id));
				return loginStatus;
			}
			
			
		}
		
		return null;
		
	}
}
