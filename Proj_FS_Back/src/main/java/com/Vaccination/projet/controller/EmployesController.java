package com.Vaccination.projet.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Optional;

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
    public ResponseEntity<Integer> createEmploye(@RequestBody CreateEmployeDto createMedecinDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(403);
        }

        try {
            employes new_medecin = employesService.createMedecinByAdmin(createMedecinDto);

            return ResponseEntity.status(HttpStatus.CREATED).body(201);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(500);
        }
    }


    //supprime un medecin grâce à son mail
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("admin/centre/medecins/delete")
    public ResponseEntity<Integer> deleteEmployeByEmail(@RequestParam("email") String email) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(403);
        }

        try {
            //debug
            System.out.println("email: " + email);
            employesService.deleteMedecinByEmail(email);

            return ResponseEntity.status(HttpStatus.OK).body(200);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(500);
        }
    }

    // récupère les medecins d'un centre
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_SUPER_ADMIN')")
    @GetMapping("admin/centre/medecins")
    public ResponseEntity<?> getMedecinsCentreController(@RequestParam(value = "idCentre", required = false) Integer idCentre) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || 
            (!authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")) && 
            !authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_SUPER_ADMIN")))) {
            
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Vous n'avez pas les droits nécessaires");
        }

        try {
            List<employes> medecins = new ArrayList<>();

            if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
                int centreAdmin = employesService.getLoggedInUserCentreId();
                medecins = employesService.getMedecinCentre(centreAdmin);
            }

            if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_SUPER_ADMIN"))) {
                if (idCentre == null) {
                    return ResponseEntity.badRequest().body("Un centre doit être choisi");
                }
                medecins = employesService.getMedecinCentre(idCentre);
            }

            return ResponseEntity.ok(medecins);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la récupération des médecins");
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
            @RequestParam String nom) {
        try {
            List<employes> medecins = employesService.chercherMedecinsByNom(centreId, nom);
            List<employes> medecins = employesService.chercherMedecinsByNom(centreId, nom);
            return ResponseEntity.ok(medecins);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
