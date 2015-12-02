package fr.unantes.beans;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Amenagement {

	@Id
	private Long id;
	private String nom;
	private String description;
	private boolean checked;
	public Amenagement() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Amenagement(Long id, String nom, String description) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.checked = false;
	}
	
	
	public Amenagement(String nom, String description) {
		super();
		this.nom = nom;
		this.description = description;
		this.checked = false;
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
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
	
}
