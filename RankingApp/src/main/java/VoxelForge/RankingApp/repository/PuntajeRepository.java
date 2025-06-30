// Archivo: src/main/java/VoxelForge/RankingApp/repository/PuntajeRepository.java
package VoxelForge.RankingApp.repository;

import VoxelForge.RankingApp.model.Puntaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PuntajeRepository extends JpaRepository<Puntaje, Integer> {
    // Buscar un puntaje específico por empleado y temporada
    Optional<Puntaje> findByEmpleado_IdEmpleadoAndTemporada_IdTemporada(Integer idEmpleado, Integer idTemporada);

    // Buscar todos los puntajes de una temporada, ordenados de mayor a menor
    List<Puntaje> findByTemporada_IdTemporadaOrderByPuntajeTotalDesc(Integer idTemporada);
}