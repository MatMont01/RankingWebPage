import {Navigate, Route, Routes} from "react-router-dom";
import {ProtectedRoute} from './ProtectedRoute';
import {useAuth} from "../context/AuthContext";

import LoginPage from "../pages/LoginPage";
import RegisterPage from "../pages/RegisterPage";
import NotFoundPage from "../pages/NotFoundPage";
import RankingPage from "../pages/RankingPage";
import EmpleadosPage from "../pages/EmpleadosPage";
import TemporadasPage from "../pages/TemporadasPage";
import CursosPage from "../pages/CursosPage";
import AccionesPage from "../pages/AccionesPage";
import HistorialEventosPage from "../pages/HistorialEventosPage";
import AsistenciasPage from "../pages/AsistenciasPage";
import type {JSX} from "react";

const PublicRoute = ({children}: { children: JSX.Element }) => {
    const {isAuthenticated} = useAuth();
    return isAuthenticated ? <Navigate to="/"/> : children;
}

export function AppRoutes() {
    return (
        <Routes>
            {/* --- Rutas Públicas --- */}
            <Route path="/login" element={<PublicRoute><LoginPage/></PublicRoute>}/>
            <Route path="/register" element={<PublicRoute><RegisterPage/></PublicRoute>}/>

            {/* --- Rutas Protegidas --- */}
            <Route path="/" element={<ProtectedRoute><RankingPage/></ProtectedRoute>}/>
            <Route path="/temporadas" element={<ProtectedRoute><TemporadasPage/></ProtectedRoute>}/>
            <Route path="/empleados" element={<ProtectedRoute><EmpleadosPage/></ProtectedRoute>}/>
            <Route path="/cursos" element={<ProtectedRoute><CursosPage/></ProtectedRoute>}/>
            <Route path="/asistencias" element={<ProtectedRoute><AsistenciasPage/></ProtectedRoute>}/>
            <Route path="/acciones" element={<ProtectedRoute><AccionesPage/></ProtectedRoute>}/>
            <Route path="/historial" element={<ProtectedRoute><HistorialEventosPage/></ProtectedRoute>}/>

            {/* --- Redirecciones y Página no Encontrada --- */}
            <Route path="/home" element={<Navigate to="/"/>}/>
            <Route path="*" element={<NotFoundPage/>}/>
        </Routes>
    );
}