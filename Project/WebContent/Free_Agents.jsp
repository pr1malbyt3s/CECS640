<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="edu.louisville.cecs640.final_project.Agent_Bean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%-- Free agents page. --%>
<title>Storm Stats - Free Agents</title>
</head>
<div class="topnav">
	<a href="Players">Player Stats</a>
	<a href="Games">Game Results</a>
	<a href="FreeAgents">Free Agent Tracker</a>
	<a href="Home">Home</a>
	<a href="Logout">Logout</a>
</div>
<body>
	<h1>Free Agent Tracker</h1>
	<%-- Get free agent data from servlet and display on page. --%>
	<%ArrayList<Agent_Bean> agent_list = (ArrayList<Agent_Bean>) session.getAttribute("agentdata");%>
	<table border = "1" bordercolor="black" cellpadding = "5">
		<tr>
			<th>Name</th>
			<th>Team</th>
			<th>Age</th>
			<th>POS</th>
			<th>G</th>
			<th>A</th>
			<th>PTS</th>
			<th>+/-</th>
			<th>CAP</th>
		</tr>
		<%for (Agent_Bean agent : agent_list) { %>
			<tr>
				<td><%= agent.getName() %></td>
				<td><%= agent.getTeam() %></td>
				<td><%= agent.getAge() %></td>
				<td><%= agent.getPos() %></td>
				<td><%= agent.getG() %></td>
				<td><%= agent.getA() %></td>
				<td><%= agent.getPts() %></td>
				<td><%= agent.getPm() %></td>
				<td><%= agent.getCap() %></td>
			</tr>
		<%}%>	 
	</table><br>
	<ul>
		<li>POS: Position</li>
		<li>G: Goals</li>
		<li>A: Assists</li>
		<li>PTS: Points</li>
		<li>+/-: Plus/Minus Rating</li>
		<li>CAP: Expected Salary Cap Hit</li>
	</ul>
	<br>
	<%-- Free agent deletion feature. --%>
	<h2>Free Agent Deletion</h2>
	<form action="FreeAgents" method="POST">
		<label for="removeAgent"><b>Free Agent to Remove:</b></label>
		<input type="text" name="removeAgent">
		<input type="submit" value="Remove Free Agent">
	</form><br>
	<%if (request.getAttribute("admessage") != null) {
		String ad_msg = (String) request.getAttribute("admessage");
		out.print(ad_msg);
	%>
	<a href="FreeAgents">Click here to view updated table.</a>
	<%} %>
	<%-- Free agent update feature. --%>
	<h2>Free Agent Update</h2>
	<form action="FreeAgents" method="POST">
		<label for="updateAgent"><b>Free Agent to Update:</b></label>
		<input type="text" name="updateAgent">
		<label for="statName">Choose an Attribute:</label>
		<select id="statName" name="statName">
			<option value="G">G</option>
			<option value="A">A</option>
			<option value="PTS">Pts</option>
			<option value="PM">+/-</option>
			<option value="CAP">Cap</option>
		</select>
		<label for="statValue"><b>Insert New Value:</b></label>
		<input type="text" name="statValue">
		<input type="submit" value="Update Free Agent">
	</form><br>
	<%if (request.getAttribute("aumessage") != null) {
		String au_msg = (String) request.getAttribute("aumessage");
		out.print(au_msg);
	%>
	<a href="FreeAgents">Click here to view updated table.</a>
	<%} %>
	<%-- Free agent insertion feature. --%>
	<h2>Free Agent Insert</h2>
	<form action="FreeAgents" method="POST">
	<label for="insertAgent"><b>Free Agent to Insert:</b></label>
	<input type="text" name="insertAgent">
	<label for="playerTeam"><b>Free Agent Team:</b></label>
	<input type="text" name="playerTeam">
	<label for="playerAge"><b>Free Agent Age:</b></label>
	<input type="text" name="playerAge">
	<label for="playerPos"><b>Free Agent Position:</b></label>
	<input type="text" name="playerPos">
	<input type="submit" value="Insert Player">
	</form><br>
	<%if (request.getAttribute("aimessage") != null) {
		String ai_msg = (String) request.getAttribute("aimessage");
		out.print(ai_msg);
	%>
	<a href="FreeAgents">Click here to view updated table.</a>
	<%} %>
</body>
</html>
