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

import es.cic.curso.coleccionCartas.model.Cromos;
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
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        cromosRepository.deleteAll();
    }

    @Test
    public void testGetAllCromos() throws Exception {
        Cromos cromo1 = new Cromos();
        cromo1.setNombre("Cromo 1");
        cromosRepository.save(cromo1);

        Cromos cromo2 = new Cromos();
        cromo2.setNombre("Cromo 2");
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
        Cromos cromo = new Cromos();
        cromo.setNombre("Cromo Test");
        cromo = cromosRepository.save(cromo);

        // Then
        mockMvc.perform(get("/api/cromos/{id}", cromo.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Cromo Test"));
    }

    @Test
    public void testCreateCromos() throws Exception {

        // Given
        Cromos cromo = new Cromos();
        cromo.setNombre("Nuevo Cromo");

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
        Cromos cromo = new Cromos();
        cromo.setNombre("Cromo Original");
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
        Cromos cromo = new Cromos();
        cromo.setNombre("Cromo a Eliminar");
        cromo = cromosRepository.save(cromo);

        // Then
        mockMvc.perform(delete("/api/cromos/{id}", cromo.getId()))
                .andExpect(status().isOk());
    }
}