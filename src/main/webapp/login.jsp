<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Zetta Bank</title>
<link rel="icon" href="images/zettaBankLogo.png">
<link rel="stylesheet" href="css/login.css">
</head>
<body>
  <% String userType = request.getParameter("type"); %>
    <form action="login" method ="post">
	<table>
		<tr>
			<td>Enter User id:</td>
			<td><input type="text" name="user_name" required=required autofocus/></td>
		</tr>
		<tr>
			<td>Enter Password:</td>
			<td><input type="password" name="password" required=required/></td>
		</tr>
		<tr>
			<td><input type ="submit" value="LOGIN"/></td>
		</tr>
		<tr>
			<td><input type="hidden" name="type" value=<%=userType%> autofocus/></td>
		</tr>
	</table>
	
</form>
   
</body>
</html>