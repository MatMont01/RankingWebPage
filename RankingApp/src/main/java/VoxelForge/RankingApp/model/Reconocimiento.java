// Archivo: src/main/java/VoxelForge/RankingApp/model/Reconocimiento.java
package VoxelForge.RankingApp.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reconocimiento")
public class Reconocimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReconocimiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_otorgante", nullable = false)
    private Empleado otorgante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_receptor", nullable = false)
    private Empleado receptor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_temporada", nullable = false)
    private Temporada temporada;

    @Column(columnDefinition = "TEXT")
    private String motivo;

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
    public Integer getIdReconocimiento() {
        return idReconocimiento;
    }

    public void setIdReconocimiento(Integer idReconocimiento) {
        this.idReconocimiento = idReconocimiento;
    }

    public Empleado getOtorgante() {
        return otorgante;
    }

    public void setOtorgante(Empleado otorgante) {
        this.otorgante = otorgante;
    }

    public Empleado getReceptor() {
        return receptor;
    }

    public void setReceptor(Empleado receptor) {
        this.receptor = receptor;
    }

    public Temporada getTemporada() {
        return temporada;
    }

    public void setTemporada(Temporada temporada) {
        this.temporada = temporada;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
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