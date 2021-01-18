package com.biblio.gestionlivres.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class DetailCommande {

	@Id
	@GeneratedValue
	@JsonIgnore
	private int id;

	@OneToOne
	private Livre livre;

	private int quantiteCommande;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Livre getLivre() {
		return livre;
	}

	public void setLivre(Livre livre) {
		this.livre = livre;
	}

	public int getQuantiteCommande() {
		return quantiteCommande;
	}

	public void setQuantiteCommande(int quantiteCommande) {
		this.quantiteCommande = quantiteCommande;
	}

}
