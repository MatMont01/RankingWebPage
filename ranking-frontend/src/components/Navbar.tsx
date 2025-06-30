// Archivo: src/components/Navbar.tsx

import { Link, NavLink } from "react-router-dom";
import { useAuth } from "../context/AuthContext";

// Define los enlaces que se mostrarán cuando el usuario esté autenticado
const navLinks = [
    { to: "/", label: "Ranking" },
    { to: "/temporadas", label: "Temporadas" },
    { to: "/empleados", label: "Empleados" },
    { to: "/cursos", label: "Cursos" },
    { to: "/asistencias", label: "Asistencias" },
    { to: "/acciones", label: "Acciones" },
    { to: "/historial", label: "Historial" },
];

export default function Navbar() {
    const { isAuthenticated, user, logout } = useAuth();

    // Estilo para el enlace activo, para una mejor experiencia de usuario
    const activeLinkStyle = {
        backgroundColor: '#1d4ed8', // Un azul más oscuro
        fontWeight: '600',
    };

    return (
        <nav className="bg-blue-700 text-white px-4 py-3 shadow-md">
            <div className="max-w-7xl mx-auto flex justify-between items-center">
                <Link to="/" className="font-extrabold text-lg tracking-tight">
                    🏆 Ranking Gamificado
                </Link>

                {isAuthenticated && (
                    <div className="flex items-center gap-6">
                        <ul className="flex gap-2">
                            {navLinks.map(link => (
                                <li key={link.to}>
                                    <NavLink
                                        to={link.to}
                                        style={({ isActive }) => isActive ? activeLinkStyle : {}}
                                        className="px-3 py-2 rounded-md text-sm font-medium hover:bg-blue-800 transition-colors"
                                    >
                                        {link.label}
                                    </NavLink>
                                </li>
                            ))}
                        </ul>

                        <div className="flex items-center gap-4 border-l border-blue-500 pl-6">
                            <span className="text-sm">Hola, {user?.usuario}</span>
                            <button
                                onClick={logout}
                                className="bg-red-600 px-3 py-1.5 text-sm font-semibold rounded-md hover:bg-red-700 transition-colors"
                            >
                                Cerrar Sesión
                            </button>
                        </div>
                    </div>
                )}
            </div>
        </nav>
    );
}