import { API } from './api';

export interface AsistenciaRequest {
    idEmpleado: number;
    fecha: string;
    horaLlegada: string;
    estadoPuntualidad: 'PUNTUAL' | 'TARDE';
}


export const registrarAsistencia = (data: AsistenciaRequest) =>
    API.post('/asistencia', data).then(res => res.data);