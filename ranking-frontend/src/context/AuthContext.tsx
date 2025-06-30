// Archivo: src/context/AuthContext.tsx

import {createContext, useState, useContext, useEffect, type ReactNode} from 'react';
import {type AuthRequest, login as loginService, type AuthResponse} from '../services/authService';

interface User {
    usuario: string;
}

interface AuthContextType {
    isAuthenticated: boolean;
    user: User | null; // Almacenaremos la info del usuario
    login: (data: AuthRequest) => Promise<void>;
    logout: () => void;
    isLoading: boolean;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

export const AuthProvider = ({children}: { children: ReactNode }) => {
    const [user, setUser] = useState<User | null>(null);
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        const storedToken = localStorage.getItem('authToken');
        const storedUser = localStorage.getItem('authUser');
        if (storedToken && storedUser) {
            setUser(JSON.parse(storedUser));
        }
        setIsLoading(false);
    }, []);

    const login = async (data: AuthRequest) => {
        const response: AuthResponse = await loginService(data);
        const userData = {usuario: response.usuario};

        setUser(userData);
        localStorage.setItem('authToken', response.token);
        localStorage.setItem('authUser', JSON.stringify(userData));
    };

    const logout = () => {
        setUser(null);
        localStorage.removeItem('authToken');
        localStorage.removeItem('authUser');
    };

    const value = {
        isAuthenticated: !!user,
        user,
        login,
        logout,
        isLoading
    };

    return (
        <AuthContext.Provider value={value}>
            {children}
        </AuthContext.Provider>
    );
};

export const useAuth = () => {
    const context = useContext(AuthContext);
    if (context === undefined) {
        throw new Error('useAuth debe ser usado dentro de un AuthProvider');
    }
    return context;
};