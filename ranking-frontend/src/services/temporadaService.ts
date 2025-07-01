import {API} from './api';

export interface Temporada {
    idTemporada: number;
    nombre: string;
    fechaInicio: string;
    fechaFin: string;
    estado: 'ACTIVA' | 'INACTIVA' | 'FINALIZADA';
}

export const getTemporadas = () => API.get<Temporada[]>('/temporadas').then(res => res.data);

export const getTemporadaActiva = () => API.get<Temporada>('/temporadas/activa').then(res => res.data);

export const createTemporada = (data: Omit<Temporada, 'idTemporada'>) =>
    API.post<Temporada>('/temporadas', data).then(res => res.data);

export const deleteTemporada = (id: number) => API.delete(`/temporadas/${id}`);

export const updateEstadoTemporada = (id: number, estado: 'ACTIVA' | 'INACTIVA' | 'FINALIZADA') =>
    API.patch(`/temporadas/${id}/estado`, { estado });