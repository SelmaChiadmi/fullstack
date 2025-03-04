package com.Vaccination.projet.Repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Vaccination.projet.entities.centres;
import com.Vaccination.projet.entities.reservations;



public interface ReservationRepo extends JpaRepository<reservations, Integer> {

    reservations findByCreneau_Centre_IdAndCreneau_IdAndPatient_Id(int centreId, int creneauId, int patientId);
    Optional<reservations> findById(int reservationId);

        @Query("SELECT r FROM reservations r WHERE r.employe.id = :medecinId AND r.creneau.jour = :date")
    List<reservations> findByMedecinIdAndDate(@Param("medecinId") int medecinId, 
                                                               @Param("date") LocalDate date);
}



