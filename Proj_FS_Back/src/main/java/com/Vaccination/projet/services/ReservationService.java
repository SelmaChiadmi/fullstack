package com.Vaccination.projet.services;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Vaccination.projet.entities.centres;
import com.Vaccination.projet.entities.creneaux;
import com.Vaccination.projet.entities.employes;
import com.Vaccination.projet.entities.patient;
import com.Vaccination.projet.entities.reservations;

import jakarta.transaction.Transactional;

import com.Vaccination.projet.Repositories.CreneauRepo;
import com.Vaccination.projet.Repositories.EmployesRepo;
import com.Vaccination.projet.Repositories.PatientRepo;
import com.Vaccination.projet.Repositories.ReservationRepo;
import com.Vaccination.projet.dto.patientDto;
import com.Vaccination.projet.dto.reservationDto;


@Service
public class ReservationService {
    
    @Autowired
    private ReservationRepo reservationrepo;

    @Autowired
    private PatientRepo patientRepository;

    @Autowired
    private CreneauRepo creneauRepository;

    @Autowired
    private EmployesRepo employeRepository;

    @Autowired
    private PatientService patientService;

   


    public ReservationService(ReservationRepo reservationrepo, PatientRepo patientrepo, CreneauRepo creneauRepo, EmployesRepo employesRepo) {
        this.reservationrepo = reservationrepo;
        this.patientRepository = patientrepo;
        this.creneauRepository = creneauRepo;
        this.employeRepository = employesRepo;
      

    }


     public reservations bookAppointment(int centreId, LocalDate date, LocalTime heure, patientDto patientDto) {
        // Étape 1 : Récupérer les médecins disponibles pour le centre
        List<employes> doctors = employeRepository.findDoctorsByCentreId(centreId);
    

            // Étape 2 : Rechercher le créneau correspondant
            creneaux creneau = creneauRepository.findByCentreIdAndJourAndHeure(centreId,date, heure)
            
            .orElseThrow(() -> new IllegalStateException("Le créneau demandé n'existe pas ou n'est pas disponible."));
        
       

        // Étape 3 : Vérifier la disponibilité du créneau
        if (!creneau.isDisponible()) {
            throw new IllegalStateException("Ce créneau n'est plus disponible.");
        }
        

        
       
        if (doctors.isEmpty()) {
            throw new IllegalStateException("Aucun médecin disponible dans ce centre");
        }
      

        patient patient = patientService.getOrCreatePatient(patientDto);
        

        // Étape 2 : Sélectionner un médecin aléatoire
        Random random = new Random();
        employes selectedDoctor = doctors.get(random.nextInt(doctors.size()));
       
        
        // Étape 4 : Créer une nouvelle réservation
        reservations reservation = new reservations(patient, selectedDoctor, false, creneau);
        
        System.out.println(creneau);
        creneau.setDisponible(false);
       
        creneauRepository.save(creneau);
       
        //associer le patient a la resa
        reservation.setPatient(patient);
        
        reservationrepo.save(reservation);
        

        // Étape 5 : Sauvegarder la réservation
        return reservation;
        
    }

    @Transactional
    public void cancelBooking(int bookingId) {
        // Récupérer la réservation
        Optional<reservations> booking = reservationrepo.findById(bookingId);
        if(!booking.isPresent()){
            throw new IllegalStateException("Cette reservation n'existe pas");

        }

        reservations reservation = booking.get();

        patient patient = reservation.getPatient();
        if (patient != null) {
            patient.getReservations().remove(reservation); // Supprimer la réservation de la liste du patient
        }

        // Récupérer le créneau associé
        creneaux creneau = reservation.getCreneau();
        reservation.setCreneau(null);

        // Supprimer la réservation
        reservationrepo.delete(reservation);

        if (creneau != null) {
            creneau.setDisponible(true);
            creneauRepository.save(creneau); // Mise à jour du créneau
        }
    }



    public reservationDto getReservationDetails(int centreId, int creneauId, int patientId) {
        // Récupérer les informations de la réservation
        reservations reservation = reservationrepo.findByCreneau_Centre_IdAndCreneau_IdAndPatient_Id(centreId, creneauId, patientId);
        
        if (reservation == null) {
            throw new IllegalStateException("La réservation n'existe pas.");
        }

        // Récupération des informations de la réservation
        String centreName = reservation.getCreneau().getCentre().getNom(); // Accéder au centre via creneau
        LocalDate reservationDate = reservation.getCreneau().getDate(); // Format de la date
        LocalTime reservationTime = reservation.getCreneau().getHeure(); // Format de l'heure
    
        String doctorName = reservation.getemploye().getPrenom() + " " + reservation.getemploye().getNom();

        // Retourner le DTO avec ces informations
        return new reservationDto(centreName, reservationDate, reservationTime, doctorName);
    }

    @Transactional
    public void updateValidationStatus(int reservationId, boolean isValidated) {
        // Vérifier si la réservation existe
        Optional<reservations> reservationOpt = reservationrepo.findById(reservationId);
        if (!reservationOpt.isPresent()) {
            throw new IllegalArgumentException("La réservation avec l'ID " + reservationId + " n'existe pas.");
        }

        // Mettre à jour le champ is_validated
        reservations reservation = reservationOpt.get();
        reservation.setIs_validated(isValidated);
        reservationrepo.save(reservation);
    }
        

}


    