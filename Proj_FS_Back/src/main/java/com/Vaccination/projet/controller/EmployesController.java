package com.Vaccination.projet.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.Vaccination.projet.entities.employes;
import com.Vaccination.projet.services.EmployesService;
import com.Vaccination.projet.dto.CreateEmployeDto;
import com.Vaccination.projet.dto.CreateEmployeDto;

@RestController
public class EmployesController {

    private final EmployesService employesService;

    // Injection par constructeur
    public EmployesController(EmployesService employesService) {
        this.employesService = employesService;
    }
    // crée un medecin
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("admin/centre/medecins/create")
    public ResponseEntity<String> createEmploye(@RequestBody CreateEmployeDto createMedecinDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Vous n'êtes pas admin");
        }

        try {
            employes new_medecin = employesService.createMedecinByAdmin(createMedecinDto);

            return ResponseEntity.status(HttpStatus.CREATED).body("Employé créé avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la création de l'employé: " + e.getMessage());
        }
    }

    // récupère les medecins d'un centre
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("admin/centre/medecins")
    public ResponseEntity<?> getMedecinsCentreController() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Vous n'êtes pas admin");
        }

        try {

            int centreAdmin = employesService.getLoggedInUserCentreId();
            List<employes> medecins = employesService.getMedecinCentre(centreAdmin);

            return ResponseEntity.ok(medecins);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la récupération de l'employé: " + e.getMessage());
        }
    }


    // supprime un medecin par son id
    @DeleteMapping("admin/employe/{employe_id}")
    public ResponseEntity<String> deleteByIdController(@PathVariable("employe_id") int employe_id) {
        try {
            employesService.deleteById(employe_id);
            return ResponseEntity.ok("L'employé a été supprimé avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la suppression de l'employé");
        }
    }

    // récupère  un employe d'un centre via son id
    @GetMapping("admin/centre/{id}/medecin")
    public ResponseEntity<List<employes>> rechercherMedecins(
            @PathVariable("id") int centreId,
            @RequestParam String nom) {
        try {
            List<employes> medecins = employesService.chercherMedecinsByNom(centreId, nom);
            return ResponseEntity.ok(medecins);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
