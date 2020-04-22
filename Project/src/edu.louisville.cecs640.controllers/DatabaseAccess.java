package edu.louisville.cecs640.controllers;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ArrayList;
import edu.louisville.cecs640.final_project.Agent_Bean;
import edu.louisville.cecs640.final_project.Game_Bean;
import edu.louisville.cecs640.final_project.Player_Bean;

public class DatabaseAccess {
	private Connection dbConnection = null;
	private String dbName = null;
	private String userID = null;
	private String password = null;
	private String host = null;
	private String url = null;
	private String driver = null;
	private String crypt = null;
	
	public void GetConnectionProperties(String fileName) {
		Properties p = null;
		try {
			InputStream f = getClass().getClassLoader().getResourceAsStream(fileName);
			p = new Properties();
			p.load(f);
			userID = p.getProperty("userID");
			password = p.getProperty("password");
			host = p.getProperty("host");
			driver = p.getProperty("driver");
			dbName = p.getProperty("dbName");
			crypt = p.getProperty("crypt");
		} catch(Exception e) {
			System.out.println("GetConnectionProperties Error.");
			e.printStackTrace();
		}
		
	}

	public void DatabaseConnect() {
		url = host + dbName;
		try {
			Class.forName(driver);
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("DatabaseConnect Error.");
		}
		try {
			dbConnection = DriverManager.getConnection(url, userID, password);
		}
		catch (SQLException sqle) {
			System.out.println("SQL Connect Error.");
		}
	}
	
	public void DatabaseDisconnect() {
		try {
			dbConnection.close();
		}
		catch (Exception sqle) {
			System.out.println("SQL Disconnect Error.");
		}
	}
	
	public boolean ValidateUser(String user, String password) throws SQLException {
		boolean validate = false;
		String query = "SELECT * FROM Users WHERE UPPER(NAME) = UPPER(?) AND DECRYPT_CHAR(PASSWORD, ?) = ? ";
		PreparedStatement ps = dbConnection.prepareStatement(query);
		ps.setString(1, user);
		ps.setString(2, crypt);
		ps.setString(3, password);
		try {
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				validate = true;
			}
		} 
		catch (SQLException e) {
			System.out.println("ValidateUser SQL Execute Error.");
		}
		return validate;
	}
	
	public ArrayList<Player_Bean> ListPlayers() throws SQLException {
		ArrayList<Player_Bean> players = new ArrayList<Player_Bean>();
		String query = "SELECT * FROM Players";
		try {
			Statement s = dbConnection.createStatement();
			ResultSet rs = s.executeQuery(query);
			while (rs.next()) {
				Player_Bean player = new Player_Bean();
				player.setName(rs.getString("NAME"));
				player.setNum(rs.getInt("NUM"));
				player.setAge(rs.getInt("AGE"));
				player.setPos(rs.getString("POS"));
				player.setGP(rs.getInt("GP"));
				player.setG(rs.getInt("G"));
				player.setA(rs.getInt("A"));
				player.setPts(rs.getInt("PTS"));
				player.setPim(rs.getInt("PIM"));
				player.setXGF(rs.getDouble("xGF"));
				player.setXGA(rs.getDouble("xGA"));
				players.add(player);
			}
		}
		catch (SQLException e) {
			System.out.println("List Players SQL Error.");
		}
		return players;
	}
	
	public void DeletePlayer(String playerName) throws SQLException {
		String query = "DELETE FROM Players WHERE NAME = ? ";
		PreparedStatement ps = dbConnection.prepareStatement(query);
		ps.setString(1, playerName);
		try {
			ps.executeUpdate();
		} 
		catch (SQLException e) {
			System.out.println("DeletePlayer SQL Execute Error.");
		}
	}
	
	public void UpdatePlayer(String playerName, String attribute, double value) throws SQLException {
		String stat = attribute;
		String query = null;
		switch (stat) {
			case "GP":
				query = "UPDATE Players SET GP = ? WHERE NAME = ?";
				break;
			case "G":
				query = "UPDATE Players SET G = ? WHERE NAME = ?";
				break;
			case "A":
				query = "UPDATE Players SET A = ? WHERE NAME = ?";
				break;
			case "PTS":
				query = "UPDATE Players SET PTS = ? WHERE NAME = ?";
				break;
			case "PIM":
				query = "UPDATE Players SET PIM = ? WHERE NAME = ?";
				break;
			case "xGF":
				query = "UPDATE Players SET xGF = ? WHERE NAME = ?";
				break;
			case "xGA":
				query = "UPDATE Players SET xGA = ? WHERE NAME = ?";
				break;
		}
		PreparedStatement ps = dbConnection.prepareStatement(query);
		ps.setDouble(1, value);
		ps.setString(2, playerName);
		try {
			ps.executeUpdate();
			System.out.println(ps);
		}
		catch (SQLException e) {
			System.out.println("UpdatePlayer SQL Execute Error.");
			System.out.println(ps);
			System.out.println(ps.executeUpdate());
		}
	}
	
	public void InsertPlayer (String playerName, int num, int age, String pos) throws SQLException {
		String query = "INSERT INTO Players values(?, ?, ?, ?, 0, 0, 0, 0, 0, 0, 0)";
		PreparedStatement ps = dbConnection.prepareStatement(query);
		ps.setString(1, playerName);
		ps.setInt(2, num);
		ps.setInt(3, age);
		ps.setString(4, pos);
		try {
			ps.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println("InsertPlayer SQL Execute Error.");
		}
	}
	
	public ArrayList<Game_Bean> ListGames() throws SQLException {
		ArrayList<Game_Bean> games = new ArrayList<Game_Bean>();
		String query = "SELECT * FROM Games";
		try {
			Statement s = dbConnection.createStatement();
			ResultSet rs = s.executeQuery(query);
			while (rs.next()) {
				Game_Bean game = new Game_Bean();
				game.setDate(rs.getString("DATE"));
				game.setOpp(rs.getString("OPP"));
				game.setGF(rs.getInt("GF"));
				game.setGA(rs.getInt("GA"));
				game.setSF(rs.getInt("SF"));
				game.setXGF(rs.getDouble("xGF"));
				game.setXGA(rs.getDouble("xGA"));
				game.setHDSF(rs.getInt("HDSF"));
				game.setHDSA(rs.getInt("HDSA"));
				game.setPDO(rs.getDouble("PDO"));
				games.add(game);
			}	
		}
		catch (SQLException e) {
			System.out.println("List Games SQL Error.");
		}
		return games;
	}
	
	public void DeleteGame(String date) throws SQLException {
		String query = "DELETE FROM Games WHERE DATE = ? ";
		PreparedStatement ps = dbConnection.prepareStatement(query);
		ps.setString(1, date);
		try {
			ps.executeUpdate();
		} 
		catch (SQLException e) {
			System.out.println("DeletePlayer SQL Execute Error.");
		}
	}
	
	public void UpdateGame(String date, String attribute, double value) throws SQLException {
		String stat = attribute;
		String query = null;
		switch (stat) {
			case "GF":
				query = "UPDATE Games SET GF = ? WHERE DATE = ?";
				break;
			case "GA":
				query = "UPDATE Games SET GA = ? WHERE DATE = ?";
				break;
			case "SF":
				query = "UPDATE Games SET SF = ? WHERE DATE = ?";
				break;
			case "xGF":
				query = "UPDATE Games SET xGF = ? WHERE DATE = ?";
				break;
			case "xGA":
				query = "UPDATE Games SET xGA = ? WHERE DATE = ?";
				break;
			case "HDSF":
				query = "UPDATE Games SET HDSF = ? WHERE DATE = ?";
				break;
			case "HDSA":
				query = "UPDATE Games SET HDSA = ? WHERE DATE = ?";
				break;
			case "PDO":
				query = "UPDATE Games SET PDO = ? WHERE DATE = ?";
				break;
		}
		PreparedStatement ps = dbConnection.prepareStatement(query);
		ps.setDouble(1, value);
		ps.setString(2, date);
		try {
			ps.executeUpdate();
			System.out.println(ps);
		}
		catch (SQLException e) {
			System.out.println("UpdateGame SQL Execute Error.");
			System.out.println(ps);
			System.out.println(ps.executeUpdate());
		}
	}
	
	public void InsertGame (String date, String opp) throws SQLException {
		String query = "INSERT INTO Games values(?, ?, 0, 0, 0, 0, 0, 0, 0, 0)";
		PreparedStatement ps = dbConnection.prepareStatement(query);
		ps.setString(1, date);
		ps.setString(2, opp);
		try {
			ps.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println("InsertGame SQL Execute Error.");
		}
	}
	
	public ArrayList<Agent_Bean> ListAgents() throws SQLException {
		ArrayList<Agent_Bean> agents = new ArrayList<Agent_Bean>();
		String query = "SELECT * FROM FreeAgents";
		try {
			Statement s = dbConnection.createStatement();
			ResultSet rs = s.executeQuery(query);
			while (rs.next()) {
				Agent_Bean agent = new Agent_Bean();
				agent.setName(rs.getString("NAME"));
				agent.setTeam(rs.getString("TEAM"));
				agent.setAge(rs.getInt("AGE"));
				agent.setPos(rs.getString("POS"));
				agent.setG(rs.getInt("G"));
				agent.setA(rs.getInt("A"));
				agent.setPts(rs.getInt("PTS"));
				agent.setPm(rs.getInt("PM"));
				agent.setCap(rs.getInt("CAP"));
				agents.add(agent);
			}
		}
		catch (SQLException e) {
			System.out.println("List Agents SQL Error.");
		}
		return agents;
	}
	
	public void DeleteAgent(String playerName) throws SQLException {
		String query = "DELETE FROM FreeAgents WHERE NAME = ? ";
		PreparedStatement ps = dbConnection.prepareStatement(query);
		ps.setString(1, playerName);
		try {
			ps.executeUpdate();
		} 
		catch (SQLException e) {
			System.out.println("DeleteAgent SQL Execute Error.");
		}
	}
	
	public void UpdateAgent(String playerName, String attribute, int value) throws SQLException {
		String stat = attribute;
		String query = null;
		switch (stat) {
			case "CAP":
				query = "UPDATE FreeAgents SET CAP = ? WHERE NAME = ? ";
				break;
			case "G":
				query = "UPDATE FreeAgents SET G = ? WHERE NAME = ? ";
				break;
			case "A":
				query = "UPDATE FreeAgents SET A = ? WHERE NAME = ? ";
				break;
			case "PTS":
				query = "UPDATE FreeAgents SET PTS = ? WHERE NAME = ? ";
				break;
			case "PM":
				query = "UPDATE FreeAgents SET PM = ? WHERE NAME = ? ";
				break;
		}
		PreparedStatement ps = dbConnection.prepareStatement(query);
		ps.setInt(1, value);
		ps.setString(2, playerName);
		try {
			ps.executeUpdate();
			System.out.println(ps);
		}
		catch (SQLException e) {
			System.out.println("UpdateAgent SQL Execute Error.");
			System.out.println(ps);
			System.out.println(ps.executeUpdate());
		}
	}
	
	public void InsertAgent (String playerName, String teamName, int age, String pos) throws SQLException {
		String query = "INSERT INTO FreeAgents values(?, ?, ?, ?, 0, 0, 0, 0, 0)";
		PreparedStatement ps = dbConnection.prepareStatement(query);
		ps.setString(1, playerName);
		ps.setString(2, teamName);
		ps.setInt(3, age);
		ps.setString(4, pos);
		try {
			ps.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println("InsertAgent SQL Execute Error.");
		}
	}
}
