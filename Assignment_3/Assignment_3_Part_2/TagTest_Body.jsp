<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%--Implement taglib directive to define custom tag library location and prefix--%>
<%@ taglib uri="/A3P2" prefix="app" %>
<title>Assignment 3 Problem 2</title>
</head>
<body>
	<%---Call custom tag with body printvertstring.--%>
	<app:printvertstring>Java is awesome!</app:printvertstring>
</body>
</html>
