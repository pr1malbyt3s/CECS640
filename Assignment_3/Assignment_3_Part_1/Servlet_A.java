package edu.louisville.cecs640.assignment3_1;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Servlet_A
 */
@WebServlet("/Servlet_A")
public class Servlet_A extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet_A() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Create 'greeting' parameter.
		String greeting = "Hi CECS640 2020 Class";
		// Create 'time' parameter.
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("hh:mm:ss a");
		String time = dateFormat.format(now);
		// Create a new HTTP Session.
		HttpSession p1Session = request.getSession();
		// Ensure session is not null.
		if (p1Session != null) {
			p1Session.setAttribute("greeting", greeting);
			p1Session.setAttribute("time", time);
		}
		if (p1Session == null) {
			System.out.println("Session is null");
		}
		// Redirect response to Servlet_B.
		response.sendRedirect("Servlet_B");
	}
}
