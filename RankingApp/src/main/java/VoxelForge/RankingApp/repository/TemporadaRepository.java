// Archivo: src/main/java/VoxelForge/RankingApp/repository/TemporadaRepository.java
package VoxelForge.RankingApp.repository;

import VoxelForge.RankingApp.model.Temporada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TemporadaRepository extends JpaRepository<Temporada, Integer> {

    Optional<Temporada> findFirstByEstadoOrderByFechaInicioDesc(String estado);
}