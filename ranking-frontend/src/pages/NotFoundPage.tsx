export default function NotFoundPage() {
    return (
        <div className="min-h-screen flex flex-col justify-center items-center bg-gray-100">
            <h1 className="text-6xl font-bold text-blue-700 mb-4">404</h1>
            <h2 className="text-2xl font-semibold mb-2">Página no encontrada</h2>
            <p className="text-gray-600 mb-6">
                La ruta que buscas no existe o fue movida.
            </p>
            <a
                href="/"
                className="bg-blue-600 text-white px-5 py-2 rounded hover:bg-blue-700 transition"
            >
                Ir al inicio
            </a>
        </div>
    );
}
