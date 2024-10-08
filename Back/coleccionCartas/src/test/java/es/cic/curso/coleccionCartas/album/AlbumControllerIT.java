package es.cic.curso.coleccionCartas.album;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.FileWriter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.cic.curso.coleccionCartas.model.Album;
import es.cic.curso.coleccionCartas.repository.AlbumRepository;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AlbumControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        albumRepository.deleteAll();
    }

    @Test
    public void testGetAllAlbums() throws Exception {

        // Given
        Album album1 = Album.builder()
                .nombre("Album 1")
                .build();
        albumRepository.save(album1);

        Album album2 = Album.builder()
                .nombre("Album 2")
                .build();
        albumRepository.save(album2);

        // Then
        mockMvc.perform(get("/api/albums"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].nombre").value("Album 1"))
                .andExpect(jsonPath("$[1].nombre").value("Album 2"));
    }

    @Test
    public void testGetAlbumById() throws Exception {

        // Given
        Album album = Album.builder()
                .nombre("Album Test")
                .build();
        album = albumRepository.save(album);

        // Then
        mockMvc.perform(get("/api/albums/{id}", album.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Album Test"));
    }

    @Test
    public void testGetAlbumImage() throws Exception {

        // Given
        File tempFile = new File(System.getProperty("java.io.tmpdir"), "image.jpg");
        tempFile.deleteOnExit();

        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("test content");
        }

        Album album = Album.builder()
                .nombre("Album con Imagen")
                .descripcion("Descripción del Album")
                .imagen(tempFile.getAbsolutePath())
                .build();
        album = albumRepository.save(album);

        // Then
        mockMvc.perform(get("/api/albums/" + album.getId() + "/image"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Disposition", "attachment; filename=\"image.jpg\""));
    }

    @Test
    public void testCreateAlbum() throws Exception {

        // Given
        Album album = Album.builder()
                .nombre("Nuevo Album")
                .build();

        // Then
        mockMvc.perform(post("/api/albums")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(album)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Nuevo Album"));
    }

    @Test
    public void testCrearAlbumWithImage() throws Exception {

        // Given
        MockMultipartFile file = new MockMultipartFile("file", "image.jpg", "image/jpeg",
                "test image content".getBytes());

        // When
        mockMvc.perform(multipart("/api/albums/upload")
                .file(file)
                .param("nombre", "Album con Imagen")
                .param("descripcion", "Descripción del Album"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Album con Imagen"))
                .andExpect(jsonPath("$.descripcion").value("Descripción del Album"));

        File savedFile = new File("images/image.jpg");
        if (savedFile.exists()) {
            savedFile.delete();
        }
    }

    @Test
    public void testUpdateAlbumById() throws Exception {

        // Given
        Album album = Album.builder()
                .nombre("Album a Original")
                .build();
        album = albumRepository.save(album);

        album.setNombre("Album Actualizado");

        // Then
        mockMvc.perform(put("/api/albums/{id}", album.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(album)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Album Actualizado"));
    }

    @Test
    public void testDeleteAlbumById() throws Exception {

        // Given
        Album album = Album.builder()
                .nombre("Album a Eliminar")
                .build();
        album = albumRepository.save(album);

        // Then
        mockMvc.perform(delete("/api/albums/{id}", album.getId()))
                .andExpect(status().isOk());
    }
}