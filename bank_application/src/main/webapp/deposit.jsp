<%@ page import="com.zettamine.bank.service.*"%>
<%@ page import="jakarta.servlet.RequestDispatcher" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Deposit Amount</title>
</head>
<body>

	<%
	HttpSession currentSession = request.getSession(false);
	if (currentSession != null && currentSession.getAttribute("userType") != null) {
		if (!"A".equals(currentSession.getAttribute("userType"))) {
			response.sendRedirect(request.getContextPath() + "/unauthorized.jsp");
			return;
		}
	} else {
		response.sendRedirect(request.getContextPath() + "/unauthorized.jsp");
		return;
	}
	%>

	<h2>Deposit Amount</h2>

	<%
	String accountNumberErrorMessage = "";
	String amountErrorMessage = "";

	if (request.getMethod().equals("POST")) {
		String accountNumber = request.getParameter("accountNumber");
		int accountNum = Integer.parseInt(accountNumber);
		String amount = request.getParameter("amount");
		double amountDeposit = Double.parseDouble(amount);

		AccountService accountService = new AccountServiceImpl();

		if (accountService.checkValidAccountNumber(accountNum) == false) {
			accountNumberErrorMessage = "Account number does not exist.";
		} else {

			request.setAttribute("accountNumber", accountNumber);
			request.setAttribute("amount", amount);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/adminController?action=deposit");

			dispatcher.forward(request, response);
			return;
		}
	}
	%>

	<form action="deposit.jsp" method="post">
		Account Number: <input type="text" name="accountNumber" required><br>
		<br>
		<%
		if (!accountNumberErrorMessage.isEmpty()) {
		%>
		<span style="color: red"><%=accountNumberErrorMessage%></span><br>
		<br>
		<%
		}
		%>
		Amount: <input type="number" name="amount" step="0.01" min="1"
			required><br>
		<br> <input type="submit" value="Deposit">
		<button>
			<a href="admin.jsp">Back</a>
		</button>
	</form>

</body>
</html>
