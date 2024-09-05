package es.cic.curso.coleccionCartas.cromo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import es.cic.curso.coleccionCartas.controller.CromosController;
import es.cic.curso.coleccionCartas.exception.ActualizarException;
import es.cic.curso.coleccionCartas.model.Cromos;
import es.cic.curso.coleccionCartas.service.CromosService;

@SpringBootTest
public class CromosControllerTest {

    @Mock
    private CromosService cromosService;

    @InjectMocks
    private CromosController cromosController;

    private Cromos cromo1;
    private Cromos cromo2;

    @BeforeEach
    void setUp() {
        cromo1 = Cromos.builder()
                .id(1L)
                .nombre("Cromo 1")
                .build();

        cromo2 = Cromos.builder()
                .id(2L)
                .nombre("Cromo 2")
                .build();
    }

    @Test
    void testGetAllCromos() {

        // Given
        List<Cromos> cromosList = Arrays.asList(cromo1, cromo2);

        // When
        when(cromosService.findAll()).thenReturn(cromosList);

        List<Cromos> result = cromosController.getAllCromos();

        // Then
        verify(cromosService, times(1)).findAll();
        assert (result.size() == 2);
        assert (result.get(0).getNombre().equals("Cromo 1"));
        assert (result.get(1).getNombre().equals("Cromo 2"));
    }

    @Test
    void testGetCromosById() {

        // When
        when(cromosService.findById(1L)).thenReturn(cromo1);

        Cromos result = cromosController.getCromosById(1L);

        // Then
        verify(cromosService, times(1)).findById(1L);
        assert (result.getNombre().equals("Cromo 1"));
    }

    @Test
    void testGetCromosById_NotFound() {

        // When
        when(cromosService.findById(1L)).thenReturn(null);

        // Then
        try {
            cromosController.getCromosById(1L);
        } catch (ActualizarException e) {
            assert (e.getMessage().equals("No se ha encontrado el cromo con id 1"));
        }

        verify(cromosService, times(1)).findById(1L);
    }

    @Test
    void testCreateCromos() {

        // When
        when(cromosService.save(any(Cromos.class))).thenReturn(cromo1);

        Cromos result = cromosController.createCromos(cromo1);

        // Then
        verify(cromosService, times(1)).save(cromo1);
        assert (result.getNombre().equals("Cromo 1"));
    }

    @Test
    void testUpdateCromosById() {

        // When
        when(cromosService.findById(1L)).thenReturn(cromo1);
        when(cromosService.save(any(Cromos.class))).thenReturn(cromo1);

        Cromos result = cromosController.updateCromosById(1L, cromo1);

        // Then
        verify(cromosService, times(1)).findById(1L);
        verify(cromosService, times(1)).save(cromo1);
        assert (result.getNombre().equals("Cromo 1"));
    }

    @Test
    void testUpdateCromosById_NotFound() {

        // When
        when(cromosService.findById(1L)).thenReturn(null);

        // Then
        try {
            cromosController.updateCromosById(1L, cromo1);
        } catch (ActualizarException e) {
            assert (e.getMessage().equals("No se ha encontrado el cromo con id 1"));
        }

        verify(cromosService, times(1)).findById(1L);
    }

    @Test
    void testDeleteCromosById() {

        // When
        cromosController.deleteCromosById(1L);

        // Then
        verify(cromosService, times(1)).deleteById(1L);
    }
}