package com.Vaccination.projet.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Vaccination.projet.entities.reservations;



public interface ReservationRepo extends JpaRepository<reservations, Integer> {

    reservations findByCreneau_Centre_IdAndCreneau_IdAndPatient_Id(int centreId, int creneauId, int patientId);
    Optional<reservations> findById(int reservationId);
}

    
