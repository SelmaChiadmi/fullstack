package com.Vaccination.projet.services;

import java.util.List;
import java.util.Random;

import org.hibernate.internal.util.securitymanager.SystemSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Vaccination.projet.Repositories.CentreRepository;
import com.Vaccination.projet.Repositories.EmployesRepo;
import com.Vaccination.projet.entities.centres;
import com.Vaccination.projet.entities.employes;

@Service
public class EmployesService {
    
    @Autowired
    private EmployesRepo employesRepo;

    @Autowired
    private CentreRepository centreRepository;

    private final PasswordEncoder passwordEncoder;


    // @Autowired
    //private PasswordEncoder passwordEncoder;


    public EmployesService(EmployesRepo employesRepo, PasswordEncoder passwordEncoder) {
        this.employesRepo = employesRepo;
        this.passwordEncoder = passwordEncoder;
        
    }

    public employes createEmploye(String nom, String prenom, String mail, boolean isMed, boolean isAdmin, boolean isSuperAdmin, int telephone, int centreId) {
        centres centre = centreRepository.findById(centreId)
                                         .orElseThrow(() -> new RuntimeException("Centre introuvable"));
    
        // Mot de passe par défaut
        String defaultPassword = "defaultPassword123"; // À changer après la première connexion
    
        // Hachage du mot de passe par défaut
        String hashedPassword = passwordEncoder.encode(defaultPassword);
    
        // Créer l'employé
        employes employe = new employes(nom, prenom, mail, hashedPassword, isMed, isAdmin, isSuperAdmin, telephone);
        employe.setCentre(centre);
    
        // Sauvegarder l'employé
        return employesRepo.save(employe);
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
        return employesRepo.existsByMail(email); // Méthode que vous pouvez définir dans le repository
    }

    


}
