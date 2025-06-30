// Archivo: src/main/java/VoxelForge/RankingApp/service/EmpleadoService.java
package VoxelForge.RankingApp.service;

import VoxelForge.RankingApp.model.Empleado;
import VoxelForge.RankingApp.repository.EmpleadoRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;
    private final PasswordEncoder passwordEncoder;

    public EmpleadoService(EmpleadoRepository empleadoRepository, PasswordEncoder passwordEncoder) {
        this.empleadoRepository = empleadoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Empleado> findAll() {
        return empleadoRepository.findAll();
    }

    public Optional<Empleado> findById(Integer id) {
        return empleadoRepository.findById(id);
    }

    public Optional<Empleado> findByUsuario(String usuario) {
        return empleadoRepository.findByUsuario(usuario);
    }

    public Empleado save(Empleado empleado) {
        empleado.setPassword(passwordEncoder.encode(empleado.getPassword()));
        return empleadoRepository.save(empleado);
    }

    public void deleteById(Integer id) {
        empleadoRepository.deleteById(id);
    }
}