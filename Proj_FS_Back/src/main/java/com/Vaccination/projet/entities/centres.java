package com.Vaccination.projet.entities;

import jakarta.persistence.Table;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
@Table(name = "t_centres")
public class centres {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @JsonIgnore
    private int id;
    private String nom;
    private String ville;
   
    @OneToMany(mappedBy = "centre", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<employes> employes;

    @OneToMany(mappedBy = "centre", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<creneaux> creneaux;

    public centres(){

    }

    public centres( int id, String nom, String ville) {
        this.id = id;
        this.nom = nom;
        this.ville = ville;

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
    public String getVille() {
        return ville;
    }
    public void setVille(String ville) {
        this.ville = ville;
    }

    public List<employes> getEmployes(){
        return this.employes;
    }

    public List<creneaux> getCreneaux() {
        return creneaux;
    }

    public void setCreneaux(List<creneaux> creneaux) {
        this.creneaux = creneaux;
    }
}
