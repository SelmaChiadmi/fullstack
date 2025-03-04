package com.Vaccination.projet.services;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.Vaccination.projet.Repositories.CreneauRepo;
import com.Vaccination.projet.entities.creneaux;

@Service
public class CreneauService {
    
    @Autowired
    private CreneauRepo creneauRepo;

    public CreneauService(CreneauRepo creneauRepo) {
        this.creneauRepo = creneauRepo;
    }

    
    public List<LocalDate> getJoursDisponibles(int centreId) {
        return creneauRepo.findDistinctJourDisponible(centreId);
    }

    public List<LocalTime> getHeuresDisponibles(LocalDate jour) {
        return creneauRepo.findByJourAndDisponible(jour);
    }

    public List<Object[]> getAvailableCreneauByCentre(int centreId){
        return creneauRepo.findAvailableCreneauxByCentre(centreId);
    }

    public List<LocalTime> getAvailableCreneaux(int centerId, LocalDate date) {
        return creneauRepo.findAvailableCreneauxByCenterAndDate(centerId, date);
    }

    
}