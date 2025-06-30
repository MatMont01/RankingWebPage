// Archivo: src/main/java/VoxelForge/RankingApp/model/CursoCompletado.java
package VoxelForge.RankingApp.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "curso_completado")
public class CursoCompletado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCursoCompletado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empleado", nullable = false)
    private Empleado empleado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_curso", nullable = false)
    private Curso curso;

    @Column(nullable = false)
    private LocalDate fechaCompletado;

    // Getters y Setters
    public Integer getIdCursoCompletado() {
        return idCursoCompletado;
    }

    public void setIdCursoCompletado(Integer idCursoCompletado) {
        this.idCursoCompletado = idCursoCompletado;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public LocalDate getFechaCompletado() {
        return fechaCompletado;
    }

    public void setFechaCompletado(LocalDate fechaCompletado) {
        this.fechaCompletado = fechaCompletado;
    }
}