package services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import com.Vaccination.projet.Repositories.CreneauRepo;
import com.Vaccination.projet.Repositories.EmployesRepo;
import com.Vaccination.projet.Repositories.PatientRepo;
import com.Vaccination.projet.Repositories.ReservationRepo;
import com.Vaccination.projet.dto.patientDto;
import com.Vaccination.projet.dto.reservationDto;
import com.Vaccination.projet.entities.centres;
import com.Vaccination.projet.entities.creneaux;
import com.Vaccination.projet.entities.employes;
import com.Vaccination.projet.entities.patient;
import com.Vaccination.projet.entities.reservations;
import com.Vaccination.projet.services.PatientService;
import com.Vaccination.projet.services.ReservationService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public class ReservationServiceTest {

    @Mock
    private ReservationRepo reservationrepo;
    @Mock
    private PatientRepo patientRepository;
    @Mock
    private CreneauRepo creneauRepository;
    @Mock
    private EmployesRepo employeRepository;
    @Mock
    private PatientService patientService;

    @InjectMocks
    private ReservationService reservationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBookAppointment_ShouldCreateReservation_WhenValidData() {
        // Given
        int centreId = 1;
        LocalDate date = LocalDate.of(2023, 5, 10);
        LocalTime heure = LocalTime.of(10, 30);
        patientDto patientDto = new patientDto();
        
        employes doctor = new employes(); // Mock du médecin
        doctor.setPrenom("John");
        doctor.setNom("Doe");

        List<employes> doctors = List.of(doctor);
        creneaux creneau = new creneaux();
        creneau.setDisponible(true);
        creneau.setCentre(new centres());

        when(employeRepository.findDoctorsByCentreId(centreId)).thenReturn(doctors);
        when(creneauRepository.findByCentreIdAndJourAndHeure(centreId, date, heure)).thenReturn(Optional.of(creneau));
        when(patientService.getOrCreatePatient(any(patientDto.class))).thenReturn(new patient());

        // When
        reservations reservation = reservationService.bookAppointment(centreId, date, heure, patientDto);

        // Then
        assertNotNull(reservation);
        assertEquals(doctor, reservation.getemploye());
        assertFalse(creneau.isDisponible());
        verify(reservationrepo).save(reservation);
        verify(creneauRepository).save(creneau);
    }

    @Test
    void testBookAppointment_ShouldThrowException_WhenCreneauNotAvailable() {
        // Given
        int centreId = 1;
        LocalDate date = LocalDate.of(2023, 5, 10);
        LocalTime heure = LocalTime.of(10, 30);
        patientDto patientDto = new patientDto();

        List<employes> doctors = List.of(new employes());
        creneaux creneau = new creneaux();
        creneau.setDisponible(false);

        when(employeRepository.findDoctorsByCentreId(centreId)).thenReturn(doctors);
        when(creneauRepository.findByCentreIdAndJourAndHeure(centreId, date, heure)).thenReturn(Optional.of(creneau));

        // When & Then
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            reservationService.bookAppointment(centreId, date, heure, patientDto);
        });
        assertEquals("Ce créneau n'est plus disponible.", exception.getMessage());
    }




@Test
void testGetReservationDetails_ShouldThrowException_WhenReservationNotFound() {
    // Given
    int centreId = 1;
    int creneauId = 1;
    int patientId = 1;
    when(reservationrepo.findByCreneau_Centre_IdAndCreneau_IdAndPatient_Id(centreId, creneauId, patientId))
            .thenReturn(null);

    // When & Then
    IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
        reservationService.getReservationDetails(centreId, creneauId, patientId);
    });
    assertEquals("La réservation n'existe pas.", exception.getMessage());
}
}