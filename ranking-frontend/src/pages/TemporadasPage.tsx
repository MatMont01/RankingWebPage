import {useEffect, useState, type FormEvent} from "react";
import {
    getTemporadas,
    createTemporada,
    deleteTemporada,
    type Temporada
} from "../services/temporadaService";

export default function TemporadasPage() {
    const [temporadas, setTemporadas] = useState<Temporada[]>([]);
    const [nombre, setNombre] = useState("");
    const [fechaInicio, setFechaInicio] = useState("");
    const [fechaFin, setFechaFin] = useState("");
    const [estado, setEstado] = useState("ACTIVA");
    const [loading, setLoading] = useState(false);

    const fetchTemporadas = async () => {
        setLoading(true);
        const data = await getTemporadas();
        setTemporadas(data);
        setLoading(false);
    };

    useEffect(() => {
        fetchTemporadas();
    }, []);

    const handleSubmit = async (e: FormEvent) => {
        e.preventDefault();
        if (!nombre || !fechaInicio || !fechaFin) return;
        await createTemporada({nombre, fechaInicio, fechaFin, estado});
        setNombre("");
        setFechaInicio("");
        setFechaFin("");
        setEstado("ACTIVA");
        fetchTemporadas();
    };

    const handleDelete = async (id: number | undefined) => {
        if (!id) return;
        await deleteTemporada(id);
        fetchTemporadas();
    };

    return (
        <div className="max-w-2xl mx-auto p-6">
            <h1 className="text-2xl font-bold mb-6">Temporadas</h1>

            <form onSubmit={handleSubmit} className="mb-6 flex gap-2 flex-wrap">
                <input
                    className="border rounded px-2 py-1"
                    placeholder="Nombre"
                    value={nombre}
                    onChange={e => setNombre(e.target.value)}
                    required
                />
                <input
                    className="border rounded px-2 py-1"
                    type="date"
                    value={fechaInicio}
                    onChange={e => setFechaInicio(e.target.value)}
                    required
                />
                <input
                    className="border rounded px-2 py-1"
                    type="date"
                    value={fechaFin}
                    onChange={e => setFechaFin(e.target.value)}
                    required
                />
                <select
                    className="border rounded px-2 py-1"
                    value={estado}
                    onChange={e => setEstado(e.target.value)}
                >
                    <option value="ACTIVA">ACTIVA</option>
                    <option value="INACTIVA">INACTIVA</option>
                </select>
                <button
                    className="bg-blue-600 text-white px-4 py-1 rounded hover:bg-blue-700"
                    type="submit"
                >
                    Agregar
                </button>
            </form>

            {loading ? (
                <div>Cargando...</div>
            ) : (
                <ul className="space-y-2">
                    {temporadas.map((temporada) => (
                        <li
                            key={temporada.idTemporada}
                            className="flex items-center justify-between bg-white border rounded p-2 shadow-sm"
                        >
                            <div>
                                <span className="font-medium">{temporada.nombre}</span>
                                <span className="ml-2 text-sm text-gray-500">
                  ({temporada.fechaInicio} a {temporada.fechaFin}) - {temporada.estado}
                </span>
                            </div>
                            <button
                                className="text-sm bg-red-500 text-white rounded px-3 py-1 hover:bg-red-600"
                                onClick={() => handleDelete(temporada.idTemporada)}
                            >
                                Eliminar
                            </button>
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
}
