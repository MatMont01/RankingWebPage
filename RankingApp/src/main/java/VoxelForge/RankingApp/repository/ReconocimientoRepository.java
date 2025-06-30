// Archivo: src/main/java/VoxelForge/RankingApp/repository/ReconocimientoRepository.java
package VoxelForge.RankingApp.repository;

import VoxelForge.RankingApp.model.Reconocimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReconocimientoRepository extends JpaRepository<Reconocimiento, Integer> {
}