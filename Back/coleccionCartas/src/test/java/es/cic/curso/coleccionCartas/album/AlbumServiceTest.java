package es.cic.curso.coleccionCartas.album;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import es.cic.curso.coleccionCartas.model.Album;
import es.cic.curso.coleccionCartas.model.Cromos;
import es.cic.curso.coleccionCartas.repository.AlbumRepository;
import es.cic.curso.coleccionCartas.service.AlbumService;

@SpringBootTest
public class AlbumServiceTest {

    @Mock
    private AlbumRepository albumRepository;

    @InjectMocks
    private AlbumService albumService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {

        // Given
        Album album1 = new Album();
        album1.setNombre("Album 1");
        Album album2 = new Album();
        album2.setNombre("Album 2");

        // When
        when(albumRepository.findAll()).thenReturn(Arrays.asList(album1, album2));

        List<Album> result = albumService.findAll();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Album 1", result.get(0).getNombre());
        assertEquals("Album 2", result.get(1).getNombre());
    }

    @Test
    public void testFindById() {

        // Gievn
        Album album = new Album();
        album.setNombre("Album Test");

        // When
        when(albumRepository.findById(1L)).thenReturn(Optional.of(album));

        Album result = albumService.findById(1L);

        // Then
        assertNotNull(result);
        assertEquals("Album Test", result.getNombre());
    }

    @Test
    public void testGetCromosByAlbumId() {

        // Given
        Album album = new Album();
        Cromos cromo1 = new Cromos();
        Cromos cromo2 = new Cromos();
        album.setCromos(Arrays.asList(cromo1, cromo2));

        // When
        when(albumRepository.findById(1L)).thenReturn(Optional.of(album));

        List<Cromos> result = albumService.getCromosByAlbumId(1L);

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testGetAlbumImage() throws IOException {

        // Given
        Album album = new Album();
        album.setImagen("test_image.jpg");

        // When
        when(albumRepository.findById(1L)).thenReturn(Optional.of(album));

        File tempFile = File.createTempFile("test_image", ".jpg");
        tempFile.deleteOnExit();

        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("test content");
        }

        album.setImagen(tempFile.getAbsolutePath());

        Resource result = albumService.getAlbumImage(1L);

        // Then
        assertNotNull(result);
        assertTrue(result.exists());
    }

    @Test
    public void testSave() {

        // Given
        Album album = new Album();
        album.setNombre("Album Test");

        // When
        when(albumRepository.save(any(Album.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Album result = albumService.save(album);

        // Then
        assertNotNull(result);
        assertEquals("Album Test", result.getNombre());
    }

    @Test
    public void testDeleteById() {

        // When
        doNothing().when(albumRepository).deleteById(1L);

        albumService.deleteById(1L);

        // Then
        verify(albumRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testSaveAlbumWithImage() throws IOException {

        // Given
        Album album = new Album();
        album.setNombre("Album Test");

        MultipartFile file = new MockMultipartFile("file", "image.jpg", "image/jpeg", "test image content".getBytes());

        // When
        when(albumRepository.save(any(Album.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Album result = albumService.saveAlbumWithImage(album, file);

        // Then
        assertNotNull(result);
        assertEquals(Paths.get("images").toAbsolutePath().toString() + File.separator + "image.jpg",
                result.getImagen());

        verify(albumRepository, times(1)).save(any(Album.class));

        File savedFile = new File(result.getImagen());
        if (savedFile.exists()) {
            savedFile.delete();
        }
    }

    @Test
    public void testSaveAlbumWithImage_EmptyFile() throws IOException {

        // Given
        Album album = new Album();
        album.setNombre("Album Test");
        album.setImagen("existing_image.jpg");

        MultipartFile file = new MockMultipartFile("file", "", "image/jpeg", new byte[0]);

        // When
        when(albumRepository.save(any(Album.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Album result = albumService.saveAlbumWithImage(album, file);

        // Then
        assertNotNull(result);
        assertEquals("existing_image.jpg", result.getImagen());

        verify(albumRepository, times(1)).save(any(Album.class));
    }
}