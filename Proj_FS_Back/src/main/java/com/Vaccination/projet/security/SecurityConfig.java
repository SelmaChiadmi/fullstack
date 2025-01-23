/*package com.Vaccination.projet.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final customUserDetailsService customUserDetailsService;

    // Injection du service CustomUserDetailsService
    public SecurityConfig(customUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    /*@Bean
    /*public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/admin/**").authenticated() // Les URLs commençant par /admin nécessitent une authentification
            .anyRequest().permitAll() // Toutes les autres URLs sont publiques
        )
        .formLogin(login -> login
            .loginPage("/admin/login") // Spécifie la page de connexion
            .failureUrl("/admin/login?error=true")
            .permitAll() // La page de connexion est accessible à tous
        );
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Utiliser BCrypt pour encoder les mots de passe
    }
}*/