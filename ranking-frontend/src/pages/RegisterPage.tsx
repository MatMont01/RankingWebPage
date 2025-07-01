import {useState, type FormEvent} from 'react';
import {useNavigate, Link} from 'react-router-dom';
import {useAuth} from '../context/AuthContext';
import {registro} from '../services/authService';

export default function RegisterPage() {
    const [nombre, setNombre] = useState('');
    const [correo, setCorreo] = useState('');
    const [usuario, setUsuario] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const {login} = useAuth();
    const navigate = useNavigate();

    const handleSubmit = async (e: FormEvent) => {
        e.preventDefault();
        setError('');
        try {
            await registro({nombre, correo, usuario, password});
            await login({usuario, password});
            navigate('/');
        } catch (err: any) {
            setError(err.response?.data || 'Error en el registro.');
        }
    };

    return (
        <div className="flex items-center justify-center min-h-screen bg-gray-100 dark:bg-gray-900">
            <div className="w-full max-w-md p-8 space-y-6 bg-white rounded-lg shadow-md dark:bg-gray-800">
                <h1 className="text-2xl font-bold text-center text-gray-900 dark:text-white">Crear Nueva Cuenta</h1>
                <form onSubmit={handleSubmit} className="space-y-4">
                    <div>
                        <label htmlFor="nombre" className="block mb-2 text-sm font-medium text-left">Nombre
                            Completo</label>
                        <input id="nombre" type="text" value={nombre} onChange={(e) => setNombre(e.target.value)}
                               className="w-full px-3 py-2 border rounded-lg dark:bg-gray-700 dark:border-gray-600"
                               required/>
                    </div>
                    <div>
                        <label htmlFor="correo" className="block mb-2 text-sm font-medium text-left">Correo</label>
                        <input id="correo" type="email" value={correo} onChange={(e) => setCorreo(e.target.value)}
                               className="w-full px-3 py-2 border rounded-lg dark:bg-gray-700 dark:border-gray-600"
                               required/>
                    </div>
                    <div>
                        <label htmlFor="usuario" className="block mb-2 text-sm font-medium text-left">Usuario</label>
                        <input id="usuario" type="text" value={usuario} onChange={(e) => setUsuario(e.target.value)}
                               className="w-full px-3 py-2 border rounded-lg dark:bg-gray-700 dark:border-gray-600"
                               required/>
                    </div>
                    <div>
                        <label htmlFor="password"
                               className="block mb-2 text-sm font-medium text-left">Contraseña</label>
                        <input id="password" type="password" value={password}
                               onChange={(e) => setPassword(e.target.value)}
                               className="w-full px-3 py-2 border rounded-lg dark:bg-gray-700 dark:border-gray-600"
                               required/>
                    </div>
                    {error && <p className="text-sm text-red-500 text-center">{error}</p>}
                    <div>
                        <button type="submit"
                                className="w-full mt-2 px-4 py-2 font-bold text-white bg-blue-600 rounded-lg hover:bg-blue-700">Registrarse
                        </button>
                    </div>
                    <p className="text-sm text-center text-gray-600 dark:text-gray-400">
                        ¿Ya tienes una cuenta? <Link to="/login" className="font-medium text-blue-600 hover:underline">Inicia
                        Sesión</Link>
                    </p>
                </form>
            </div>
        </div>
    );
}