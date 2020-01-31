package edu.louisville.cse640.assignment1_2;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Reverse
 */
@WebServlet("/Reverse")
public class Reverse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Reverse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Set default URL to outputForm.jsp
		String url = "/outputForm.jsp";
		// Store the nString request parameter in a variable.
        String rString = request.getParameter("nString");
        // Check that string only contains letters, numbers, or spaces.
        // If so, return an error message.
        if (!rString.matches("[\\w*\\s*]*")) {
            url = "/inputForm.jsp";
            request.setAttribute("error", "String can only contain letters, numbers, and spaces.");
        }
        // If string passes check, perform reverse function and set reversed string as request attribute.
        else {
        	String revString = new StringBuilder(rString).reverse().toString();
        	request.setAttribute("reversedString", revString);
        }
        ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher(url);
        dispatcher.forward(request, response);
	}
}
