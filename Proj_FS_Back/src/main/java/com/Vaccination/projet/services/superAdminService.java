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
import com.Vaccination.projet.dto.CreateEmployeDto;
import com.Vaccination.projet.dto.recupSuperAdminDto;
import com.Vaccination.projet.entities.centres;
import com.Vaccination.projet.entities.employes;
import jakarta.transaction.Transactional;


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
    public List<recupSuperAdminDto> getSuperAdminsBySuperAdmin() {
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
    }

    public employes createSuperAdminByAdmin(CreateEmployeDto createSuperAdminDto, int centreId) {
        centres centre = centreRepository.findCentreById(centreId);

        if (existsByEmail(createSuperAdminDto.getMail())) {
            throw new IllegalArgumentException("Un employé avec cet email existe déjà.");
        }
        // Mot de passe par défaut
        String defaultPassword = "superadmin"; // À changer après la première connexion

        // Hachage du mot de passe par défaut
        String hashedPassword = passwordEncoder.encode(defaultPassword);

        // Créer le Super Admin
        employes newSuperAdmin = new employes();
        newSuperAdmin.setNom(createSuperAdminDto.getNom());
        newSuperAdmin.setPrenom(createSuperAdminDto.getPrenom());
        newSuperAdmin.setMail(createSuperAdminDto.getMail());
        newSuperAdmin.setTelephone(createSuperAdminDto.getTelephone());
        newSuperAdmin.setmdp(hashedPassword);
        newSuperAdmin.set_med(false); 
        newSuperAdmin.set_admin(false); 
        newSuperAdmin.set_super_admin(true); // C'est un super admin

        newSuperAdmin.setCentre(centre); //c'est seulement un centre de référence pour ne pas avoir la case vide

        // Sauvegarder l'employé
        return employesRepo.save(newSuperAdmin);
    }

    public boolean existsByEmail(String email) {
        return employesRepo.existsByMail(email);
    }

    @Transactional
    public void deleteSuperAdminByEmail(String email) {
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        if (currentUserEmail.equals(email)) {
            throw new IllegalStateException("Impossible de supprimer le super admin avec lequel vous êtes connecté.");
        }

       
        employesRepo.deleteByMail(email);
    }

    public List<employes> getAdminsByCentre(Integer idCentre) {
            return employesRepo.chercherAdmins(idCentre);
        
    }
}


