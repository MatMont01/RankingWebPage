import {API} from './api';

export interface Mecanica {
    idMecanica?: number;
    nombre: string;
    descripcion: string;
}

export const getMecanicas = () => API.get<Mecanica[]>('/mecanicas').then(res => res.data);
export const getMecanica = (id: number) => API.get<Mecanica>(`/mecanicas/${id}`).then(res => res.data);
export const createMecanica = (data: Mecanica) => API.post<Mecanica>('/mecanicas', data).then(res => res.data);
export const updateMecanica = (id: number, data: Mecanica) => API.put<Mecanica>(`/mecanicas/${id}`, data).then(res => res.data);
export const deleteMecanica = (id: number) => API.delete(`/mecanicas/${id}`).then(res => res.data);
