import {useEffect, useState, type FormEvent} from "react";
import {getCursos, createCurso, type Curso} from "../services/cursoService";

export default function CursosPage() {
    const [cursos, setCursos] = useState<Curso[]>([]);
    const [nombre, setNombre] = useState("");
    const [descripcion, setDescripcion] = useState("");
    const [puntos, setPuntos] = useState<number | ''>(10);
    const [loading, setLoading] = useState(true);

    const fetchCursos = async () => {
        setLoading(true);
        try {
            const data = await getCursos();
            setCursos(data);
        } catch (error) {
            console.error(error);
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchCursos();
    }, []);

    const handleSubmit = async (e: FormEvent) => {
        e.preventDefault();
        if (!nombre || puntos === '') return;
        try {
            await createCurso({nombre, descripcion, puntosAlCompletar: Number(puntos)});
            setNombre("");
            setDescripcion("");
            setPuntos(10);
            fetchCursos();
        } catch (error) {
            alert("No se pudo crear el curso.");
        }
    };

    return (
        <div className="max-w-4xl mx-auto p-6">
            <h1 className="text-2xl font-bold mb-6">Gestión de Cursos</h1>

            <form onSubmit={handleSubmit}
                  className="mb-6 p-4 bg-white dark:bg-gray-800 rounded-lg shadow-sm border dark:border-gray-700 flex gap-4 flex-wrap items-end">
                <input
                    className="border rounded px-2 py-2 flex-grow dark:bg-gray-700 dark:border-gray-600"
                    placeholder="Nombre del curso"
                    value={nombre}
                    onChange={e => setNombre(e.target.value)}
                    required
                />
                <input
                    className="border rounded px-2 py-2 flex-grow dark:bg-gray-700 dark:border-gray-600"
                    placeholder="Descripción (opcional)"
                    value={descripcion}
                    onChange={e => setDescripcion(e.target.value)}
                />
                <input
                    className="border rounded px-2 py-2 w-28 dark:bg-gray-700 dark:border-gray-600"
                    type="number"
                    placeholder="Puntos"
                    value={puntos}
                    onChange={e => setPuntos(e.target.value === '' ? '' : Number(e.target.value))}
                    required
                />
                <button
                    className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700"
                    type="submit"
                >
                    Agregar Curso
                </button>
            </form>

            {loading ? (
                <div className="text-center">Cargando...</div>
            ) : (
                <div className="bg-white dark:bg-gray-800 rounded-lg shadow-md border dark:border-gray-700">
                    <ul className="divide-y dark:divide-gray-700">
                        {cursos.map((curso) => (
                            <li
                                key={curso.idCurso}
                                className="flex items-center justify-between p-4"
                            >
                                <div>
                                    <span className="font-medium dark:text-white">{curso.nombre}</span>
                                    {curso.descripcion && (
                                        <p className="text-sm text-gray-600 dark:text-gray-400">{curso.descripcion}</p>
                                    )}
                                </div>
                                <span className="font-bold text-green-600 dark:text-green-400 text-lg">
                                    +{curso.puntosAlCompletar} pts
                                </span>
                            </li>
                        ))}
                    </ul>
                </div>
            )}
        </div>
    );
}