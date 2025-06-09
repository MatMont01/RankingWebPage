package VoxelForge.RankingApp.service;

import VoxelForge.RankingApp.model.Mecanica;
import VoxelForge.RankingApp.repository.MecanicaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MecanicaService {

    private final MecanicaRepository mecanicaRepository;

    public MecanicaService(MecanicaRepository mecanicaRepository) {
        this.mecanicaRepository = mecanicaRepository;
    }

    public List<Mecanica> findAll() {
        return mecanicaRepository.findAll();
    }

    public Optional<Mecanica> findById(Integer id) {
        return mecanicaRepository.findById(id);
    }

    public Mecanica save(Mecanica mecanica) {
        return mecanicaRepository.save(mecanica);
    }

    public void deleteById(Integer id) {
        mecanicaRepository.deleteById(id);
    }
}