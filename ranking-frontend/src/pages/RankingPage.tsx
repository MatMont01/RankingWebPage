import {useEffect, useState} from "react";
import {getPuntajes, type Puntaje} from "../services/puntajeService";
import {getTemporadas, type Temporada} from "../services/temporadaService";
import {type Persona} from "../services/personaService";

const MEDALS = ["🏆", "🥈", "🥉"];

export default function RankingPage() {
    const [ranking, setRanking] = useState<Puntaje[]>([]);
    const [temporadas, setTemporadas] = useState<Temporada[]>([]);
    const [temporadaId, setTemporadaId] = useState<number | "">("");
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        const fetchTemporadas = async () => {
            const temp = await getTemporadas();
            setTemporadas(temp);
            const activa = temp.find(t => t.estado === "ACTIVA") || temp[0];
            setTemporadaId(activa?.idTemporada ?? "");
        };
        fetchTemporadas();
    }, []);

    useEffect(() => {
        if (!temporadaId) return;
        setLoading(true);
        getPuntajes().then(data => {
            const rankingActual = data
                .filter(p => {
                    if ("idTemporada" in p.temporada) {
                        return p.temporada.idTemporada === Number(temporadaId);
                    } else {
                        return (p.temporada as Temporada).idTemporada === Number(temporadaId);
                    }
                })
                .sort((a, b) => b.puntaje - a.puntaje);
            setRanking(rankingActual);
            setLoading(false);
        });
    }, [temporadaId]);

    return (
        <div className="max-w-3xl mx-auto p-6 dark:bg-gray-900 min-h-screen transition-colors duration-300">
            <h1 className="text-3xl font-bold mb-8 text-center text-gray-800 dark:text-yellow-400">
                🏅 Ranking Gamificado
            </h1>
            <div className="flex items-center justify-between mb-8">
                <span className="font-semibold text-gray-700 dark:text-gray-300">Temporada:</span>
                <select
                    className="border rounded px-3 py-2 bg-gray-50 dark:bg-gray-800 dark:text-gray-200 border-gray-300 dark:border-gray-600 focus:outline-none"
                    value={temporadaId}
                    onChange={e => setTemporadaId(Number(e.target.value))}
                >
                    {temporadas.map(t => (
                        <option key={t.idTemporada} value={t.idTemporada}>
                            {t.nombre}
                        </option>
                    ))}
                </select>
            </div>

            {loading ? (
                <div className="text-gray-700 dark:text-gray-300">Cargando ranking...</div>
            ) : ranking.length === 0 ? (
                <div className="text-gray-600 dark:text-gray-400">No hay puntajes para esta temporada.</div>
            ) : (
                <ol className="space-y-3">
                    {ranking.map((p, idx) => (
                        <li
                            key={p.idPuntaje}
                            className={`flex items-center justify-between p-4 rounded-xl border shadow-sm transition-colors duration-200
                                ${idx === 0
                                ? "bg-yellow-100 border-yellow-400 dark:bg-yellow-900 dark:border-yellow-600"
                                : idx === 1
                                    ? "bg-gray-100 border-gray-400 dark:bg-gray-800 dark:border-gray-500"
                                    : idx === 2
                                        ? "bg-orange-100 border-orange-400 dark:bg-orange-900 dark:border-orange-600"
                                        : "bg-white border-gray-200 dark:bg-gray-900 dark:border-gray-700"
                            }
                            `}
                        >
                            <div className="flex items-center gap-3">
                                <span className={`text-2xl w-8 text-center`}>
                                    {idx < 3 ? MEDALS[idx] : idx + 1}
                                </span>
                                <span className="font-medium text-gray-800 dark:text-gray-100">
                                    {"nombre" in p.persona
                                        ? (p.persona as Persona).nombre
                                        : p.persona.idPersona}
                                </span>
                            </div>
                            <span className="text-xl font-bold text-blue-700 dark:text-blue-400">
                                {p.puntaje} pts
                            </span>
                        </li>
                    ))}
                </ol>
            )}
        </div>
    );
}
