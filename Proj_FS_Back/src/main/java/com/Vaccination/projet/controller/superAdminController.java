package com.Vaccination.projet.controller;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Vaccination.projet.entities.centres;
import com.Vaccination.projet.entities.employes;
import com.Vaccination.projet.services.CentreService;
import com.Vaccination.projet.services.EmployesService;
import com.Vaccination.projet.services.superAdminService;


import com.Vaccination.projet.dto.CreateCenterDto;
import com.Vaccination.projet.dto.CreateEmployeDto;
import com.Vaccination.projet.dto.recupSuperAdminDto;

@RestController
public class superAdminController {

    private final superAdminService superAdminService;
    private final EmployesService employesService;
    // Injection par constructeur
    public superAdminController(superAdminService superadminservice, EmployesService employesService) {
        this.superAdminService = superadminservice;
        this.employesService = employesService;
    }


    @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
    @GetMapping("admin/config")
    public ResponseEntity<List<recupSuperAdminDto>> getSuperAdminsBySuperAdmin(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_SUPER_ADMIN"))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Collections.emptyList());
        }

        List<recupSuperAdminDto> superAdmins = superAdminService.getSuperAdminsBySuperAdmin();
        
            return ResponseEntity.ok(superAdmins);

    }

    @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")

    @PostMapping("admin/config/create/{centreId}")
    public ResponseEntity<Integer> createSuperAdminBySuperAdminController(@RequestBody CreateEmployeDto superadmindto, @PathVariable("centreId") int centreId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_SUPER_ADMIN"))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(403);

        }
        try {
            // Création du Super Admin via le service
            employes newSuperAdmin = superAdminService.createSuperAdminByAdmin(superadmindto, centreId);
            

            return ResponseEntity.status(HttpStatus.CREATED).body(201);
        } catch (IllegalArgumentException e) {
            // Si l'email est déjà utilisé
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(400);
        } catch (Exception e) {
            // Erreur générale
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(500);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
    @DeleteMapping("admin/config/delete/{email}")
    public ResponseEntity<Integer> deleteSuperAdmin(@PathVariable("email") String email) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_SUPER_ADMIN"))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(403);
        }

        try {
            System.out.println("Deleting super admin with email: " + email);
            superAdminService.deleteSuperAdminByEmail(email);
            return ResponseEntity.ok(200);
        } catch (IllegalStateException e) {
            // Gérer le cas où l'utilisateur essaie de se supprimer lui-même
            return ResponseEntity.status(308).body(308);
        } catch (Exception e) {
            System.err.println("Error during super admin deletion: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(500);
        }

         
    }


    @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
    @GetMapping("admin/centre/admins")
    public ResponseEntity<?> getMedecinsCentreController(@RequestParam(value = "idCentre", required = true) Integer idCentre) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || 
            !authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_SUPER_ADMIN"))) {
            
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Vous n'êtes pas super admin");
        }

        try {
                List<employes> admins = superAdminService.getAdminsByCentre(idCentre);
            
                 return ResponseEntity.ok(admins);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(500);
        }
    }



}
