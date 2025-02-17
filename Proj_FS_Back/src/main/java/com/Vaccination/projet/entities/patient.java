package com.Vaccination.projet.entities;

import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "t_patients")
public class patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String prenom;
    private String mail;
    private int telephone;
    private LocalDate date_naissance;

    @OneToMany(mappedBy = "patient")
    @JsonBackReference
    private List<reservations> reservations;  

    public patient() {
    }

    public patient(String nom, String prenom, String mail, int telephone, LocalDate date_naissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.date_naissance = date_naissance;
        this.mail = mail;
        this.reservations = new ArrayList<>();
    }

    public void addReservation(reservations reservation) {
        System.out.println("addResa : je suis a la ligne 47");
        reservations.add(reservation);
        System.out.println("addResa : je suis a la ligne 49");

        reservation.setPatient(this); 
    }


    public void removeReservation(reservations reservation) {
        reservations.remove(reservation);
        reservation.setPatient(null);
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public int getTelephone() {
        return telephone;
    }
    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }
    public LocalDate getDate_naissance() {
        return date_naissance;
    }
    public void setDate_naissance(LocalDate date_naissance) {
        this.date_naissance = date_naissance;
    }

    public List<reservations> getReservations() {
        return reservations;
    }

    public void setReservations(List<reservations> reservations) {
        this.reservations = reservations;
    }

    
}
