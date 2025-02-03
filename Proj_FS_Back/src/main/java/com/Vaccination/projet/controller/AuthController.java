/*package com.Vaccination.projet.controller;

import com.Vaccination.projet.utils.JwtUtils;
import com.Vaccination.projet.Repositories.EmployesRepo;
import com.Vaccination.projet.entities.employes;
import com.Vaccination.projet.security.customUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    private EmployesRepo employeRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private customUserDetailsService customUserDetailsService;

    @PostMapping("/public/login")
    public ResponseEntity<?> authenticateUser(@RequestBody Map<String, String> loginRequest) {
        String mail = loginRequest.get("mail");
        String password = loginRequest.get("password");

        // Recherche de l'employé par email
        employes employe = employeRepository.findByMail(mail)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));

        // Vérifier si le mot de passe en clair correspond à celui de la base de données
        if (!employe.getmdp().equals(password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Mot de passe incorrect");
        }

        // Générer le JWT si l'authentification est réussie
        String jwt = jwtUtils.generateJwt(mail);  // Remplace par ton utilitaire de génération de token

        return ResponseEntity.ok(Map.of("token", jwt));  // Retourner le token dans la réponse
    }
}*/
