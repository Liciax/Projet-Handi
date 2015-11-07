package fr.univnantes.servlet;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.ObjectifyService;

import fr.unantes.bean.Website;

public class WebsiteServlet extends HttpServlet {
	 static {
	        ObjectifyService.register(Website.class);
	    }

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
/*
		try {
			ObjectifyService.ofy();			

			//Crée l'objet Website à partir de l'url
			String url = req.getRequestURL().toString();
			
			
			Website website = new Website(url);

			// Enregistrement de l'objet dans le Datastore avec Objectify
			ofy().save().entity(website).now();
			resp.sendRedirect("/");

		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
	}
}
