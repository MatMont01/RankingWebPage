// Archivo: src/services/eventoPuntajeService.ts

import {API} from './api';
import type {Empleado} from './empleadoService';

export interface EventoPuntaje {
    idEvento: number;
    empleado: Empleado;
    origenPuntos: string;
    puntosOtorgados: number;
    fecha: string;
    descripcion: string;
}

// Suponiendo que tienes un endpoint en el backend en /api/eventos-puntaje
export const getEventosPuntaje = () =>
    API.get<EventoPuntaje[]>('/eventos-puntaje').then(res => res.data);