

package com.Vaccination.projet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.Vaccination.projet.Repositories.ReservationRepo;
import com.Vaccination.projet.dto.patientDto;
import com.Vaccination.projet.dto.reservationDto;
import com.Vaccination.projet.services.ReservationService;
import com.Vaccination.projet.entities.reservations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class ReservationController {

    private final ReservationService reservationService;

   
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    // Endpoint pour récupérer les réservations d'un patient
    @PostMapping("public/centre/{centreId}/bookings")
    public ResponseEntity<Object> bookAppointment(@PathVariable("centreId") int centreId,
                                                 @RequestParam("date") LocalDate date,
                                                 @RequestParam("heure") LocalTime heure,
                                                 @RequestBody patientDto patientDto) {

        System.out.println("Centre ID: " + centreId);
        System.out.println("Date: " + date);
        System.out.println("Heure: " + heure);
        System.out.println("Patient DTO: " + patientDto);
        try {
       
            reservationService.bookAppointment(centreId,date,heure, patientDto);

         
            return ResponseEntity.status(HttpStatus.CREATED).body("resa prise en compte");
        } catch (IllegalStateException e) {
           
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur inattendue est survenue.");
        }
    }

    // Endpoint pour récupérer les réservations d'un patient
    @DeleteMapping("/booking/{bookingId}")
    public ResponseEntity<String> cancelBooking(@PathVariable int bookingId) {
        reservationService.cancelBooking(bookingId);
        return ResponseEntity.ok("Réservation annulée avec succès.");
    }


    // Endpoint pour mettre à jour le statut de validation d'une réservation
    @PreAuthorize("hasAuthority('ROLE_MEDECIN')")
    @PatchMapping("/admin/planning/reservation/{reservationId}")
    public ResponseEntity<String> updateReservationValidation(
            @PathVariable int reservationId, 
            @RequestParam boolean isValidated) {
        
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.getAuthorities().stream()
            .anyMatch(a -> a.getAuthority().equals("ROLE_SUPER_ADMIN"))) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden");
        }
        try {
            reservationService.updateValidationStatus(reservationId, isValidated);
            return ResponseEntity.ok("Le statut de la réservation a été mis à jour avec succès.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur est survenue.");
        }
    }

}

    /*@GetMapping("public/bookings/{id}")
    public ResponseEntity<Object> GetAppointmentDetail(@PathVariable("id") int bookingId) {
        reservationDto reservationDto = reservationService.getReservationDetails(bookingId, bookingId, bookingId)

        
    }*/



    

    

