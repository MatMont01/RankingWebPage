import axios from 'axios';

export const API = axios.create({
    baseURL: 'http://localhost:8081/api',
});

// Interceptor para añadir el token a las cabeceras de cada petición
API.interceptors.request.use(config => {
    const token = localStorage.getItem('authToken');
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
}, error => {
    return Promise.reject(error);
});