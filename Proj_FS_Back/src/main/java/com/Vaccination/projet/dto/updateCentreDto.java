package com.Vaccination.projet.dto;

public class updateCentreDto {
    private String nom;
    private String ville;

    public updateCentreDto() {}

    public updateCentreDto(String nom, String ville) {
        this.nom = nom;
        this.ville = ville;
    }

    // Getters et Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
    
}
