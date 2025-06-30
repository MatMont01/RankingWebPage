import {API} from './api';

export interface ReconocimientoRequest {
    idOtorgante: number;
    idReceptor: number;
    motivo: string;
    puntosOtorgados?: number;
}

export const darReconocimiento = (data: ReconocimientoRequest) =>
    API.post('/reconocimientos', data).then(res => res.data);