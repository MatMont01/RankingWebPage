package VoxelForge.RankingApp.controller;

import VoxelForge.RankingApp.model.EventoPuntaje;
import VoxelForge.RankingApp.service.EventoPuntajeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eventos-puntaje")
public class EventoPuntajeController {

    private final EventoPuntajeService eventoPuntajeService;

    public EventoPuntajeController(EventoPuntajeService eventoPuntajeService) {
        this.eventoPuntajeService = eventoPuntajeService;
    }

    // Endpoint para obtener el historial de todos (para un admin, por ejemplo)
    @GetMapping
    public List<EventoPuntaje> getAllEventos() {
        return eventoPuntajeService.findAll();
    }

    // ¡NUEVO ENDPOINT! Para obtener el historial de un solo empleado
    @GetMapping("/empleado/{idEmpleado}")
    public ResponseEntity<List<EventoPuntaje>> getHistorialPorEmpleado(@PathVariable Integer idEmpleado) {
        List<EventoPuntaje> historial = eventoPuntajeService.findByEmpleadoId(idEmpleado);
        return ResponseEntity.ok(historial);
    }
}