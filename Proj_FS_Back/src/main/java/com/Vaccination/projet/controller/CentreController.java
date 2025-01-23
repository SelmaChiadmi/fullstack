package com.Vaccination.projet.controller;
import org.springframework.web.bind.annotation.*;
import com.Vaccination.projet.entities.centres;
import com.Vaccination.projet.services.CentreService;


import java.util.List;

@RestController
public class CentreController {

    private final CentreService centreService;

    

    // Injection par constructeur
    public CentreController(CentreService centreService) {
        this.centreService = centreService;
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
