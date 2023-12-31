package com.zettamine.bank.controller;

import java.io.IOException;
import java.util.Map;

import com.zettamine.bank.service.UserService;
import com.zettamine.bank.service.UserServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static  UserService userService;
       
    
    public LoginController() {
        userService = new UserServiceImpl();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("user_name"));
		String password = request.getParameter("password");
		char type = request.getParameter("type").charAt(0);
		
		Map<String,String> result = userService.checkLogin(id, password,type);
		System.out.println(result);
	}

}
