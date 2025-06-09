import {API} from './api';

export interface Temporada {
    idTemporada?: number;
    nombre: string;
    fechaInicio: string;
    fechaFin: string;
    estado: string;
}

export const getTemporadas = () => API.get<Temporada[]>('/temporadas').then(res => res.data);
export const getTemporada = (id: number) => API.get<Temporada>(`/temporadas/${id}`).then(res => res.data);
export const createTemporada = (data: Temporada) => API.post<Temporada>('/temporadas', data).then(res => res.data);
export const updateTemporada = (id: number, data: Temporada) => API.put<Temporada>(`/temporadas/${id}`, data).then(res => res.data);
export const deleteTemporada = (id: number) => API.delete(`/temporadas/${id}`).then(res => res.data);
