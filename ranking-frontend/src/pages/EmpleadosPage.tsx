// Archivo: src/pages/EmpleadosPage.tsx

import {useEffect, useState} from "react";
import {getEmpleados, deleteEmpleado, type Empleado} from "../services/empleadoService";

export default function EmpleadosPage() {
    const [empleados, setEmpleados] = useState<Empleado[]>([]);
    const [loading, setLoading] = useState(true);

    const fetchEmpleados = async () => {
        setLoading(true);
        try {
            const data = await getEmpleados();
            setEmpleados(data);
        } catch (error) {
            console.error("Error al obtener los empleados:", error);
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchEmpleados();
    }, []);

    const handleDelete = async (id: number) => {
        if (window.confirm("¿Estás seguro de que quieres eliminar a este empleado? Esta acción es irreversible.")) {
            try {
                await deleteEmpleado(id);
                fetchEmpleados();
            } catch (error) {
                alert("No se pudo eliminar al empleado.");
            }
        }
    };

    return (
        <div className="max-w-4xl mx-auto p-6">
            <h1 className="text-2xl font-bold mb-6">Gestión de Empleados</h1>
            <p className="mb-6 text-gray-600 dark:text-gray-400">
                Los nuevos empleados se añaden a través de la página de <a href="/register"
                                                                           className="text-blue-500 hover:underline">Registro</a>.
            </p>

            {loading ? (
                <div className="text-center">Cargando empleados...</div>
            ) : (
                <div className="bg-white dark:bg-gray-800 rounded-lg shadow-md border dark:border-gray-700">
                    <ul className="divide-y dark:divide-gray-700">
                        {empleados.map((empleado) => (
                            <li
                                key={empleado.idEmpleado}
                                className="flex items-center justify-between p-4"
                            >
                                <div>
                                    <span className="font-medium dark:text-white">{empleado.nombre}</span>
                                    <span className="text-gray-500 ml-4 dark:text-gray-400">@{empleado.usuario}</span>
                                </div>
                                <button
                                    className="text-sm bg-red-500 text-white rounded px-3 py-1 hover:bg-red-600"
                                    onClick={() => handleDelete(empleado.idEmpleado)}
                                >
                                    Eliminar
                                </button>
                            </li>
                        ))}
                    </ul>
                </div>
            )}
        </div>
    );
}