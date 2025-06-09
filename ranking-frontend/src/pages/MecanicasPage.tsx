import {useEffect, useState, type FormEvent} from "react";
import {
    getMecanicas,
    createMecanica,
    deleteMecanica,
    type Mecanica
} from "../services/mecanicaService";

export default function MecanicasPage() {
    const [mecanicas, setMecanicas] = useState<Mecanica[]>([]);
    const [nombre, setNombre] = useState("");
    const [descripcion, setDescripcion] = useState("");
    const [loading, setLoading] = useState(false);

    const fetchMecanicas = async () => {
        setLoading(true);
        const data = await getMecanicas();
        setMecanicas(data);
        setLoading(false);
    };

    useEffect(() => {
        fetchMecanicas();
    }, []);

    const handleSubmit = async (e: FormEvent) => {
        e.preventDefault();
        if (!nombre) return;
        await createMecanica({nombre, descripcion});
        setNombre("");
        setDescripcion("");
        fetchMecanicas();
    };

    const handleDelete = async (id: number | undefined) => {
        if (!id) return;
        await deleteMecanica(id);
        fetchMecanicas();
    };

    return (
        <div className="max-w-2xl mx-auto p-6">
            <h1 className="text-2xl font-bold mb-6">Mecánicas</h1>

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
                    placeholder="Descripción"
                    value={descripcion}
                    onChange={e => setDescripcion(e.target.value)}
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
                    {mecanicas.map((mecanica) => (
                        <li
                            key={mecanica.idMecanica}
                            className="flex items-center justify-between bg-white border rounded p-2 shadow-sm"
                        >
                            <div>
                                <span className="font-medium">{mecanica.nombre}</span>
                                {mecanica.descripcion && (
                                    <span className="ml-2 text-gray-500">{mecanica.descripcion}</span>
                                )}
                            </div>
                            <button
                                className="text-sm bg-red-500 text-white rounded px-3 py-1 hover:bg-red-600"
                                onClick={() => handleDelete(mecanica.idMecanica)}
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
