package com.biblio.gestionlivres.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biblio.gestionlivres.model.Commande;

@Repository
public interface CommandeDAO extends JpaRepository<Commande, Integer> {


}
