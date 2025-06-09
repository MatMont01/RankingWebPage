import {BrowserRouter} from "react-router-dom";

import Navbar from "./components/Navbar";
import {AppRoutes} from "./routes";

export default function App() {
    return (
        <BrowserRouter>
            <div className="min-h-screen bg-gray-100 dark:bg-gray-900 transition-colors duration-300">
                <Navbar/>
                <AppRoutes/>
            </div>
        </BrowserRouter>
    );
}
