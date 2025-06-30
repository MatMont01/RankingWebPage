// Archivo: src/main/java/VoxelForge/RankingApp/service/PuntajeService.java
package VoxelForge.RankingApp.service;

import VoxelForge.RankingApp.model.Empleado;
import VoxelForge.RankingApp.model.EventoPuntaje;
import VoxelForge.RankingApp.model.Puntaje;
import VoxelForge.RankingApp.model.Temporada;
import VoxelForge.RankingApp.repository.PuntajeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PuntajeService {

    private final PuntajeRepository puntajeRepository;
    private final EventoPuntajeService eventoPuntajeService;

    public PuntajeService(PuntajeRepository puntajeRepository, EventoPuntajeService eventoPuntajeService) {
        this.puntajeRepository = puntajeRepository;
        this.eventoPuntajeService = eventoPuntajeService;
    }

    public List<Puntaje> findAll() {
        return puntajeRepository.findAll();
    }

    public Optional<Puntaje> findById(Integer id) {
        return puntajeRepository.findById(id);
    }

    public List<Puntaje> findByTemporada(Integer idTemporada) {
        return puntajeRepository.findByTemporada_IdTemporadaOrderByPuntajeTotalDesc(idTemporada);
    }

    @Transactional
    public Puntaje save(Puntaje puntaje) {
        return puntajeRepository.save(puntaje);
    }

    @Transactional
    public void deleteById(Integer id) {
        puntajeRepository.deleteById(id);
    }

    /**
     * Método centralizado para añadir puntos a un empleado en una temporada específica.
     * Crea un evento de puntaje y actualiza el total.
     *
     * @param empleado     El empleado que recibe los puntos.
     * @param temporada    La temporada activa.
     * @param puntos       Los puntos a otorgar.
     * @param origen       De dónde provienen los puntos (ej. 'Curso Completado').
     * @param descripcion  Una descripción del evento.
     * @param idReferencia ID del objeto que originó los puntos (ej. id_curso_completado).
     */
    @Transactional
    public void otorgarPuntos(Empleado empleado, Temporada temporada, int puntos, String origen, String descripcion, Integer idReferencia) {
        if (puntos <= 0) {
            return; // No se otorgan puntos si no es una cantidad positiva.
        }

        // 1. Obtener o crear el registro de puntaje para el empleado en la temporada
        Puntaje puntaje = puntajeRepository.findByEmpleado_IdEmpleadoAndTemporada_IdTemporada(
                        empleado.getIdEmpleado(), temporada.getIdTemporada())
                .orElseGet(() -> {
                    Puntaje nuevoPuntaje = new Puntaje();
                    nuevoPuntaje.setEmpleado(empleado);
                    nuevoPuntaje.setTemporada(temporada);
                    nuevoPuntaje.setPuntajeTotal(0);
                    return nuevoPuntaje;
                });

        // 2. Actualizar el puntaje total
        puntaje.setPuntajeTotal(puntaje.getPuntajeTotal() + puntos);
        puntaje.setFechaActualizacion(LocalDateTime.now());
        puntajeRepository.save(puntaje);

        // 3. Registrar el evento que otorgó los puntos
        EventoPuntaje evento = new EventoPuntaje();
        evento.setEmpleado(empleado);
        evento.setTemporada(temporada);
        evento.setPuntosOtorgados(puntos);
        evento.setOrigenPuntos(origen);
        evento.setDescripcion(descripcion);
        evento.setIdReferencia(idReferencia);
        evento.setFecha(LocalDateTime.now());
        eventoPuntajeService.save(evento);
    }
}