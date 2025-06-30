// Archivo: src/main/java/VoxelForge/RankingApp/repository/CursoRepository.java
package VoxelForge.RankingApp.repository;

import VoxelForge.RankingApp.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {
}