package com.Vaccination.projet.dto;


import java.time.LocalDate;
import java.time.LocalTime;


public class ResaPlanning {
    private Integer id;
    private String nomPatient;
    private String prenomPatient;
    private LocalDate dateNaissancePatient;
    private int numeroPatient;
    private LocalDate dateReservation;
    private LocalTime heureReservation;
    private Boolean statutReservation;


    public ResaPlanning(Integer id, String nomPatient, String prenomPatient,
                          LocalDate dateNaissancePatient, int numeroPatient, LocalDate dateReservation, 
                          LocalTime heureReservation, Boolean statutReservation) {
        this.id = id;
        this.nomPatient = nomPatient;
        this.prenomPatient = prenomPatient;
        this.dateNaissancePatient = dateNaissancePatient;
        this.numeroPatient = numeroPatient;
        this.dateReservation = dateReservation;
        this.heureReservation = heureReservation;
        this.statutReservation = statutReservation;
    }

    public ResaPlanning(){}

    public Integer getId() {
        return id;
    }

    public String getNomPatient() {
        return nomPatient;
    }

    public void setNomPatient(String nomPatient) {
        this.nomPatient = nomPatient;
    }

    public String getPrenomPatient() {
        return prenomPatient;
    }

    public void setPrenomPatient(String prenomPatient) {
        this.prenomPatient = prenomPatient;
    }

    public LocalDate getDateNaissancePatient() {
        return dateNaissancePatient;
    }

    public void setDateNaissancePatient(LocalDate dateNaissancePatient) {
        this.dateNaissancePatient = dateNaissancePatient;
    }

    public int getNumeroPatient() {
        return numeroPatient;
    }

    public void setNumeroPatient(int numeroPatient) {
        this.numeroPatient = numeroPatient;
    }

    public LocalDate getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(LocalDate dateReservation) {
        this.dateReservation = dateReservation;
    }

    public LocalTime getHeureReservation() {
        return heureReservation;
    }

    public void setHeureReservation(LocalTime heureReservation) {
        this.heureReservation = heureReservation;
    }

    public Boolean getStatutReservation() {
        return statutReservation;
    }

    public void setStatutReservation(Boolean statutReservation) {
        this.statutReservation = statutReservation;
    }


    
}
