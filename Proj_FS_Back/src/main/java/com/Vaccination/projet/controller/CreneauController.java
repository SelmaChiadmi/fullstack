package com.Vaccination.projet.controller;
import org.springframework.web.bind.annotation.*;

import com.Vaccination.projet.entities.centres;
import com.Vaccination.projet.entities.creneaux;
import com.Vaccination.projet.services.CreneauService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
public class CreneauController {

    private final CreneauService creneauService;

    

    // Injection par constructeur
    public CreneauController(CreneauService creneauService) {
        this.creneauService = creneauService;
    }

    // Endpoint pour récupérer les jours disponibles
    @GetMapping("public/centre/{id}/jours")
    public List<LocalDate> getJoursDisponiblesController(@PathVariable("id") int centreId){
        List<LocalDate> jours = creneauService.getJoursDisponibles(centreId);
        return jours;

    }

    @GetMapping("public/jour/{date}/heures")
    public List<LocalTime> getHeuresDisponiblesController(@PathVariable("date") LocalDate date){
        List<LocalTime> heures = creneauService.getHeuresDisponibles(date);
        return heures;

    }

     @GetMapping("public/centre/{id}/timeslots")
    public List<Object[]> getAvailableCreneauByCity(@PathVariable("id") int centreId){
        List<Object[]> centreTimeslot = creneauService.getAvailableCreneauByCentre(centreId);
        System.out.println(centreTimeslot);
        return centreTimeslot;

    }

    @GetMapping("/public/centre/{centerId}/creneaux")
    public List<LocalTime> getAvailableCreneaux(@PathVariable("centerId") int centerId, @RequestParam("date") LocalDate date) {
        List<LocalTime> creneaux = creneauService.getAvailableCreneaux(centerId, date);
        return creneaux;
    }

    


    
}
