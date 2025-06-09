import {API} from './api';

export interface Persona {
    idPersona?: number;
    nombre: string;
    correo: string;
}

export const getPersonas = () => API.get<Persona[]>('/personas').then(res => res.data);
export const getPersona = (id: number) => API.get<Persona>(`/personas/${id}`).then(res => res.data);
export const createPersona = (data: Persona) => API.post<Persona>('/personas', data).then(res => res.data);
export const updatePersona = (id: number, data: Persona) => API.put<Persona>(`/personas/${id}`, data).then(res => res.data);
export const deletePersona = (id: number) => API.delete(`/personas/${id}`).then(res => res.data);
