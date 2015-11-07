package fr.unantes.bean;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
@Index
public class Website {
	@Id
	Long id_website;
	String url;
	String nom_amenagement;
	String description_amenagement;
	
	private Website(){}
	
	public Website(String url, String nom_amenagement, String description_amenagement){
		this.url = url;
		this.nom_amenagement = nom_amenagement;
		this.description_amenagement = description_amenagement;
	}

	public Long getId_website() {
		return id_website;
	}

	public void setId_website(Long id_website) {
		this.id_website = id_website;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getNom_amenagement() {
		return nom_amenagement;
	}

	public void setNom_amenagement(String nom_amenagement) {
		this.nom_amenagement = nom_amenagement;
	}

	public String getDescription_amenagement() {
		return description_amenagement;
	}

	public void setDescription_amenagement(String description_amenagement) {
		this.description_amenagement = description_amenagement;
	}
	
	


}
