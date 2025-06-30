import { API } from './api';
import type { Empleado } from './empleadoService';
import type { Temporada } from './temporadaService';

export interface Puntaje {
    idPuntaje: number;
    empleado: Empleado;
    temporada: Temporada;
    puntajeTotal: number;
    fechaActualizacion: string;
}

export const getRankingPorTemporada = (idTemporada: number) =>
    API.get<Puntaje[]>(`/ranking/temporada/${idTemporada}`).then(res => res.data);