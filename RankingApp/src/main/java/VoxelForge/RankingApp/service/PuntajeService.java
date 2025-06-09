package VoxelForge.RankingApp.service;

import VoxelForge.RankingApp.model.Puntaje;
import VoxelForge.RankingApp.repository.PuntajeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PuntajeService {

    private final PuntajeRepository puntajeRepository;

    public PuntajeService(PuntajeRepository puntajeRepository) {
        this.puntajeRepository = puntajeRepository;
    }

    public List<Puntaje> findAll() {
        return puntajeRepository.findAll();
    }

    public Optional<Puntaje> findById(Integer id) {
        return puntajeRepository.findById(id);
    }

    public Puntaje save(Puntaje puntaje) {
        return puntajeRepository.save(puntaje);
    }

    public void deleteById(Integer id) {
        puntajeRepository.deleteById(id);
    }
}
