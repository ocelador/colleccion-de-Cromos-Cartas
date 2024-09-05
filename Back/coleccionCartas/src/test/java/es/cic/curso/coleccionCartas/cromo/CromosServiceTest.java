package es.cic.curso.coleccionCartas.cromo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import es.cic.curso.coleccionCartas.model.Cromos;
import es.cic.curso.coleccionCartas.repository.CromosRepository;
import es.cic.curso.coleccionCartas.service.CromosService;

@SpringBootTest
public class CromosServiceTest {

    @Mock
    private CromosRepository cromosRepository;

    @InjectMocks
    private CromosService cromosService;

    private Cromos cromo1;
    private Cromos cromo2;

    @BeforeEach
    void setUp() {
        cromo1 = new Cromos();
        cromo1.setId(1L);
        cromo1.setNombre("Cromo 1");

        cromo2 = new Cromos();
        cromo2.setId(2L);
        cromo2.setNombre("Cromo 2");
    }

    @Test
    void testFindAll() {

        // Given
        List<Cromos> cromosList = Arrays.asList(cromo1, cromo2);

        // When
        when(cromosRepository.findAll()).thenReturn(cromosList);

        List<Cromos> result = cromosService.findAll();

        // Then
        verify(cromosRepository, times(1)).findAll();
        assert (result.size() == 2);
        assert (result.get(0).getNombre().equals("Cromo 1"));
        assert (result.get(1).getNombre().equals("Cromo 2"));
    }

    @Test
    void testFindById() {

        // When
        when(cromosRepository.findById(1L)).thenReturn(Optional.of(cromo1));

        Cromos result = cromosService.findById(1L);

        // Then
        verify(cromosRepository, times(1)).findById(1L);
        assert (result.getNombre().equals("Cromo 1"));
    }

    @Test
    void testFindById_NotFound() {

        // When
        when(cromosRepository.findById(1L)).thenReturn(Optional.empty());

        Cromos result = cromosService.findById(1L);

        // Then
        verify(cromosRepository, times(1)).findById(1L);
        assert (result == null);
    }

    @Test
    void testSave() {

        // When
        when(cromosRepository.save(any(Cromos.class))).thenReturn(cromo1);

        Cromos result = cromosService.save(cromo1);

        // Then
        verify(cromosRepository, times(1)).save(cromo1);
        assert (result.getNombre().equals("Cromo 1"));
    }

    @Test
    void testDeleteById() {

        // When
        cromosService.deleteById(1L);

        // Then
        verify(cromosRepository, times(1)).deleteById(1L);
    }
}