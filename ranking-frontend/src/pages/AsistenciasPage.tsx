import {useEffect, useState, type FormEvent} from "react";
import {getEmpleados, type Empleado} from "../services/empleadoService";
import {registrarAsistencia} from "../services/asistenciaService";

export default function AsistenciasPage() {
    const [empleados, setEmpleados] = useState<Empleado[]>([]);

    // Estados del formulario
    const [empleadoId, setEmpleadoId] = useState<number | ''>('');
    const [fecha, setFecha] = useState(new Date().toISOString().split('T')[0]); // Default a hoy
    const [horaLlegada, setHoraLlegada] = useState('');
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        getEmpleados().then(setEmpleados);
    }, []);

    // Función para determinar si fue puntual (ej. antes de las 09:00)
    const determinarPuntualidad = (hora: string): 'PUNTUAL' | 'TARDE' => {
        const horaLimite = "09:00:00";
        return hora <= horaLimite ? 'PUNTUAL' : 'TARDE';
    };

    const handleSubmit = async (e: FormEvent) => {
        e.preventDefault();
        if (!empleadoId || !fecha || !horaLlegada) {
            return alert("Por favor, completa todos los campos.");
        }

        setLoading(true);
        try {
            await registrarAsistencia({
                idEmpleado: Number(empleadoId),
                fecha,
                horaLlegada,
                estadoPuntualidad: determinarPuntualidad(horaLlegada)
            });
            alert("Asistencia registrada con éxito.");
            // Resetear formulario
            setEmpleadoId('');
            setHoraLlegada('');
        } catch (error) {
            alert("Error al registrar la asistencia. Es posible que ya exista un registro para este empleado en esta fecha.");
            console.error(error);
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="max-w-4xl mx-auto p-6">
            <h1 className="text-2xl font-bold mb-6">Registro de Asistencia</h1>
            <p className="mb-6 text-gray-600 dark:text-gray-400">
                Desde aquí se puede registrar la asistencia diaria de los empleados. La lógica para otorgar puntos por
                puntualidad mensual es un proceso automático en el backend.
            </p>

            <form onSubmit={handleSubmit}
                  className="p-4 bg-white dark:bg-gray-800 rounded-lg shadow-sm border dark:border-gray-700 flex gap-4 flex-wrap items-end">
                <div className="flex-1 min-w-[200px]">
                    <label htmlFor="empleado" className="block text-sm font-medium mb-1">Empleado</label>
                    <select
                        id="empleado"
                        value={empleadoId}
                        onChange={e => setEmpleadoId(Number(e.target.value))}
                        required
                        className="w-full border rounded px-2 py-2 dark:bg-gray-700 dark:border-gray-600"
                    >
                        <option value="" disabled>Selecciona un empleado</option>
                        {empleados.map(e => (
                            <option key={e.idEmpleado} value={e.idEmpleado}>
                                {e.nombre}
                            </option>
                        ))}
                    </select>
                </div>

                <div className="flex-1 min-w-[150px]">
                    <label htmlFor="fecha" className="block text-sm font-medium mb-1">Fecha</label>
                    <input
                        id="fecha"
                        className="w-full border rounded px-2 py-2 dark:bg-gray-700 dark:border-gray-600"
                        type="date"
                        value={fecha}
                        onChange={e => setFecha(e.target.value)}
                        required
                    />
                </div>

                <div className="flex-1 min-w-[150px]">
                    <label htmlFor="hora" className="block text-sm font-medium mb-1">Hora de Llegada</label>
                    <input
                        id="hora"
                        className="w-full border rounded px-2 py-2 dark:bg-gray-700 dark:border-gray-600"
                        type="time"
                        step="1" // para incluir segundos
                        value={horaLlegada}
                        onChange={e => setHoraLlegada(e.target.value)}
                        required
                    />
                </div>

                <button
                    className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700"
                    type="submit"
                    disabled={loading}
                >
                    {loading ? "Registrando..." : "Registrar Asistencia"}
                </button>
            </form>
        </div>
    );
}