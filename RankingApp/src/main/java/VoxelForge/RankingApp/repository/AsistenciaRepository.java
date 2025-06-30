// Archivo: src/main/java/VoxelForge/RankingApp/repository/AsistenciaRepository.java
package VoxelForge.RankingApp.repository;

import VoxelForge.RankingApp.model.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AsistenciaRepository extends JpaRepository<Asistencia, Integer> {
    // Buscar asistencias de un empleado en un rango de fechas
    List<Asistencia> findByEmpleado_IdEmpleadoAndFechaBetween(Integer idEmpleado, LocalDate fechaInicio, LocalDate fechaFin);
}