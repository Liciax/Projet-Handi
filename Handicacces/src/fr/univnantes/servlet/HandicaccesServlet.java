package fr.univnantes.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class HandicaccesServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
		
		//this.getServletContext().getRequestDispatcher( "popup.html" ).forward( req, resp );
	}
}
