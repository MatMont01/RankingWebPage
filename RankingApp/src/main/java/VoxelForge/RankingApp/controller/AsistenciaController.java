package VoxelForge.RankingApp.controller;

import VoxelForge.RankingApp.dto.AsistenciaRequest;
import VoxelForge.RankingApp.model.Asistencia;
import VoxelForge.RankingApp.model.Empleado;
import VoxelForge.RankingApp.service.AsistenciaService;
import VoxelForge.RankingApp.service.EmpleadoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/asistencia")
public class AsistenciaController {

    private final AsistenciaService asistenciaService;
    private final EmpleadoService empleadoService; // Inyectamos EmpleadoService

    public AsistenciaController(AsistenciaService asistenciaService, EmpleadoService empleadoService) {
        this.asistenciaService = asistenciaService;
        this.empleadoService = empleadoService;
    }

    @PostMapping
    public ResponseEntity<?> registrarAsistencia(@RequestBody AsistenciaRequest request) {
        try {
            // Buscamos el empleado usando el ID que viene en el DTO
            Empleado empleado = empleadoService.findById(request.getIdEmpleado())
                    .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado con ID: " + request.getIdEmpleado()));

            // Creamos la entidad Asistencia completa
            Asistencia nuevaAsistencia = new Asistencia();
            nuevaAsistencia.setEmpleado(empleado);
            nuevaAsistencia.setFecha(request.getFecha());
            nuevaAsistencia.setHoraLlegada(request.getHoraLlegada());
            nuevaAsistencia.setEstadoPuntualidad(request.getEstadoPuntualidad());

            // Guardamos la entidad completa
            Asistencia asistenciaGuardada = asistenciaService.save(nuevaAsistencia);

            return new ResponseEntity<>(asistenciaGuardada, HttpStatus.CREATED);

        } catch (Exception e) {
            // Esto atrapará el error si el empleado no se encuentra o si hay una violación de constraint (duplicado)
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}