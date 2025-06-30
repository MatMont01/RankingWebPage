// Archivo: src/services/historialService.ts

import { API } from './api';
import type { Empleado } from './empleadoService';

export interface EventoPuntaje {
    idEvento: number;
    empleado: Empleado;
    origenPuntos: string;
    puntosOtorgados: number;
    fecha: string;
    descripcion: string;
}

// La función ahora pide el ID del empleado
export const getHistorialEventosPorEmpleado = (idEmpleado: number) =>
    API.get<EventoPuntaje[]>(`/eventos-puntaje/empleado/${idEmpleado}`).then(res => res.data);