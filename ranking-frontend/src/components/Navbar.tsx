import { Link, useLocation } from "react-router-dom";

const navLinks = [
    { to: "/", label: "Ranking" },
    { to: "/personas", label: "Personas" },
    { to: "/temporadas", label: "Temporadas" },
    { to: "/mecanicas", label: "Mecánicas" },
    { to: "/puntajes", label: "Puntajes" },
    { to: "/eventos-mecanica", label: "Eventos" },
];

export default function Navbar() {
    const location = useLocation();

    return (
        <nav className="bg-blue-700 text-white px-4 py-3 mb-8 shadow">
            <div className="max-w-5xl mx-auto flex gap-4 items-center">
        <span className="font-extrabold text-lg mr-6 tracking-tight">
          Gamified Ranking
        </span>
                <ul className="flex gap-4">
                    {navLinks.map(link => (
                        <li key={link.to}>
                            <Link
                                to={link.to}
                                className={`px-3 py-1 rounded hover:bg-blue-800 transition ${
                                    location.pathname === link.to
                                        ? "bg-blue-900 font-semibold"
                                        : ""
                                }`}
                            >
                                {link.label}
                            </Link>
                        </li>
                    ))}
                </ul>
            </div>
        </nav>
    );
}
