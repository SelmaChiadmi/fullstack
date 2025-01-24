

package com.Vaccination.projet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Vaccination.projet.Repositories.ReservationRepo;
import com.Vaccination.projet.dto.patientDto;
import com.Vaccination.projet.dto.reservationDto;
import com.Vaccination.projet.services.ReservationService;
import com.Vaccination.projet.entities.reservations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@RestController
public class ReservationController {

    private final ReservationService reservationService;

    // Injection par constructeur
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("public/centre/{centreId}/bookings")
    public ResponseEntity<Object> bookAppointment(@PathVariable int centreId,
                                                 @RequestParam("date") LocalDate date,
                                                 @RequestParam("heure") LocalTime heure,
                                                  @RequestBody patientDto patientDto) {

        System.out.println("Centre ID: " + centreId);
        System.out.println("Date: " + date);
        System.out.println("Heure: " + heure);
        System.out.println("Patient DTO: " + patientDto);
        try {
            // Appel au service pour créer une réservation
            reservations reservation = reservationService.bookAppointment(centreId,date,heure, patientDto);

            // Retourne la réservation avec un statut 201 Created
            return ResponseEntity.status(HttpStatus.CREATED).body("resa prise en compte");
        } catch (IllegalStateException e) {
            // Retourne une réponse avec un statut 400 et le message d'erreur
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            // Gestion générique des erreurs, retour 500 Internal Server Error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur inattendue est survenue.");
        }
    }

    @DeleteMapping("/booking/{bookingId}")
    public ResponseEntity<String> cancelBooking(@PathVariable int bookingId) {
        reservationService.cancelBooking(bookingId);
        return ResponseEntity.ok("Réservation annulée avec succès.");
    }

    @PatchMapping("/admin/update-validation/{reservationId}")
    public ResponseEntity<String> updateReservationValidation(
            @PathVariable int reservationId, 
            @RequestParam boolean isValidated) {
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



    

    

