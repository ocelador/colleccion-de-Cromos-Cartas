package es.cic.curso.coleccionCartas.album;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import es.cic.curso.coleccionCartas.controller.AlbumController;
import es.cic.curso.coleccionCartas.exception.ActualizarException;
import es.cic.curso.coleccionCartas.model.Album;
import es.cic.curso.coleccionCartas.model.Cromos;
import es.cic.curso.coleccionCartas.service.AlbumService;

@SpringBootTest
public class AlbumControllerTest {

    @Mock
    private AlbumService albumService;

    @InjectMocks
    private AlbumController albumController;

    private Album album1;
    private Album album2;
    private Cromos cromo1;
    private Cromos cromo2;

    @BeforeEach
    void setUp() {
        album1 = new Album();
        album1.setId(1L);
        album1.setNombre("Album 1");

        album2 = new Album();
        album2.setId(2L);
        album2.setNombre("Album 2");

        cromo1 = new Cromos();
        cromo1.setId(1L);
        cromo1.setNombre("Cromo 1");

        cromo2 = new Cromos();
        cromo2.setId(2L);
        cromo2.setNombre("Cromo 2");
    }

    @Test
    void testGetAllAlbums() {

        // Given
        List<Album> albumsList = Arrays.asList(album1, album2);

        // When
        when(albumService.findAll()).thenReturn(albumsList);

        List<Album> result = albumController.getAllAlbums();

        // Then
        verify(albumService, times(1)).findAll();
        assert (result.size() == 2);
        assert (result.get(0).getNombre().equals("Album 1"));
        assert (result.get(1).getNombre().equals("Album 2"));
    }

    @Test
    void testGetAlbumById() {

        // When
        when(albumService.findById(1L)).thenReturn(album1);

        // Then
        Album result = albumController.getAlbumById(1L);

        verify(albumService, times(1)).findById(1L);
        assert (result.getNombre().equals("Album 1"));
    }

    @Test
    void testGetAlbumById_NotFound() {

        // When
        when(albumService.findById(1L)).thenReturn(null);

        // Then
        try {
            albumController.getAlbumById(1L);
        } catch (ActualizarException e) {
            assert (e.getMessage().equals("No se ha encontrado el album con id 1"));
        }

        verify(albumService, times(1)).findById(1L);
    }

    @Test
    void testGetCromosByAlbumId() {

        // Given
        List<Cromos> cromosList = Arrays.asList(cromo1, cromo2);

        // When
        when(albumService.getCromosByAlbumId(1L)).thenReturn(cromosList);

        List<Cromos> result = albumController.getCromosByAlbumId(1L);

        // Then
        verify(albumService, times(1)).getCromosByAlbumId(1L);
        assert (result.size() == 2);
        assert (result.get(0).getNombre().equals("Cromo 1"));
        assert (result.get(1).getNombre().equals("Cromo 2"));
    }

    @Test
    void testGetAlbumImage() throws IOException {

        // Given
        Resource resource = new ByteArrayResource("some text".getBytes()) {
            @Override
            public String getFilename() {
                return "filename.txt";
            }
        };

        // When
        when(albumService.getAlbumImage(1L)).thenReturn(resource);

        ResponseEntity<Resource> response = albumController.getAlbumImage(1L);

        // Then
        verify(albumService, times(1)).getAlbumImage(1L);
        assert (response.getStatusCode().value() == 200);
        assert (response.getHeaders().get(HttpHeaders.CONTENT_DISPOSITION).get(0)
                .contains("filename=\"filename.txt\""));
        assert (response.getBody().equals(resource));
    }

    @Test
    void testGetAlbumImage_NotFound() throws IOException {

        // When
        when(albumService.getAlbumImage(1L)).thenThrow(new IOException());

        ResponseEntity<Resource> response = albumController.getAlbumImage(1L);

        // Then
        verify(albumService, times(1)).getAlbumImage(1L);
        assert (response.getStatusCode().value() == 404);
    }

    @Test
    void testCreateAlbum() {

        // When
        when(albumService.save(any(Album.class))).thenReturn(album1);

        Album result = albumController.createAlbum(album1);

        // Then
        verify(albumService, times(1)).save(album1);
        assert (result.getNombre().equals("Album 1"));
    }

    @Test
    void testCrearAlbumWithImage() throws IOException {

        // Given
        MultipartFile file = new MockMultipartFile("file", "filename.txt", "text/plain", "some text".getBytes());

        // When
        when(albumService.saveAlbumWithImage(any(Album.class), eq(file))).thenReturn(album1);

        Album result = albumController.crearAlbumWithImage(file, "Album 1", "Descripción del Album 1");

        // Then
        verify(albumService, times(1)).saveAlbumWithImage(any(Album.class), eq(file));
        assert (result.getNombre().equals("Album 1"));
    }

    @Test
    void testUpdateAlbumById() {

        // When
        when(albumService.findById(1L)).thenReturn(album1);
        when(albumService.save(any(Album.class))).thenReturn(album1);

        Album result = albumController.updateAlbumById(1L, album1);

        // Then
        verify(albumService, times(1)).findById(1L);
        verify(albumService, times(1)).save(album1);
        assert (result.getNombre().equals("Album 1"));
    }

    @Test
    void testUpdateAlbumById_NotFound() {

        // When
        when(albumService.findById(1L)).thenReturn(null);

        // Then
        try {
            albumController.updateAlbumById(1L, album1);
        } catch (ActualizarException e) {
            assert (e.getMessage().equals("No se ha encontrado el cromo con id 1"));
        }

        verify(albumService, times(1)).findById(1L);
    }

    @Test
    void testDeleteAlbumById() {

        // When
        albumController.deleteAlbumById(1L);

        // Then
        verify(albumService, times(1)).deleteById(1L);
    }
}