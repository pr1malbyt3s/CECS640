<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Input Form for servlet application. -->
<title>Input Form</title>
</head>
<body>
	<%
		//Creates error_message paramter if error attribute exists in request.
	    String error_message = "";
	    Object error = request.getAttribute("error");
	    if (error != null)
	        error_message = error.toString();
	%>
	<!-- Sends POST request to Reverse servlet. -->
	<form action="Reverse" method="POST">
		<table style="border-spacing: 4px;">
			<tr>
				<!-- Get string to be reversed from user input. -->
				<td>Enter String to be Reversed:</td>
				<td><input type="text" name="nString" size="20" required></td>
				<!-- Prints error message if one exists. -->
				<td style="color: red"><%=error_message%></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Reverse"></td>
				<td></td>
			</tr>
		</table>
    </form>
</body>
</html>
