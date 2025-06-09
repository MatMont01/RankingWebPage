# Ranking Gamificado

Sistema web completo para gestionar un ranking gamificado de empleados (o cualquier grupo), aplicando mecánicas de gamificación.  
Incluye backend en Java Spring Boot, frontend en React + Vite + TailwindCSS y base de datos PostgreSQL.

---

## Características principales

- Visualización de ranking de personas ordenado por puntaje.
- Distinción visual de los 3 primeros lugares con medallas.
- Ranking por temporada (incluye historial y reinicio).
- Gestión de personas, temporadas, mecánicas y eventos.
- CRUD completo desde interfaz web.
- Soporte para **dark mode** en la interfaz.
- API REST segura y sencilla.
- Separación de frontend y backend.

---

## Tecnologías utilizadas

- **Frontend:** React + Vite + TypeScript + TailwindCSS
- **Backend:** Java 21+ Spring Boot
- **Base de datos:** PostgreSQL
- **Gestor de dependencias:** npm/yarn (frontend), Maven/Gradle (backend)

---

## Estructura del proyecto

/backend/ # Código fuente Spring Boot
/frontend/ # Código fuente React + Vite


---

### Estructura del Frontend

src/
├── assets/ # Imágenes y recursos estáticos
├── components/ # Componentes reutilizables (Navbar, etc.)
├── pages/ # Cada pantalla de la app (Personas, Ranking, etc.)
├── routes/ # Definición de rutas de la SPA
├── services/ # Llamadas a la API
├── App.tsx
└── main.tsx


---

### Estructura del Backend

src/
├── controller/ # Controladores REST
├── service/ # Lógica de negocio
├── repository/ # Interfaces para acceso a datos
└── model/ # Entidades JPA


---

## Instalación y ejecución

### 1. Clona el repositorio

2. Backend

    Configura PostgreSQL y crea una base de datos (por ejemplo, rankingdb).

    Ajusta las variables de conexión (application.properties).

    Ejecuta:

cd backend
./mvnw spring-boot:run

o

./gradlew bootRun

La API REST quedará disponible en http://localhost:8081/api
3. Frontend

cd frontend
npm install
npm run dev

La app estará en http://localhost:5173
Uso

    Ingresa a la web y navega por los módulos: Personas, Temporadas, Mecánicas, Puntajes, Eventos.

    Registra datos y observa cómo el ranking principal cambia en tiempo real.

    Modo oscuro: El sistema soporta dark mode automáticamente. Puedes forzarlo agregando la clase dark al <html> o usando un switch.

Configuración de CORS (si es necesario)

Si consumes la API desde otro puerto, habilita CORS en el backend:

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins("http://localhost:5173")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
            }
        };
    }
}

Consideraciones de desarrollo

    No hay autenticación ni roles para simplificar la experiencia.

    Usa ON DELETE CASCADE en las llaves foráneas para facilitar el borrado en cascada (puedes personalizar esto según tu lógica de negocio).

    Todo el diseño es responsive y accesible.

Query de la base de datos:

-- Tabla Persona
CREATE TABLE persona (
    id_persona SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(100)
);

-- Tabla Temporada
CREATE TABLE temporada (
    id_temporada SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    estado VARCHAR(10) NOT NULL CHECK (estado IN ('ACTIVA', 'INACTIVA'))
);

-- Tabla Puntaje (Ranking por temporada)
CREATE TABLE puntaje (
    id_puntaje SERIAL PRIMARY KEY,
    id_persona INTEGER NOT NULL REFERENCES persona(id_persona),
    id_temporada INTEGER NOT NULL REFERENCES temporada(id_temporada),
    puntaje INTEGER NOT NULL DEFAULT 0,
    fecha_actualizacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UNIQUE(id_persona, id_temporada) -- Solo un puntaje por persona/temporada
);

-- Tabla Mecanica (3 mecánicas mínimas)
CREATE TABLE mecanica (
    id_mecanica SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    descripcion TEXT
);

-- Tabla EventoMecanica (Registro de eventos de mecánicas)
CREATE TABLE evento_mecanica (
    id_evento SERIAL PRIMARY KEY,
    id_persona INTEGER NOT NULL REFERENCES persona(id_persona),
    id_mecanica INTEGER NOT NULL REFERENCES mecanica(id_mecanica),
    id_temporada INTEGER NOT NULL REFERENCES temporada(id_temporada),
    fecha TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    puntos_otorgados INTEGER NOT NULL
);

INSERT INTO persona (nombre, correo) VALUES
('Ana Perez', 'ana@ejemplo.com'),
('Carlos López', 'carlos@ejemplo.com');


INSERT INTO temporada (nombre, fecha_inicio, fecha_fin, estado) VALUES
('Temporada 2025', '2025-01-01', '2025-12-31', 'ACTIVA');


INSERT INTO mecanica (nombre, descripcion) VALUES
('Logro por Asistencia', 'Se otorgan puntos por asistir diariamente a la oficina.'),
('Meta Cumplida', 'Se otorgan puntos al completar una meta semanal o mensual.'),
('Colaboración', 'Se otorgan puntos por colaborar con otros compañeros en proyectos o tareas.');

Créditos

Proyecto para la materia Gamificación y Programación Funcional
Desarrollado por Matias Montaño Prado
