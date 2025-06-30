// Archivo: src/main/java/VoxelForge/RankingApp/model/LogroProyecto.java
package VoxelForge.RankingApp.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "logro_proyecto")
public class LogroProyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLogroProyecto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empleado", nullable = false)
    private Empleado empleado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_temporada", nullable = false)
    private Temporada temporada;

    @Column(nullable = false, length = 150)
    private String nombreProyecto;

    @Column(nullable = false, length = 150)
    private String nombreLogro;

    @Column(nullable = false)
    private Integer puntosOtorgados;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fecha;

    // --- ¡AQUÍ ESTÁ LA CORRECCIÓN! ---
    @PrePersist
    protected void prePersist() {
        if (this.fecha == null) {
            this.fecha = LocalDateTime.now();
        }
    }

    // Getters y Setters... (el resto de la clase no cambia)
    public Integer getIdLogroProyecto() {
        return idLogroProyecto;
    }

    public void setIdLogroProyecto(Integer idLogroProyecto) {
        this.idLogroProyecto = idLogroProyecto;
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

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public String getNombreLogro() {
        return nombreLogro;
    }

    public void setNombreLogro(String nombreLogro) {
        this.nombreLogro = nombreLogro;
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
}