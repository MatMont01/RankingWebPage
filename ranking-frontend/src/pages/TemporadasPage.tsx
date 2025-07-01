import {useEffect, useState, type FormEvent} from "react";
import {
    getTemporadas,
    createTemporada,
    deleteTemporada,
    type Temporada
} from "../services/temporadaService";
import {updateEstadoTemporada} from "../services/temporadaService"; // Nuevo método PATCH

export default function TemporadasPage() {
    const [temporadas, setTemporadas] = useState<Temporada[]>([]);
    const [nombre, setNombre] = useState("");
    const [fechaInicio, setFechaInicio] = useState("");
    const [fechaFin, setFechaFin] = useState("");
    const [estado, setEstado] = useState<Temporada['estado']>("ACTIVA");
    const [loading, setLoading] = useState(true);

    const fetchTemporadas = async () => {
        setLoading(true);
        try {
            const data = await getTemporadas();
            setTemporadas(data);
        } catch (error) {
            console.error("Error al obtener temporadas", error);
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchTemporadas();
    }, []);

    const handleSubmit = async (e: FormEvent) => {
        e.preventDefault();
        if (!nombre || !fechaInicio || !fechaFin) return;
        try {
            await createTemporada({nombre, fechaInicio, fechaFin, estado});
            setNombre("");
            setFechaInicio("");
            setFechaFin("");
            setEstado("ACTIVA");
            fetchTemporadas();
        } catch (error) {
            alert("No se pudo crear la temporada.");
        }
    };

    const handleDelete = async (id: number) => {
        if (window.confirm("¿Seguro que quieres eliminar esta temporada? Se eliminarán los puntajes asociados.")) {
            try {
                await deleteTemporada(id);
                fetchTemporadas();
            } catch (error) {
                alert("No se pudo eliminar la temporada.");
            }
        }
    };

    // Cambiar estado de la temporada (ACTIVA <-> INACTIVA)
    const handleToggleEstado = async (temporada: Temporada) => {
        const nuevoEstado = temporada.estado === "ACTIVA" ? "INACTIVA" : "ACTIVA";
        try {
            await updateEstadoTemporada(temporada.idTemporada, nuevoEstado);
            fetchTemporadas();
        } catch (error) {
            alert("No se pudo actualizar el estado.");
        }
    };

    return (
        <div className="max-w-4xl mx-auto p-6">
            <h1 className="text-2xl font-bold mb-6">Gestión de Temporadas</h1>

            <form onSubmit={handleSubmit}
                  className="mb-6 p-4 bg-white dark:bg-gray-800 rounded-lg shadow-sm border dark:border-gray-700 flex gap-4 flex-wrap items-end">
                <input
                    className="border rounded px-2 py-2 dark:bg-gray-700 dark:border-gray-600"
                    placeholder="Nombre de la temporada"
                    value={nombre}
                    onChange={e => setNombre(e.target.value)}
                    required
                />
                <input
                    className="border rounded px-2 py-2 dark:bg-gray-700 dark:border-gray-600"
                    type="date"
                    value={fechaInicio}
                    onChange={e => setFechaInicio(e.target.value)}
                    required
                />
                <input
                    className="border rounded px-2 py-2 dark:bg-gray-700 dark:border-gray-600"
                    type="date"
                    value={fechaFin}
                    onChange={e => setFechaFin(e.target.value)}
                    required
                />
                <select
                    className="border rounded px-2 py-2 dark:bg-gray-700 dark:border-gray-600"
                    value={estado}
                    onChange={e => setEstado(e.target.value as Temporada['estado'])}
                >
                    <option value="ACTIVA">ACTIVA</option>
                    <option value="INACTIVA">INACTIVA</option>
                    <option value="FINALIZADA">FINALIZADA</option>
                </select>
                <button
                    className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700"
                    type="submit"
                >
                    Agregar
                </button>
            </form>

            {loading ? (
                <div className="text-center">Cargando...</div>
            ) : (
                <div className="bg-white dark:bg-gray-800 rounded-lg shadow-md border dark:border-gray-700">
                    <ul className="divide-y dark:divide-gray-700">
                        {temporadas.map((temporada) => (
                            <li
                                key={temporada.idTemporada}
                                className="flex items-center justify-between p-4"
                            >
                                <div>
                                    <span className="font-medium dark:text-white">{temporada.nombre}</span>
                                    <span className={`ml-4 text-xs font-semibold px-2.5 py-1 rounded-full ${
                                        temporada.estado === 'ACTIVA' ? 'bg-green-100 text-green-800 dark:bg-green-900 dark:text-green-300' :
                                            temporada.estado === 'INACTIVA' ? 'bg-yellow-100 text-yellow-800 dark:bg-yellow-900 dark:text-yellow-300' :
                                                'bg-gray-100 text-gray-800 dark:bg-gray-700 dark:text-gray-300'
                                    }`}>
                                        {temporada.estado}
                                    </span>
                                    <span className="ml-4 text-sm text-gray-500 dark:text-gray-400">
                                      ({temporada.fechaInicio} a {temporada.fechaFin})
                                    </span>
                                </div>
                                <div className="flex gap-2">
                                    <button
                                        className="text-sm bg-blue-500 text-white rounded px-3 py-1 hover:bg-blue-600"
                                        onClick={() => handleToggleEstado(temporada)}
                                    >
                                        {temporada.estado === "ACTIVA" ? "Desactivar" : "Activar"}
                                    </button>
                                    <button
                                        className="text-sm bg-red-500 text-white rounded px-3 py-1 hover:bg-red-600"
                                        onClick={() => handleDelete(temporada.idTemporada)}
                                    >
                                        Eliminar
                                    </button>
                                </div>
                            </li>
                        ))}
                    </ul>
                </div>
            )}
        </div>
    );
}