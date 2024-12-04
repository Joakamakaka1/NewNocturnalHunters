# Proyecto API Rest - Gestión de Clientes y Partidas

## Descripción del Proyecto

Este proyecto consiste en el desarrollo de una API Rest utilizando **Spring Boot**, **JWT** y **Hibernate**. La API permite gestionar los datos de los clientes y sus partidas, así como consultar información sobre personajes y enemigos en un juego. Esta solución permite una integración eficiente y segura para los usuarios, garantizando el control de acceso mediante tokens JWT.

## Objetivo del Proyecto

El objetivo principal de esta API es proporcionar una plataforma eficiente para gestionar los datos de los jugadores (clientes), sus partidas, y la consulta de personajes y enemigos del juego. A través de una arquitectura REST, los usuarios pueden interactuar con el sistema de manera sencilla y segura.

## Justificación del Proyecto

Este sistema es esencial para los desarrolladores de videojuegos que necesitan almacenar y gestionar datos relacionados con los usuarios, el progreso en sus partidas, así como las características de los personajes y enemigos del juego. Implementando Spring Boot y JWT, proporcionamos una solución robusta, escalable y segura. Además, Hibernate nos permite interactuar de manera eficiente con la base de datos, simplificando el proceso de persistencia de datos.

## Tablas Involucradas

### 1. Tabla de **Clientes**
Contiene la información básica de los usuarios registrados en el sistema.

- **ID**: Campo autoincremental, clave primaria.
- **Nombre**: Cadena de texto, no nulo, único.
- **Email**: Cadena de texto, no nulo, único.
- **Contraseña**: Cadena de texto, no nulo.

### 2. Tabla de **Partidas**
Almacena los detalles de las partidas jugadas por los clientes.

- **ID**: Campo autoincremental, clave primaria.
- **Resultado**: Cadena de texto que indica el resultado de la partida (ej. victoria, derrota).
- **Duración**: Tiempo en el que se jugó la partida.
- **Fecha de inicio**: Fecha y hora en la que comenzó la partida.

### 3. Tabla de **Personajes**
Contiene los datos de los personajes disponibles en el juego.

- **ID**: Campo autoincremental, clave primaria.
- **Nombre**: Cadena de texto, no nulo, único.
- **Estadísticas**: Datos numéricos que indican las características del personaje (por ejemplo, fuerza, agilidad).
- **Tipo**: Enum con tres posibles valores: `básico`, `intermedio`, `boss`.

### 4. Tabla de **Enemigos**
Almacena los datos de los enemigos del juego.

- **ID**: Campo autoincremental, clave primaria.
- **Nombre**: Cadena de texto, no nulo, único.
- **Estadísticas**: Datos numéricos que indican las características del enemigo.
- **Tipo**: Enum con tres posibles valores: `básico`, `intermedio`, `boss`.

## Requisitos

- **Java 17** o superior
- **Spring Boot** 2.7.x o superior
- **JWT (JSON Web Tokens)** para autenticación y autorización
- **Hibernate** para la gestión de la persistencia en la base de datos
- **Maven** o **Gradle** para la gestión de dependencias
- **Base de Datos**: MySQL, PostgreSQL o cualquier sistema compatible con Hibernate
- **Postman** o herramientas similares para probar la API
