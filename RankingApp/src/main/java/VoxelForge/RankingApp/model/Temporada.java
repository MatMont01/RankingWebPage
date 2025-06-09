package VoxelForge.RankingApp.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "temporada")
public class Temporada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTemporada;

    private String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String estado;

    // Getters y setters
    public Integer getIdTemporada() {
        return idTemporada;
    }

    public void setIdTemporada(Integer idTemporada) {
        this.idTemporada = idTemporada;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
