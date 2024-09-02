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
import org.springframework.web.bind.annotation.RestController;

import es.cic.curso.coleccionCartas.exception.ActualizarException;
import es.cic.curso.coleccionCartas.model.Album;
import es.cic.curso.coleccionCartas.service.AlbumService;

@RestController
<<<<<<< HEAD
@RequestMapping("/api/albums")
=======
@RequestMapping("/albums")
>>>>>>> 6e5026e7bc4cb5e57ab0376f51d11cbdaf20fdb5
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @GetMapping
    public List<Album> getAllAlbums() {
        return albumService.findAll();
    }

    @GetMapping("/{id}")
    public Album getAlbumById(@PathVariable Long id) {
        Album albumes = albumService.findById(id);
        if (albumes == null) {
            throw new ActualizarException("No se ha encontrado el album con id " + id);
        }
        return albumes;
    }

    @PostMapping
    public Album createAlbum(@RequestBody Album album) {
        return albumService.save(album);
    }

    @PutMapping("/{id}")
    public Album updateAlbumById(@PathVariable Long id, @RequestBody Album albums) {
        Album existingAlbum = albumService.findById(id);
        if (existingAlbum == null) {
            throw new ActualizarException("No se ha encontrado el cromo con id " + id);
        }
        albums.setId(id);
        return albumService.save(albums);
    }

    @DeleteMapping("/{id}")
    public void deleteAlbumById(@PathVariable Long id) {
        albumService.deleteById(id);
    }

}
