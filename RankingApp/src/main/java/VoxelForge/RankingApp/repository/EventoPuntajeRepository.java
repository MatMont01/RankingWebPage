// Archivo: src/main/java/VoxelForge/RankingApp/repository/EventoPuntajeRepository.java
package VoxelForge.RankingApp.repository;

import VoxelForge.RankingApp.model.EventoPuntaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoPuntajeRepository extends JpaRepository<EventoPuntaje, Integer> {
    // Buscar todos los eventos de un empleado en una temporada
    List<EventoPuntaje> findByEmpleado_IdEmpleadoOrderByFechaDesc(Integer idEmpleado);

    List<EventoPuntaje> findByEmpleado_IdEmpleado(Integer idEmpleado);
}