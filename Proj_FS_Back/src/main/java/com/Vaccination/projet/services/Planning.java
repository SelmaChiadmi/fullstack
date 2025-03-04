package com.Vaccination.projet.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.Vaccination.projet.Repositories.EmployesRepo;
import com.Vaccination.projet.Repositories.PatientRepo;
import com.Vaccination.projet.Repositories.ReservationRepo;
import com.Vaccination.projet.dto.ResaPlanning;
import com.Vaccination.projet.entities.creneaux;
import com.Vaccination.projet.entities.employes;
import com.Vaccination.projet.entities.patient;
import com.Vaccination.projet.entities.reservations;

@Service
public class Planning {

    @Autowired
    private ReservationRepo reservationRepo;

    @Autowired
    private EmployesRepo employesRepo;

    @Autowired
    private PatientRepo patientRepo;

    public Planning(ReservationRepo reservationRepo, EmployesRepo employesRepo, PatientRepo patienrepo) {
        this.reservationRepo = reservationRepo;
        this.employesRepo = employesRepo;
        this.patientRepo = patienrepo;
    }


    public List<ResaPlanning> getPatientsByDate(LocalDate date) {
        // Récupérer l'ID du médecin authentifié
        int medecinId =  getLoggedInUserCentreId();
        
        
        List<reservations> reservations = reservationRepo.findByMedecinIdAndDate(medecinId, date);
        
        
            List<ResaPlanning> reservationDTOs = new ArrayList<>();
        
            
            for (reservations reservation : reservations) {
                
                patient patient = reservation.getPatient();
                creneaux creneau = reservation.getCreneau();
                    
                // Construire le DTO avec toutes les informations
                ResaPlanning reservationDTO = new ResaPlanning(
                    reservation.getId(),
                    patient.getNom(),
                    patient.getPrenom(),
                    patient.getDate_naissance(),
                    patient.getTelephone(),
                    creneau.getDate(),
                    creneau.getHeure(),
                    reservation.getIs_validated()
                );
    
                reservationDTOs.add(reservationDTO);
            }
    
            return reservationDTOs;
    }

    public int getLoggedInUserCentreId() {
        
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
    

        employes employe = employesRepo.findByMail(username)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        
     
        return employe.getCentre().getId(); 
    }
    
}
