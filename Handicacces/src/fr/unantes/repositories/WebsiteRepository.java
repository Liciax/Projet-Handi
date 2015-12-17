package fr.unantes.repositories;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.ReadPolicy;
import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

import fr.unantes.beans.Layout;
import fr.unantes.beans.Website;
 
import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WebsiteRepository {

	private static WebsiteRepository websiteRepository = null;
	 
	 static {
	  ObjectifyService.register(Website.class);
	  ObjectifyService.register(Layout.class);
	 }
	 
	 private WebsiteRepository() {
	 }
	 
	 public static synchronized WebsiteRepository getInstance() {
	  if (null == websiteRepository) {
		  websiteRepository = new WebsiteRepository();
	  }
	  return websiteRepository;
	 }
	 
	 //Retourne tous les sites web
	 public List<Website> findWebsites() {
		 List<Website> websites = ofy().load().type(Website.class).list();
		 return websites;
	 }
	 
	 //Retourne tous les sites web qui possède un aménagement particulier
	 public List<Website> findWebsitesByAmenagements(int id_layout) {
		 List<Website> websites = ofy().load().type(Website.class).filter("layout", id_layout).list();
	     return websites;
	    }
	 
	 //Retourne le site selon son URL
	 public Website findWebsiteByURL(String url){
		 Website website = ofy().load().type(Website.class).id(url).now();
		 return website;
	 }

	 //Crée un site web
	 public Website create(Website website) throws Exception {	  
	String url = website.getUrl();
	if(ofy().load().type(Website.class).id(url).now() != null){
		throw new Exception("Site web deja existant");
	}
		 ofy().save().entity(website).now();
		 return website;
	 }
	 
	 
	 //Update le site web et lui ajoute un amenagement
	 public Website update(Website editedWebsite) throws Exception{
		 String url = editedWebsite.getUrl();
		 if (url == null) {
			   throw new Exception("Website innexistant");
		 }
		List<Layout> liste = editedWebsite.getLayouts();
		Website website = ofy().load().type(Website.class).id(url).now();
		for(Layout each : liste){
			if(!website.contains(each)){
				website.getLayouts().add(each);
			}
		 }
		
		ofy().save().entity(website).now(); 
		 return website;
	 }
	 
	 public void remove(String url) {
	  if (url == null) {
	   return;
	  }
	  ofy().delete().type(Website.class).id(url).now();
	 }
	 
}
