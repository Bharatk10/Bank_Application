package com.zettamine.bank.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.zettamine.bank.dto.CreateAccount;
import com.zettamine.bank.service.AccountService;
import com.zettamine.bank.service.AccountServiceImpl;
import com.zettamine.bank.service.AdminService;
import com.zettamine.bank.service.AdminServiceImpl;
import com.zettamine.bank.service.TransactionService;
import com.zettamine.bank.service.TransactionServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/adminController")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminService adminService;
	private AccountService accountService;
	private TransactionService transactionService;

	public AdminController() {

		accountService = new AccountServiceImpl();
		adminService = new AdminServiceImpl();
		transactionService = new TransactionServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession currentSession = request.getSession(false);
		int adminId = Integer.parseInt(currentSession.getAttribute("id").toString());

		String action = request.getParameter("action");

		switch (action) {

		case "deposit":
			String accountNumber = request.getParameter("accountNumber");
			double amount = Double.parseDouble(request.getParameter("amount"));
			
			boolean depositStatus = accountService.deposit(Integer.parseInt(accountNumber), amount,adminId);
			handleStatus(depositStatus, "The Amount is successfully Deposited", "Deposit Failed", response);
			break;
		case "withdraw":
			String accountNumberWithdraw = request.getParameter("accountNumber");
			double amountWithdraw = Double.parseDouble(request.getParameter("amount"));
			boolean withdrawStatus = accountService.withdraw(Integer.parseInt(accountNumberWithdraw), amountWithdraw,adminId);
			handleStatus(withdrawStatus, "The Amount is successfully Withdrawn", "Withdraw Failed", response);
			break;
		case "create":
			String firstName = request.getParameter("first-name");
			String lastName = request.getParameter("last-name");
			String email = request.getParameter("email");
			String mobile = request.getParameter("mobile");
			String gender = request.getParameter("gender");
			String maritalStatus = request.getParameter("marital-status");
			String address = request.getParameter("address");
			String nominee = request.getParameter("nominee");
			String aadhar = request.getParameter("aadhar");
			String pan = request.getParameter("pan");

			System.out.println(firstName);
			System.out.println(lastName);
			System.out.println(email);
			System.out.println(mobile);
			System.out.println(gender);
			System.out.println(maritalStatus);
			System.out.println(address);
			System.out.println(nominee);
			System.out.println(aadhar);
			System.out.println(pan);
			CreateAccount createAccount = CreateAccount.builder().firstName(firstName).lastName(lastName).email(email)
					.mobile(mobile).aadhar(aadhar).pan(pan).gender(gender).maritalStatus(maritalStatus).nominee(nominee)
					.address(address).build();
			boolean createStatus = accountService.createAccount(createAccount);
			handleStatus(createStatus, "The Account is successfully Created", "Account Creation Failed", response);
			break;
		case  "transfer":
			int senderAccountNumber = Integer.parseInt(request.getParameter("senderAccountNumber"));
			int recieverAccountNumber = Integer.parseInt(request.getParameter("recieverAccountNumber"));
			amount = Double.parseDouble(request.getParameter("amount"));
			boolean transferStatus = accountService.transferAmount(senderAccountNumber, recieverAccountNumber, amount, adminId);
			handleStatus(transferStatus, "The Tranfer transaction is successfully Completed", "Transfer Transaction is Failed", response);
			break;
		default:
			response.sendRedirect(request.getContextPath() + "/unauthorized.jsp");
		}
		

	}
	private void handleStatus(Boolean status, String successMessage, String failureMessage,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		out.println("<script>");
		if (status) {
			out.println("alert('" + successMessage + "');");
		} else {
			out.println("alert('" + failureMessage + "');");
		}
		out.println("window.location.href = 'admin.jsp';");
		out.println("</script>");
		out.close();
	}
}
