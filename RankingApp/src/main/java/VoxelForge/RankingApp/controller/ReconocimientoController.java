// Archivo: src/main/java/VoxelForge/RankingApp/controller/ReconocimientoController.java
package VoxelForge.RankingApp.controller;

import VoxelForge.RankingApp.dto.ReconocimientoRequest;
import VoxelForge.RankingApp.model.Empleado;
import VoxelForge.RankingApp.model.Reconocimiento;
import VoxelForge.RankingApp.model.Temporada;
import VoxelForge.RankingApp.service.EmpleadoService;
import VoxelForge.RankingApp.service.ReconocimientoService;
import VoxelForge.RankingApp.service.TemporadaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reconocimientos")
public class ReconocimientoController {

    private final ReconocimientoService reconocimientoService;
    private final EmpleadoService empleadoService;
    private final TemporadaService temporadaService;

    public ReconocimientoController(ReconocimientoService reconocimientoService, EmpleadoService empleadoService, TemporadaService temporadaService) {
        this.reconocimientoService = reconocimientoService;
        this.empleadoService = empleadoService;
        this.temporadaService = temporadaService;
    }

    @PostMapping
    public ResponseEntity<?> darReconocimiento(@RequestBody ReconocimientoRequest request) {
        try {
            Empleado otorgante = empleadoService.findById(request.getIdOtorgante())
                    .orElseThrow(() -> new IllegalArgumentException("Empleado otorgante no encontrado"));
            Empleado receptor = empleadoService.findById(request.getIdReceptor())
                    .orElseThrow(() -> new IllegalArgumentException("Empleado receptor no encontrado"));
            Temporada temporadaActiva = temporadaService.findTemporadaActiva()
                    .orElseThrow(() -> new IllegalStateException("No hay una temporada activa."));

            Reconocimiento reconocimiento = new Reconocimiento();
            reconocimiento.setOtorgante(otorgante);
            reconocimiento.setReceptor(receptor);
            reconocimiento.setTemporada(temporadaActiva);
            reconocimiento.setMotivo(request.getMotivo());
            reconocimiento.setPuntosOtorgados(request.getPuntosOtorgados());

            Reconocimiento result = reconocimientoService.darReconocimiento(reconocimiento);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}