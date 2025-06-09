package VoxelForge.RankingApp.service;

import VoxelForge.RankingApp.model.Temporada;
import VoxelForge.RankingApp.repository.TemporadaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TemporadaService {

    private final TemporadaRepository temporadaRepository;

    public TemporadaService(TemporadaRepository temporadaRepository) {
        this.temporadaRepository = temporadaRepository;
    }

    public List<Temporada> findAll() {
        return temporadaRepository.findAll();
    }

    public Optional<Temporada> findById(Integer id) {
        return temporadaRepository.findById(id);
    }

    public Temporada save(Temporada temporada) {
        return temporadaRepository.save(temporada);
    }

    public void deleteById(Integer id) {
        temporadaRepository.deleteById(id);
    }
}
