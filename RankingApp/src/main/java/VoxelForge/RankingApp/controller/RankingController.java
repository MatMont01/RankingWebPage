// Archivo: src/main/java/VoxelForge/RankingApp/controller/RankingController.java
package VoxelForge.RankingApp.controller;

import VoxelForge.RankingApp.model.Puntaje;
import VoxelForge.RankingApp.service.PuntajeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ranking")
public class RankingController {

    private final PuntajeService puntajeService;

    public RankingController(PuntajeService puntajeService) {
        this.puntajeService = puntajeService;
    }

    @GetMapping("/temporada/{idTemporada}")
    public ResponseEntity<List<Puntaje>> getRankingPorTemporada(@PathVariable Integer idTemporada) {
        List<Puntaje> ranking = puntajeService.findByTemporada(idTemporada);
        return ResponseEntity.ok(ranking);
    }
}