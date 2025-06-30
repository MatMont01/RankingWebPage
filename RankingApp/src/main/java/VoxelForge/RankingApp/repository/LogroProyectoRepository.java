// Archivo: src/main/java/VoxelForge/RankingApp/repository/LogroProyectoRepository.java
package VoxelForge.RankingApp.repository;

import VoxelForge.RankingApp.model.LogroProyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogroProyectoRepository extends JpaRepository<LogroProyecto, Integer> {
}