package edu.louisville.cecs640.final_project;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * Servlet used to perform logout requests.
 */

@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Logout() {
        super();
    }

	// GET request handling.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Invalidates session upon request. Redirects user to login page.
		HttpSession session = request.getSession();
		session.invalidate();
		String url = "Login";
		response.sendRedirect(url);
	}

	// POST request handling.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Handles requests same as GETs.
		doGet(request, response);
	}

}
