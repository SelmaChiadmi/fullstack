package com.Vaccination.projet.dto;

public class recupSuperAdminDto {
    
    private String lastName;
    private String firstName;
    private String email;
    private int num;

    

    public recupSuperAdminDto() {}

    public recupSuperAdminDto(String nom, String prenom, String email, int telephone) {
        this.lastName = nom;
        this.firstName = prenom;
        this.email = email;
        this.num = telephone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getnum() {
        return num;
    }

    public void setTelephone(int telephone) {
        this.num = telephone;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getmail() {
        return email;
    }

    public void setmail(String mail) {
        this.email = mail;
    }


}
