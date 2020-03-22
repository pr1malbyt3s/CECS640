package edu.louisville.cecs640.assignment4;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import edu.louisville.cecs640.controllers.UsersTableAccess;

/* Login servlet class used to process credential request from login page.
 * Uses external UsersTableAccess class to perform database functions.
 */

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsersTableAccess database = null;
   
    public Login() {
        super();
    }
    
    // Method to perform connection properties loading and database connection.		
    public void DBConnect() {
    	database = new UsersTableAccess();
    	if (database != null) {
    		try {
    			database.GetConnectionProperties("config.properties");
        		database.DatabaseConnect();
    		}
    		catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    	
    }
    
	// Handles all GET requests as POST requests.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	// Request handling- where all the magic happens.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Set default URL.
		String url = "Welcome.jsp";
		// Get request login parameters.
		String user = request.getParameter("username");
		String pw = request.getParameter("password");
		// Ensure that parameters are not empty. If so, set an empty field error message.
		if (user == null || pw == null ) {
			url = "Login.jsp";
			request.setAttribute("error", "Input fields cannot be empty");
		}
		else {
			// Perform database connection and user validation.
			try {
				DBConnect();
				// If successful, set name attribute for Welcome page redirection.
				if(database.ValidateUser(user, pw) == true) {
					request.setAttribute("name", user);
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
		// Forward the request response to the appropriate URL.
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
