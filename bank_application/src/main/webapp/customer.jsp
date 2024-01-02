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
		if (!"C".equals(currentSession.getAttribute("userType"))) { 
			response.sendRedirect(request.getContextPath() + "/unauthorized.jsp");
			return;
		}
	} else {
		response.sendRedirect(request.getContextPath() + "/unauthorized.jsp");
		return;
	}
	%>
	<h1>
		welcome user
		<%=session.getAttribute("id")%></h1>

	<button>
		<a href="logout"><i class="fa-solid fa-arrow-right-from-bracket"></i></a>
	</button>
</body>
</html>