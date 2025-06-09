package VoxelForge.RankingApp.service;

import VoxelForge.RankingApp.model.EventoMecanica;
import VoxelForge.RankingApp.repository.EventoMecanicaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoMecanicaService {

    private final EventoMecanicaRepository eventoMecanicaRepository;

    public EventoMecanicaService(EventoMecanicaRepository eventoMecanicaRepository) {
        this.eventoMecanicaRepository = eventoMecanicaRepository;
    }

    public List<EventoMecanica> findAll() {
        return eventoMecanicaRepository.findAll();
    }

    public Optional<EventoMecanica> findById(Integer id) {
        return eventoMecanicaRepository.findById(id);
    }

    public EventoMecanica save(EventoMecanica eventoMecanica) {
        return eventoMecanicaRepository.save(eventoMecanica);
    }

    public void deleteById(Integer id) {
        eventoMecanicaRepository.deleteById(id);
    }
}
