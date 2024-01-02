<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Zetta Bank</title>
<style>
.field {
display : flex;
justify-content : center;
align-items : center;
line-height : 3;
}
</style>
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
<div>

  <form id="ac-form" action="adminController?action=create" method="post" >
    <div class="field" >
    <label for="first-name">First Name:</label>
    <input type="text" placeholder="First Name" name="first-name" required="required" />
    </div>
    
    <div class="field">
    <label for="last-name">Last Name:</label>
    <input type="text" placeholder="Last Name" name="last-name" required="required" />
    </div>
    
    <div class="field">
    <label for="email">Email:</label>
    <input type="email" placeholder="Email" name="email" />
    </div>
    
    <div class="field">
    <label for="mobile">Mobile Number:</label>
    <input type="tel" placeholder="Mobile Number" name="mobile" required="required" />
    </div>
    
    <div class="field">
    <label for="gender">Gender:</label>
    <select name="gender">
      <option value="MALE">Male</option>
      <option value="FEMALE">Female</option>
      <option value="OTHER">Other</option>
    </select>
    </div>
    
    <div class="field">
    <label for="marital-status">Marital Status:</label>
    <select name="marital-status">
      <option value="SINGLE">Single</option>
      <option value="MARRIED">Married</option>
      <option value="WIDOWED">Widowed</option>
      <option value="DIVORCED">Divorced</option>
    </select>
    </div>
    
    <div class="field">
    <label for="address">Address:</label>
    <textarea name="address" placeholder="Enter Address"></textarea>
    </div>
    
    <div class="field">
    <label for="nominee">Nominee:</label>
    <input type="text" placeholder="Nominee" name="nominee" />
    </div>
    
    <div class="field">
    <label for="aadhar">Aadhar Number:</label>
    <input type="text" placeholder="Aadhar Number" name="aadhar" />
    </div>
    
    <div class="field">
    <label for="pan">Pan Number:</label>
    <input type="text" placeholder="Pan Number" name="pan" />
    </div>
    
    <div class="field">
    <input type="submit" value="Submit">
    </div>
  </form>
</div>

</body>
</html>