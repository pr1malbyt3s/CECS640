<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="edu.louisville.cecs640.final_project.Player_Bean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%-- Player statistics page. --%>
<title>Storm Stats - Players</title>
</head>
<div class="topnav">
	<a href="Players">Player Stats</a>
	<a href="Games">Game Results</a>
	<a href="FreeAgents">Free Agent Tracker</a>
	<a href="Home">Home</a>
	<a href="Logout">Logout</a>
</div>
<body>
	<h1>Player Stats</h1>
	<%-- Get player stats from servlet and display on page. --%>
	<%ArrayList<Player_Bean> player_list = (ArrayList<Player_Bean>) session.getAttribute("playerdata");%>
	<table border = "1" bordercolor="black" cellpadding = "5">
		<tr>
			<th>Name</th>
			<th>#</th>
			<th>Age</th>
			<th>POS</th>
			<th>GP</th>
			<th>G</th>
			<th>A</th>
			<th>PTS</th>
			<th>PIM</th>
			<th>xGF</th>
			<th>xGA</th>
		</tr>
		<%for (Player_Bean player : player_list) { %>
			<tr>
				<td><%= player.getName() %></td>
				<td><%= player.getNum() %></td>
				<td><%= player.getAge() %></td>
				<td><%= player.getPos() %></td>
				<td><%= player.getGP() %></td>
				<td><%= player.getG() %></td>
				<td><%= player.getA() %></td>
				<td><%= player.getPts() %></td>
				<td><%= player.getPim() %></td>
				<td><%= player.getXGF() %></td>
				<td><%= player.getXGA() %></td>
			</tr>
		<%}%>	 
	</table><br>
	<ul>
		<li>#: Jersey Number</li>
		<li>POS: Position</li>
		<li>GP: Games Played</li>
		<li>G: Goals</li>
		<li>A: Assists</li>
		<li>PTS: Points</li>
		<li>PIM: Penalty Minutes</li>
		<li>xGF: Expected Goals For</li>
		<li>xGA: Expected Goals Against</li>
	</ul>
	<br>
	<%-- Player deletion feature. --%>
	<h2>Player Deletion</h2>
	<form action="Players" method="POST">
		<label for="removePlayer"><b>Player to Remove:</b></label>
		<input type="text" name="removePlayer">
		<input type="submit" value="Remove Player">
	</form><br>
	<%if (request.getAttribute("pdmessage") != null) {
		String pd_msg = (String) request.getAttribute("pdmessage");
		out.print(pd_msg);
	%>
	<a href="Players">Click here to view updated table.</a>
	<%} %>
	<%-- Player update feature. --%>
	<h2>Player Update</h2>
	<form action="Players" method="POST">
		<label for="updatePlayer"><b>Player to Update:</b></label>
		<input type="text" name="updatePlayer">
		<label for="statName">Choose an Attribute:</label>
		<select id="statName" name="statName">
			<option value="GP">GP</option>
			<option value="G">G</option>
			<option value="A">A</option>
			<option value="PTS">Pts</option>
			<option value="PIM">PIM</option>
			<option value="xGF">xGF</option>
			<option value="xGA">xGA</option>
		</select>
		<label for="statValue"><b>Insert New Value:</b></label>
		<input type="text" name="statValue">
		<input type="submit" value="Update Player">
	</form><br>
	<%if (request.getAttribute("pumessage") != null) {
		String pu_msg = (String) request.getAttribute("pumessage");
		out.print(pu_msg);
	%>
	<a href="Players">Click here to view updated table.</a>
	<%} %>
	<%-- Player insertion feature. --%>
	<h2>Player Insert</h2>
	<form action="Players" method="POST">
	<label for="insertPlayer"><b>Player to Insert:</b></label>
	<input type="text" name="insertPlayer">
	<label for="playerNum"><b>Player Number:</b></label>
	<input type="text" name="playerNum">
	<label for="playerAge"><b>Player Age:</b></label>
	<input type="text" name="playerAge">
	<label for="playerPos"><b>Player Position:</b></label>
	<input type="text" name="playerPos">
	<input type="submit" value="Insert Player">
	</form><br>
	<%if (request.getAttribute("pimessage") != null) {
		String pi_msg = (String) request.getAttribute("pimessage");
		out.print(pi_msg);
	%>
	<a href="Players">Click here to view updated table.</a>
	<%} %>
</body>
</html>
