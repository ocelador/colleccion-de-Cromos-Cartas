package es.cic.curso.coleccionCartas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
