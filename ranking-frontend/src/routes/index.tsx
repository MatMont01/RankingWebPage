import PersonasPage from "../pages/PersonasPage";
import TemporadasPage from "../pages/TemporadasPage";
import MecanicasPage from "../pages/MecanicasPage";
import PuntajesPage from "../pages/PuntajesPage";
import EventosMecanicaPage from "../pages/EventosMecanicaPage";
import NotFoundPage from "../pages/NotFoundPage";
import RankingPage from "../pages/RankingPage.tsx";
import {Navigate, Route, Routes} from "react-router-dom";

export function AppRoutes() {
    return (
        <Routes>
            <Route path="/" element={<RankingPage/>}/>
            <Route path="/personas" element={<PersonasPage/>}/>
            <Route path="/temporadas" element={<TemporadasPage/>}/>
            <Route path="/mecanicas" element={<MecanicasPage/>}/>
            <Route path="/puntajes" element={<PuntajesPage/>}/>
            <Route path="/eventos-mecanica" element={<EventosMecanicaPage/>}/>
            {/* Redirección y 404 */}
            <Route path="/home" element={<Navigate to="/"/>}/>
            <Route path="*" element={<NotFoundPage/>}/>
        </Routes>
    );
}
