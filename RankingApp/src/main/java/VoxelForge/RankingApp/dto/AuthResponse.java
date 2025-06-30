// Archivo: src/main/java/VoxelForge/RankingApp/dto/AuthResponse.java
package VoxelForge.RankingApp.dto;

// DTO para devolver el token de autenticación
public class AuthResponse {
    private String token;
    private String usuario;

    public AuthResponse(String token, String usuario) {
        this.token = token;
        this.usuario = usuario;
    }

    // Getters y Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}