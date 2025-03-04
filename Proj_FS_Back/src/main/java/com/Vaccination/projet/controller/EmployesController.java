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
import com.Vaccination.projet.dto.CreateMedecinDto;

@RestController
public class EmployesController {

    private final EmployesService employesService;
  
   


    // Injection par constructeur
    public EmployesController(EmployesService employesService) {
        this.employesService = employesService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("admin/centre/medecins/create")
    public ResponseEntity<String> createEmploye(@RequestBody CreateMedecinDto createMedecinDto) {
        

        try {
            
            employes new_medecin = employesService.createMedecinByAdmin(createMedecinDto);
            new_medecin.setCentre((null));
    
            return ResponseEntity.status(HttpStatus.CREATED).body("Employé créé avec succès");
    
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la création de l'employé: " + e.getMessage());
        }
    }
    

    @DeleteMapping("admin/employe/{employe_id}")
    private void deleteById(@PathVariable("employe_id")int employe_id){
         employesService.deleteById(employe_id);
         System.out.println("L'employé a été supprimé");
    }

    @GetMapping("admin/centre/{id}/medecin")
    public ResponseEntity<List<employes>> rechercherMedecins(
            @PathVariable("id") int centreId,
            @RequestParam  String nom ) {
        try {
            List<employes> medecins = employesService.chercherMedecins(centreId, nom);
            return ResponseEntity.ok(medecins);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    


}

