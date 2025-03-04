package services;


import com.Vaccination.projet.dto.patientDto;
import com.Vaccination.projet.entities.patient;
import com.Vaccination.projet.services.PatientService;
import com.Vaccination.projet.Repositories.PatientRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {

    @Mock
    private PatientRepo patientrepo;

    @InjectMocks
    private PatientService patientService;

    private patientDto patientDto;
    private patient existingPatient;

    @BeforeEach
    public void setup() {
        patientDto = new patientDto("Doe", "John", "johndoe@example.com", 1234567890, LocalDate.of(1985, 5, 15), null);

        existingPatient = new patient();
        existingPatient.setNom("Doe");
        existingPatient.setPrenom("John");
        existingPatient.setMail("johndoe@example.com");
        existingPatient.setTelephone(1234567890);
        existingPatient.setDate_naissance(LocalDate.of(1985, 5, 15));
    }

    @Test
    public void testGetOrCreatePatient_ShouldReturnExistingPatient_WhenPatientExists() {
        // Simuler que le patient existe déjà
        when(patientrepo.findByMail(patientDto.getEmail())).thenReturn(existingPatient);

        patient result = patientService.getOrCreatePatient(patientDto);

        assertEquals(existingPatient, result);
        verify(patientrepo, times(1)).findByMail(patientDto.getEmail());
        verify(patientrepo, times(0)).save(any(patient.class)); // Aucun appel à save
    }

    @Test
    public void testGetOrCreatePatient_ShouldCreateNewPatient_WhenPatientDoesNotExist() {
        // Simuler que le patient n'existe pas
        when(patientrepo.findByMail(patientDto.getEmail())).thenReturn(null);

        patient result = patientService.getOrCreatePatient(patientDto);

        assertNotNull(result);
        assertEquals(patientDto.getEmail(), result.getMail());
        assertEquals(patientDto.getFirstName(), result.getPrenom());
        assertEquals(patientDto.getLastName(), result.getNom());
        assertEquals(patientDto.getTelephone(), result.getTelephone());
        verify(patientrepo, times(1)).findByMail(patientDto.getEmail());
        verify(patientrepo, times(1)).save(any(patient.class)); // Un appel à save
    }

    @Test
    public void testGetOrCreatePatient_ShouldThrowException_WhenPatientEmailIsNull() {
        // Créer un DTO avec un email nul
        patientDto invalidPatientDto = new patientDto("boub", "John", null, 1234567890, LocalDate.of(1985, 5, 15), null);

        // Appeler la méthode
        assertThrows(IllegalArgumentException.class, () -> patientService.getOrCreatePatient(invalidPatientDto));
    }

    @Test
    public void testFindAllPatients_ShouldReturnPatients() {
        // Simuler la liste des patients retournée par le repository
        when(patientrepo.findAll()).thenReturn(List.of(existingPatient));

        List<patient> result = patientService.findAllPatients();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(existingPatient, result.get(0));
    }
}
