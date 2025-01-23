package com.Vaccination.projet.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class reservationDto {

    private String centreName;
    private LocalDate reservationDate;
    private LocalTime reservationTime;
    private String doctorName;
    
        // Constructeurs, getters, setters
    
        public reservationDto(String centreName, LocalDate reservationDate, LocalTime reservationTime
                                     ,String doctorName) {
            this.centreName = centreName;
            this.reservationDate = reservationDate;
            this.reservationTime = reservationTime;
            this.doctorName = doctorName;
        }

        public reservationDto(){}

        public String getCentreName() {
            return centreName;
        }
    
        public void setCentreName(String centreName) {
            this.centreName = centreName;
        }
    
        public LocalDate getDate() {
            return reservationDate;
        }
    
        public void setDate(LocalDate creneauDate) {
            this.reservationDate = creneauDate;
        }
    
        public LocalTime getCreneauTime() {
            return reservationTime;
        }
    
        public void setCreneauTime(LocalTime creneauTime) {
            this.reservationTime = creneauTime;
        }

    
        public String getDoctorName() {
            return doctorName;
        }
    
        public void setDoctorName(String doctorName) {
            this.doctorName = doctorName;
        }
    
        
    }
        

