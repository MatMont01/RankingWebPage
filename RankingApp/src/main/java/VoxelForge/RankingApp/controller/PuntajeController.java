package VoxelForge.RankingApp.controller;

import VoxelForge.RankingApp.model.Puntaje;
import VoxelForge.RankingApp.service.PuntajeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/puntajes")
public class PuntajeController {

    private final PuntajeService puntajeService;

    public PuntajeController(PuntajeService puntajeService) {
        this.puntajeService = puntajeService;
    }

    @GetMapping
    public List<Puntaje> getAll() {
        return puntajeService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Puntaje> getById(@PathVariable Integer id) {
        return puntajeService.findById(id);
    }

    @PostMapping
    public Puntaje create(@RequestBody Puntaje puntaje) {
        return puntajeService.save(puntaje);
    }

    @PutMapping("/{id}")
    public Puntaje update(@PathVariable Integer id, @RequestBody Puntaje puntaje) {
        puntaje.setIdPuntaje(id);
        return puntajeService.save(puntaje);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        puntajeService.deleteById(id);
    }
}
