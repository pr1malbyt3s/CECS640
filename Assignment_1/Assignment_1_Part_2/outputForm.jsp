<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String rString = (String)request.getAttribute("reversedString");
		out.print("Your reversed string is: " + rString);
	%>
</body>
</html>
