<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Zetta Bank</title>
</head>
<body>
	<%
    HttpSession currentSession = request.getSession(false);
    if (currentSession != null && currentSession.getAttribute("userType") != null) {  
        char userType = currentSession.getAttribute("userType").toString().charAt(0);
        if (userType == 'A') {
        	
        	%>
        	<h1>Unauthorized Access</h1>
	<p>You are not authorized to access this page.</p>
        	<p>
    		Please <a href="admin.jsp">visit</a> the appropriate page.
    		</p>
    		<% 
        } else if (userType == 'C') {
        	%>
           <p>
           <h1>Unauthorized Access</h1>
	<p>You are not authorized to access this page.</p>
    		Please <a href="customer.jsp">visit</a> the appropriate page.
    		</p>
    		<% 
        }    
    } else {
    	%>
    	<h1>Session Expired/Please Login again</h1>
	<p>
		Please   <a href="index.html">login</a> here.
	</p>
      <% 
    }
%>
</body>
</html>