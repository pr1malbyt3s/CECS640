<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%-- Application login page. --%>
<title>Storm Stats - Login</title>
</head>
<body>
  	<%--Check if error attribute was set by Login servlet.--%>
	<%
		String error_message = "";
		Object error = request.getAttribute("error");
		if (error != null) {
			error_message = error.toString();
		}
	%>
	<h1>Storm Stats</h1>
	Please login below:<br>
	<br>
	<%--Sends POST request to Validate servlet.--%>
	<form action="Login" method="POST">
    	<label for="username"><b>Username:</b></label><br>
        <input type="text" name="username" required><br>
            
        <label for="password"><b>Password:</b></label><br>
        <input type="password" name="password" required><br>
            
        <input type="submit" value="Login">
	</form>
	<br>
 	<%--Prints error message if one exists.--%>
	<font color="red"><%=error_message%></font>
</body>
</html>
