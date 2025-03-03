/*package com.Vaccination.projet.controller;
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


    @PreAuthorize("hasRole('Super Admin')")
    @GetMapping("admin/config")
    public ResponseEntity<List<recupSuperAdminDto>> getSuperAdminsBySuperAdmin(){

        List<recupSuperAdminDto> superAdmins = superAdminService.getSuperAdminsBySuperAdmin();
        
            return ResponseEntity.ok(superAdmins);

    }



}
*/