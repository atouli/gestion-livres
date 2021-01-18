package com.biblio.gestionlivres.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biblio.gestionlivres.model.DetailCommande;

@Repository
public interface DetailCommandeDAO extends JpaRepository<DetailCommande, Integer> {


}
