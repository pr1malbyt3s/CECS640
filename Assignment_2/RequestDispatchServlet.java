package edu.louisville.cse640.assignment2;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestDispatcherServlet
 */
@WebServlet("/RequestDispatcherServlet")
public class RequestDispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RequestDispatcherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<TITLE>Carolina Hurricanes</TITLE>");
		out.println("</HEAD>");
		out.println("<BODY>");
		out.println("<B>Carolina Hurricanes</B><BR>");
		//Initiating the RequestDispatcher.
		RequestDispatcher rd = request.getRequestDispatcher("Picture.html");
		//Forward method example.
		//rd.forward(request, response);
		//Include method example.
		rd.include(request, response);
		out.println("<BR>");
		out.println("Take Warning.");
		out.println("</BODY>");
		out.println("</HTML>");
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
