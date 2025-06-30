// Archivo: src/main/java/VoxelForge/RankingApp/model/Puntaje.java
package VoxelForge.RankingApp.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "puntaje")
public class Puntaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPuntaje;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empleado", nullable = false)
    private Empleado empleado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_temporada", nullable = false)
    private Temporada temporada;

    @Column(nullable = false)
    private Integer puntajeTotal = 0;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaActualizacion;

    // Getters y Setters
    public Integer getIdPuntaje() {
        return idPuntaje;
    }

    public void setIdPuntaje(Integer idPuntaje) {
        this.idPuntaje = idPuntaje;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Temporada getTemporada() {
        return temporada;
    }

    public void setTemporada(Temporada temporada) {
        this.temporada = temporada;
    }

    public Integer getPuntajeTotal() {
        return puntajeTotal;
    }

    public void setPuntajeTotal(Integer puntajeTotal) {
        this.puntajeTotal = puntajeTotal;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
}