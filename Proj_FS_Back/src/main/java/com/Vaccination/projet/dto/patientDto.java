package com.Vaccination.projet.dto;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class patientDto {

    private String firstName;
    private String lastName;
    private int telephone;
    private LocalDate birthDate;
    private String email;
    private List<reservationDto> reservations = Collections.emptyList();

    public patientDto() {}

    public patientDto(String nom, String prenom, String mail, int telephone, LocalDate dateNaissance, List<reservationDto> reservations) {
        this.lastName = nom;
        this.firstName = prenom;
        this.email = mail;
        this.telephone = telephone;
        this.birthDate = dateNaissance;
        this.reservations = reservations;
    }





    public List<reservationDto> getReservations() {
        return reservations;
    }

    public void setReservations(List<reservationDto> reservations) {
        this.reservations = reservations;
    } 

    // Getters et Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

     public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
}
