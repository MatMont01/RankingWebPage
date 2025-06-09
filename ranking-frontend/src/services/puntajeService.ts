import {API} from './api';
import type {Persona} from "./personaService.ts";
import type {Temporada} from "./temporadaService.ts";

export type Puntaje = {
    idPuntaje?: number;
    persona: { idPersona: number } | Persona;
    temporada: { idTemporada: number } | Temporada;
    puntaje: number;
    fechaActualizacion: string;
};

export const getPuntajes = () => API.get<Puntaje[]>('/puntajes').then(res => res.data);
export const getPuntaje = (id: number) => API.get<Puntaje>(`/puntajes/${id}`).then(res => res.data);
export const createPuntaje = (data: Puntaje) => API.post<Puntaje>('/puntajes', data).then(res => res.data);
export const updatePuntaje = (id: number, data: Puntaje) => API.put<Puntaje>(`/puntajes/${id}`, data).then(res => res.data);
export const deletePuntaje = (id: number) => API.delete(`/puntajes/${id}`).then(res => res.data);