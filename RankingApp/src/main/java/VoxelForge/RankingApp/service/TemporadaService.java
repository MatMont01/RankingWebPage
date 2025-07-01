// Archivo: src/main/java/VoxelForge/RankingApp/service/TemporadaService.java
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

    public Optional<Temporada> findTemporadaActiva() {
        return temporadaRepository.findFirstByEstadoOrderByFechaInicioDesc("ACTIVA");
    }

    public Temporada save(Temporada temporada) {
        return temporadaRepository.save(temporada);
    }

    public void deleteById(Integer id) {
        temporadaRepository.deleteById(id);
    }

    public Optional<Temporada> patchTemporada(Integer id, Temporada partial) {
        return findById(id).map(temporada -> {
            if (partial.getNombre() != null) temporada.setNombre(partial.getNombre());
            if (partial.getFechaInicio() != null) temporada.setFechaInicio(partial.getFechaInicio());
            if (partial.getFechaFin() != null) temporada.setFechaFin(partial.getFechaFin());
            if (partial.getEstado() != null) temporada.setEstado(partial.getEstado());
            return save(temporada);
        });
    }
}