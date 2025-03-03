package com.Vaccination.projet.controller;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

        List<recupSuperAdminDto> superAdmins = superAdminService.getSuperAdminsBySuperAdmin();
        
            return ResponseEntity.ok(superAdmins);

    }

    @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
    @GetMapping("admin/config/create/{centreId}")
    public ResponseEntity<String> createSuperAdminBySuperAdminController(@RequestBody CreateEmployeDto superadmindto, @PathVariable("centreId") int centreId){
        try {
            // Création du Super Admin via le service
            employes newSuperAdmin = superAdminService.createSuperAdminByAdmin(superadmindto, centreId);
            
            return ResponseEntity.status(HttpStatus.CREATED).body("Super Admin créé avec succès.");
        } catch (IllegalArgumentException e) {
            // Si l'email est déjà utilisé
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erreur : " + e.getMessage());
        } catch (Exception e) {
            // Erreur générale
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la création du Super Admin.");
        }


    }




}
