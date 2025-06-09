package VoxelForge.RankingApp.controller;

import VoxelForge.RankingApp.model.Mecanica;
import VoxelForge.RankingApp.service.MecanicaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mecanicas")
public class MecanicaController {

    private final MecanicaService mecanicaService;

    public MecanicaController(MecanicaService mecanicaService) {
        this.mecanicaService = mecanicaService;
    }

    @GetMapping
    public List<Mecanica> getAll() {
        return mecanicaService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Mecanica> getById(@PathVariable Integer id) {
        return mecanicaService.findById(id);
    }

    @PostMapping
    public Mecanica create(@RequestBody Mecanica mecanica) {
        return mecanicaService.save(mecanica);
    }

    @PutMapping("/{id}")
    public Mecanica update(@PathVariable Integer id, @RequestBody Mecanica mecanica) {
        mecanica.setIdMecanica(id);
        return mecanicaService.save(mecanica);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        mecanicaService.deleteById(id);
    }
}
