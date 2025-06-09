package VoxelForge.RankingApp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "mecanica")
public class Mecanica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMecanica;

    private String nombre;
    private String descripcion;

    // Getters y setters
    public Integer getIdMecanica() {
        return idMecanica;
    }

    public void setIdMecanica(Integer idMecanica) {
        this.idMecanica = idMecanica;
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
}
