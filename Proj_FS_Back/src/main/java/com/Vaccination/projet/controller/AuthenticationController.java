package com.Vaccination.projet.controller;

import com.Vaccination.projet.utils.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.Vaccination.projet.dto.AuthDto;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/public")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;

    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public Map<String, String> authenticate(@RequestBody AuthDto authdto ) {

        String username = authdto.getUsername();
        String password = authdto.getPassword();
        // Authentifier 
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        // Charger l'utilisateur 
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        // Générer le token JWT
       
        String jwtToken = jwtTokenProvider.generateToken(userDetails);

        // Retourner le token
        Map<String, String> response = new HashMap<>();
        response.put("token", jwtToken);
        return response;
    }
}

