// Archivo: src/main/java/VoxelForge/RankingApp/model/EventoPuntaje.java
package VoxelForge.RankingApp.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "evento_puntaje")
public class EventoPuntaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEvento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empleado", nullable = false)
    private Empleado empleado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_temporada", nullable = false)
    private Temporada temporada;

    @Column(nullable = false, length = 100)
    private String origenPuntos;

    @Column(nullable = false)
    private Integer puntosOtorgados;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fecha;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private Integer idReferencia;

    // Getters y Setters
    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
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

    public String getOrigenPuntos() {
        return origenPuntos;
    }

    public void setOrigenPuntos(String origenPuntos) {
        this.origenPuntos = origenPuntos;
    }

    public Integer getPuntosOtorgados() {
        return puntosOtorgados;
    }

    public void setPuntosOtorgados(Integer puntosOtorgados) {
        this.puntosOtorgados = puntosOtorgados;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getIdReferencia() {
        return idReferencia;
    }

    public void setIdReferencia(Integer idReferencia) {
        this.idReferencia = idReferencia;
    }
}