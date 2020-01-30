<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Output Form</title>
</head>
<body>
	<%
		String rString = request.getParameter("nString");
		String revString = new StringBuilder(rString).reverse().toString();
		out.print("Your reversed string is: " + revString);
	%>
</body>
</html>
