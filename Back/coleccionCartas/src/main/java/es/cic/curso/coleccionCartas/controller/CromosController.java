package es.cic.curso.coleccionCartas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.cic.curso.coleccionCartas.exception.ActualizarException;
import es.cic.curso.coleccionCartas.model.Cromos;
import es.cic.curso.coleccionCartas.service.CromosService;

@RestController
@RequestMapping("/api/cromos")
public class CromosController {

    @Autowired
    private CromosService cromosService;

    @GetMapping
    public List<Cromos> getAllCromos() {
        return cromosService.findAll();
    }

    @GetMapping("/{id}")
    public Cromos getCromosById(@PathVariable Long id) {
        Cromos cromos = cromosService.findById(id);
        if (cromos == null) {
            throw new ActualizarException("No se ha encontrado el cromo con id " + id);
        }
        return cromos;
    }

    @GetMapping("/by-nombre")
    public List<Cromos> getCromosByNombre(@RequestParam String nombre) {
        return cromosService.getCromosByNombre(nombre);
    }

    @GetMapping("/{albumId}/cromos/adquiridos")
    public List<Cromos> getCromosAdquiridosByAlbumId(@PathVariable Long albumId) {
        return cromosService.getCromosAdquiridosByAlbumId(albumId);
    }

    @PostMapping
    public Cromos createCromos(@RequestBody Cromos cromos) {
        return cromosService.save(cromos);
    }

    @PutMapping("/{id}")
    public Cromos updateCromosById(@PathVariable Long id, @RequestBody Cromos cromos) {
        Cromos existingCromos = cromosService.findById(id);
        if (existingCromos == null) {
            throw new ActualizarException("No se ha encontrado el cromo con id " + id);
        }
        cromos.setId(id);
        return cromosService.save(cromos);
    }

    @DeleteMapping("/{id}")
    public void deleteCromosById(@PathVariable Long id) {
        cromosService.deleteById(id);
    }
}