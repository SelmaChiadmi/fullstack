package com.Vaccination.projet.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Vaccination.projet.dto.CreateCenterDto;
import com.Vaccination.projet.dto.updateCentreDto;
import com.Vaccination.projet.entities.centres;
import com.Vaccination.projet.services.CentreService;
import com.Vaccination.projet.entities.employes;
import com.Vaccination.projet.services.EmployesService;


import java.util.List;


@RestController
public class CentreController {

    private final CentreService centreService;
    private final EmployesService employesService;

    

    // Injection par constructeur
    public CentreController(CentreService centreService, EmployesService employesService) {
        this.centreService = centreService;
        this.employesService = employesService;
    }


    @GetMapping("public/centres")
    public List<centres> getCentres() {
        List<centres> centresList = centreService.getAllCentres();
        System.out.println(centresList); 
        return centresList;
    }

    @GetMapping("public/centres/{ville}")
    public List<centres> getCentresByCity(@PathVariable("ville") String ville){
        List<centres> centresList = centreService.findByVilleIgnoreCase(ville);
        System.out.println(centresList); 
        return centresList;

    }

   
    // Modifier un centre
    @PutMapping("admin/centre/{centreId}/modify")
    public ResponseEntity<Integer> updateCentre(@PathVariable("centreId") int centreId, @RequestBody updateCentreDto centre) {
        try {
            centreService.updateCentre(centreId, centre);
            return ResponseEntity.ok(200);
        // retourner les erreurs
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(500);
        }
    }

    //ajouter un centre 
    @PostMapping("admin/centre/new")
    public ResponseEntity<Integer> addCentre(@RequestBody CreateCenterDto centreDto) {
        try {
            centres centre = new centres();
            centre.setNom(centreDto.getNom());
            centre.setVille(centreDto.getVille());
            centreService.addCentre(centre);

            return ResponseEntity.status(HttpStatus.CREATED).body(201); // donne un statut 201
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(500);
        }
    }


    
}
