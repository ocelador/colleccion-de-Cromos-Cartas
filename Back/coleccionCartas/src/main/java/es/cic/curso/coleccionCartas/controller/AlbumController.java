package es.cic.curso.coleccionCartas.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    public ResponseEntity<Resource> getImage(@PathVariable Long id) {
        try {
            Path imagePath = albumService.getImagePath(id);
            Resource resource = new UrlResource(imagePath.toUri());
            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
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
