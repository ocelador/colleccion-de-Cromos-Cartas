package es.cic.curso.coleccionCartas.cromo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.coleccionCartas.model.Cromos;
import es.cic.curso.coleccionCartas.repository.CromosRepository;
import es.cic.curso.coleccionCartas.service.CromosService;

@SpringBootTest
@Transactional
public class CromosServiceIT {

    @Autowired
    private CromosService cromosService;

    @Autowired
    private CromosRepository cromosRepository;

    @BeforeEach
    public void setUp() {
        cromosRepository.deleteAll();
    }

    @Test
    public void testFindAll() {

        // Given
        Cromos cromo1 = new Cromos();
        cromo1.setNombre("Cromo 1");
        cromosRepository.save(cromo1);

        Cromos cromo2 = new Cromos();
        cromo2.setNombre("Cromo 2");
        cromosRepository.save(cromo2);

        // When
        List<Cromos> result = cromosService.findAll();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testFindById() {

        // Given
        Cromos cromo = new Cromos();
        cromo.setNombre("Cromo Test");
        cromo = cromosRepository.save(cromo);

        // When
        Cromos result = cromosService.findById(cromo.getId());

        // Then
        assertNotNull(result);
        assertEquals(cromo.getId(), result.getId());
    }

    @Test
    public void testFindById_NotFound() {

        // When
        Cromos result = cromosService.findById(Long.MAX_VALUE);

        // Then
        assertNull(result);
    }

    @Test
    public void testSave() {

        // Given
        Cromos cromo = new Cromos();
        cromo.setNombre("Nuevo Cromo");

        // When
        Cromos result = cromosService.save(cromo);

        // Then
        assertNotNull(result);
        assertEquals("Nuevo Cromo", result.getNombre());
    }

    @Test
    public void testDeleteById() {

        // Given
        Cromos cromo = new Cromos();
        cromo.setNombre("Cromo a Eliminar");
        cromo = cromosRepository.save(cromo);

        // When
        cromosService.deleteById(cromo.getId());

        Cromos result = cromosRepository.findById(cromo.getId()).orElse(null);

        // Then
        assertNull(result);
    }
}