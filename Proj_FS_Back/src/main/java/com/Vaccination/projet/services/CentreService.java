package com.Vaccination.projet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.Vaccination.projet.Repositories.CentreRepository;
import com.Vaccination.projet.entities.centres;

@Service
public class CentreService {
    
    @Autowired
    private CentreRepository centrerepo;

    public CentreService(CentreRepository centrerepo) {
        this.centrerepo = centrerepo;
    }

    public List<centres> getAllCentres(){
    
        return centrerepo.findAll();
    }

    public List<centres> getCentreByCity(String ville){
        return centrerepo.findByVilleIgnoreCase(ville);

    }

    public centres getCentreById(int id){
        return centrerepo.findCentreById(id);
    }

    public void addCentre(centres centre){
         centrerepo.save(centre);
    }

    public List<centres> findByVilleIgnoreCase(String ville){
        return centrerepo.findByVilleIgnoreCase(ville);
    }

   

    

}

   