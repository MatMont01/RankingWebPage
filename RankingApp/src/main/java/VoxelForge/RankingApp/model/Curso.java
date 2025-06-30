// Archivo: src/main/java/VoxelForge/RankingApp/model/Curso.java
package VoxelForge.RankingApp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "curso")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCurso;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(nullable = false)
    private Integer puntosAlCompletar;

    // Getters y Setters
    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getPuntosAlCompletar() {
        return puntosAlCompletar;
    }

    public void setPuntosAlCompletar(Integer puntosAlCompletar) {
        this.puntosAlCompletar = puntosAlCompletar;
    }
}