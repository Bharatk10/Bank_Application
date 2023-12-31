<%@ page import="com.zettamine.bank.dto.Admin" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Zetta Bank</title>

<script src="https://kit.fontawesome.com/c323173fb6.js"
	crossorigin="anonymous"></script>

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
	<%
		Admin admin = (Admin)session.getAttribute("userData");
		out.print(admin);
	%>
	<button>
	<a href="createAccount.jsp">create account</a>
	</button>
	<button>
		<a href="withdraw.jsp">Withdraw</a>
		</button>
		<br>
			<button>
		<a href="deposit.jsp">Deposit</a>
		</button>
		<br>
		<button>
		<a href="transfer.jsp">Transfer</a>
		</button>
		<a href="logout"><i class="fa-solid fa-arrow-right-from-bracket"></i></a>
	
</body>
</html>                          