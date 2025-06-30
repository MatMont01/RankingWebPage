// Archivo: src/main/java/VoxelForge/RankingApp/controller/LogroProyectoController.java
package VoxelForge.RankingApp.controller;

import VoxelForge.RankingApp.dto.LogroProyectoRequest;
import VoxelForge.RankingApp.model.Empleado;
import VoxelForge.RankingApp.model.LogroProyecto;
import VoxelForge.RankingApp.model.Temporada;
import VoxelForge.RankingApp.service.EmpleadoService;
import VoxelForge.RankingApp.service.LogroProyectoService;
import VoxelForge.RankingApp.service.TemporadaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/proyectos/logros")
public class LogroProyectoController {

    private final LogroProyectoService logroProyectoService;
    private final EmpleadoService empleadoService;
    private final TemporadaService temporadaService;

    public LogroProyectoController(LogroProyectoService logroProyectoService, EmpleadoService empleadoService, TemporadaService temporadaService) {
        this.logroProyectoService = logroProyectoService;
        this.empleadoService = empleadoService;
        this.temporadaService = temporadaService;
    }

    @PostMapping
    public ResponseEntity<?> registrarLogro(@RequestBody LogroProyectoRequest request) {
        try {
            Empleado empleado = empleadoService.findById(request.getIdEmpleado())
                    .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado"));
            Temporada temporadaActiva = temporadaService.findTemporadaActiva()
                    .orElseThrow(() -> new IllegalStateException("No hay una temporada activa."));

            LogroProyecto logro = new LogroProyecto();
            logro.setEmpleado(empleado);
            logro.setTemporada(temporadaActiva);
            logro.setNombreProyecto(request.getNombreProyecto());
            logro.setNombreLogro(request.getNombreLogro());
            logro.setPuntosOtorgados(request.getPuntosOtorgados());

            LogroProyecto result = logroProyectoService.registrarLogro(logro);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}