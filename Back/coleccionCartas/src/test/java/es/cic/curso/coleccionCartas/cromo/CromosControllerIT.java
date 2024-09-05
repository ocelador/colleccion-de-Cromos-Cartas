package es.cic.curso.coleccionCartas.cromo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.cic.curso.coleccionCartas.model.Album;
import es.cic.curso.coleccionCartas.model.Cromos;
import es.cic.curso.coleccionCartas.repository.AlbumRepository;
import es.cic.curso.coleccionCartas.repository.CromosRepository;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CromosControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CromosRepository cromosRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Album album;

    @BeforeEach
    public void setUp() {
        cromosRepository.deleteAll();
        albumRepository.deleteAll();

        album = Album.builder()
                .nombre("Album Test")
                .build();
        album = albumRepository.save(album);
    }

    @Test
    public void testGetAllCromos() throws Exception {
        Cromos cromo1 = Cromos.builder()
                .nombre("Cromo 1")
                .build();
        cromosRepository.save(cromo1);

        Cromos cromo2 = Cromos.builder()
                .nombre("Cromo 2")
                .build();
        cromosRepository.save(cromo2);

        mockMvc.perform(get("/api/cromos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].nombre").value("Cromo 1"))
                .andExpect(jsonPath("$[1].nombre").value("Cromo 2"));
    }

    @Test
    public void testGetCromosById() throws Exception {

        // Given
        Cromos cromo = Cromos.builder()
                .nombre("Cromo Test")
                .build();
        cromo = cromosRepository.save(cromo);

        // Then
        mockMvc.perform(get("/api/cromos/{id}", cromo.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Cromo Test"));
    }

    @Test
    public void testGetCromosByNombre() throws Exception {

        // Given
        Cromos cromo1 = Cromos.builder()
                .nombre("Cromo Test")
                .build();
        cromosRepository.save(cromo1);

        Cromos cromo2 = Cromos.builder()
                .nombre("Cromo Test")
                .build();
        cromosRepository.save(cromo2);

        // When & Then
        mockMvc.perform(get("/api/cromos/by-nombre")
                .param("nombre", "Cromo Test"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].nombre").value("Cromo Test"))
                .andExpect(jsonPath("$[1].nombre").value("Cromo Test"));
    }

    @Test
    public void testGetCromosAdquiridosByAlbumId() throws Exception {

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

        // When & Then
        mockMvc.perform(get("/api/cromos/{albumId}/cromos/adquiridos", album.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].nombre").value("Cromo Adquirido 1"))
                .andExpect(jsonPath("$[1].nombre").value("Cromo Adquirido 2"));
    }

    @Test
    public void testCreateCromos() throws Exception {

        // Given
        Cromos cromo = Cromos.builder()
                .nombre("Nuevo Cromo")
                .build();

        // Then
        mockMvc.perform(post("/api/cromos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cromo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Nuevo Cromo"));
    }

    @Test
    public void testUpdateCromosById() throws Exception {

        // Given
        Cromos cromo = Cromos.builder()
                .nombre("Cromo Original")
                .build();
        cromo = cromosRepository.save(cromo);

        // When
        cromo.setNombre("Cromo Actualizado");

        // Then
        mockMvc.perform(put("/api/cromos/{id}", cromo.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cromo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Cromo Actualizado"));
    }

    @Test
    public void testDeleteCromosById() throws Exception {

        // Given
        Cromos cromo = Cromos.builder()
                .nombre("Cromo a Eliminar")
                .build();
        cromo = cromosRepository.save(cromo);

        // Then
        mockMvc.perform(delete("/api/cromos/{id}", cromo.getId()))
                .andExpect(status().isOk());
    }
}