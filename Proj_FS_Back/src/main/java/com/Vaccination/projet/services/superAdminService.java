package com.Vaccination.projet.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.hibernate.internal.util.securitymanager.SystemSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Vaccination.projet.Repositories.CentreRepository;
import com.Vaccination.projet.Repositories.EmployesRepo;
import com.Vaccination.projet.dto.CreateMedecinDto;
import com.Vaccination.projet.dto.recupSuperAdminDto;
import com.Vaccination.projet.entities.centres;
import com.Vaccination.projet.entities.employes;


@Service
public class superAdminService {

    @Autowired
    private EmployesRepo employesRepo;

    @Autowired
    private CentreRepository centreRepository;

    private PasswordEncoder passwordEncoder;


    public superAdminService(EmployesRepo employesRepo, CentreRepository centreRepository, PasswordEncoder passwordEncoder) {
        this.employesRepo = employesRepo;
        this.centreRepository = centreRepository;
        this.passwordEncoder = passwordEncoder;
        
    }


    //ce service sert à récupérer les super admins quand on est super admin nous meme
    public List<recupSuperAdminDto> getSuperAdminsBySuperAdmin(){

        
        List<employes> superadmins = employesRepo.findByIsSuperAdminTrue();
        List<recupSuperAdminDto> superadminsdto = new ArrayList<>();

        for (employes emp : superadmins) {
            // Créer un EmployeDTO avec uniquement les informations nécessaires
            superadminsdto.add(new recupSuperAdminDto(
                emp.getNom(),
                emp.getPrenom(),
                emp.getMail(),
                emp.getTelephone()
            ));
        }
        return superadminsdto;
    

    }}


