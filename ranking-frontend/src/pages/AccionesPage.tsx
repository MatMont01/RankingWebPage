// Archivo: src/pages/AccionesPage.tsx

import {useEffect, useState, type FormEvent} from "react";
import {getEmpleados, type Empleado} from "../services/empleadoService";
import {getCursos, completarCurso, type Curso} from "../services/cursoService";
import {darReconocimiento} from "../services/reconocimientoService";
import {registrarLogro} from "../services/logroProyectoService";
import {useAuth} from "../context/AuthContext";

export default function AccionesPage() {
    const {user} = useAuth();
    const [empleados, setEmpleados] = useState<Empleado[]>([]);
    const [cursos, setCursos] = useState<Curso[]>([]);

    const [cursoEmpleadoId, setCursoEmpleadoId] = useState<number | ''>('');
    const [cursoId, setCursoId] = useState<number | ''>('');

    const [receptorId, setReceptorId] = useState<number | ''>('');
    const [motivoReconocimiento, setMotivoReconocimiento] = useState('');

    const [logroEmpleadoId, setLogroEmpleadoId] = useState<number | ''>('');
    const [nombreProyecto, setNombreProyecto] = useState('');
    const [nombreLogro, setNombreLogro] = useState('');
    const [puntosLogro, setPuntosLogro] = useState<number | ''>(20);

    useEffect(() => {
        getEmpleados().then(setEmpleados);
        getCursos().then(setCursos);
    }, []);

    const handleCompletarCurso = async (e: FormEvent) => {
        e.preventDefault();
        if (!cursoEmpleadoId || !cursoId) return alert("Selecciona un empleado y un curso.");

        try {
            await completarCurso({
                idEmpleado: Number(cursoEmpleadoId),
                idCurso: Number(cursoId),
                fechaCompletado: new Date().toISOString().split('T')[0]
            });
            alert("¡Curso completado y puntos asignados!");
            setCursoEmpleadoId('');
            setCursoId('');
        } catch (error) {
            alert("Error al asignar puntos por curso.");
        }
    };

    const handleReconocimiento = async (e: FormEvent) => {
        e.preventDefault();
        const otorgante = empleados.find(emp => emp.usuario === user?.usuario);

        if (!otorgante) return alert("No se pudo identificar al usuario que otorga el reconocimiento.");
        if (!receptorId) return alert("Selecciona un empleado para reconocer.");

        try {
            await darReconocimiento({
                idOtorgante: otorgante.idEmpleado,
                idReceptor: Number(receptorId),
                motivo: motivoReconocimiento
            });
            alert("¡Reconocimiento enviado con éxito!");
            setReceptorId('');
            setMotivoReconocimiento('');
        } catch (error) {
            alert("Error al enviar el reconocimiento.");
        }
    };

    const handleRegistrarLogro = async (e: FormEvent) => {
        e.preventDefault();
        if (!logroEmpleadoId || !nombreProyecto || !nombreLogro || !puntosLogro) return alert("Completa todos los campos del logro.");

        try {
            await registrarLogro({
                idEmpleado: Number(logroEmpleadoId),
                nombreProyecto,
                nombreLogro,
                puntosOtorgados: Number(puntosLogro)
            });
            alert("¡Logro registrado y puntos asignados!");
            setLogroEmpleadoId('');
            setNombreProyecto('');
            setNombreLogro('');
            setPuntosLogro(20);
        } catch (error) {
            alert("Error al registrar el logro.");
        }
    }

    const empleadoLogueadoId = empleados.find(e => e.usuario === user?.usuario)?.idEmpleado;

    return (
        <div className="max-w-4xl mx-auto p-6 space-y-8">
            <h1 className="text-2xl font-bold mb-6">Panel de Acciones</h1>

            <div className="p-4 bg-white dark:bg-gray-800 rounded-lg shadow-sm border dark:border-gray-700">
                <h2 className="text-xl font-semibold mb-4 dark:text-white">Marcar Curso como Completado</h2>
                <form onSubmit={handleCompletarCurso} className="flex gap-4 flex-wrap items-end">
                    <select value={cursoEmpleadoId} onChange={e => setCursoEmpleadoId(Number(e.target.value))} required
                            className="border rounded px-2 py-2 dark:bg-gray-700 dark:border-gray-600">
                        <option value="" disabled>Selecciona Empleado</option>
                        {empleados.map(e => <option key={e.idEmpleado} value={e.idEmpleado}>{e.nombre}</option>)}
                    </select>
                    <select value={cursoId} onChange={e => setCursoId(Number(e.target.value))} required
                            className="border rounded px-2 py-2 dark:bg-gray-700 dark:border-gray-600">
                        <option value="" disabled>Selecciona Curso</option>
                        {cursos.map(c => <option key={c.idCurso} value={c.idCurso}>{c.nombre}</option>)}
                    </select>
                    <button type="submit"
                            className="bg-green-600 text-white px-4 py-2 rounded hover:bg-green-700">Asignar Puntos
                    </button>
                </form>
            </div>

            <div className="p-4 bg-white dark:bg-gray-800 rounded-lg shadow-sm border dark:border-gray-700">
                <h2 className="text-xl font-semibold mb-4 dark:text-white">Dar Reconocimiento</h2>
                <form onSubmit={handleReconocimiento} className="flex gap-4 flex-wrap items-end">
                    <select value={receptorId} onChange={e => setReceptorId(Number(e.target.value))} required
                            className="border rounded px-2 py-2 dark:bg-gray-700 dark:border-gray-600">
                        <option value="" disabled>Empleado a reconocer</option>
                        {empleados.filter(e => e.idEmpleado !== empleadoLogueadoId).map(e => <option key={e.idEmpleado}
                                                                                                     value={e.idEmpleado}>{e.nombre}</option>)}
                    </select>
                    <input value={motivoReconocimiento} onChange={e => setMotivoReconocimiento(e.target.value)}
                           placeholder="Motivo del reconocimiento"
                           className="border rounded px-2 py-2 flex-grow dark:bg-gray-700 dark:border-gray-600"
                           required/>
                    <button type="submit"
                            className="bg-yellow-500 text-white px-4 py-2 rounded hover:bg-yellow-600">Reconocer
                    </button>
                </form>
            </div>

            <div className="p-4 bg-white dark:bg-gray-800 rounded-lg shadow-sm border dark:border-gray-700">
                <h2 className="text-xl font-semibold mb-4 dark:text-white">Registrar Logro de Proyecto</h2>
                <form onSubmit={handleRegistrarLogro} className="flex gap-4 flex-wrap items-end">
                    <select value={logroEmpleadoId} onChange={e => setLogroEmpleadoId(Number(e.target.value))} required
                            className="border rounded px-2 py-2 dark:bg-gray-700 dark:border-gray-600">
                        <option value="" disabled>Selecciona Empleado</option>
                        {empleados.map(e => <option key={e.idEmpleado} value={e.idEmpleado}>{e.nombre}</option>)}
                    </select>
                    <input value={nombreProyecto} onChange={e => setNombreProyecto(e.target.value)}
                           placeholder="Nombre del Proyecto"
                           className="border rounded px-2 py-2 dark:bg-gray-700 dark:border-gray-600" required/>
                    <input value={nombreLogro} onChange={e => setNombreLogro(e.target.value)}
                           placeholder="Logro conseguido"
                           className="border rounded px-2 py-2 flex-grow dark:bg-gray-700 dark:border-gray-600"
                           required/>
                    <input value={puntosLogro} onChange={e => setPuntosLogro(Number(e.target.value))} type="number"
                           placeholder="Puntos"
                           className="border rounded px-2 py-2 w-24 dark:bg-gray-700 dark:border-gray-600" required/>
                    <button type="submit"
                            className="bg-purple-600 text-white px-4 py-2 rounded hover:bg-purple-700">Registrar Logro
                    </button>
                </form>
            </div>
        </div>
    );
}