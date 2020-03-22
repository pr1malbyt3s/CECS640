package edu.louisville.cecs640.assignment4;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import edu.louisville.cecs640.controllers.UsersTableAccess;
/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsersTableAccess database = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }
    
    public void DBConnect() {
    	database = new UsersTableAccess();
    	if (database != null) {
    		try {
    			database.GetConnectionProperties("/home/pr1malbyt3s/Documents/CECS640/Assignment_4/Assignment_4_Project/src/config.properties");
        		database.DatabaseConnect();
    		}
    		catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    	
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "Welcome.jsp";
		String user = request.getParameter("username");
		String pw = request.getParameter("password");
		if (user == null || pw == null ) {
			url = "Login.jsp";
			request.setAttribute("error", "Input fields cannot be empty");
		}
		else {
			try {
				DBConnect();
				if(database.ValidateUser(user, pw) == true) {
					request.setAttribute("name", user);
				}
				else {
					url = "Login.jsp";
					request.setAttribute("error", "Invalid username or password. Please try again.");
				}
				database.DatabaseDisconnect();
			}
			catch (Exception e) {
				System.out.println("Error with request");
			}
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
