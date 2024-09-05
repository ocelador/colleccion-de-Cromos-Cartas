package es.cic.curso.coleccionCartas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.cic.curso.coleccionCartas.model.Album;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
}
