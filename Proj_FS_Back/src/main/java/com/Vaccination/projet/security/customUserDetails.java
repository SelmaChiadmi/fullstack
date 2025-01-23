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
        // Utiliser la méthode getRole() pour obtenir le rôle sous forme de String
        String role = employe.getRole();
        // Ajouter le rôle en ajoutant le préfixe ROLE_ (requis par Spring Security)
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase().replace(" ", "_")));
    }

    @Override
    public String getPassword() {
        return employe.getmdp();
    }

    @Override
    public String getUsername() {
        return employe.getMail();
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
