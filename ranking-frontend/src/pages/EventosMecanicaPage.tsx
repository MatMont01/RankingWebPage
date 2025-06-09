import { useEffect, useState, type FormEvent } from "react";
import {
    getEventosMecanica,
    createEventoMecanica,
    deleteEventoMecanica,
    type EventoMecanica,
} from "../services/eventoMecanicaService";
import { getPersonas, type Persona } from "../services/personaService";
import { getMecanicas, type Mecanica } from "../services/mecanicaService";
import { getTemporadas, type Temporada } from "../services/temporadaService";

export default function EventosMecanicaPage() {
    const [eventos, setEventos] = useState<EventoMecanica[]>([]);
    const [personas, setPersonas] = useState<Persona[]>([]);
    const [mecanicas, setMecanicas] = useState<Mecanica[]>([]);
    const [temporadas, setTemporadas] = useState<Temporada[]>([]);
    const [idPersona, setIdPersona] = useState<number | "">("");
    const [idMecanica, setIdMecanica] = useState<number | "">("");
    const [idTemporada, setIdTemporada] = useState<number | "">("");
    const [fecha, setFecha] = useState("");
    const [puntosOtorgados, setPuntosOtorgados] = useState<number | "">("");
    const [loading, setLoading] = useState(false);

    const fetchAll = async () => {
        setLoading(true);
        setEventos(await getEventosMecanica());
        setPersonas(await getPersonas());
        setMecanicas(await getMecanicas());
        setTemporadas(await getTemporadas());
        setLoading(false);
    };

    useEffect(() => {
        fetchAll();
    }, []);

    const handleSubmit = async (e: FormEvent) => {
        e.preventDefault();
        if (!idPersona || !idMecanica || !idTemporada || !puntosOtorgados) return;
        await createEventoMecanica({
            persona: { idPersona: Number(idPersona) },
            mecanica: { idMecanica: Number(idMecanica) },
            temporada: { idTemporada: Number(idTemporada) },
            fecha: fecha || new Date().toISOString(),
            puntosOtorgados: Number(puntosOtorgados),
        });
        setIdPersona("");
        setIdMecanica("");
        setIdTemporada("");
        setFecha("");
        setPuntosOtorgados("");
        fetchAll();
    };

    const handleDelete = async (id: number | undefined) => {
        if (!id) return;
        await deleteEventoMecanica(id);
        fetchAll();
    };

    return (
        <div className="max-w-4xl mx-auto p-6">
            <h1 className="text-2xl font-bold mb-6">Eventos de Mecánica</h1>

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
                    value={idMecanica}
                    onChange={e => setIdMecanica(Number(e.target.value))}
                    required
                >
                    <option value="">Mecánica</option>
                    {mecanicas.map(m => (
                        <option key={m.idMecanica} value={m.idMecanica}>
                            {m.nombre}
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
                    type="datetime-local"
                    value={fecha}
                    onChange={e => setFecha(e.target.value)}
                />
                <input
                    className="border rounded px-2 py-1"
                    type="number"
                    min={0}
                    placeholder="Puntos otorgados"
                    value={puntosOtorgados}
                    onChange={e => setPuntosOtorgados(Number(e.target.value))}
                    required
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
                    {eventos.map(ev => (
                        <li
                            key={ev.idEvento}
                            className="flex items-center justify-between bg-white border rounded p-2 shadow-sm"
                        >
                            <div>
                                <span className="font-medium">
                                    {"nombre" in ev.persona
                                        ? (ev.persona as Persona).nombre
                                        : ev.persona.idPersona}
                                </span>
                                <span className="ml-2 text-sm text-gray-500">
                                    [
                                    {"nombre" in ev.mecanica
                                        ? (ev.mecanica as Mecanica).nombre
                                        : ev.mecanica.idMecanica
                                    }
                                    ] — (
                                    {"nombre" in ev.temporada
                                        ? (ev.temporada as Temporada).nombre
                                        : ev.temporada.idTemporada
                                    }
                                    )
                                </span>
                                <span className="ml-2 text-xs text-gray-400">
                                    [{ev.fecha?.slice(0, 16).replace("T", " ")}]
                                </span>
                                <span className="ml-2 text-green-600 font-semibold">
                                    +{ev.puntosOtorgados} pts
                                </span>
                            </div>
                            <button
                                className="text-sm bg-red-500 text-white rounded px-3 py-1 hover:bg-red-600"
                                onClick={() => handleDelete(ev.idEvento)}
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
