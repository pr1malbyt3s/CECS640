package edu.louisville.cecs640.final_project;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import edu.louisville.cecs640.controllers.DatabaseAccess;

/* 
 * Players servlet class used to handle all interaction with Players table.
 * Uses external DatabaseAccess class to perform database functions.
 */

@WebServlet("/Players")
public class Players extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DatabaseAccess database = null;
	
    public Players() {
        super();
    }
    
    // Method to perform connection properties loading and database connection.
    public void DBConnect() {
    	database = new DatabaseAccess();
    	if (database != null) {
    		try {
    			String config = getServletContext().getInitParameter("dbConfig");
    			database.GetConnectionProperties(config);
    			database.DatabaseConnect();
    		}
    		catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    }
    
    // Method use to handle player deletion requests.
    public String RemovePlayer(String playerName) {
    	String message = null;
    	if (playerName != null) {
    		try {
    			DBConnect();
    			database.DeletePlayer(playerName);
    			database.DatabaseDisconnect();
    			message = "Successful player removal.";
    		}
    		catch (Exception e) {
    			System.out.println("Error with player removal request.");
    			message = "Error removing player.";
    		}
    	}
    	return message;
    }
    
    // Method used to handle player update requests.
    public String UpdatePlayer(String playerName, String attribute, double value) {
    	String message = null;
    	if (playerName != null) {
    		try {
    			DBConnect();
        		database.UpdatePlayer(playerName, attribute, value);
        		database.DatabaseDisconnect();
        		message = "Successful player update.";
    		}
    		catch (Exception e) {
    			System.out.println("Error with player update request.");
    			message = "Error updating player.";
    		}
    	}
    	return message;
    }
    
    // Method used to handle player insertion requests.
    public String InsertPlayer(String playerName, int num, int age, String pos) {
    	String message = null;
    	if (playerName != null) {
    		try {
    			DBConnect();
    			database.InsertPlayer(playerName, num, age, pos);
    			database.DatabaseDisconnect();
    			message = "Successful player insertion.";
    		}
    		catch (Exception e) {
    			System.out.println("Error with insert player request.");
    			message = "Error inserting player.";
    		}
    	}
    	return message;
    }
    
    // GET request handling.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Performs session validation.
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			String url = "Login";
			response.sendRedirect(url);
		}
		// If session is valid, displays Players table contents by default.
		else {
			String url = "Player_Stats.jsp";
			try {
				DBConnect();
				ArrayList<Player_Bean> players = database.ListPlayers();
				session.setAttribute("playerdata", players);
				database.DatabaseDisconnect();
			}
			catch (Exception e) {
				System.out.println("Error with Players GET request.");
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request,response);
		}
	}
	
	// POST	request handling.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Performs session validation.
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			String url = "Login";
			response.sendRedirect(url);
		}
		// If session valid, checks for request parameters to know which function to perform.
		else {
			String url = "Player_Stats.jsp";
			// Remove player request.
			if (request.getParameter("removePlayer") != null) {
				String removePlayer = request.getParameter("removePlayer");
				request.setAttribute("pdmessage", RemovePlayer(removePlayer));
			}
			// Update player request.
			else if (request.getParameter("updatePlayer") != null) {
				String updatePlayer = request.getParameter("updatePlayer");
				String statName = request.getParameter("statName");
				double statValue = Double.parseDouble(request.getParameter("statValue"));
				request.setAttribute("pumessage", UpdatePlayer(updatePlayer, statName, statValue));
			// Insert player request.
			} else if (request.getParameter("insertPlayer") != null) {
				String insertPlayer = request.getParameter("insertPlayer");
				int playerNum = Integer.parseInt(request.getParameter("playerNum"));
				int playerAge = Integer.parseInt(request.getParameter("playerAge"));
				String playerPos = request.getParameter("playerPos");
				request.setAttribute("pimessage", InsertPlayer(insertPlayer, playerNum, playerAge, playerPos));
			}
			// Perform a GET request if no other criteria matched.
			else {
				doGet(request, response);
			}
			// Forward request appropriately.
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request,response);
		}
	}
}
