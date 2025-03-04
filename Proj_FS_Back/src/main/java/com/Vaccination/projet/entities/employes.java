package com.Vaccination.projet.entities;

import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
@Table(name = "t_employes")
public class employes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;
    private String nom;
    private String prenom;


    private Boolean is_med;
    private Boolean is_admin;
    private Boolean is_super_admin;
    private int telephone;

    private String mail;

    @JsonIgnore
    private String mdp;

    @ManyToOne
    @JoinColumn(name = "id_centre", foreignKey = @jakarta.persistence.ForeignKey(name = "fk_id_centre"))
    @JsonIgnore
    private centres centre;
    
    @OneToMany(mappedBy = "employe", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<reservations> reservations = new ArrayList<>();


    public employes(){

    }

    public employes(String nom, String prenom,String mail, String mdp, Boolean is_med, Boolean is_admin, Boolean is_super_admin, int telephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.mdp = mdp;
        this.is_admin = is_admin;
        this.is_med = is_med;
        this.is_super_admin = is_super_admin;
        this.telephone = telephone;

    }

    public int getId(){
        return id;
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
    public String getmdp() {
        return mdp;
    }
    public void setmdp(String mdp) {
        this.mdp = mdp;
    }
    public centres getCentre() {
        return this.centre;
    }
    public void setCentre(centres centre) {
        this.centre = centre;
    }
    public String getRole() {
        if(is_med){
            return "Médecin";
        }
        else if(is_admin){
            return "Admin";
        }
        else if(is_super_admin){
            return "Super Admin";
        }
        else{
            return "Employé";
        }

    }
    public void set_med(Boolean is_med) {
        this.is_med = is_med;
    }
    public void set_admin(Boolean is_admin) {
        this.is_admin = is_admin;
    }
    public void set_super_admin(Boolean is_super_admin) {
        this.is_super_admin = is_super_admin;
    }
    public int getTelephone() {
        return telephone;
    }
    public void setTelephone(int Telephone) {
        this.telephone = Telephone;
    }

    public List<reservations> getReservations(){
        return this.reservations;

    }

    public void setTelephone(String telephone2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setTelephone'");
    }
}
