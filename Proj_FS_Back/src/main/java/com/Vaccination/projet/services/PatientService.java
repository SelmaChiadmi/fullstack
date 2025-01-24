package com.Vaccination.projet.services;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Vaccination.projet.Repositories.CentreRepository;
import com.Vaccination.projet.Repositories.PatientRepo;
import com.Vaccination.projet.Repositories.ReservationRepo;
import com.Vaccination.projet.dto.patientDto;
import com.Vaccination.projet.dto.reservationDto;
import com.Vaccination.projet.entities.centres;
import com.Vaccination.projet.entities.patient;

import jakarta.transaction.Transactional;

@Service
public class PatientService {
    
    @Autowired
    private PatientRepo patientrepo;

    @Autowired
    private CentreRepository centrerepo;

    @Autowired
    private ReservationRepo reservationrepo;



    public PatientService(PatientRepo patientrepo) {
        this.patientrepo = patientrepo;
    }

    public List<patient> findAllPatients() {
        return patientrepo.findAll();
    }

    public patient getPatientByMail(String email){
        return patientrepo.findByMail(email);
    }

    public patient createNewPatient(patientDto patientDto) {
        patient newPatient = new patient();
        newPatient.setNom(patientDto.getLastName());
        newPatient.setPrenom(patientDto.getFirstName());
        newPatient.setTelephone(patientDto.getTelephone());
        newPatient.setDate_naissance(patientDto.getBirthDate());
        newPatient.setMail(patientDto.getEmail());
    
        return newPatient;
    }

  
    public patient getOrCreatePatient(patientDto patientDto) {
        // Vérifier si le patient existe déjà
        patient existingPatient = patientrepo.findByMail(patientDto.getEmail());
        
        if (existingPatient != null) {
            // Si le patient existe, le retourner
            return existingPatient;
        }
        
        // Si le patient n'existe pas, créer un nouveau patient
        patient newPatient = new patient();
        newPatient.setNom(patientDto.getLastName());
        newPatient.setPrenom(patientDto.getFirstName());
        newPatient.setTelephone(patientDto.getTelephone());
        newPatient.setDate_naissance(patientDto.getBirthDate());
        newPatient.setMail(patientDto.getEmail());
        
        // Sauvegarder le patient et le retourner
        return patientrepo.save(newPatient);
    }

    public List<patientDto> searchPatientsByNameAndSurname(String nom, String prenom) {
    List<patient> patients = patientrepo.findByNomIgnoreCaseAndPrenomIgnoreCase(nom, prenom);

    return patients.stream().map(patient -> {
        patientDto dto = new patientDto();
        dto.setFirstName(patient.getPrenom());
        dto.setLastName(patient.getNom());
        dto.setEmail(patient.getMail());
        dto.setTelephone(patient.getTelephone());
        dto.setBirthDate(patient.getDate_naissance());

        // Convertir les réservations en reservationDto
        List<reservationDto> reservationsDto = patient.getReservations().stream().map(reservation -> {
            return new reservationDto(
                reservation.getCreneau().getCentre().getNom(),    // Nom du centre
                reservation.getCreneau().getDate(),              // Date de la réservation
                reservation.getCreneau().getHeure(),             // Heure de la réservation
                reservation.getemploye().getNom()              // Nom du médecin
            );
        }).collect(Collectors.toList());

        dto.setReservations(reservationsDto);
        return dto;
    }).collect(Collectors.toList());
}


   

    
   
}
