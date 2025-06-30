// Archivo: src/main/java/VoxelForge/RankingApp/dto/CursoCompletadoRequest.java
package VoxelForge.RankingApp.dto;

import java.time.LocalDate;

public class CursoCompletadoRequest {
    private Integer idEmpleado;
    private Integer idCurso;
    private LocalDate fechaCompletado;

    // Getters y Setters
    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public LocalDate getFechaCompletado() {
        return fechaCompletado;
    }

    public void setFechaCompletado(LocalDate fechaCompletado) {
        this.fechaCompletado = fechaCompletado;
    }
}