package com.Vaccination.projet.Repositories;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Vaccination.projet.entities.creneaux;



public interface CreneauRepo extends JpaRepository<creneaux, Integer> {

    @Query("SELECT c.jour, c.heure FROM creneaux c WHERE c.disponible = TRUE AND c.centre.id = :centreId")
    List<Object[]> findAvailableCreneauxByCentre(@Param("centreId") int centreId);
    
    @Query("SELECT c.heure FROM creneaux c WHERE c.jour = :date AND c.disponible = TRUE AND c.centre.id = :centreId")
    List<LocalTime> findAvailableCreneauxByCenterAndDate(@Param("centreId") int centreId, @Param("date") LocalDate date);

   
}