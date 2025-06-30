import {API} from './api';

export interface LogroProyectoRequest {
    idEmpleado: number;
    nombreProyecto: string;
    nombreLogro: string;
    puntosOtorgados: number;
}

export const registrarLogro = (data: LogroProyectoRequest) =>
    API.post('/proyectos/logros', data).then(res => res.data);