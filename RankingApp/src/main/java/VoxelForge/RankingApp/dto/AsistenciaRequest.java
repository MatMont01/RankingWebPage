package VoxelForge.RankingApp.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class AsistenciaRequest {
    private Integer idEmpleado;
    private LocalDate fecha;
    private LocalTime horaLlegada;
    private String estadoPuntualidad;

    // Getters y Setters
    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(LocalTime horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public String getEstadoPuntualidad() {
        return estadoPuntualidad;
    }

    public void setEstadoPuntualidad(String estadoPuntualidad) {
        this.estadoPuntualidad = estadoPuntualidad;
    }
}