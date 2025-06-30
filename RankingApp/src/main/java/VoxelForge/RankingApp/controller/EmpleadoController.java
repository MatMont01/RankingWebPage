// Archivo: src/main/java/VoxelForge/RankingApp/controller/EmpleadoController.java
package VoxelForge.RankingApp.controller;

import VoxelForge.RankingApp.model.Empleado;
import VoxelForge.RankingApp.model.EventoPuntaje;
import VoxelForge.RankingApp.service.EmpleadoService;
import VoxelForge.RankingApp.service.EventoPuntajeService; // <-- 1. IMPORTAMOS EL SERVICIO
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    private final EmpleadoService empleadoService;
    private final EventoPuntajeService eventoPuntajeService; // <-- 2. AÑADIMOS EL CAMPO

    // 3. ACTUALIZAMOS EL CONSTRUCTOR PARA INYECTAR AMBOS SERVICIOS
    public EmpleadoController(EmpleadoService empleadoService, EventoPuntajeService eventoPuntajeService) {
        this.empleadoService = empleadoService;
        this.eventoPuntajeService = eventoPuntajeService;
    }

    @GetMapping
    public List<Empleado> getAll() {
        return empleadoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> getById(@PathVariable Integer id) {
        return empleadoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // --- ¡NUEVO ENDPOINT AÑADIDO! ---
    // Endpoint para buscar un empleado por su nombre de usuario
    @GetMapping("/usuario/{usuario}")
    public ResponseEntity<Empleado> getByUsuario(@PathVariable String usuario) {
        return empleadoService.findByUsuario(usuario)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    public ResponseEntity<Empleado> update(@PathVariable Integer id, @RequestBody Empleado empleadoDetails) {
        return empleadoService.findById(id)
                .map(empleado -> {
                    empleado.setNombre(empleadoDetails.getNombre());
                    empleado.setCorreo(empleadoDetails.getCorreo());
                    // No se debería poder actualizar el usuario o la contraseña por aquí
                    // Para actualizar la contraseña se debería usar un endpoint específico y seguro
                    return ResponseEntity.ok(empleadoService.save(empleado)); // OJO: esto re-hasheará la contraseña si se envía
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!empleadoService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        empleadoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/historial")
    public ResponseEntity<List<EventoPuntaje>> getHistorialDeEmpleado(@PathVariable("id") Integer idEmpleado) {
        List<EventoPuntaje> historial = eventoPuntajeService.findByEmpleadoId(idEmpleado);
        return ResponseEntity.ok(historial);
    }
}