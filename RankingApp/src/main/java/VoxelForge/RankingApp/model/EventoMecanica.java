package VoxelForge.RankingApp.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "evento_mecanica")
public class EventoMecanica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEvento;

    @ManyToOne
    @JoinColumn(name = "id_persona")
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "id_mecanica")
    private Mecanica mecanica;

    @ManyToOne
    @JoinColumn(name = "id_temporada")
    private Temporada temporada;

    private LocalDateTime fecha;
    private Integer puntosOtorgados;

    // Getters y setters
    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Mecanica getMecanica() {
        return mecanica;
    }

    public void setMecanica(Mecanica mecanica) {
        this.mecanica = mecanica;
    }

    public Temporada getTemporada() {
        return temporada;
    }

    public void setTemporada(Temporada temporada) {
        this.temporada = temporada;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Integer getPuntosOtorgados() {
        return puntosOtorgados;
    }

    public void setPuntosOtorgados(Integer puntosOtorgados) {
        this.puntosOtorgados = puntosOtorgados;
    }
}
