package es.cic.curso.coleccionCartas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.cic.curso.coleccionCartas.model.Cromos;

@Repository
public interface CromosRepository extends JpaRepository<Cromos, Long> {
    List<Cromos> findByAlbumId(Long albumId);

    List<Cromos> findByNombre(String nombre);

    List<Cromos> findByAlbumIdAndAdquirido(Long albumId, boolean adquirido);
}
