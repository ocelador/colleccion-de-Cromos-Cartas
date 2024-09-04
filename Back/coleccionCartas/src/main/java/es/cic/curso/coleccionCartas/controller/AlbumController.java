package es.cic.curso.coleccionCartas.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import es.cic.curso.coleccionCartas.exception.ActualizarException;
import es.cic.curso.coleccionCartas.model.Album;
import es.cic.curso.coleccionCartas.model.Cromos;
import es.cic.curso.coleccionCartas.service.AlbumService;

@RestController
@RequestMapping("/api/albums")
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

    @GetMapping("/{id}/cromos")
    public List<Cromos> getCromosByAlbumId(@PathVariable Long id) {
        List<Cromos> cromos = albumService.getCromosByAlbumId(id);
        return cromos;
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<Resource> getAlbumImage(@PathVariable Long id) {
        try {
            Resource resource = albumService.getAlbumImage(id);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Album createAlbum(@RequestBody Album album) {
        return albumService.save(album);
    }

    @PostMapping("/upload")
    public Album crearAlbumWithImage(@RequestParam("file") MultipartFile file,
            @RequestParam("nombre") String nombre,
            @RequestParam("descripcion") String descripcion) {

        Album album = new Album();
        album.setNombre(nombre);
        album.setDescripcion(descripcion);

        try {
            return albumService.saveAlbumWithImage(album, file);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
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
