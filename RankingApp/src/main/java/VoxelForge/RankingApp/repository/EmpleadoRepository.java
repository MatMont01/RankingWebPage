// Archivo: src/main/java/VoxelForge/RankingApp/repository/EmpleadoRepository.java
package VoxelForge.RankingApp.repository;

import VoxelForge.RankingApp.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {
    // Método para buscar un empleado por su nombre de usuario
    Optional<Empleado> findByUsuario(String usuario);
}