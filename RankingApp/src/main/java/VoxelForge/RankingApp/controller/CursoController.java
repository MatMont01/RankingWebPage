// Archivo: src/main/java/VoxelForge/RankingApp/controller/CursoController.java
package VoxelForge.RankingApp.controller;

import VoxelForge.RankingApp.dto.CursoCompletadoRequest;
import VoxelForge.RankingApp.model.Curso;
import VoxelForge.RankingApp.model.CursoCompletado;
import VoxelForge.RankingApp.model.Empleado;
import VoxelForge.RankingApp.service.CursoCompletadoService;
import VoxelForge.RankingApp.service.CursoService;
import VoxelForge.RankingApp.service.EmpleadoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    private final CursoService cursoService;
    private final CursoCompletadoService cursoCompletadoService;
    private final EmpleadoService empleadoService;

    public CursoController(CursoService cursoService, CursoCompletadoService cursoCompletadoService, EmpleadoService empleadoService) {
        this.cursoService = cursoService;
        this.cursoCompletadoService = cursoCompletadoService;
        this.empleadoService = empleadoService;
    }

    @GetMapping
    public List<Curso> getAllCursos() {
        return cursoService.findAll();
    }

    @PostMapping
    public ResponseEntity<Curso> createCurso(@RequestBody Curso curso) {
        Curso nuevoCurso = cursoService.save(curso);
        return new ResponseEntity<>(nuevoCurso, HttpStatus.CREATED);
    }

    @PostMapping("/completar")
    public ResponseEntity<?> completarCurso(@RequestBody CursoCompletadoRequest request) {
        try {
            Empleado empleado = empleadoService.findById(request.getIdEmpleado())
                    .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado"));
            Curso curso = cursoService.findById(request.getIdCurso())
                    .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado"));

            CursoCompletado cursoCompletado = new CursoCompletado();
            cursoCompletado.setEmpleado(empleado);
            cursoCompletado.setCurso(curso);
            cursoCompletado.setFechaCompletado(request.getFechaCompletado());

            CursoCompletado result = cursoCompletadoService.completarCurso(cursoCompletado);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}