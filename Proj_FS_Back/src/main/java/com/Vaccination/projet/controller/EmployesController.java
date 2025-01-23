package com.Vaccination.projet.controller;
import org.springframework.web.bind.annotation.*;
import com.Vaccination.projet.entities.employes;
import com.Vaccination.projet.services.EmployesService;


import java.util.List;

@RestController
public class EmployesController {

    private final EmployesService employesService;


    // Injection par constructeur
    public EmployesController(EmployesService employesService) {
        this.employesService = employesService;
    }

    @GetMapping("admin/employes/{centre_id}")

    private List<employes> getEmployesByCentreId(@PathVariable("centre_id")int id_centre){
        return employesService.getAllEmployesByCityId(id_centre);
    }

    @DeleteMapping("admin/employe/{employe_id}")
    private void deleteById(@PathVariable("employe_id")int employe_id){
         employesService.deleteById(employe_id);
         System.out.println("L'employé a été supprimé");
    }


}


