package tn.esprit.spring.kaddem.services;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class DepartementServiceImplTest {
    @InjectMocks
    private DepartementServiceImpl departementService;

    @Mock
    private DepartementRepository departementRepository;


    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void retrieveAllDepartements() {
        // Create a list of Departement objects for testing
        List<Departement> departements = new ArrayList<>();
        departements.add(new Departement(1, "Dept A"));
        departements.add(new Departement(2, "Dept B"));

        // Mock the behavior of the departementRepository
        when(departementRepository.findAll()).thenReturn(departements);

        // Call the service method to retrieve departements
        List<Departement> result = departementService.retrieveAllDepartements();

        // Verify the results
        assertEquals(2, result.size());
        assertEquals("Dept A", result.get(0).getNomDepart());
        assertEquals("Dept B", result.get(1).getNomDepart());
    }


    @Test
    void addDepartement() {
        // Create a Departement object for testing
        Departement departement = new Departement(1, "Dept A");

        // Mock the behavior of the departementRepository.save method
        when(departementRepository.save(departement)).thenReturn(departement);

        // Call the service method to add a departement
        Departement result = departementService.addDepartement(departement);

        // Verify the result
        assertEquals(1, result.getIdDepart().intValue());
        assertEquals("Dept A", result.getNomDepart());

    }

    @Test
    void updateDepartement() {
        // Create a Departement object for testing
        Departement departement = new Departement(1, "Dept A");

        // Mock the behavior of the departementRepository.save method
        when(departementRepository.save(departement)).thenReturn(departement);

        // Call the service method to update a departement
        Departement result = departementService.updateDepartement(departement);

        // Verify the result
        assertEquals(1, result.getIdDepart().intValue());
        assertEquals("Dept A", result.getNomDepart());
    }

    @Test
    void retrieveDepartement() {
        // Create a Departement object for testing
        Departement departement = new Departement(1, "Dept A");

        // Mock the behavior of the departementRepository.findById method
        when(departementRepository.findById(1)).thenReturn(Optional.of(departement));
        when(departementRepository.findById(2)).thenReturn(Optional.empty());

        // Call the service method to retrieve a departement that exists
        Departement result1 = departementService.retrieveDepartement(1);

        // Verify the results for an existing departement
        assertEquals(1, result1.getIdDepart().intValue());
        assertEquals("Dept A", result1.getNomDepart());

        // Verify that a non-existent departement returns a NoSuchElementException
        assertThrows(NoSuchElementException.class, () -> departementService.retrieveDepartement(2));
    }


    @Test
    void deleteDepartement() {
        // Create a Departement object for testing
        Departement departement = new Departement(1, "Dept A");

        // Mock the behavior of the departementRepository
        when(departementRepository.findById(1)).thenReturn(Optional.of(departement));

        // Call the service method to delete a departement
        departementService.deleteDepartement(1);

        // Verify that the repository's delete method is called with the expected departement
        verify(departementRepository).delete(departement);
    }
}