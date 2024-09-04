package es.cic.curso.coleccionCartas.album;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import es.cic.curso.coleccionCartas.model.Album;
import es.cic.curso.coleccionCartas.repository.AlbumRepository;
import es.cic.curso.coleccionCartas.service.AlbumService;

@SpringBootTest
@Transactional
public class AlbumServiceIT {

    @Autowired
    private AlbumService albumService;

    @Autowired
    private AlbumRepository albumRepository;

    @BeforeEach
    public void setUp() {
        Album album1 = new Album();
        Album album2 = new Album();
        album1.setId(1L);
        album1.setNombre("nombreTest");
        album1.setCromos(new ArrayList<>());
        album2.setId(1L);
        album2.setNombre("nombreTest");
        album2.setCromos(new ArrayList<>());
        albumRepository.save(album1);
        albumRepository.save(album2);
    }

    @AfterEach
    public void tearDown() {
        albumRepository.deleteAll();
    }

    @Test
    public void testFindAll() {

        List<Album> result = albumService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testFindById() {
        Album result = albumService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void testFindById_NotFound() {
        Album result = albumService.findById(Long.MAX_VALUE);

        assertNull(result);
    }

    @Test
    public void testSave() {
        Album album = new Album();
        album.setId(Long.MAX_VALUE);
        album.setNombre("nombreTest");
        Album result = albumService.save(album);

        assertNotNull(result);
    }

    @Test
    public void testDeleteById() {
        albumService.deleteById(1L);

        Album result = albumRepository.findById(1L).orElse(null);
        assertNull(result);
    }

    @Test
    public void testSaveAlbumWithImage() throws IOException {
        Album album = albumService.findById(1L);

        MultipartFile file = new MockMultipartFile("file", "image.jpg", "image/jpeg", "test image content".getBytes());

        Album result = albumService.saveAlbumWithImage(album, file);

        assertNotNull(result);
        assertEquals("/home/usuario/images/image.jpg", result.getImagen());

        File savedFile = new File("/home/usuario/images/image.jpg");
        if (savedFile.exists()) {
            savedFile.delete();
        }
    }

    @Test
    public void testSaveAlbumWithImage_EmptyFile() throws IOException {

        Album album = albumService.findById(1L);

        MultipartFile file = new MockMultipartFile("file", "", "image/jpeg", new byte[0]);

        Album result = albumService.saveAlbumWithImage(album, file);

        assertNotNull(result);
        assertNull(result.getImagen());
    }
}