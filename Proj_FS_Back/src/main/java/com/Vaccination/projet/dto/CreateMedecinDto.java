
package com.Vaccination.projet.dto;

import java.time.LocalDate;
import java.util.List;

public class CreateMedecinDto {

    private String nom;
    private String prenom;
    private String mail;
    private int telephone;


     public CreateMedecinDto() {}

    public CreateMedecinDto(String nom, String prenom, String mail, int telephone) {
       this.nom = nom;
       this.prenom=prenom;
       this.mail=mail;
       this.telephone=telephone;

    }

    // Getters et setters
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

    
}
