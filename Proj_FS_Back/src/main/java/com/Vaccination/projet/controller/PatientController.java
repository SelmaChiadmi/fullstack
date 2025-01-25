

package com.Vaccination.projet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Vaccination.projet.Repositories.PatientRepo;
import com.Vaccination.projet.dto.patientDto;
import com.Vaccination.projet.entities.patient;
import com.Vaccination.projet.services.PatientService;

import java.util.List;
import java.util.Optional;


// Contrôleur pour les patients

@RestController
public class PatientController {

    private final PatientService patientservice;

    // Injection par constructeur
    public PatientController(PatientService patientservice) {
        this.patientservice = patientservice;
    }


    // Endpoint pour créer un patient
    @PostMapping("/create-patient")
    public ResponseEntity<String> createPatient(@RequestBody patientDto patientDto) {
        // Créer ou récupérer un patient selon son email
        patient patient = patientservice.getOrCreatePatient(patientDto);
        
        // Une fois le patient créé ou existant, répondre avec l'étape suivante (choisir un créneau)
        return ResponseEntity.status(201).body("Patient created or already exists. Proceed to choose a time slot.");
    }

    @GetMapping("/admin/search-patient")
    public ResponseEntity<List<patientDto>> searchPatient(
            @RequestParam("nom") String nom,
            @RequestParam("prenom") String prenom) {
        List<patientDto> patients = patientservice.searchPatientsByNameAndSurname(nom, prenom);
        if (patients.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(patients);
    }

    // Ajout de Wassil 
    // Endpoint pour récupérer un patient par son mail
    @GetMapping("public/verify-email")
    public ResponseEntity<Boolean> verifyEmail(@RequestParam("email") String email) {
        Optional<patientDto> patient = patientservice.getPatientByEmail(email);
        return ResponseEntity.ok(patient.isPresent());
    }
}

    

