package com.Vaccination.projet.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Vaccination.projet.entities.patient;



public interface PatientRepo extends JpaRepository<patient, Integer> {
        
   
    patient findByMail(String mail);

    List<patient> findByNomIgnoreCaseAndPrenomIgnoreCase(String nom, String prenom);

    patient findById(int Id);

    
}
