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

/* FreeAgents servlet class used to handle all interaction with FreeAgents table.
 *  Uses external DatabaseAccess class to perform database functions.
 */

@WebServlet("/FreeAgents")
public class FreeAgents extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DatabaseAccess database = null;
       
    public FreeAgents() {
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
    
    // Method used to handle free agent deletion requests.
    public String RemoveAgent(String playerName) {
    	String message = null;
    	if (playerName != null) {
    		try {
    			DBConnect();
    			database.DeleteAgent(playerName);
    			database.DatabaseDisconnect();
    			message = "Successful Free Agent removal.";
    		}
    		catch (Exception e) {
    			System.out.println("Error with free agent removal request.");
    			message = "Error removing free agent.";
    		}
    	}
    	return message;
    }
    
    // Method used to handle free agent update requests.
    public String UpdateAgent(String playerName, String attribute, int value) {
    	String message = null;
    	if (playerName != null) {
    		try {
    			DBConnect();
        		database.UpdateAgent(playerName, attribute, value);
        		database.DatabaseDisconnect();
        		message = "Successful free agent update.";
    		}
    		catch (Exception e) {
    			System.out.println("Error with free agent update request.");
    			message = "Error updating free agent.";
    		}
    	}
    	return message;
    }
    
    // Method used to handle free agent insertion requests.
    public String InsertAgent(String playerName, String teamName, int age, String pos) {
    	String message = null;
    	if (playerName != null) {
    		try {
    			DBConnect();
    			database.InsertAgent(playerName, teamName, age, pos);
    			database.DatabaseDisconnect();
    			message = "Successful free agent insertion.";
    		}
    		catch (Exception e) {
    			System.out.println("Error with insert free agent request.");
    			message = "Error inserting free agent.";
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
		// If session valid, displays Games table contents by default.
		else {
			String url = "Free_Agents.jsp";
			try {
				DBConnect();
				ArrayList<Agent_Bean> agents = database.ListAgents();
				session.setAttribute("agentdata", agents);
				database.DatabaseDisconnect();
			}
			catch (Exception e) {
				System.out.println("Error with FreeAgents GET request.");
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request,response);
		}
	}

	// POST request handling.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Performs session validation.
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			String url = "Login";
			response.sendRedirect(url);
		}
		// If session valid, checks for request parameters to know which function to perform.
		else {
			String url = "Free_Agents.jsp";
			// Remove agent request.
			if (request.getParameter("removeAgent") != null) {
				String removeAgent = request.getParameter("removeAgent");
				request.setAttribute("admessage", RemoveAgent(removeAgent));
			}
			// Update agent request.
			else if (request.getParameter("updateAgent") != null) {
				String updateAgent = request.getParameter("updateAgent");
				String statName = request.getParameter("statName");
				int statValue = Integer.parseInt(request.getParameter("statValue"));
				request.setAttribute("aumessage", UpdateAgent(updateAgent, statName, statValue));
			// Insert agent request.
			} else if (request.getParameter("insertAgent") != null) {
				String insertAgent = request.getParameter("insertAgent");
				String playerTeam = request.getParameter("playerTeam");
				int playerAge = Integer.parseInt(request.getParameter("playerAge"));
				String playerPos = request.getParameter("playerPos");
				request.setAttribute("aimessage", InsertAgent(insertAgent, playerTeam, playerAge, playerPos));
			}
			// Perform GET request if no other criteria matched.
			else {
				doGet(request, response);
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request,response);
		}
	}		
}
