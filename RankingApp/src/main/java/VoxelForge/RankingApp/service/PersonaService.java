package VoxelForge.RankingApp.service;

import VoxelForge.RankingApp.model.Persona;
import VoxelForge.RankingApp.repository.PersonaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    private final PersonaRepository personaRepository;

    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public List<Persona> findAll() {
        return personaRepository.findAll();
    }

    public Optional<Persona> findById(Integer id) {
        return personaRepository.findById(id);
    }

    public Persona save(Persona persona) {
        return personaRepository.save(persona);
    }

    public void deleteById(Integer id) {
        personaRepository.deleteById(id);
    }
}
