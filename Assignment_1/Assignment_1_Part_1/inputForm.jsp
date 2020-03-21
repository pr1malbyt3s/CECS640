<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%-- Input Form for JSP only application. --%>
<title>Input Form</title>
</head>
<body>
	<%-- Sends a POST request to outputForm.jsp --%>
	<form action="outputForm.jsp" method="POST">
	<%-- Get string to be reversed from user input. --%>
	Enter String to be Reversed: <input type="text" name="nString" required/>
    <input type="submit" value="Reverse"/>
	</form>
</body>
</html>
