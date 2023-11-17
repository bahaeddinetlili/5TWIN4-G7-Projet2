package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.*;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EtudiantServiceImplTest {

    @Mock
    private EtudiantRepository etudiantRepository;

    @Mock
    private ContratRepository contratRepository;

    @Mock
    private EquipeRepository equipeRepository;

    @Mock
    private DepartementRepository departementRepository;

    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllEtudiants() {

        List<Etudiant> etudiants = new ArrayList<>();

        etudiants.add(new Etudiant("bairem"  , "mehrez"));
        etudiants.add(new Etudiant("mohamed"  , "oues"));

        when(etudiantRepository.findAll()).thenReturn(etudiants);

        List<Etudiant> retrievedEtudiants = etudiantService.retrieveAllEtudiants();

        assertEquals(etudiants.size(), retrievedEtudiants.size());

    }

    @Test
    void testAddEtudiant() {

        Etudiant etudiantToAdd = new Etudiant("eya", "kasmi");
        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(etudiantToAdd);

        Etudiant addedEtudiant = etudiantService.addEtudiant(etudiantToAdd);
        assertEquals(etudiantToAdd, addedEtudiant);
    }


   
    @Test
    void retrieveEtudiant() {

        Integer etudiantId = 1;
        Etudiant expectedEtudiant = new Etudiant("name", "prenom");

        when(etudiantRepository.findById(etudiantId)).thenReturn(Optional.of(expectedEtudiant));


        Etudiant retrievedEtudiant = etudiantService.retrieveEtudiant(etudiantId);


        assertEquals(expectedEtudiant, retrievedEtudiant);
        verify(etudiantRepository, times(1)).findById(etudiantId);
    }

    @Test
    void removeEtudiant() {

        Integer etudiantId = 1;
        Etudiant etudiantToRemove = new Etudiant(/* Initialize with necessary fields */);

        when(etudiantRepository.findById(etudiantId)).thenReturn(java.util.Optional.of(etudiantToRemove));


        etudiantService.removeEtudiant(etudiantId);


        verify(etudiantRepository, times(1)).findById(etudiantId);
        verify(etudiantRepository, times(1)).delete(etudiantToRemove);
    }

    @Test
    void assignEtudiantToDepartement() {

        Integer etudiantId = 1;
        Integer departementId = 10;

        Etudiant etudiant = new Etudiant();
        Departement departement = new Departement();

        when(etudiantRepository.findById(etudiantId)).thenReturn(java.util.Optional.of(etudiant));
        when(departementRepository.findById(departementId)).thenReturn(java.util.Optional.of(departement));


        etudiantService.assignEtudiantToDepartement(etudiantId, departementId);


        verify(etudiantRepository, times(1)).findById(etudiantId);
        verify(departementRepository, times(1)).findById(departementId);
        verify(etudiantRepository, times(1)).save(etudiant);

        assertEquals(departement, etudiant.getDepartement());
    }



    @Test
    void getEtudiantsByDepartement() {
        Integer departementId = 10;

        List<Etudiant> etudiants = new ArrayList<>();

        when(etudiantRepository.findEtudiantsByDepartement_IdDepart(departementId)).thenReturn(etudiants);

        List<Etudiant> retrievedEtudiants = etudiantService.getEtudiantsByDepartement(departementId);


        verify(etudiantRepository, times(1)).findEtudiantsByDepartement_IdDepart(departementId);
        assertEquals(etudiants.size(), retrievedEtudiants.size());
    }
}