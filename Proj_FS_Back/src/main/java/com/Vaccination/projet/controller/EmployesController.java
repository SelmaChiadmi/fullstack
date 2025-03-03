package com.Vaccination.projet.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.Vaccination.projet.entities.employes;
import com.Vaccination.projet.services.EmployesService;
import com.Vaccination.projet.dto.CreateEmployeDto;

@RestController
public class EmployesController {

    private final EmployesService employesService;

    // Injection par constructeur
    public EmployesController(EmployesService employesService) {
        this.employesService = employesService;
    }

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

    @GetMapping("admin/centre/{id}/medecin")
    public ResponseEntity<List<employes>> rechercherMedecins(
            @PathVariable("id") int centreId,
            @RequestParam String nom) {
        try {
            List<employes> medecins = employesService.chercherMedecins(centreId, nom);
            return ResponseEntity.ok(medecins);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
