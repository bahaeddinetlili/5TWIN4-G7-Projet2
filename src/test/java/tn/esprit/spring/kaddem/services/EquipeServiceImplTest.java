package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class EquipeServiceImplTest {

    @Mock
    private EquipeRepository equipeRepository;
    @InjectMocks
    private EquipeServiceImpl equipeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @Order(1)
    public void testAddContrat() {
        Equipe sampleEquipe = new Equipe();
        when(equipeRepository.save(any(Equipe.class))).thenReturn(sampleEquipe);

        Equipe savedEquipe = equipeService.addEquipe(sampleEquipe);

        verify(equipeRepository, times(1)).save(any(Equipe.class));

        assertEquals(sampleEquipe, savedEquipe);
    }

    @Order(2)
    public void testRetrieveContrat() {
        Integer equipeId = 1;
        Equipe sampleEquipe = new Equipe();
        when(equipeRepository.findById(equipeId)).thenReturn(Optional.of(sampleEquipe));

        Equipe retrievedEquipe = equipeService.retrieveEquipe(equipeId);

        verify(equipeRepository, times(1)).findById(equipeId);

        assertEquals(sampleEquipe, retrievedEquipe);
    }

    @Test
    @Order(3)
    public void testUpdateContrat() {
        Equipe sampleEquipe = new Equipe();
        when(equipeRepository.save(any(Equipe.class))).thenReturn(sampleEquipe);
        Equipe updatedEquipe = equipeService.updateEquipe(sampleEquipe);
        verify(equipeRepository, times(1)).save(any(Equipe.class));
        assertEquals(sampleEquipe, updatedEquipe);
    }

    @Test
    @Order(4)
    public void testRemoveContrat() {
        Integer updatedEquipeId = 1;
        Equipe sampleContrat = new Equipe();
        when(equipeRepository.findById(updatedEquipeId)).thenReturn(Optional.of(sampleContrat));

        equipeService.deleteEquipe(updatedEquipeId);

        verify(equipeRepository, times(1)).delete(sampleContrat);
    }
}
