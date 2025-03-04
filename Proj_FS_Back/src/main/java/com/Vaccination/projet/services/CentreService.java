package com.Vaccination.projet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.Vaccination.projet.Repositories.CentreRepository;
import com.Vaccination.projet.Repositories.EmployesRepo;
import com.Vaccination.projet.dto.updateCentreDto;
import com.Vaccination.projet.entities.centres;
import com.Vaccination.projet.entities.employes;

@Service
public class CentreService {
    
    @Autowired
    private CentreRepository centrerepo;
    @Autowired
    private EmployesRepo employerepo;
   



    public CentreService(CentreRepository centrerepo, EmployesRepo employesRepo) {
        this.centrerepo = centrerepo;
        this.employerepo = employesRepo;
        
    }

    public List<centres> getAllCentres(){
    
        return centrerepo.findAll();
    }

    public List<centres> getCentreByCity(String ville){
        return centrerepo.findByVilleIgnoreCase(ville);

    }

    public centres getCentreById(int centreId){
       
        
        return centrerepo.findCentreById(centreId);
    }

    public void addCentre(centres centre){
        if (centre == null) {
            throw new IllegalArgumentException("Centre ne peut pas être nul");
        }
         centrerepo.save(centre);
    }

    public List<centres> findByVilleIgnoreCase(String ville){
        return centrerepo.findByVilleIgnoreCase(ville);
    }

    // modifier un centre
    public void updateCentre(int centreId, updateCentreDto centreDto) {
        centres centreToUpdate = centrerepo.findCentreById(centreId);
        if (centreToUpdate == null) {
            throw new IllegalStateException("Centre non trouvé.");
        }
        centreToUpdate.setNom(centreDto.getNom());
        centreToUpdate.setVille(centreDto.getVille());
        centrerepo.save(centreToUpdate);
    }

    public int getLoggedInUserCentreId() {
        
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
    

        employes employe = employerepo.findByMail(username)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        
     
        return employe.getCentre().getId(); 
    }
    

}

   
