package VoxelForge.RankingApp.controller;

import VoxelForge.RankingApp.model.EventoMecanica;
import VoxelForge.RankingApp.service.EventoMecanicaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/eventos-mecanica")
public class EventoMecanicaController {

    private final EventoMecanicaService eventoMecanicaService;

    public EventoMecanicaController(EventoMecanicaService eventoMecanicaService) {
        this.eventoMecanicaService = eventoMecanicaService;
    }

    @GetMapping
    public List<EventoMecanica> getAll() {
        return eventoMecanicaService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<EventoMecanica> getById(@PathVariable Integer id) {
        return eventoMecanicaService.findById(id);
    }

    @PostMapping
    public EventoMecanica create(@RequestBody EventoMecanica eventoMecanica) {
        return eventoMecanicaService.save(eventoMecanica);
    }

    @PutMapping("/{id}")
    public EventoMecanica update(@PathVariable Integer id, @RequestBody EventoMecanica eventoMecanica) {
        eventoMecanica.setIdEvento(id);
        return eventoMecanicaService.save(eventoMecanica);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        eventoMecanicaService.deleteById(id);
    }
}
