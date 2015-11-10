package fr.univnantes.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.VoidWork;

import fr.unantes.bean.Amenagement;
import fr.unantes.bean.Website;

import static com.googlecode.objectify.ObjectifyService.ofy;

import com.google.api.server.spi.config.Api;



   

@SuppressWarnings("serial")
public class AjoutRenseignementsServlet extends HttpServlet {
	 static {
		 	ObjectifyService.register(Website.class);
	        ObjectifyService.register(Amenagement.class);
	    }
	 

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		try {
			ObjectifyService.ofy();
			
			//Crée l'objet Website à partir de l'url
			String url = req.getParameter("url");
			//String url = req.getRequestURL().toString();


			//Crée les aménagements renseignés par l'utilisateur
			String[] nom = req.getParameterValues("nom");
			for (String nom_amenagement : nom) {
				Website website = new Website(url, nom_amenagement, "");

				// Enregistrement de l'objet dans le Datastore avec Objectify
				ofy().save().entity(website).now();

			}
			resp.sendRedirect("/");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
