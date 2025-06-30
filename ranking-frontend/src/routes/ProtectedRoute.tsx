// Archivo: src/routes/ProtectedRoute.tsx

import { Navigate, useLocation } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import type {JSX} from "react";

export const ProtectedRoute = ({ children }: { children: JSX.Element }) => {
    const { isAuthenticated, isLoading } = useAuth();
    const location = useLocation();

    // Mientras se verifica el token del localStorage, mostramos un mensaje.
    if (isLoading) {
        return <div>Cargando sesión...</div>;
    }

    // Si no está autenticado, lo redirigimos al login.
    // Guardamos la página que intentaba visitar para redirigirlo allí después.
    if (!isAuthenticated) {
        return <Navigate to="/login" state={{ from: location }} replace />;
    }

    // Si está autenticado, mostramos la página solicitada.
    return children;
};