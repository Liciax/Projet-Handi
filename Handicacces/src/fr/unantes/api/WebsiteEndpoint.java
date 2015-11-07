package fr.unantes.api;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JPACursorHelper;

import fr.unantes.bean.Website;

import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@Api(name = "websiteendpoint", namespace = @ApiNamespace(ownerDomain = "unantes.fr", ownerName = "unantes.fr", packagePath = "bean") )
public class WebsiteEndpoint {

	/**
	 * This method lists all the entities inserted in datastore. It uses HTTP
	 * GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 *         persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listWebsite")
	public CollectionResponse<Website> listWebsite(@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<Website> execute = null;

		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select from Website as Website");
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<Website>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and
			// accomodate
			// for lazy fetch.
			for (Website obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<Website> builder().setItems(execute).setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET
	 * method.
	 *
	 * @param id
	 *            the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getWebsite")
	public Website getWebsite(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		Website website = null;
		try {
			website = mgr.find(Website.class, id);
		} finally {
			mgr.close();
		}
		return website;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity
	 * already exists in the datastore, an exception is thrown. It uses HTTP
	 * POST method.
	 *
	 * @param website
	 *            the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertWebsite")
	public Website insertWebsite(Website website) {
		EntityManager mgr = getEntityManager();
		try {
			if (containsWebsite(website)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(website);
		} finally {
			mgr.close();
		}
		return website;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does
	 * not exist in the datastore, an exception is thrown. It uses HTTP PUT
	 * method.
	 *
	 * @param website
	 *            the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateWebsite")
	public Website updateWebsite(Website website) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsWebsite(website)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(website);
		} finally {
			mgr.close();
		}
		return website;
	}

	/**
	 * This method removes the entity with primary key id. It uses HTTP DELETE
	 * method.
	 *
	 * @param id
	 *            the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removeWebsite")
	public void removeWebsite(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		try {
			Website website = mgr.find(Website.class, id);
			mgr.remove(website);
		} finally {
			mgr.close();
		}
	}

	private boolean containsWebsite(Website website) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			Website item = mgr.find(Website.class, website.getId_website());
			if (item == null) {
				contains = false;
			}
		} finally {
			mgr.close();
		}
		return contains;
	}

	private static EntityManager getEntityManager() {
		return EMF.get().createEntityManager();
	}

}
