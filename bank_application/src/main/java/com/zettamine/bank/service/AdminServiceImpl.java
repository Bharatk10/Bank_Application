package com.zettamine.bank.service;

import java.time.LocalDate;
import java.util.Optional;

import com.zettamine.bank.dao.AdminDaoImpl;
import com.zettamine.bank.dto.Account;
import com.zettamine.bank.dto.Address;
import com.zettamine.bank.dto.Admin;
import com.zettamine.bank.dto.CreateAccount;
import com.zettamine.bank.dto.Customer;
import com.zettamine.bank.dto.Gender;




public class AdminServiceImpl implements AdminService{

	private AdminDaoImpl adminRepo = new AdminDaoImpl();
	@Override
	public Admin getAdminDetails(int adminid) {
		Optional<Admin> adminOpt = adminRepo.get(adminid);
		if(adminOpt.isPresent()) {
			Admin admin = adminOpt.get();
			return admin;
		}
		return null;
	}
	
	
	
	
	
	

}
