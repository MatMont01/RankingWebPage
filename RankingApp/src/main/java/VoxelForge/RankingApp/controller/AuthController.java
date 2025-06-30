// Archivo: src/main/java/VoxelForge/RankingApp/controller/AuthController.java
package VoxelForge.RankingApp.controller;

import VoxelForge.RankingApp.dto.AuthResponse;
import VoxelForge.RankingApp.dto.LoginRequest;
import VoxelForge.RankingApp.dto.RegistroRequest;
import VoxelForge.RankingApp.model.Empleado;
import VoxelForge.RankingApp.security.JwtTokenProvider;
import VoxelForge.RankingApp.service.EmpleadoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final EmpleadoService empleadoService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    public AuthController(EmpleadoService empleadoService, AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider) {
        this.empleadoService = empleadoService;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;

    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsuario(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new AuthResponse(jwt, loginRequest.getUsuario()));
    }

    @PostMapping("/registro")
    public ResponseEntity<?> registerUser(@RequestBody RegistroRequest registroRequest) {
        if (empleadoService.findByUsuario(registroRequest.getUsuario()).isPresent()) {
            return new ResponseEntity<>("¡El nombre de usuario ya está en uso!", HttpStatus.BAD_REQUEST);
        }

        Empleado empleado = new Empleado();
        empleado.setNombre(registroRequest.getNombre());
        empleado.setCorreo(registroRequest.getCorreo());
        empleado.setUsuario(registroRequest.getUsuario());
        empleado.setPassword(registroRequest.getPassword());

        Empleado result = empleadoService.save(empleado);
        return new ResponseEntity<>("Usuario registrado exitosamente", HttpStatus.CREATED);
    }
}