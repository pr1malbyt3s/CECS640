package edu.louisville.cecs640.assignment3_1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Servlet_B
 */
@WebServlet("/Servlet_B")
public class Servlet_B extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet_B() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    // Get the current session.
		HttpSession p1Session = request.getSession();
		response.setContentType("text/html");
		if (p1Session == null) {
			System.out.println("Session is null");
		}
		else {
    // Create PrintWriter to write HTML and session attributes.
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<TITLE>Assignment 3 Problem 1</TITLE>");
		out.println("</HEAD>");
		out.println("<BODY>");
		out.println("Assignment 3 Problem 1 <BR>");
    // Printing the greeting passed from Servlet_A
		out.println(p1Session.getAttribute("greeting") + "<BR>");
    // Printing the time passed from Servlet_A
		out.println(p1Session.getAttribute("time"));
		out.println("</BODY>");
		out.println("</HTML>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
