// Archivo: src/main/java/VoxelForge/RankingApp/dto/LoginRequest.java
package VoxelForge.RankingApp.dto;

// DTO para recibir las credenciales de inicio de sesión
public class LoginRequest {
    private String usuario;
    private String password;

    // Getters y Setters
    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}