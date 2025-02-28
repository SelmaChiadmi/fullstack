package com.Vaccination.projet.dto;

public class CreateCenterDto {
    private String nom;
    private String ville;

    public CreateCenterDto() {}

    public CreateCenterDto(String nom, String ville) {
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
