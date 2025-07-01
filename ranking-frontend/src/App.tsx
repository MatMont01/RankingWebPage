import {BrowserRouter} from "react-router-dom";
import Navbar from "./components/Navbar";
import {AppRoutes} from "./routes";
import {AuthProvider} from "./context/AuthContext";

export default function App() {
    return (
        <BrowserRouter>
            <AuthProvider>
                <div className="min-h-screen bg-gray-100 dark:bg-gray-900 transition-colors duration-300">
                    <Navbar/>
                    <AppRoutes/>
                </div>
            </AuthProvider>
        </BrowserRouter>
    );
}