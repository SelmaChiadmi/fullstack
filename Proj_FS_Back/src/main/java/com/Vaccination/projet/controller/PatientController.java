

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

@RestController
public class PatientController {

    private final PatientService patientservice;

    // Injection par constructeur
    public PatientController(PatientService patientservice) {
        this.patientservice = patientservice;
    }

    @GetMapping("/patients/check-email")
    public ResponseEntity<Map<String, String>>checkPatientEmail(@RequestParam String email) {
        patient existingPatient = patientservice.getPatientByMail(email);
        if (existingPatient == null) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Patient non trouvé dans la base de données. Veuillez remplir le formulaire.");
            response.put("email", email);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        Map<String, String> response = new HashMap<>();
        response.put("message", "Patient trouvé. Vous pouvez directement choisir un créneau.");
        return ResponseEntity.ok(response  );
    }

    // Endpoint pour rentrer les infos du patient
    @PostMapping("/public/patients/create-patient")
    public patient createPatient(@RequestBody patientDto patientDto) {
        // Créer ou récupérer un patient selon son email
        patient newPatient = new patient();
        newPatient.setNom(patientDto.getLastName());
        newPatient.setPrenom(patientDto.getFirstName());
        newPatient.setMail(patientDto.getEmail());
        newPatient.setTelephone(patientDto.getTelephone());
        newPatient.setDate_naissance(patientDto.getBirthDate());
        
        
        return  newPatient;
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
}

    

