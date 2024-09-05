package es.cic.curso.coleccionCartas.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cromos {
    @Id
    @SequenceGenerator(name = "cromos_seq", sequenceName = "cromos_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cromos_seq")
    private Long id;
    private String nombre;
    private String descripcion;
    private int anio;
    private double valor;
    private String rareza;
    private Boolean adquirido;

    @ManyToOne
    @JoinColumn(name = "album_id")
    @JsonBackReference
    private Album album;
}