package com.zettamine.bank.service;

import java.util.Map;

public interface UserService {
	
	Map<String,String> checkLogin(int id,String password,char userType);
}
