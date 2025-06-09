package VoxelForge.RankingApp.controller;

import VoxelForge.RankingApp.model.Temporada;
import VoxelForge.RankingApp.service.TemporadaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{id}")
    public Optional<Temporada> getById(@PathVariable Integer id) {
        return temporadaService.findById(id);
    }

    @PostMapping
    public Temporada create(@RequestBody Temporada temporada) {
        return temporadaService.save(temporada);
    }

    @PutMapping("/{id}")
    public Temporada update(@PathVariable Integer id, @RequestBody Temporada temporada) {
        temporada.setIdTemporada(id);
        return temporadaService.save(temporada);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        temporadaService.deleteById(id);
    }
}
