package fr.unantes.bean;

import com.googlecode.objectify.annotation.*;
import com.googlecode.objectify.Key;

@Entity
@Index
public class Amenagement {
	@Id
	Long id;
	String nom;
	String description;

	private Amenagement() {
	}

	public Amenagement(String nom, String description) {
		this.nom = nom;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}