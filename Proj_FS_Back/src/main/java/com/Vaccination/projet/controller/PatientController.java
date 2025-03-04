

package com.Vaccination.projet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Vaccination.projet.Repositories.PatientRepo;
import com.Vaccination.projet.dto.patientDto;
import com.Vaccination.projet.entities.patient;
import com.Vaccination.projet.services.PatientService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


// Contrôleur pour les patients

@RestController
public class PatientController {

    private final PatientService patientservice;

    // Injection par constructeur
    public PatientController(PatientService patientservice) {
        this.patientservice = patientservice;
    }

   


    

    // Endpoint pour rentrer les infos du patient

    @Autowired
    private PatientRepo patientRepo;

    @PostMapping("/public/patients/create-patient")
    public patient createPatient(@RequestBody patientDto patientDto) {
        // Créer un patient 
        patient newPatient = new patient();
        newPatient.setNom(patientDto.getLastName());
        newPatient.setPrenom(patientDto.getFirstName());
        newPatient.setMail(patientDto.getEmail());
        newPatient.setTelephone(patientDto.getTelephone());
        newPatient.setDate_naissance(patientDto.getBirthDate());       
        // Sauvegarder le patient dans la base de données
        return patientRepo.save(newPatient);
    }

    /*@GetMapping("/admin/search-patient")
    public ResponseEntity<List<patientDto>> searchPatient(
            @RequestParam("nom") String nom,
            @RequestParam("prenom") String prenom) {
        List<patientDto> patients = patientservice.searchPatientsByNameAndSurname(nom, prenom);
        if (patients.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(patients);
    }*/

    
    
}

    

