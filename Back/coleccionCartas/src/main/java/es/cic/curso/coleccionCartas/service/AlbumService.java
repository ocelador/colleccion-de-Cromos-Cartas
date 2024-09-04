package es.cic.curso.coleccionCartas.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import es.cic.curso.coleccionCartas.model.Album;
import es.cic.curso.coleccionCartas.model.Cromos;
import es.cic.curso.coleccionCartas.repository.AlbumRepository;

@Service
@Transactional
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Transactional(readOnly = true)
    public List<Album> findAll() {
        return albumRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Album findById(Long id) {
        return albumRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Cromos> getCromosByAlbumId(Long id) {
        Album album = albumRepository.findById(id).orElse(null);
        if (album != null) {
            return album.getCromos();
        }
        return null;
    }

    public Resource getAlbumImage(Long albumId) throws IOException {
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new IllegalArgumentException("Album not found"));

        Path filePath = Paths.get(album.getImagen());
        Resource resource = new UrlResource(filePath.toUri());

        if (resource.exists() || resource.isReadable()) {
            return resource;
        } else {
            throw new IOException("Could not read file: " + filePath);
        }
    }

    public Album save(Album album) {
        return albumRepository.save(album);
    }

    public void deleteById(Long id) {
        albumRepository.deleteById(id);
    }

    public Album saveAlbumWithImage(Album album, MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            String uploadDir = Paths.get("images").toAbsolutePath().toString();
            File uploadDirFile = new File(uploadDir);
            if (!uploadDirFile.exists()) {
                uploadDirFile.mkdirs();
            }

            String filePath = uploadDir + File.separator + file.getOriginalFilename();
            File dest = new File(filePath);
            file.transferTo(dest);

            album.setImagen(filePath);
        }

        return albumRepository.save(album);
    }
}
