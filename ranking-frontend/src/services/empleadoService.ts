import {API} from './api';

export interface Empleado {
    idEmpleado: number;
    nombre: string;
    correo: string;
    usuario: string;
    fechaCreacion: string;
}

export const getEmpleados = () => API.get<Empleado[]>('/empleados').then(res => res.data);

export const deleteEmpleado = (id: number) => API.delete(`/empleados/${id}`);
export const getEmpleadoPorUsuario = (usuario: string) =>
    API.get<Empleado>(`/empleados/usuario/${usuario}`).then(res => res.data);