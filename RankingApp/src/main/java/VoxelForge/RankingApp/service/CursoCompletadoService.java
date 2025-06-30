// Archivo: src/main/java/VoxelForge/RankingApp/service/CursoCompletadoService.java
package VoxelForge.RankingApp.service;

import VoxelForge.RankingApp.model.*;
import VoxelForge.RankingApp.repository.CursoCompletadoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CursoCompletadoService {

    private final CursoCompletadoRepository cursoCompletadoRepository;
    private final PuntajeService puntajeService;
    private final TemporadaService temporadaService;

    public CursoCompletadoService(CursoCompletadoRepository cursoCompletadoRepository, PuntajeService puntajeService, TemporadaService temporadaService) {
        this.cursoCompletadoRepository = cursoCompletadoRepository;
        this.puntajeService = puntajeService;
        this.temporadaService = temporadaService;
    }

    @Transactional
    public CursoCompletado completarCurso(CursoCompletado cursoCompletado) {
        CursoCompletado saved = cursoCompletadoRepository.save(cursoCompletado);

        // Otorgar puntos al completar el curso
        Temporada temporadaActiva = temporadaService.findTemporadaActiva()
                .orElseThrow(() -> new IllegalStateException("No hay una temporada activa para asignar puntos."));

        Curso curso = saved.getCurso();
        String descripcion = "Completado del curso: " + curso.getNombre();

        puntajeService.otorgarPuntos(
                saved.getEmpleado(),
                temporadaActiva,
                curso.getPuntosAlCompletar(),
                "Curso Completado",
                descripcion,
                saved.getIdCursoCompletado()
        );

        return saved;
    }
}