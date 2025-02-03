/*package com.Vaccination.projet.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

import com.Vaccination.projet.entities.employes; 

public class customUserDetails implements UserDetails {

    private final employes employe;

    public customUserDetails(employes employe) {
        this.employe = employe;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = employe.getRole();
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase().replace(" ", "_")));
    }

    @Override
    public String getPassword() {
        return employe.getmdp();  // Mot de passe en clair
    }

    @Override
    public String getUsername() {
        return employe.getMail();  // Utilisation de l'email comme username
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}*/

