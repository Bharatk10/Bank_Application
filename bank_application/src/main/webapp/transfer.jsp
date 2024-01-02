<%@ page import="com.zettamine.bank.service.*" %>
<%@ page import="jakarta.servlet.RequestDispatcher" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Zetta Banak</title>
<link rel="icon" href="images/zettaBankLogo.png">
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
</body>
<h2>Transfer Amount</h2>

<%
    String senderAccountNumberErrorMessage = "";
	String recieverAccountNumberErrorMessage = "";
    String amountErrorMessage = "";

    if (request.getMethod().equals("POST")) {
        String senderAccountNumber = request.getParameter("senderAccountNumber");
        String recieverAccountNumber = request.getParameter("recieverAccountNumber");
        int senderAccountNum = Integer.parseInt(senderAccountNumber);
        int recieverAccountNum = Integer.parseInt(recieverAccountNumber);
        String amount = request.getParameter("amount");
        double amountWithdrawl = Double.parseDouble(amount);

        AccountService accountService = new AccountServiceImpl();

        if (accountService.checkValidAccountNumber(senderAccountNum) == false ){
            senderAccountNumberErrorMessage = " Sender Account number does not exist.  Please try Again";
            
        }else if (accountService.checkValidAccountNumber(recieverAccountNum) == false){
            recieverAccountNumberErrorMessage = "Reciever Account number does not exist.  Please try Again";
            
        }else if(senderAccountNum == recieverAccountNum){
        	 recieverAccountNumberErrorMessage = "Sender Account Number and Reciever Account number cannot be same Please Try again";
        }
        else if (accountService.checkAmountToWithdraw(senderAccountNum, amountWithdrawl) == false ) {  
            amountErrorMessage = "Insufficient funds.";
        } else {

        	RequestDispatcher dispatcher = request.getRequestDispatcher("/adminController?action=transfer");
            dispatcher.forward(request, response);
           	return;
        }
    }
%>

<form action="transfer.jsp" method="post">
    Sender Account Number: <input type="text" name="senderAccountNumber" required><br><br>
    <% if (!senderAccountNumberErrorMessage.isEmpty()) { %>
        <span style="color:red"><%= senderAccountNumberErrorMessage %></span><br><br>
    <% } %>
    Reciever Account Number: <input type="text" name="recieverAccountNumber" required><br><br>
    <% if (!recieverAccountNumberErrorMessage.isEmpty()) { %>
        <span style="color:red"><%= recieverAccountNumberErrorMessage %></span><br><br>
    <% } %>
    Amount: <input type="number" name="amount" step="0.01" min="1" required><br><br>
    <% if (!amountErrorMessage.isEmpty()) { %>
        <span style="color:red"><%= amountErrorMessage %></span><br><br>
    <% } %>
    <input type="submit" value="Transfer">
    <button><a href="admin.jsp">Back</a></button>
</form>

</html>