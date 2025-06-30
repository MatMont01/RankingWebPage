// Archivo: src/main/java/VoxelForge/RankingApp/service/AsistenciaService.java
package VoxelForge.RankingApp.service;

import VoxelForge.RankingApp.model.Asistencia;
import VoxelForge.RankingApp.model.Empleado;
import VoxelForge.RankingApp.model.Temporada;
import VoxelForge.RankingApp.repository.AsistenciaRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AsistenciaService {

    private final AsistenciaRepository asistenciaRepository;
    private final EmpleadoService empleadoService;
    private final PuntajeService puntajeService;
    private final TemporadaService temporadaService;

    // Inyectamos los servicios que necesitamos
    public AsistenciaService(AsistenciaRepository asistenciaRepository, EmpleadoService empleadoService, PuntajeService puntajeService, TemporadaService temporadaService) {
        this.asistenciaRepository = asistenciaRepository;
        this.empleadoService = empleadoService;
        this.puntajeService = puntajeService;
        this.temporadaService = temporadaService;
    }

    public Asistencia save(Asistencia asistencia) {
        return asistenciaRepository.save(asistencia);
    }

    public List<Asistencia> findAsistenciasDeEmpleadoEnMes(Integer idEmpleado, int anio, int mes) {
        LocalDate inicioMes = LocalDate.of(anio, mes, 1);
        LocalDate finMes = inicioMes.withDayOfMonth(inicioMes.lengthOfMonth());
        return asistenciaRepository.findByEmpleado_IdEmpleadoAndFechaBetween(idEmpleado, inicioMes, finMes);
    }

    /**
     * Tarea programada para verificar la asistencia perfecta del mes anterior.
     * Se ejecuta a las 02:00 AM del primer día de cada mes.
     * La expresión cron es: "segundo minuto hora día-del-mes mes día-de-la-semana"
     */
    @Scheduled(cron = "0 0 2 1 * *")
    @Transactional
    public void verificarAsistenciaPerfectaMensual() {
        System.out.println("--- Iniciando Job de Verificación de Asistencia Perfecta ---");

        // 1. Determinar el mes anterior
        YearMonth mesAnterior = YearMonth.now().minusMonths(1);

        // 2. Obtener la temporada activa (si no hay, no se pueden dar puntos)
        Temporada temporadaActiva = temporadaService.findTemporadaActiva()
                .orElse(null);

        if (temporadaActiva == null) {
            System.out.println("No hay temporada activa. No se otorgarán puntos por asistencia.");
            return;
        }

        // 3. Obtener la lista de todos los empleados
        List<Empleado> empleados = empleadoService.findAll();

        for (Empleado empleado : empleados) {
            // 4. Obtener todos los días laborables (Lunes a Viernes) del mes anterior
            Set<LocalDate> diasLaborables = mesAnterior.atDay(1).datesUntil(mesAnterior.atEndOfMonth().plusDays(1))
                    .filter(date -> date.getDayOfWeek() != DayOfWeek.SATURDAY && date.getDayOfWeek() != DayOfWeek.SUNDAY)
                    .collect(Collectors.toSet());

            if (diasLaborables.isEmpty()) {
                continue; // No hay días laborables en el mes, pasar al siguiente empleado
            }

            // 5. Obtener los registros de asistencia PUNTUAL del empleado para ese mes
            List<Asistencia> asistenciasDelMes = findAsistenciasDeEmpleadoEnMes(
                    empleado.getIdEmpleado(), mesAnterior.getYear(), mesAnterior.getMonthValue()
            );

            Set<LocalDate> diasAsistidosPuntualmente = asistenciasDelMes.stream()
                    .filter(asistencia -> "PUNTUAL".equals(asistencia.getEstadoPuntualidad()))
                    .map(Asistencia::getFecha)
                    .collect(Collectors.toSet());

            // 6. Verificar si el conjunto de días laborables es igual al conjunto de días con asistencia puntual
            if (diasAsistidosPuntualmente.containsAll(diasLaborables)) {
                System.out.println("¡Asistencia perfecta para " + empleado.getNombre() + "! Otorgando 20 puntos.");

                // 7. Otorgar los puntos
                String descripcion = "Bono por asistencia perfecta en " + mesAnterior.getMonth().toString() + " " + mesAnterior.getYear();
                puntajeService.otorgarPuntos(
                        empleado,
                        temporadaActiva,
                        20, // Puntos a otorgar
                        "Puntualidad Mensual",
                        descripcion,
                        null // No hay un ID de referencia específico para este evento
                );
            }
        }
        System.out.println("--- Finalizado Job de Verificación de Asistencia Perfecta ---");
    }
}