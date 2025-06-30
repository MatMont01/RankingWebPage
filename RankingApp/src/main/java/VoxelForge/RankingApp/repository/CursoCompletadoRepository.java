// Archivo: src/main/java/VoxelForge/RankingApp/repository/CursoCompletadoRepository.java
package VoxelForge.RankingApp.repository;

import VoxelForge.RankingApp.model.CursoCompletado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoCompletadoRepository extends JpaRepository<CursoCompletado, Integer> {
}