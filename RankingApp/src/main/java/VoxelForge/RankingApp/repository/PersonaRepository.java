package VoxelForge.RankingApp.repository;

import VoxelForge.RankingApp.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Integer> {
}
