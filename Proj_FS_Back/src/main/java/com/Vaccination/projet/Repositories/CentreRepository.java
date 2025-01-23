package com.Vaccination.projet.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Vaccination.projet.entities.centres;



public interface CentreRepository extends JpaRepository<centres, Integer> {

    @SuppressWarnings("null")
    List<centres> findAll();
    centres findCentreById(int id);
    //List<centres> findCentreByVille(String ville);
    List<centres> findByVilleIgnoreCase(@Param("ville") String ville);
    centres findByNom(String nom);
   
}
