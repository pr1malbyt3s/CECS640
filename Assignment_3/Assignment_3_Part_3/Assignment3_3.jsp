<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%--Bean declaration.--%>
<jsp:useBean id="opBean" class="edu.louisville.cecs640.assignment3_3.Assignment3_Bean"/>
<html>
<head>
<meta charset="UTF-8">
<title>Assignment 3 Problem 3</title>
</head>
<body>
	<%
		// Set operation string.
		opBean.setOperation("bad");
		// Print operation string.
		out.println("Operation: " + opBean.getOperation());
		// Set var_1 value.
		opBean.setVar1(27);
		// Print var_1 value.
		out.println("var_1: " + opBean.getVar1());
		// Set var_2 value.
		opBean.setVar2(15);
		// Print var_2 value.
		out.println("var_2: " + opBean.getVar2());
		// Perform and print calculation.
		out.println("Calculation: " + opBean.calculate());
	%>
</body>
</html>
