package com.Vaccination.projet.services;

import java.util.List;
import java.util.Random;

import org.hibernate.internal.util.securitymanager.SystemSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Vaccination.projet.Repositories.EmployesRepo;
import com.Vaccination.projet.entities.employes;

@Service
public class EmployesService {
    
    @Autowired
    private EmployesRepo employesRepo;

    public EmployesService(EmployesRepo employesRepo) {
        this.employesRepo = employesRepo;
    }

    public List<employes> getAllEmployesByCityId(int id_centre){
        return employesRepo.findAllByCentreId(id_centre);
    }

    public void deleteById(int id){
        employesRepo.deleteById(id);
    }



}
