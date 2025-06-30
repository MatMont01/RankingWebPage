// Archivo: src/main/java/VoxelForge/RankingApp/controller/TemporadaController.java
package VoxelForge.RankingApp.controller;

import VoxelForge.RankingApp.model.Temporada;
import VoxelForge.RankingApp.service.TemporadaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/temporadas")
public class TemporadaController {

    private final TemporadaService temporadaService;

    public TemporadaController(TemporadaService temporadaService) {
        this.temporadaService = temporadaService;
    }

    @GetMapping
    public List<Temporada> getAll() {
        return temporadaService.findAll();
    }

    @GetMapping("/activa")
    public ResponseEntity<Temporada> getTemporadaActiva() {
        return temporadaService.findTemporadaActiva()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Temporada create(@RequestBody Temporada temporada) {
        return temporadaService.save(temporada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Temporada> update(@PathVariable Integer id, @RequestBody Temporada temporadaDetails) {
        return temporadaService.findById(id)
                .map(temporada -> {
                    temporada.setNombre(temporadaDetails.getNombre());
                    temporada.setFechaInicio(temporadaDetails.getFechaInicio());
                    temporada.setFechaFin(temporadaDetails.getFechaFin());
                    temporada.setEstado(temporadaDetails.getEstado());
                    return ResponseEntity.ok(temporadaService.save(temporada));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        temporadaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}