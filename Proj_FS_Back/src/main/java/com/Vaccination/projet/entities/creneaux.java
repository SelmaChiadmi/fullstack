package com.Vaccination.projet.entities;

import java.time.LocalDate;
//import java.persistence.Table;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
@Table(name = "t_creneaux")
public class creneaux {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_centre", nullable = false)
    @JsonIgnore
    private centres centre;

    
    private LocalTime heure;
    private LocalDate jour;

   @OneToOne(mappedBy = "creneau")
    private reservations reservation;


    private boolean disponible;

    public creneaux(){}
    public creneaux( int id, boolean disponible, LocalDate jour, LocalTime heure) {
        this.id = id;
        this.disponible = disponible;
        this.jour = jour;
        this.heure = heure;
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public centres getCentre() {
        return centre;
    }

    public void setCentre(centres centre) {
        this.centre = centre;
    }

    public LocalTime getHeure() {
        return heure;
    }

    public LocalDate getDate() {
        return jour;
    }


    public void setHeure(LocalTime heure) {
        this.heure = heure;
    }

    public void setDate(LocalDate jour) {
        this.jour = jour;
    }

    public reservations getReservations() {
        return reservation;
    }

    public void setReservations(reservations reservation) {
        this.reservation = reservation;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
