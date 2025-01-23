/*package com.Vaccination.projet.security;

import com.Vaccination.projet.Repositories.EmployesRepo; // Assurez-vous d'avoir ce repo
import com.Vaccination.projet.entities.employes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class customUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private EmployesRepo employesRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Charger l'utilisateur à partir de la base de données par email (username)
        employes employe = employesRepo.findByMail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

        // Retourner un CustomUserDetails
        return new customUserDetails(employe);
    }
}*/
