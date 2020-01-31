<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Output Form for servlet application. -->
<title>Output Form</title>
</head>
<body>
	<%
		//Store reversed string from request in rString variable.
		String rString = (String)request.getAttribute("reversedString");
		//Print the reversed string.
		out.print("Your reversed string is: " + rString);
	%>
</body>
</html>
