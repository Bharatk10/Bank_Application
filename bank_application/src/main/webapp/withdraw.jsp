<%@ page import="com.zettamine.bank.service.*" %>
<%@ page import="jakarta.servlet.RequestDispatcher" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Withdraw Amount</title>
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

<h2>Withdraw Amount</h2>

<%
    String accountNumberErrorMessage = "";
    String amountErrorMessage = "";

    if (request.getMethod().equals("POST")) {
        String accountNumber = request.getParameter("accountNumber");
        int accountNum = Integer.parseInt(accountNumber);
        String amount = request.getParameter("amount");
        double amountWithdrawl = Double.parseDouble(amount);

        AccountService accountService = new AccountServiceImpl();

        if (accountService.checkValidAccountNumber(accountNum)==false){
            accountNumberErrorMessage = "Account number does not exist.  Please try Again";
            
        } else if (accountService.checkAmountToWithdraw(accountNum, amountWithdrawl) == false) {  
            amountErrorMessage = "Insufficient funds.";
        } else {

        	
        	RequestDispatcher dispatcher = request.getRequestDispatcher("/adminController?action=withdraw");


            dispatcher.forward(request, response);
           
        }
    }
%>

<form action="withdraw.jsp" method="post">
    Account Number: <input type="text" name="accountNumber" required><br><br>
    <% if (!accountNumberErrorMessage.isEmpty()) { %>
        <span style="color:red"><%= accountNumberErrorMessage %></span><br><br>
    <% } %>
    Amount: <input type="number" name="amount" step="0.01" min="1" required><br><br>
    <% if (!amountErrorMessage.isEmpty()) { %>
        <span style="color:red"><%= amountErrorMessage %></span><br><br>
    <% } %>
    <input type="submit" value="Withdraw">
    <button><a href="admin.jsp">Back</a></button>
</form>

</body>
</html>
