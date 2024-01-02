package com.zettamine.bank.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import com.zettamine.bank.dto.Admin;
import com.zettamine.bank.service.AdminService;
import com.zettamine.bank.service.AdminServiceImpl;
import com.zettamine.bank.service.UserService;
import com.zettamine.bank.service.UserServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static UserService userService;
	private static AdminService adminService;

	public LoginController() {
		userService = new UserServiceImpl();
		adminService = new AdminServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("user_id"));
		String password = request.getParameter("password");
		char type = request.getParameter("type").charAt(0);

		Map<String, String> result = userService.checkLogin(id, password, type);
		
		System.out.println(result);

		HttpSession session = request.getSession();
		
		if(result == null) {
			response.setContentType("text/html");
			
			String message = "Invalid UserId";
			sendRedirectWithMessage(response,request.getContextPath() +"/login.jsp?type=" + type,message);
		}
		else {
			if (result.get("status").equals("true")) {
				session.setAttribute("userType", result.get("type"));
				session.setAttribute("id", result.get("id"));
				session.setAttribute("status", true);
				if (result.get("type").charAt(0) == 'A') {
					
					String userid = (String)session.getAttribute("id");
					int user = Integer.valueOf(userid);
					Admin admin = adminService.getAdminDetails(user);
					session.setAttribute("userData", admin);
					response.sendRedirect(request.getContextPath() +"/admin.jsp");
				} else {

					response.sendRedirect(request.getContextPath() +"/customer.jsp");
				}
				
			} else {

				response.setContentType("text/html");
//				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
//				rd.forward(request, response);
				String message = "Invalid Password";
				sendRedirectWithMessage(response,request.getContextPath() +"/login.jsp?type=" + type,message);
			}
		}

		
	}
	private void sendRedirectWithMessage(HttpServletResponse response, String url, String message) throws IOException {
	    response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    out.println("<script>");
	    out.println("alert('" + message + "');");
	    out.println("window.location.href = '" + url + "';");
	    out.println("</script>");
	    out.close();
	}

}
