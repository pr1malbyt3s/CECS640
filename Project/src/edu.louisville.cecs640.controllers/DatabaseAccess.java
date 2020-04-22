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

/* Class used to control database access across application.
 * Methods are called from respective servlets to perform specific functions.
 */

public class DatabaseAccess {
	// Set global variables for class.
	private Connection dbConnection = null;
	private String dbName = null;
	private String userID = null;
	private String password = null;
	private String host = null;
	private String url = null;
	private String driver = null;
	private String crypt = null;
	
	/* Method to get database connection properties from configuration file.
	 * Prevents connection details, such as credentials from being hard-coded in source.
	 * Assigns retrieved properties to global connection variables.
	 */
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
	
	// Method that performs driver loading and database connection.
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
	
	// Method that performs database disconnect upon query execution.
	public void DatabaseDisconnect() {
		try {
			dbConnection.close();
		}
		catch (Exception sqle) {
			System.out.println("SQL Disconnect Error.");
		}
	}
	
	/* Method that performs user validation.
	 * Accepts username and password to perform query.
	 * Compares password against encrypted password value stored in database.
	 */
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
	
	/* Method that retrieves all contents from Players table.
	 * Stores retrieved contents in an ArrayList of Player_Bean types.
	 * Returns constructed ArrayList.
	 */
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
	
	/* Method used to delete an individual player by name.
	 * Accepts player's name as input to execute query.
	 */
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
	
	/* Method used to update an individual player by name.
	 * Accepts player's name, the attribute to update, and its value to execute query.
	 * Only used to update statistics and not player roster attributes.
	 */
	public void UpdatePlayer(String playerName, String attribute, double value) throws SQLException {
		String stat = attribute;
		String query = null;
		switch (stat) {
			case "GP": // Games Played
				query = "UPDATE Players SET GP = ? WHERE NAME = ?";
				break;
			case "G": // Goals
				query = "UPDATE Players SET G = ? WHERE NAME = ?";
				break;
			case "A": // Assists
				query = "UPDATE Players SET A = ? WHERE NAME = ?";
				break;
			case "PTS": // Points
				query = "UPDATE Players SET PTS = ? WHERE NAME = ?";
				break;
			case "PIM": // Penalty Minutes
				query = "UPDATE Players SET PIM = ? WHERE NAME = ?";
				break;
			case "xGF": // Expected Goals For
				query = "UPDATE Players SET xGF = ? WHERE NAME = ?";
				break;
			case "xGA": // Expected Goals Against
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
	
	/* Method used to insert a new player.
	 * Accepts player's name, number, age, and position.
	 * All statistical values are defaulted to zero.
	 */
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
	
	/* Method that retrieves all contents from Games table.
	 * Stores retrieved contents in an ArrayList of Game_Bean types.
	 * Returns constructed ArrayList.
	 */
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
	
	/* Method used to delete and individual game by date.
	 * Accepts games date as a string to execute query.
	 */
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
	
	/* Method used to update game statistical values.
	 * Accepts game's date, attribute to update, and its updated value.
	 * Cannot be used to update opponent.
	 */
	public void UpdateGame(String date, String attribute, double value) throws SQLException {
		String stat = attribute;
		String query = null;
		switch (stat) {
			case "GF": // Goals For
				query = "UPDATE Games SET GF = ? WHERE DATE = ?";
				break;
			case "GA": // Goals Against
				query = "UPDATE Games SET GA = ? WHERE DATE = ?";
				break;
			case "SF": // Shots For
				query = "UPDATE Games SET SF = ? WHERE DATE = ?";
				break;
			case "xGF": // Expected Goals For
				query = "UPDATE Games SET xGF = ? WHERE DATE = ?";
				break;
			case "xGA": // Expected Goals Against
				query = "UPDATE Games SET xGA = ? WHERE DATE = ?";
				break;
			case "HDSF": // High Danger Shots For
				query = "UPDATE Games SET HDSF = ? WHERE DATE = ?";
				break;
			case "HDSA": // High Danger Shots Against
				query = "UPDATE Games SET HDSA = ? WHERE DATE = ?";
				break;
			case "PDO": // PDO Rating
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
	
	/* Method used to insert new game into games table.
	 * Accepts game date and opponent string values.
	 * Statistical values defaulted to zero.
	 */
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
	
	/* Method that retrieves all contents from FreeAgents table.
	 * Stores retrieved contents in an ArrayList of Agent_Bean types.
	 * Returns constructed ArrayList.
	 */
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
	
	/* Method used to delete free agent by name.
	 * Accepts free agent's name to execute query.
	 */
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
	
	/* Method used to update free agent statistical values by name.
	 * Accepts free agent's name, the attribute name, and updated attribute value.
	 * Cannot be used to update free agent biological attributes.
	 */
	public void UpdateAgent(String playerName, String attribute, int value) throws SQLException {
		String stat = attribute;
		String query = null;
		switch (stat) {
			case "CAP": // Expected Cap Hit
				query = "UPDATE FreeAgents SET CAP = ? WHERE NAME = ? ";
				break;
			case "G": // Goals
				query = "UPDATE FreeAgents SET G = ? WHERE NAME = ? ";
				break;
			case "A": // Assists
				query = "UPDATE FreeAgents SET A = ? WHERE NAME = ? ";
				break;
			case "PTS": // Points
				query = "UPDATE FreeAgents SET PTS = ? WHERE NAME = ? ";
				break;
			case "PM": // Plus/Minus Rating
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
	
	/* Method used to insert a free agent by name.
	 * Accepts free agent's name, current team, age, and position.
	 * All statistical values defaulted to zero.
	 */
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
