package com.biblio.gestionlivres.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biblio.gestionlivres.model.Livre;

@Repository
public interface LivreDAO extends JpaRepository<Livre, Integer> {

}
