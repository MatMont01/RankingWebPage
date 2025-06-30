// Archivo: src/main/java/VoxelForge/RankingApp/model/Empleado.java
package VoxelForge.RankingApp.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "empleado")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEmpleado;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(unique = true, length = 100)
    private String correo;

    @Column(unique = true, nullable = false, length = 50)
    private String usuario;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, updatable = false) // No necesitamos la definición de columna aquí
    private LocalDateTime fechaCreacion;

    // --- ¡AQUÍ ESTÁ LA CORRECCIÓN! ---
    @PrePersist
    protected void prePersist() {
        if (this.fechaCreacion == null) {
            this.fechaCreacion = LocalDateTime.now();
        }
    }

    // Getters y Setters
    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}