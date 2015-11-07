package fr.univnantes.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.VoidWork;

import fr.unantes.bean.Amenagement;
import fr.unantes.bean.Website;

import static com.googlecode.objectify.ObjectifyService.ofy;

@SuppressWarnings("serial")
public class AmenagementServlet extends HttpServlet {
	 static {
	        ObjectifyService.register(Amenagement.class);
	    }

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		try {
			ObjectifyService.ofy();

			// Création de l'objet
			String nom = req.getParameter("nom");
			String description = req.getParameter("description");
			//Amenagement amenagement = new Amenagement(nom, description);

			// Enregistrement de l'objet dans le Datastore avec Objectify
			//ofy().save().entity(amenagement).now();
			resp.sendRedirect("/");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		try {
			ObjectifyService.ofy();

			String[] nom = req.getParameterValues("nom");
			for (String nom_amenagement : nom) {
				//Amenagement amenagement = new Amenagement(nom_amenagement, "");

				// Enregistrement de l'objet dans le Datastore avec Objectify
				//ofy().save().entity(amenagement).now();

			}

			resp.sendRedirect("/");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
