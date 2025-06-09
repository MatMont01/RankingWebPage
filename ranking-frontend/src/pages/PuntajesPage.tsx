import { useEffect, useState, type FormEvent } from "react";
import {
    getPuntajes,
    createPuntaje,
    deletePuntaje,
    type Puntaje,
} from "../services/puntajeService";
import { getPersonas, type Persona } from "../services/personaService";
import { getTemporadas, type Temporada } from "../services/temporadaService";

export default function PuntajesPage() {
    const [puntajes, setPuntajes] = useState<Puntaje[]>([]);
    const [personas, setPersonas] = useState<Persona[]>([]);
    const [temporadas, setTemporadas] = useState<Temporada[]>([]);
    const [idPersona, setIdPersona] = useState<number | "">("");
    const [idTemporada, setIdTemporada] = useState<number | "">("");
    const [puntaje, setPuntaje] = useState<number | "">("");
    const [fechaActualizacion, setFechaActualizacion] = useState("");
    const [loading, setLoading] = useState(false);

    const fetchAll = async () => {
        setLoading(true);
        setPuntajes(await getPuntajes());
        setPersonas(await getPersonas());
        setTemporadas(await getTemporadas());
        setLoading(false);
    };

    useEffect(() => {
        fetchAll();
    }, []);

    const handleSubmit = async (e: FormEvent) => {
        e.preventDefault();
        if (!idPersona || !idTemporada || !puntaje) return;
        await createPuntaje({
            persona: { idPersona: Number(idPersona) },
            temporada: { idTemporada: Number(idTemporada) },
            puntaje: Number(puntaje),
            fechaActualizacion: fechaActualizacion || new Date().toISOString(),
        });
        setIdPersona("");
        setIdTemporada("");
        setPuntaje("");
        setFechaActualizacion("");
        fetchAll();
    };

    const handleDelete = async (id: number | undefined) => {
        if (!id) return;
        await deletePuntaje(id);
        fetchAll();
    };

    return (
        <div className="max-w-3xl mx-auto p-6">
            <h1 className="text-2xl font-bold mb-6">Puntajes</h1>

            <form onSubmit={handleSubmit} className="mb-6 flex gap-2 flex-wrap items-end">
                <select
                    className="border rounded px-2 py-1"
                    value={idPersona}
                    onChange={e => setIdPersona(Number(e.target.value))}
                    required
                >
                    <option value="">Persona</option>
                    {personas.map(p => (
                        <option key={p.idPersona} value={p.idPersona}>
                            {p.nombre}
                        </option>
                    ))}
                </select>
                <select
                    className="border rounded px-2 py-1"
                    value={idTemporada}
                    onChange={e => setIdTemporada(Number(e.target.value))}
                    required
                >
                    <option value="">Temporada</option>
                    {temporadas.map(t => (
                        <option key={t.idTemporada} value={t.idTemporada}>
                            {t.nombre}
                        </option>
                    ))}
                </select>
                <input
                    className="border rounded px-2 py-1"
                    type="number"
                    min={0}
                    placeholder="Puntaje"
                    value={puntaje}
                    onChange={e => setPuntaje(Number(e.target.value))}
                    required
                />
                <input
                    className="border rounded px-2 py-1"
                    type="datetime-local"
                    value={fechaActualizacion}
                    onChange={e => setFechaActualizacion(e.target.value)}
                />
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
                    {puntajes.map(p => (
                        <li
                            key={p.idPuntaje}
                            className="flex items-center justify-between bg-white border rounded p-2 shadow-sm"
                        >
                            <div>
                                <span className="font-medium">
                                    {"nombre" in p.persona
                                        ? (p.persona as Persona).nombre
                                        : p.persona.idPersona}
                                </span>
                                <span className="ml-2 text-sm text-gray-500">
                                    (
                                    {"nombre" in p.temporada
                                        ? (p.temporada as Temporada).nombre
                                        : p.temporada.idTemporada
                                    }
                                    ) — {p.puntaje} pts
                                </span>
                                <span className="ml-2 text-xs text-gray-400">
                                    [{p.fechaActualizacion?.slice(0, 16).replace("T", " ")}]
                                </span>
                            </div>
                            <button
                                className="text-sm bg-red-500 text-white rounded px-3 py-1 hover:bg-red-600"
                                onClick={() => handleDelete(p.idPuntaje)}
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
