// Archivo: src/main/java/VoxelForge/RankingApp/service/LogroProyectoService.java
package VoxelForge.RankingApp.service;

import VoxelForge.RankingApp.model.*;
import VoxelForge.RankingApp.repository.LogroProyectoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LogroProyectoService {

    private final LogroProyectoRepository logroProyectoRepository;
    private final PuntajeService puntajeService;

    public LogroProyectoService(LogroProyectoRepository logroProyectoRepository, PuntajeService puntajeService) {
        this.logroProyectoRepository = logroProyectoRepository;
        this.puntajeService = puntajeService;
    }

    @Transactional
    public LogroProyecto registrarLogro(LogroProyecto logro) {
        LogroProyecto saved = logroProyectoRepository.save(logro);

        String descripcion = "Logro '" + logro.getNombreLogro() + "' en el proyecto '" + logro.getNombreProyecto() + "'";

        puntajeService.otorgarPuntos(
                logro.getEmpleado(),
                logro.getTemporada(), // Asume que la temporada viene en el objeto
                logro.getPuntosOtorgados(),
                "Logro de Proyecto",
                descripcion,
                saved.getIdLogroProyecto()
        );

        return saved;
    }
}