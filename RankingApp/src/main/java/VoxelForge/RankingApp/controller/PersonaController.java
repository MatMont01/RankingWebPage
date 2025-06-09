package VoxelForge.RankingApp.controller;

import VoxelForge.RankingApp.model.Persona;
import VoxelForge.RankingApp.service.PersonaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {

    private final PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @GetMapping
    public List<Persona> getAll() {
        return personaService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Persona> getById(@PathVariable Integer id) {
        return personaService.findById(id);
    }

    @PostMapping
    public Persona create(@RequestBody Persona persona) {
        return personaService.save(persona);
    }

    @PutMapping("/{id}")
    public Persona update(@PathVariable Integer id, @RequestBody Persona persona) {
        persona.setIdPersona(id);
        return personaService.save(persona);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        personaService.deleteById(id);
    }
}
