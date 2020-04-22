package edu.louisville.cecs640.final_project;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.louisville.cecs640.controllers.DatabaseAccess;

/* Login servlet class used to process credential request from login page.
 * Uses external UsersTableAccess class to perform database functions.
 */

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DatabaseAccess database = null;
   
    public Login() {
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
	// GET request hander. Simply displays login page with no actions.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "Login.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}

	// Post request handling- where all the magic happens.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Set default URL.
		String url = "Home.jsp";
		// Get request login parameters.
		String user = request.getParameter("username");
		String pw = request.getParameter("password");
		// Perform database connection and user validation.
		if (user != null && pw != null) {
			try {
				DBConnect();
				// If successful, invalidate user session.
				if(database.ValidateUser(user, pw) == true) {
					HttpSession oldSession = request.getSession(false);
					if (oldSession != null) {
						oldSession.invalidate();
					}
					// Create new session ID, set an attribute to validate session checks.
					HttpSession newSession = request.getSession(true);
					newSession.setMaxInactiveInterval(10*60);
					newSession.setAttribute("user", user);
				}
				// If unsuccessful, set error attribute for Login page redirection.
				else {
					url = "Login.jsp";
					request.setAttribute("error", "Invalid username or password. Please try again.");
				}
				// Disconnect from database when done.
				database.DatabaseDisconnect();
			}
			catch (Exception e) {
				System.out.println("Error with request");
			}
		}
		else {
			url = "Login.jsp";
			request.setAttribute("error", "Input fields cannot be empty.");
		}
		// Forward the request response to the appropriate URL.
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
