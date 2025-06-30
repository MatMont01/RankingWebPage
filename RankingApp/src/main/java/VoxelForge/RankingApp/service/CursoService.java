// Archivo: src/main/java/VoxelForge/RankingApp/service/CursoService.java
package VoxelForge.RankingApp.service;

import VoxelForge.RankingApp.model.Curso;
import VoxelForge.RankingApp.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public List<Curso> findAll() {
        return cursoRepository.findAll();
    }

    public Optional<Curso> findById(Integer id) {
        return cursoRepository.findById(id);
    }

    public Curso save(Curso curso) {
        return cursoRepository.save(curso);
    }

    public void deleteById(Integer id) {
        cursoRepository.deleteById(id);
    }
}