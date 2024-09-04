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
        Cromos cromo1 = new Cromos();
        cromo1.setNombre("Cromo 1");
        cromosRepository.save(cromo1);

        Cromos cromo2 = new Cromos();
        cromo2.setNombre("Cromo 2");
        cromosRepository.save(cromo2);

        List<Cromos> result = cromosService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testFindById() {
        Cromos cromo = new Cromos();
        cromo.setNombre("Cromo Test");
        cromo = cromosRepository.save(cromo);

        Cromos result = cromosService.findById(cromo.getId());

        assertNotNull(result);
        assertEquals(cromo.getId(), result.getId());
    }

    @Test
    public void testFindById_NotFound() {
        Cromos result = cromosService.findById(Long.MAX_VALUE);

        assertNull(result);
    }

    @Test
    public void testSave() {
        Cromos cromo = new Cromos();
        cromo.setNombre("Nuevo Cromo");

        Cromos result = cromosService.save(cromo);

        assertNotNull(result);
        assertEquals("Nuevo Cromo", result.getNombre());
    }

    @Test
    public void testDeleteById() {
        Cromos cromo = new Cromos();
        cromo.setNombre("Cromo a Eliminar");
        cromo = cromosRepository.save(cromo);

        cromosService.deleteById(cromo.getId());

        Cromos result = cromosRepository.findById(cromo.getId()).orElse(null);
        assertNull(result);
    }
}