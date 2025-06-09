package VoxelForge.RankingApp.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "puntaje")
public class Puntaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPuntaje;

    @ManyToOne
    @JoinColumn(name = "id_persona")
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "id_temporada")
    private Temporada temporada;

    private Integer puntaje;
    private LocalDateTime fechaActualizacion;

    // Getters y setters
    public Integer getIdPuntaje() {
        return idPuntaje;
    }

    public void setIdPuntaje(Integer idPuntaje) {
        this.idPuntaje = idPuntaje;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Temporada getTemporada() {
        return temporada;
    }

    public void setTemporada(Temporada temporada) {
        this.temporada = temporada;
    }

    public Integer getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Integer puntaje) {
        this.puntaje = puntaje;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
}
