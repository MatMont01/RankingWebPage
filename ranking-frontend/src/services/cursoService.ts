import {API} from './api';

export interface Curso {
    idCurso: number;
    nombre: string;
    descripcion: string;
    puntosAlCompletar: number;
}

export interface CursoCompletadoRequest {
    idEmpleado: number;
    idCurso: number;
    fechaCompletado: string;
}

export const getCursos = () => API.get<Curso[]>('/cursos').then(res => res.data);

export const createCurso = (data: Omit<Curso, 'idCurso'>) =>
    API.post<Curso>('/cursos', data).then(res => res.data);

export const completarCurso = (data: CursoCompletadoRequest) =>
    API.post('/cursos/completar', data).then(res => res.data);