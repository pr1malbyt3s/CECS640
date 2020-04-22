<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="edu.louisville.cecs640.final_project.Game_Bean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%-- Game statistics page. --%>
<title>Storm Stats - Games</title>
</head>
<div class="topnav">
	<a href="Players">Player Stats</a>
	<a href="Games">Game Results</a>
	<a href="FreeAgents">Free Agent Tracker</a>
	<a href="Home">Home</a>
	<a href="Logout">Logout</a>
</div>
<body>
	<h1>Game Results</h1>
	<%-- Get game results from servlet and display on page. --%>
	<%ArrayList<Game_Bean> game_list = (ArrayList<Game_Bean>) session.getAttribute("gamedata");%>
	<table border = "1" bordercolor="black" cellpadding = "5">
		<tr>
			<th>Date</th>
			<th>Opponent</th>
			<th>GF</th>
			<th>GA</th>
			<th>SF</th>
			<th>xGF</th>
			<th>xGA</th>
			<th>HDSF</th>
			<th>HDSA</th>
			<th>PDO</th>
		</tr>
		<%for (Game_Bean game : game_list) { %>
			<tr>
				<td><%= game.getDate() %></td>
				<td><%= game.getOpp() %></td>
				<td><%= game.getGF() %></td>
				<td><%= game.getGA() %></td>
				<td><%= game.getSF() %></td>
				<td><%= game.getXGF() %></td>
				<td><%= game.getXGA() %></td>
				<td><%= game.getHDSF() %></td>
				<td><%= game.getHDSA() %></td>
				<td><%= game.getPDO() %></td>
			</tr>
		<%}%>	 
	</table><br>
	<ul>
		<li>GF: Goals For</li>
		<li>GA: Goals Against</li>
		<li>SF: Shots For</li>
		<li>xGF: Expected Goals For</li>
		<li>xGA: Expected Goals Against</li>
		<li>HDSF: High Danger Shots For</li>
		<li>HDSA: High Danger Shots Against</li>
		<li>PDO: PDO Rating</li>
	</ul>
	<%-- Game deletion feature. --%>
	<h2>Game Deletion</h2>
	<form action="Games" method="POST">
		<label for="removeGame"><b>Game to Remove:</b></label>
		<input type="text" name="removeGame">
		<input type="submit" value="Remove Game">
	</form><br>
	<%if (request.getAttribute("gdmessage") != null) {
		String gd_msg = (String) request.getAttribute("gdmessage");
		out.print(gd_msg);
	%>
	<a href="Games">Click here to view updated table.</a>
	<%} %>
	<%-- Game update feature. --%>
	<h2>Game Update</h2>
	<form action="Games" method="POST">
		<label for="updateGame"><b>Game to Update:</b></label>
		<input type="text" name="updateGame">
		<label for="statName">Choose an Attribute:</label>
		<select id="statName" name="statName">
			<option value="GF">GF</option>
			<option value="GA">GA</option>
			<option value="SF">SF</option>
			<option value="HDSA">HDSA</option>
			<option value="HDSF">HDSF</option>
			<option value="xGF">xGF</option>
			<option value="xGA">xGA</option>
			<option value="PDO">PDO</option>
		</select>
		<label for="statValue"><b>Insert New Value:</b></label>
		<input type="text" name="statValue">
		<input type="submit" value="Update Game">
	</form><br>
	<%if (request.getAttribute("gumessage") != null) {
		String gu_msg = (String) request.getAttribute("gumessage");
		out.print(gu_msg);
	%>
	<a href="Games">Click here to view updated table.</a>
	<%} %>
	<%-- Game insertion feature. --%>
	<h2>Game Insert</h2>
	<form action="Games" method="POST">
	<label for="insertGame"><b>Game Date to Insert:</b></label>
	<input type="text" name="insertGame">
	<label for="gameOpp"><b>Game Opponent to Insert:</b></label>
	<input type="text" name="gameOpp">
	<input type="submit" value="Insert Game">
	</form><br>
	<%if (request.getAttribute("gimessage") != null) {
		String gi_msg = (String) request.getAttribute("gimessage");
		out.print(gi_msg);
	%>
	<a href="Games">Click here to view updated table.</a>
	<%} %>
</body>
</html>
