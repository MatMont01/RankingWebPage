import {useState, type FormEvent} from 'react';
import {useAuth} from '../context/AuthContext';
import {useNavigate, useLocation, Link} from 'react-router-dom';

export default function LoginPage() {
    const [usuario, setUsuario] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const {login} = useAuth();
    const navigate = useNavigate();
    const location = useLocation();

    const from = location.state?.from?.pathname || "/";

    const handleSubmit = async (e: FormEvent) => {
        e.preventDefault();
        setError('');
        try {
            await login({usuario, password});
            navigate(from, {replace: true});
        } catch (err) {
            setError('Usuario o contraseña incorrectos.');
        }
    };

    return (
        <div className="flex items-center justify-center min-h-screen bg-gray-100 dark:bg-gray-900">
            <div className="w-full max-w-md p-8 space-y-6 bg-white rounded-lg shadow-md dark:bg-gray-800">
                <h1 className="text-2xl font-bold text-center text-gray-900 dark:text-white">
                    Iniciar Sesión
                </h1>
                <form onSubmit={handleSubmit} className="space-y-6">
                    <div>
                        <label htmlFor="usuario"
                               className="block mb-2 text-sm font-medium text-gray-900 dark:text-gray-300 text-left">Usuario</label>
                        <input id="usuario" type="text" value={usuario} onChange={(e) => setUsuario(e.target.value)}
                               className="w-full px-3 py-2 border rounded-lg dark:bg-gray-700 dark:border-gray-600 dark:text-white"
                               required/>
                    </div>
                    <div>
                        <label htmlFor="password"
                               className="block mb-2 text-sm font-medium text-gray-900 dark:text-gray-300 text-left">Contraseña</label>
                        <input id="password" type="password" value={password}
                               onChange={(e) => setPassword(e.target.value)}
                               className="w-full px-3 py-2 border rounded-lg dark:bg-gray-700 dark:border-gray-600 dark:text-white"
                               required/>
                    </div>
                    {error && <p className="text-sm text-red-500 text-center">{error}</p>}
                    <div>
                        <button type="submit"
                                className="w-full px-4 py-2 font-bold text-white bg-blue-600 rounded-lg hover:bg-blue-700">Entrar
                        </button>
                    </div>
                    <p className="text-sm text-center text-gray-600 dark:text-gray-400">
                        ¿No tienes una cuenta? <Link to="/register"
                                                     className="font-medium text-blue-600 hover:underline">Regístrate</Link>
                    </p>
                </form>
            </div>
        </div>
    );
}