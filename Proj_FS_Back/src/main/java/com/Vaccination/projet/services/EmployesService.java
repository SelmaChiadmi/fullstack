package com.Vaccination.projet.services;

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
import com.Vaccination.projet.entities.centres;
import com.Vaccination.projet.entities.employes;

@Service
public class EmployesService {
    
    @Autowired
    private EmployesRepo employesRepo;

    @Autowired
    private CentreRepository centreRepository;

   


    // @Autowired
    private PasswordEncoder passwordEncoder;


    public EmployesService(EmployesRepo employesRepo, CentreRepository centreRepository, PasswordEncoder passwordEncoder) {
        this.employesRepo = employesRepo;
        this.centreRepository = centreRepository;
        this.passwordEncoder = passwordEncoder;
        
    }

    public employes createMedecinByAdmin(CreateEmployeDto createMedecinDto) {
        
        int centreAdmin = getLoggedInUserCentreId();
        centres centre = centreRepository.findById(centreAdmin)
                .orElseThrow(() -> new RuntimeException("Centre non trouvé"));

        if (existsByEmail(createMedecinDto.getMail())) {
                        throw new IllegalArgumentException("Un employé avec cet email existe déjà.");
                                        }
    
        // Mot de passe par défaut
        String defaultPassword = "defaultPassword123"; // À changer après la première connexion
    
        // Hachage du mot de passe par défaut
        String hashedPassword = passwordEncoder.encode(defaultPassword);
    
        // Créer l'employé
     
        employes new_medecin = new employes();
        new_medecin.setNom(createMedecinDto.getNom());
        new_medecin.setPrenom(createMedecinDto.getPrenom());
        new_medecin.setMail(createMedecinDto.getMail());
        new_medecin.setTelephone(createMedecinDto.getTelephone());
        new_medecin.setmdp(hashedPassword);
        new_medecin.set_med(true);
        new_medecin.set_admin(false);
        new_medecin.set_super_admin(false);

        new_medecin.setCentre(centre);
    
        // Sauvegarder l'employé
        return employesRepo.save(new_medecin);
    }
    

    public List<employes> getAllEmployesByCityId(int id_centre){
        return employesRepo.findAllByCentreId(id_centre);
    }

    public void deleteById(int id){
        employesRepo.deleteById(id);
    }

    public List<employes> chercherMedecins(int centreId,String nom ) {
        // Valider les données ici si nécessaire
        if (nom == null) {
            throw new IllegalArgumentException("Veuillez rentrer un nom ");
        }
        return employesRepo.chercherMedecins(centreId,nom);
    }

    public boolean existsByEmail(String email) {
        return employesRepo.existsByMail(email); 
    }

    public int getLoggedInUserCentreId() {
        
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
    

        employes employe = employesRepo.findByMail(username)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        
     
        return employe.getCentre().getId(); 
    }
      
    }

    



