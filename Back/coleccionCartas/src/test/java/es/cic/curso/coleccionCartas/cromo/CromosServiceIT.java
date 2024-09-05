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

import es.cic.curso.coleccionCartas.model.Album;
import es.cic.curso.coleccionCartas.model.Cromos;
import es.cic.curso.coleccionCartas.repository.AlbumRepository;
import es.cic.curso.coleccionCartas.repository.CromosRepository;
import es.cic.curso.coleccionCartas.service.CromosService;

@SpringBootTest
@Transactional
public class CromosServiceIT {

    @Autowired
    private CromosService cromosService;

    @Autowired
    private CromosRepository cromosRepository;

    @Autowired
    private AlbumRepository albumRepository;

    private Album album;

    @BeforeEach
    public void setUp() {
        cromosRepository.deleteAll();

        album = Album.builder()
                .nombre("Album Test")
                .build();
        album = albumRepository.save(album);
    }

    @Test
    public void testFindAll() {

        // Given
        Cromos cromo1 = Cromos.builder()
                .nombre("Cromo 1")
                .build();
        cromosRepository.save(cromo1);

        Cromos cromo2 = Cromos.builder()
                .nombre("Cromo 2")
                .build();
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
        Cromos cromo = Cromos.builder()
                .nombre("Cromo Test")
                .build();
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
    public void testGetCromosByNombre() {

        // Given
        Cromos cromo1 = Cromos.builder()
                .nombre("Cromo Test")
                .build();
        cromosRepository.save(cromo1);

        Cromos cromo2 = Cromos.builder()
                .nombre("Cromo Test")
                .build();
        cromosRepository.save(cromo2);

        // When
        List<Cromos> result = cromosService.getCromosByNombre("Cromo Test");

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testGetCromosAdquiridosByAlbumId() {

        // Given
        Cromos cromo1 = Cromos.builder()
                .nombre("Cromo Adquirido 1")
                .adquirido(true)
                .album(album)
                .build();
        cromosRepository.save(cromo1);

        Cromos cromo2 = Cromos.builder()
                .nombre("Cromo Adquirido 2")
                .adquirido(true)
                .album(album)
                .build();
        cromosRepository.save(cromo2);

        Cromos cromo3 = Cromos.builder()
                .nombre("Cromo No Adquirido")
                .adquirido(false)
                .album(album)
                .build();
        cromosRepository.save(cromo3);

        // When
        List<Cromos> result = cromosService.getCromosAdquiridosByAlbumId(album.getId());

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testSave() {

        // Given
        Cromos cromo = Cromos.builder()
                .nombre("Nuevo Cromo")
                .build();

        // When
        Cromos result = cromosService.save(cromo);

        // Then
        assertNotNull(result);
        assertEquals("Nuevo Cromo", result.getNombre());
    }

    @Test
    public void testDeleteById() {

        // Given
        Cromos cromo = Cromos.builder()
                .nombre("Cromo a Eliminar")
                .build();
        cromo = cromosRepository.save(cromo);

        // When
        cromosService.deleteById(cromo.getId());

        Cromos result = cromosRepository.findById(cromo.getId()).orElse(null);

        // Then
        assertNull(result);
    }
}