package es.cic.curso.coleccionCartas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.coleccionCartas.model.Cromos;
import es.cic.curso.coleccionCartas.repository.CromosRepository;

@Service
@Transactional
public class CromosService {

    @Autowired
    private CromosRepository cromosRepository;

    @Transactional(readOnly = true)
    public List<Cromos> findAll() {
        return cromosRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Cromos findById(Long id) {
        return cromosRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Cromos> getCromosByNombre(String nombre) {
        return cromosRepository.findByNombre(nombre);
    }

    @Transactional(readOnly = true)
    public List<Cromos> getCromosAdquiridosByAlbumId(Long albumId) {
        return cromosRepository.findByAlbumIdAndAdquirido(albumId, true);
    }

    public Cromos save(Cromos cromos) {
        return cromosRepository.save(cromos);
    }

    public void deleteById(Long id) {
        cromosRepository.deleteById(id);
    }
}