package com.Vaccination.projet.controller;
import org.springframework.web.bind.annotation.*;
import com.Vaccination.projet.entities.centres;
import com.Vaccination.projet.services.CentreService;
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

    
}