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

/* Games servlet class used to handle all interaction with Games table.
 * Uses external DatabaseAccess class to perform database functions.
 */

@WebServlet("/Games")
public class Games extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DatabaseAccess database = null;
       
    public Games() {
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
    
    // Method to handle game deletion requests.
    public String RemoveGame(String date) {
    	String message = null;
    	if (date != null) {
    		try {
    			DBConnect();
    			database.DeleteGame(date);
    			database.DatabaseDisconnect();
    			message = "Successful game removal.";
    		}
    		catch (Exception e) {
    			System.out.println("Error with game removal request.");
    			message = "Error removing game.";
    		}
    	}
    	return message;
    }
    
    //Method to handle game update requests.
    public String UpdateGame(String date, String attribute, double value) {
    	String message = null;
    	if (date != null) {
    		try {
    			DBConnect();
        		database.UpdateGame(date, attribute, value);
        		database.DatabaseDisconnect();
        		message = "Successful game update.";
    		}
    		catch (Exception e) {
    			System.out.println("Error with game update request.");
    			message = "Error updating game.";
    		}
    	}
    	return message;
    }
    
    // Method to handle game insertion requests.
    public String InsertGame(String date, String opp) {
    	String message = null;
    	if (date != null) {
    		try {
    			DBConnect();
    			database.InsertGame(date, opp);
    			database.DatabaseDisconnect();
    			message = "Successful game insertion.";
    		}
    		catch (Exception e) {
    			System.out.println("Error with insert game request.");
    			message = "Error inserting game.";
    		}
    	}
    	return message;
    }
    
    // GET Request handling.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Performs session validation.
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			String url = "Login";
			response.sendRedirect(url);
		}
		// If session is valid, displays Games table contents by default.
		else {
			String url = "Game_Stats.jsp";
			try {
				DBConnect();
				ArrayList<Game_Bean> games = database.ListGames();
				session.setAttribute("gamedata", games);
				database.DatabaseDisconnect();
			}
			catch (Exception e) {
				System.out.println("Error with Games GET request.");
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request,response);
		}
	}

	// POST request handling.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			String url = "Login";
			response.sendRedirect(url);
		}
		// If session valid, checks for request parameters to know which function to perform.
		else {
			String url = "Game_Stats.jsp";
			// Remove game request.
			if (request.getParameter("removeGame") != null) {
				String removeGame = request.getParameter("removeGame");
				request.setAttribute("gdmessage", RemoveGame(removeGame));
			}
			// Update game request.
			else if (request.getParameter("updateGame") != null) {
				String updateGame= request.getParameter("updateGame");
				String statName = request.getParameter("statName");
				double statValue = Double.parseDouble(request.getParameter("statValue"));
				request.setAttribute("gumessage", UpdateGame(updateGame, statName, statValue));
			// Insert game request.	
			} else if (request.getParameter("insertGame") != null) {
				String insertGame = request.getParameter("insertGame");
				String gameOpp = request.getParameter("gameOpp");
				request.setAttribute("gimessage", InsertGame(insertGame, gameOpp));
			}
			// Perform a GET request if no other criteria matched.
			else {
				doGet(request, response);
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request,response);
		}
	}
}
