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
        Album album1 = new Album();
        album1.setNombre("Album 1");
        albumRepository.save(album1);

        Album album2 = new Album();
        album2.setNombre("Album 2");
        albumRepository.save(album2);

        mockMvc.perform(get("/api/albums"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].nombre").value("Album 1"))
                .andExpect(jsonPath("$[1].nombre").value("Album 2"));
    }

    @Test
    public void testGetAlbumById() throws Exception {
        Album album = new Album();
        album.setNombre("Album Test");
        album = albumRepository.save(album);

        mockMvc.perform(get("/api/albums/{id}", album.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Album Test"));
    }

    @Test
    public void testGetAlbumImage() throws Exception {
        Album album = new Album();
        album.setNombre("Album con Imagen");
        album.setDescripcion("Descripción del Album");
        album.setImagen("images/image.jpg");
        album = albumRepository.save(album);

        mockMvc.perform(get("/api/albums/" + album.getId() + "/image"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Disposition", "attachment; filename=\"image.jpg\""));
    }

    @Test
    public void testCreateAlbum() throws Exception {
        Album album = new Album();
        album.setNombre("Nuevo Album");

        mockMvc.perform(post("/api/albums")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(album)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Nuevo Album"));
    }

    @Test
    public void testCrearAlbumWithImage() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "image.jpg", "image/jpeg",
                "test image content".getBytes());

        mockMvc.perform(multipart("/api/albums/upload")
                .file(file)
                .param("nombre", "Album con Imagen")
                .param("descripcion", "Descripción del Album"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Album con Imagen"))
                .andExpect(jsonPath("$.descripcion").value("Descripción del Album"));

        File savedFile = new File("/home/usuario/images/image.jpg");
        if (savedFile.exists()) {
            savedFile.delete();
        }
    }

    @Test
    public void testUpdateAlbumById() throws Exception {
        Album album = new Album();
        album.setNombre("Album Original");
        album = albumRepository.save(album);

        album.setNombre("Album Actualizado");

        mockMvc.perform(put("/api/albums/{id}", album.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(album)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Album Actualizado"));
    }

    @Test
    public void testDeleteAlbumById() throws Exception {
        Album album = new Album();
        album.setNombre("Album a Eliminar");
        album = albumRepository.save(album);

        mockMvc.perform(delete("/api/albums/{id}", album.getId()))
                .andExpect(status().isOk());
    }
}