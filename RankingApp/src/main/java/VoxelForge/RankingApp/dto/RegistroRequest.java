// Archivo: src/main/java/VoxelForge/RankingApp/dto/RegistroRequest.java
package VoxelForge.RankingApp.dto;

// DTO para registrar un nuevo empleado
public class RegistroRequest {
    private String nombre;
    private String correo;
    private String usuario;
    private String password;

    // Getters y Setters
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
}