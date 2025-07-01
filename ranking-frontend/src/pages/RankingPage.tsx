import {useEffect, useState} from "react";
import {getRankingPorTemporada, type Puntaje} from "../services/rankingService";
import {getTemporadas, getTemporadaActiva, type Temporada} from "../services/temporadaService";

const MEDALS = ["🥇", "🥈", "🥉"];

export default function RankingPage() {
    const [ranking, setRanking] = useState<Puntaje[]>([]);
    const [temporadas, setTemporadas] = useState<Temporada[]>([]);
    const [temporadaSeleccionada, setTemporadaSeleccionada] = useState<Temporada | null>(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const CargarDatosIniciales = async () => {
            try {
                const todasTemporadas = await getTemporadas();
                setTemporadas(todasTemporadas);

                const activa = await getTemporadaActiva();
                setTemporadaSeleccionada(activa);
            } catch (error) {
                console.warn("No se encontró una temporada activa por defecto.");
                setLoading(false);
            }
        }
        CargarDatosIniciales();
    }, []);

    useEffect(() => {
        if (!temporadaSeleccionada) {
            setRanking([]);
            return;
        }
        ;

        setLoading(true);
        getRankingPorTemporada(temporadaSeleccionada.idTemporada)
            .then(setRanking)
            .finally(() => setLoading(false));
    }, [temporadaSeleccionada]);

    const handleTemporadaChange = (id: number) => {
        const temporada = temporadas.find(t => t.idTemporada === id);
        setTemporadaSeleccionada(temporada || null);
    }

    return (
        <div className="max-w-3xl mx-auto p-6">
            <h1 className="text-3xl font-bold mb-8 text-center text-gray-800 dark:text-yellow-400">
                🏆 Ranking de Empleados
            </h1>
            <div className="flex items-center justify-end mb-8">
                <select
                    className="border rounded px-3 py-2 bg-gray-50 dark:bg-gray-800 dark:text-gray-200 border-gray-300 dark:border-gray-600 focus:outline-none"
                    value={temporadaSeleccionada?.idTemporada || ""}
                    onChange={e => handleTemporadaChange(Number(e.target.value))}
                >
                    <option value="" disabled>Selecciona una temporada</option>
                    {temporadas.map(t => (
                        <option key={t.idTemporada} value={t.idTemporada}>
                            {t.nombre}
                        </option>
                    ))}
                </select>
            </div>

            {loading ? (
                <div className="text-center text-gray-700 dark:text-gray-300">Cargando ranking...</div>
            ) : ranking.length === 0 ? (
                <div className="text-center text-gray-600 dark:text-gray-400 py-10">No hay puntajes para esta
                    temporada.</div>
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
                            }`}
                        >
                            <div className="flex items-center gap-4">
                                <span className="text-2xl w-10 text-center font-bold">
                                    {idx < 3 ? MEDALS[idx] : idx + 1}
                                </span>
                                <span className="font-medium text-lg text-gray-800 dark:text-gray-100">
                                    {p.empleado.nombre}
                                </span>
                            </div>
                            <span className="text-xl font-bold text-blue-700 dark:text-blue-400">
                                {p.puntajeTotal} pts
                            </span>
                        </li>
                    ))}
                </ol>
            )}
        </div>
    );
}