import { API } from './api';

export interface AsistenciaRequest {
    idEmpleado: number;
    fecha: string; // Formato YYYY-MM-DD
    horaLlegada: string; // Formato HH:MM:SS
    estadoPuntualidad: 'PUNTUAL' | 'TARDE';
}

// Nota: Asume que el endpoint en el backend es /api/asistencia
export const registrarAsistencia = (data: AsistenciaRequest) =>
    API.post('/asistencia', data).then(res => res.data);