import { API } from './api';

export interface AuthRequest {
    usuario: string;
    password?: string;
}

export interface AuthResponse {
    token: string;
    usuario: string;
}

export interface RegistroRequest extends AuthRequest {
    nombre: string;
    correo: string;
}

export const login = (data: AuthRequest) =>
    API.post<AuthResponse>('/auth/login', data).then(res => res.data);

export const registro = (data: RegistroRequest) =>
    API.post('/auth/registro', data).then(res => res.data);