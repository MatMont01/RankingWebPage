// Archivo: src/main/java/VoxelForge/RankingApp/dto/LogroProyectoRequest.java
package VoxelForge.RankingApp.dto;

public class LogroProyectoRequest {
    private Integer idEmpleado;
    private String nombreProyecto;
    private String nombreLogro;
    private Integer puntosOtorgados;

    // Getters y Setters
    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public String getNombreLogro() {
        return nombreLogro;
    }

    public void setNombreLogro(String nombreLogro) {
        this.nombreLogro = nombreLogro;
    }

    public Integer getPuntosOtorgados() {
        return puntosOtorgados;
    }

    public void setPuntosOtorgados(Integer puntosOtorgados) {
        this.puntosOtorgados = puntosOtorgados;
    }
}