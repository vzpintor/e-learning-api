# E-Learning API

## Requisitos

- Java 11 o superior
- PostgreSQL

## Configuración

1. Clonar el repositorio
2. Configurar la base de datos en el archivo `application.properties`
3. Ejecutar el proyecto con `mvn spring-boot:run`

## Endpoints

### Cursos
- `GET /api/courses` - Obtener todos los cursos
- `POST /api/courses` - Crear un nuevo curso (Solo profesores)
- ...

### Lecciones
- `GET /api/lessons` - Obtener todas las lecciones
- `POST /api/lessons` - Crear una nueva lección (Solo profesores)
- ...

### Preguntas
- `GET /api/questions` - Obtener todas las preguntas
- `POST /api/questions` - Crear una nueva pregunta (Solo profesores)
- ...

## Librerías utilizadas

- **Spring Boot:** Para la creación de la API.
- **Spring Data JPA:** Para la interacción con la base de datos.
- **Spring Security:** Para la autenticación y autorización.
- **PostgreSQL Driver:** Para la conexión con la base de datos PostgreSQL.

