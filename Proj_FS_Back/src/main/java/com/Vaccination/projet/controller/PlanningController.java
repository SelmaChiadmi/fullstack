package com.Vaccination.projet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.Vaccination.projet.Repositories.PatientRepo;
import com.Vaccination.projet.dto.ResaPlanning;
import com.Vaccination.projet.dto.patientDto;
import com.Vaccination.projet.entities.employes;
import com.Vaccination.projet.entities.patient;
import com.Vaccination.projet.services.PatientService;
import com.Vaccination.projet.services.Planning;
import com.Vaccination.projet.services.ReservationService;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;



@RestController
public class PlanningController { 

    @Autowired
    private Planning planningService;

    public PlanningController(){}

    public PlanningController(Planning planning) {
        this.planningService = planning;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MEDECIN')")
    @GetMapping("/admin/planning")
    public ResponseEntity<?> getResaDetailsController(@RequestParam("date") LocalDate date) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || 
        (!authentication.getAuthorities().stream()
             .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")) && 
        !authentication.getAuthorities().stream()
             .anyMatch(a -> a.getAuthority().equals("ROLE_MEDECIN")))) {
        
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Vous n'avez pas les droits nécessaires");
    }

        try {
            List<ResaPlanning> reservations = planningService.getPatientsByDate(date);
            return ResponseEntity.ok(reservations);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.emptyList());
        }


    
       
    }



}