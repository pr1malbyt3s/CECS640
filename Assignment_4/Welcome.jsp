 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%-- Application welcome page. --%>
<title>Welcome!</title>
</head>
<body>
	<h1>Welcome to Assignment 4</h1>
	<%--Check if name attribute was assigned by Login servlet.--%>
  <%
		String user = "";
		Object name = request.getAttribute("name");
		// Print name if login was successful.
    if (name != null) {
			user = name.toString();
	%>
			<font color="green">Welcome <%=name%>!</font>	
	<%	}
    // Render generic Welcome Page if no login was attempted.
		else {
	%>
		Please click the link below to log in.<br>
		<a href="Login.jsp">Login</a>
	<%
		}
	%>
</body>
</html>
