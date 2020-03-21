<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%-- Output Form for JSP only application. --%>
<title>Output Form</title>
</head>
<body>
	<%
		//Receive nString parameter from submitted request.
		String rString = request.getParameter("nString");
		//Check that string only contains letters, numbers, and spaces.
		if (!rString.matches("[\\w*\\s*]*")) {
			//If not, print error message and show link for input page.
        	out.print("String can only contain letters, numbers, and spaces. Please go back and try again.");
        	%>
        	<br>
        	<br>
        	<a href="inputForm.jsp">Try Again</a>
        	<%
    	}
		else {
			//Create a new variable, revString, that reverses the original string.
			String revString = new StringBuilder(rString).reverse().toString();
			//Print the reversed string.
			out.print("Your reversed string is: " + revString);
		}	
	%>
</body>
</html>
