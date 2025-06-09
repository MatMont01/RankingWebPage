import {useEffect, useState, type FormEvent} from "react";
import {
    getPersonas,
    createPersona,
    deletePersona,
    type Persona
} from "../services/personaService";

export default function PersonasPage() {
    const [personas, setPersonas] = useState<Persona[]>([]);
    const [nombre, setNombre] = useState("");
    const [correo, setCorreo] = useState("");
    const [loading, setLoading] = useState(false);

    const fetchPersonas = async () => {
        setLoading(true);
        const data = await getPersonas();
        setPersonas(data);
        setLoading(false);
    };

    useEffect(() => {
        fetchPersonas();
    }, []);

    const handleSubmit = async (e: FormEvent) => {
        e.preventDefault();
        if (!nombre) return;
        await createPersona({nombre, correo});
        setNombre("");
        setCorreo("");
        fetchPersonas();
    };

    const handleDelete = async (id: number | undefined) => {
        if (!id) return;
        await deletePersona(id);
        fetchPersonas();
    };

    return (
        <div className="max-w-2xl mx-auto p-6">
            <h1 className="text-2xl font-bold mb-6">Personas</h1>

            <form onSubmit={handleSubmit} className="mb-6 flex gap-2">
                <input
                    className="border rounded px-2 py-1"
                    placeholder="Nombre"
                    value={nombre}
                    onChange={e => setNombre(e.target.value)}
                    required
                />
                <input
                    className="border rounded px-2 py-1"
                    placeholder="Correo"
                    type="email"
                    value={correo}
                    onChange={e => setCorreo(e.target.value)}
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
                    {personas.map((persona) => (
                        <li
                            key={persona.idPersona}
                            className="flex items-center justify-between bg-white border rounded p-2 shadow-sm"
                        >
                            <div>
                                <span className="font-medium">{persona.nombre}</span>
                                {persona.correo && (
                                    <span className="text-gray-500 ml-2">{persona.correo}</span>
                                )}
                            </div>
                            <button
                                className="text-sm bg-red-500 text-white rounded px-3 py-1 hover:bg-red-600"
                                onClick={() => handleDelete(persona.idPersona)}
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
