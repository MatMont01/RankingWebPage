package VoxelForge.RankingApp.service;

import VoxelForge.RankingApp.model.EventoPuntaje;
import VoxelForge.RankingApp.repository.EventoPuntajeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoPuntajeService {

    private final EventoPuntajeRepository eventoPuntajeRepository;

    public EventoPuntajeService(EventoPuntajeRepository eventoPuntajeRepository) {
        this.eventoPuntajeRepository = eventoPuntajeRepository;
    }

    public List<EventoPuntaje> findAll() {
        return eventoPuntajeRepository.findAll();
    }

    public Optional<EventoPuntaje> findById(Integer id) {
        return eventoPuntajeRepository.findById(id);
    }

    public EventoPuntaje save(EventoPuntaje eventoPuntaje) {
        return eventoPuntajeRepository.save(eventoPuntaje);
    }

    public void deleteById(Integer id) {
        eventoPuntajeRepository.deleteById(id);
    }

    // --- ESTE MÉTODO AHORA LLAMA AL MÉTODO CORRECTO DEL REPOSITORIO ---
    public List<EventoPuntaje> findByEmpleadoId(Integer idEmpleado) {
        return eventoPuntajeRepository.findByEmpleado_IdEmpleadoOrderByFechaDesc(idEmpleado);
    }
}