import {useEffect, useState} from "react";
import {getHistorialEventosPorEmpleado, type EventoPuntaje} from "../services/historialService";
import {getEmpleadoPorUsuario, type Empleado} from "../services/empleadoService";
import {useAuth} from "../context/AuthContext";

export default function HistorialEventosPage() {
    const {user} = useAuth();
    const [eventos, setEventos] = useState<EventoPuntaje[]>([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState('');

    useEffect(() => {
        if (!user?.usuario) {
            setLoading(false);
            setError("No se pudo identificar al usuario.");
            return;
        }

        const fetchHistorial = async () => {
            setLoading(true);
            setError('');
            try {
                const empleado: Empleado = await getEmpleadoPorUsuario(user.usuario);

                const data = await getHistorialEventosPorEmpleado(empleado.idEmpleado);
                setEventos(data);

            } catch (err) {
                console.error("Error al obtener el historial de eventos:", err);
                setError("No se pudo cargar el historial.");
            } finally {
                setLoading(false);
            }
        };

        fetchHistorial();
    }, [user]);

    const formatDate = (dateString: string) => {
        return new Date(dateString).toLocaleString('es-BO', {
            year: 'numeric', month: 'long', day: 'numeric',
            hour: '2-digit', minute: '2-digit'
        });
    }

    return (
        <div className="max-w-4xl mx-auto p-6">
            <h1 className="text-2xl font-bold mb-6">Mi Historial de Actividad</h1>

            {loading && <div className="text-center">Cargando historial...</div>}
            {error && <div className="text-center text-red-500">{error}</div>}

            {!loading && !error && (
                <div className="bg-white dark:bg-gray-800 rounded-lg shadow-md border dark:border-gray-700">
                    <ul className="divide-y dark:divide-gray-700">
                        {eventos.length > 0 ? (
                            eventos.map((evento) => (
                                <li key={evento.idEvento} className="p-4">
                                    <div className="flex items-center justify-between">
                                        <div className="flex-grow">
                                            <p className="dark:text-white">
                                                <span className="text-gray-700 dark:text-gray-300">Recibiste </span>
                                                <span className="font-bold text-green-600 dark:text-green-400">
                                                    {evento.puntosOtorgados} puntos
                                                </span>
                                                <span className="text-gray-700 dark:text-gray-300"> por </span>
                                                <span className="font-semibold text-blue-600 dark:text-blue-400">
                                                    "{evento.origenPuntos}"
                                                </span>
                                            </p>
                                            {evento.descripcion && (
                                                <p className="text-sm text-gray-500 mt-1 dark:text-gray-400">
                                                    Detalle: {evento.descripcion}
                                                </p>
                                            )}
                                        </div>
                                        <div
                                            className="text-right text-xs text-gray-500 dark:text-gray-400 min-w-max ml-4">
                                            {formatDate(evento.fecha)}
                                        </div>
                                    </div>
                                </li>
                            ))
                        ) : (
                            <p className="p-4 text-center text-gray-500">Aún no tienes actividad registrada.</p>
                        )}
                    </ul>
                </div>
            )}
        </div>
    );
}