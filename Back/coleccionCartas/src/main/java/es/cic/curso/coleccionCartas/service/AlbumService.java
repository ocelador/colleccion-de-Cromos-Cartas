package es.cic.curso.coleccionCartas.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import es.cic.curso.coleccionCartas.model.Album;
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

    public Album save(Album album) {
        return albumRepository.save(album);
    }

    public void deleteById(Long id) {
        albumRepository.deleteById(id);
    }

    public Album saveAlbumWithImage(Album album, MultipartFile file) throws IOException {

        if (!file.isEmpty()) {
            String uploadDir = "/home/usuario/images/";
            File uploadDirFile = new File(uploadDir);
            if (!uploadDirFile.exists()) {
                uploadDirFile.mkdirs();
            }

            String filePath = uploadDir + file.getOriginalFilename();
            File dest = new File(filePath);
            file.transferTo(dest);

            album.setImagen(filePath);
        }

        return albumRepository.save(album);
    }
}
