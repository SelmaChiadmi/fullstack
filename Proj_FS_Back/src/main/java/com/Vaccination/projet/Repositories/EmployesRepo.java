package com.Vaccination.projet.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Vaccination.projet.entities.employes;



public interface EmployesRepo extends JpaRepository<employes, Integer> {
    
    List<employes> findAllByCentreId(int id_centre);
    void deleteById(int id);

    @Query("SELECT e FROM employes e WHERE e.centre.id = :centreId AND e.is_med = TRUE")
    List<employes> findDoctorsByCentreId(@Param("centreId") int centreId);

    Optional<employes> findByMail(String mail);

    @Query("SELECT e FROM employes e WHERE e.centre.id = :centreId AND LOWER(e.nom) LIKE LOWER(CONCAT('%', :nom, '%')) AND e.is_med = true")
    List<employes> chercherMedecinsByNom(@Param("centreId") int centreId, @Param("nom") String nom);

    @Query("SELECT e FROM employes e WHERE e.centre.id = :centreId AND e.is_med = true")
    List<employes> chercherMedecins(@Param("centreId") int centreId);

    @Query("SELECT e FROM employes e WHERE e.centre.id = :centreId AND e.is_admin = true")
    List<employes> chercherAdmins(@Param("centreId") int centreId);

    boolean existsByMail(String mail);

    @Query("SELECT e FROM employes e WHERE e.is_super_admin = true")
    List<employes> findByIsSuperAdminTrue();


    @Query("SELECT e FROM employes e WHERE e.centre.id = :centreId AND e.is_admin = true")
    List<employes> findAdminByCentreId(@Param("centreId") int centreId);






    




    
}
