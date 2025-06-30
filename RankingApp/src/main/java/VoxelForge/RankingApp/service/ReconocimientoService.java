// Archivo: src/main/java/VoxelForge/RankingApp/service/ReconocimientoService.java
package VoxelForge.RankingApp.service;

import VoxelForge.RankingApp.model.*;
import VoxelForge.RankingApp.repository.ReconocimientoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReconocimientoService {

    private final ReconocimientoRepository reconocimientoRepository;
    private final PuntajeService puntajeService;
    private final TemporadaService temporadaService;

    public ReconocimientoService(ReconocimientoRepository reconocimientoRepository, PuntajeService puntajeService, TemporadaService temporadaService) {
        this.reconocimientoRepository = reconocimientoRepository;
        this.puntajeService = puntajeService;
        this.temporadaService = temporadaService;
    }

    @Transactional
    public Reconocimiento darReconocimiento(Reconocimiento reconocimiento) {
        if (reconocimiento.getOtorgante().getIdEmpleado().equals(reconocimiento.getReceptor().getIdEmpleado())) {
            throw new IllegalArgumentException("Un empleado no puede darse un reconocimiento a sí mismo.");
        }

        Reconocimiento saved = reconocimientoRepository.save(reconocimiento);

        Temporada temporadaActiva = temporadaService.findTemporadaActiva()
                .orElseThrow(() -> new IllegalStateException("No hay una temporada activa para asignar puntos."));

        String descripcion = "Reconocimiento de " + reconocimiento.getOtorgante().getNombre() + ": " + reconocimiento.getMotivo();

        puntajeService.otorgarPuntos(
                reconocimiento.getReceptor(),
                temporadaActiva,
                reconocimiento.getPuntosOtorgados(),
                "Reconocimiento",
                descripcion,
                saved.getIdReconocimiento()
        );

        return saved;
    }
}