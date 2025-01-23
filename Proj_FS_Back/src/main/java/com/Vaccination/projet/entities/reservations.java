package com.Vaccination.projet.entities;

import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "t_reservations")
public class reservations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;    
   
    private boolean is_validated;



    @ManyToOne
    @JoinColumn(name = "id_patient", foreignKey = @jakarta.persistence.ForeignKey(name = "fk_id_patient"))
    @JsonManagedReference
    private patient patient;

    @ManyToOne
    @JoinColumn(name = "id_employe")
    private employes employe;

    @OneToOne
    @JoinColumn(name = "id_creneau", nullable = true)
    private creneaux creneau;
    public reservations() {
        
    }
    public reservations(patient patient, employes medecin, boolean is_validated, creneaux creneau) {
        this.patient = patient;
        this.employe = medecin;
        this.creneau = creneau;
        this.is_validated = is_validated;
        
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    

    public patient getPatient(){
        return this.patient;
    }
    public void setPatient(patient patient) {
        this.patient = patient;
    }


    public employes getemploye(){
        return this.employe;
    }

    public void setEmploye(employes medecin){
        this.employe = medecin;
    }
  
   
    public creneaux getCreneau() {
        return creneau;
    }
    public void setCreneau(creneaux creneau) {
        this.creneau = creneau;
    }
    public boolean getIs_validated() {
        return is_validated;
    }

    public void setIs_validated(boolean is_validated) {
        this.is_validated = is_validated;
    }


}
