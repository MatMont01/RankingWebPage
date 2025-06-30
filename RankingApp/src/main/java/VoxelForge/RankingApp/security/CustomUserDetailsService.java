// Archivo: src/main/java/VoxelForge/RankingApp/security/CustomUserDetailsService.java
package VoxelForge.RankingApp.security;

import VoxelForge.RankingApp.model.Empleado;
import VoxelForge.RankingApp.repository.EmpleadoRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final EmpleadoRepository empleadoRepository;

    public CustomUserDetailsService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Empleado empleado = empleadoRepository.findByUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el nombre de usuario: " + username));

        return new User(empleado.getUsuario(), empleado.getPassword(), new ArrayList<>());
    }
}