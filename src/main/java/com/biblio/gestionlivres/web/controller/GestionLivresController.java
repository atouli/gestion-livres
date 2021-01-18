package com.biblio.gestionlivres.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblio.gestionlivres.dao.CommandeDAO;
import com.biblio.gestionlivres.dao.DetailCommandeDAO;
import com.biblio.gestionlivres.dao.LivreDAO;
import com.biblio.gestionlivres.model.Commande;
import com.biblio.gestionlivres.model.DetailCommande;
import com.biblio.gestionlivres.model.Livre;
import com.biblio.gestionlivres.web.exceptions.ObjetIntrouvableException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Les WS de gestion des livres
 * 
 * @author atouli
 *
 */
@RestController
@RequestMapping("/api")
@Api("API pour es opérations CRUD sur des livres")
public class GestionLivresController {

	@Autowired
	private LivreDAO livreDAO;

	@Autowired
	private CommandeDAO commandeDAO;

	@Autowired
	private DetailCommandeDAO detailCommandeDAO;

	@ApiOperation(value = "Récupérer tous les livres")
	@GetMapping(value = "/livres")
	public List<Livre> findAllLivres() {
		return livreDAO.findAll();
	}

	@ApiOperation(value = "Récupérer toutes les commandes")
	@GetMapping(value = "/commandes")
	public List<Commande> findAllCommandes() {
		return commandeDAO.findAll();
	}
	
	@ApiOperation(value = "Récupérer une commande par son id")
	@GetMapping(value = "/commandes/{id}")
	public Commande findCommandeById(@PathVariable int id) {

		Optional<Commande> commandeOpt = commandeDAO.findById(id);

		if (!commandeOpt.isPresent()) {
			throw new ObjetIntrouvableException("La commande avec l'id " + id + " n'exsite pas");
		}

		return commandeOpt.get();
	}

	@ApiOperation(value = "Passer une commande des livres")
	@PostMapping(value = "/commandes")
	public Commande commander(@RequestBody List<DetailCommande> livreCommandes) {

		Commande commande = null;
		
		if (livreCommandes.isEmpty()) {
			throw new ObjetIntrouvableException("Aucun livre commandé");
		}
		
		// Recuperer les id des livres commandes
		List<Integer> ids = livreCommandes.stream().map(dl -> dl.getLivre().getId()).collect(Collectors.toList());
		
		// Recuperer les livres de la BD
		List<Livre> livres = livreDAO.findAllById(ids);
		
		List<DetailCommande> detailsCommande = new ArrayList<>();
		float montantTotal = 0;

		for (DetailCommande detailCommande : livreCommandes) {

			// Verifier la disponibilité du livre commandé
			Optional<Livre> livre = livres.stream() //
					.filter(l -> detailCommande.getLivre().getId() == l.getId()
							&& l.getQuantite() >= detailCommande.getQuantiteCommande()) //
					.findFirst();
			
			// Si le livre est dispo
			if (livre.isPresent()) {
				Livre livreDispo = livre.get();
				
				// On ajoute son prix au montant Total de la commande
				montantTotal += livreDispo.getPrix() * detailCommande.getQuantiteCommande();
				
				// Mise à jour de stock des livres
				livreDispo.setQuantite(livreDispo.getQuantite() - detailCommande.getQuantiteCommande());
				
				// Mise à jour de la BD
				livreDAO.save(livreDispo);
				
				detailCommande.setLivre(livreDispo);
				detailsCommande.add(detailCommande);
			}
		}
		
		if (detailsCommande.isEmpty()) {
			throw new ObjetIntrouvableException("Les livres commandés n'existent pas ou en rupture de stock");
		}
		
		
		detailCommandeDAO.saveAll(detailsCommande);
		
		// Création de la commande
		commande = new Commande();
		commande.setDate(new Date());
		commande.setId(new Random().nextInt(10000));
		commande.setDetailsCommande(detailsCommande);
		commande.setMontant(montantTotal);
		
		commandeDAO.save(commande);

		return commande;
	}

}
