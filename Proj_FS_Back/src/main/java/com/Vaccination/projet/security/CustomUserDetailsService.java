package com.Vaccination.projet.security;

import com.Vaccination.projet.entities.employes;
import com.Vaccination.projet.Repositories.EmployesRepo;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final EmployesRepo employeRepository;

    public CustomUserDetailsService(EmployesRepo employeRepository) {
        this.employeRepository = employeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        employes employe = employeRepository.findByMail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé : " + username));

        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        if ("Médecin".equals(employe.getRole())) {
            authorities.add(new SimpleGrantedAuthority("ROLE_MEDECIN"));
        }
        if ("Admin".equals(employe.getRole())) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        if ("Super Admin".equals(employe.getRole())) {
            authorities.add(new SimpleGrantedAuthority("ROLE_SUPER_ADMIN"));
        }

        return new org.springframework.security.core.userdetails.User(
                employe.getMail(),
                employe.getmdp(),
                authorities
        );
    }
}
