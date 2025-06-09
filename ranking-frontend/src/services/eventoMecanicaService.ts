import {API} from './api';
import type {Persona} from "./personaService.ts";
import type {Mecanica} from "./mecanicaService.ts";
import type {Temporada} from "./temporadaService.ts";

export type EventoMecanica = {
    idEvento?: number;
    persona: { idPersona: number } | Persona;
    mecanica: { idMecanica: number } | Mecanica;
    temporada: { idTemporada: number } | Temporada;
    fecha: string;
    puntosOtorgados: number;
};

export const getEventosMecanica = () => API.get<EventoMecanica[]>('/eventos-mecanica').then(res => res.data);
export const getEventoMecanica = (id: number) => API.get<EventoMecanica>(`/eventos-mecanica/${id}`).then(res => res.data);
export const createEventoMecanica = (data: EventoMecanica) => API.post<EventoMecanica>('/eventos-mecanica', data).then(res => res.data);
export const updateEventoMecanica = (id: number, data: EventoMecanica) => API.put<EventoMecanica>(`/eventos-mecanica/${id}`, data).then(res => res.data);
export const deleteEventoMecanica = (id: number) => API.delete(`/eventos-mecanica/${id}`).then(res => res.data);