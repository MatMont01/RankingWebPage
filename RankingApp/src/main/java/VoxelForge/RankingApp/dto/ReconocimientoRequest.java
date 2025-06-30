// Archivo: src/main/java/VoxelForge/RankingApp/dto/ReconocimientoRequest.java
package VoxelForge.RankingApp.dto;

public class ReconocimientoRequest {
    private Integer idOtorgante;
    private Integer idReceptor;
    private String motivo;
    private Integer puntosOtorgados = 5; // Valor por defecto

    // Getters y Setters
    public Integer getIdOtorgante() {
        return idOtorgante;
    }

    public void setIdOtorgante(Integer idOtorgante) {
        this.idOtorgante = idOtorgante;
    }

    public Integer getIdReceptor() {
        return idReceptor;
    }

    public void setIdReceptor(Integer idReceptor) {
        this.idReceptor = idReceptor;
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
}