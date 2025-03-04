package services;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.Vaccination.projet.entities.centres;
import com.Vaccination.projet.entities.employes;
import com.Vaccination.projet.dto.updateCentreDto;
import com.Vaccination.projet.Repositories.CentreRepository;
import com.Vaccination.projet.Repositories.EmployesRepo;
import com.Vaccination.projet.services.CentreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.Optional;
import java.util.List;

public class CentreServiceTest {

    @Mock
    private CentreRepository centrerepo;
    
    @Mock
    private EmployesRepo employerepo;
    
    @InjectMocks
    private CentreService centreService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCentres() {
        // Given
        centres centre1 = new centres(1, "Centre1", "Paris");
        centres centre2 = new centres(2, "Centre2", "Lyon");
        when(centrerepo.findAll()).thenReturn(Arrays.asList(centre1, centre2));

        // When
        List<centres> centresList = centreService.getAllCentres();

        // Then
        assertEquals(2, centresList.size());
        verify(centrerepo, times(1)).findAll();
    }

    @Test
    void testGetCentreByCity() {
        // Given
        centres centre = new centres(1, "Centre1", "Paris");
        when(centrerepo.findByVilleIgnoreCase("Paris")).thenReturn(Arrays.asList(centre));

        // When
        List<centres> centresList = centreService.getCentreByCity("Paris");

        // Then
        assertEquals(1, centresList.size());
        assertEquals("Paris", centresList.get(0).getVille());
        verify(centrerepo, times(1)).findByVilleIgnoreCase("Paris");
    }

    @Test
    void testGetCentreById() {
        // Given
        centres centre = new centres(1, "Centre1", "Paris");
        when(centrerepo.findCentreById(1)).thenReturn(centre);

        // When
        centres foundCentre = centreService.getCentreById(1);

        // Then
        assertNotNull(foundCentre);
        assertEquals(1, foundCentre.getId());
        verify(centrerepo, times(1)).findCentreById(1);
    }

    @Test
    void testAddCentre() {
        // Given
        centres centre = new centres(1, "Centre1", "Paris");

        // When
        centreService.addCentre(centre);

        // Then
        verify(centrerepo, times(1)).save(centre);
    }

    @Test
    void testAddCentreThrowsExceptionWhenCentreIsNull() {
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> centreService.addCentre(null));
    }

    @Test
    void testUpdateCentre() {
        // Given
        centres centre = new centres(1, "Centre1", "Paris");
        updateCentreDto updateCentreDto = new updateCentreDto("UpdatedCentre", "Lyon");
        when(centrerepo.findCentreById(1)).thenReturn(centre);

        // When
        centreService.updateCentre(1, updateCentreDto);

        // Then
        assertEquals("UpdatedCentre", centre.getNom());
        assertEquals("Lyon", centre.getVille());
        verify(centrerepo, times(1)).save(centre);
    }

    @Test
    void testUpdateCentreThrowsExceptionWhenCentreNotFound() {
        // Given
        updateCentreDto updateCentreDto = new updateCentreDto("UpdatedCentre", "Lyon");
        when(centrerepo.findCentreById(1)).thenReturn(null);

        // When & Then
        assertThrows(IllegalStateException.class, () -> centreService.updateCentre(1, updateCentreDto));
    }

    
}

