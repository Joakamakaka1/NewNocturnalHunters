# Proyecto API Rest - Gestión de Clientes y Partidas

## Descripción del Proyecto

Este proyecto consiste en el desarrollo de una API Rest utilizando **Spring Boot**, **JWT** y **Hibernate**. La API permite gestionar los datos de los clientes y sus partidas, así como consultar información sobre personajes y enemigos en un juego. Esta solución proporciona una plataforma eficiente y segura para gestionar la interacción con los usuarios a través de autenticación basada en **JWT**.

## Objetivo del Proyecto

El objetivo principal de esta API es proporcionar una plataforma eficiente para gestionar los datos de los jugadores (clientes), sus partidas, y la consulta de personajes y enemigos del juego. A través de una arquitectura REST, los usuarios pueden interactuar con el sistema de manera sencilla y segura.

## Justificación del Proyecto

Este sistema es esencial para los desarrolladores de videojuegos que necesitan almacenar y gestionar datos relacionados con los usuarios, el progreso en sus partidas, así como las características de los personajes y enemigos del juego. Implementando **Spring Boot** y **JWT**, garantizamos una solución robusta, escalable y segura. Además, **Hibernate** facilita la interacción eficiente con la base de datos para la persistencia de datos, optimizando la gestión de relaciones.

---

## Tablas Involucradas

### 1. **Clientes**

| Atributo   | Tipo          | Descripción                                                |
|------------|---------------|------------------------------------------------------------|
| **ID**     | Long           | Clave primaria, autoincremental.                           |
| **Nombre** | VARCHAR(255)   | Nombre del cliente (único y no nulo).                      |
| **Email**  | VARCHAR(255)   | Correo electrónico del cliente (único y no nulo).           |
| **Contraseña** | VARCHAR(255) | Contraseña en texto plano (sería recomendable almacenar un hash). |
| **Rol**    | VARCHAR(50)    | Rol del cliente (por ejemplo: `USER`, `ADMIN`).             |

### 2. **Partidas**

| Atributo      | Tipo          | Descripción                                                |
|---------------|---------------|------------------------------------------------------------|
| **ID**        | Long           | Clave primaria, autoincremental.                           |
| **Resultado** | VARCHAR(50)    | Resultado de la partida (`victoria`, `derrota`, etc.).      |
| **Duración**  | INT           | Duración de la partida en minutos.                         |
| **FechaInicio** | DATETIME    | Fecha y hora de inicio de la partida.                       |
| **ClienteID** | INT           | Clave foránea que hace referencia al cliente.               |

### 3. **Personajes**

| Atributo      | Tipo          | Descripción                                                |
|---------------|---------------|------------------------------------------------------------|
| **ID**        | Long           | Clave primaria, autoincremental.                           |
| **Nombre**    | VARCHAR(255)   | Nombre del personaje (único y no nulo).                    |
| **Estadísticas** | INT        | Datos numéricos que indican las características del personaje |
| **Tipo**      | ENUM('básico', 'intermedio', 'boss') | Tipo del personaje. |
| **ClienteID** | INT           | Clave foránea que hace referencia al cliente.               |
| **Imagen**    | VARCHAR(255)   | Ruta o enlace a la imagen del personaje.                    |

### 4. **Enemigos**

| Atributo      | Tipo          | Descripción                                                |
|---------------|---------------|------------------------------------------------------------|
| **ID**        | Long           | Clave primaria, autoincremental.                           |
| **Nombre**    | VARCHAR(255)   | Nombre del enemigo (único y no nulo).                      |
| **Estadísticas** | INT        | Datos numéricos que indican las características del personaje |
| **Tipo**      | ENUM('básico', 'intermedio', 'boss') | Tipo del enemigo. |
| **Imagen**    | VARCHAR(255)   | Ruta o enlace a la imagen del enemigo.                     |

## Relaciones entre Tablas

- **Clientes a Partidas**: Un cliente puede tener muchas partidas (relación **1:N**). La tabla **Partidas** contiene una clave foránea `clienteID` que hace referencia a la tabla **Clientes**.
  
- **Clientes a Personajes**: Un cliente puede tener varios personajes (relación **1:N**). La tabla **Personajes** contiene una clave foránea `clienteID` que hace referencia a la tabla **Clientes**.

---

## Endpoints

A continuación, se detallan los endpoints para cada tabla de la base de datos:

### 1. **Clientes**

| Método | Endpoint              | Descripción                                                  |
|--------|-----------------------|--------------------------------------------------------------|
| **POST** | /clientes/login      | Inicia sesión de un cliente. Requiere email y contraseña. Retorna un token JWT si las credenciales son correctas. |
| **POST** | /clientes/register   | Registra un nuevo cliente. Requiere nombre, email, contraseña y rol. |
| **GET**  | /clientes            | Obtiene todos los clientes registrados. Solo accesible para administradores. |
| **GET**  | /clientes/{id}       | Obtiene los detalles de un cliente específico por su ID. Los clientes solo pueden consultar su propio perfil. |
| **PUT**  | /clientes/{id}       | Permite actualizar los detalles de un cliente. Solo accesible para el mismo cliente o administradores. |
| **DELETE** | /clientes/{id}     | Elimina un cliente por su ID. Solo accesible para el mismo cliente o administradores. |

### 2. **Partidas**

| Método | Endpoint               | Descripción                                                  |
|--------|------------------------|--------------------------------------------------------------|
| **POST** | /partidas              | Crea una nueva partida. Requiere los detalles de la partida y el ID del cliente. Solo accesible para administradores. |
| **GET**  | /partidas              | Recupera todas las partidas jugadas. Solo accesible para administradores. |
| **GET**  | /partidas/{clienteId}  | Obtiene todas las partidas asociadas a un cliente específico. |
| **PUT**  | /partidas/{id}         | Actualiza los detalles de una partida. Solo accesible por administradores. |
| **DELETE** | /partidas/{id}       | Elimina una partida por su ID. Solo accesible por administradores. |

### 3. **Personajes**

| Método | Endpoint               | Descripción                                                  |
|--------|------------------------|--------------------------------------------------------------|
| **POST** | /personajes            | Crea un nuevo personaje. Requiere los detalles del personaje y el ID del cliente. |
| **GET**  | /personajes            | Obtiene todos los personajes registrados. Accesible para todos los usuarios. |
| **GET**  | /personajes/{id}       | Obtiene los detalles de un personaje específico por su ID. Accesible para todos los usuarios. |
| **PUT**  | /personajes/{id}       | Actualiza los detalles de un personaje. Solo accesible por administradores. |
| **DELETE** | /personajes/{id}     | Elimina un personaje por su ID. Solo accesible por administradores. |

### 4. **Enemigos**

| Método | Endpoint               | Descripción                                                  |
|--------|------------------------|--------------------------------------------------------------|
| **POST** | /enemigos             | Crea un nuevo enemigo. Requiere los detalles del enemigo. Solo accesible para administradores. |
| **GET**  | /enemigos             | Obtiene todos los enemigos disponibles. Accesible para todos los usuarios. |
| **GET**  | /enemigos/{id}        | Obtiene los detalles de un enemigo específico por su ID. Accesible para todos los usuarios. |
| **PUT**  | /enemigos/{id}        | Actualiza los detalles de un enemigo. Solo accesible por administradores. |
| **DELETE** | /enemigos/{id}      | Elimina un enemigo por su ID. Solo accesible por administradores. |

---

## Lógica de Negocio

La lógica de negocio está centrada en los siguientes aspectos clave:

- **Autenticación y Autorización**: Utilizamos **JWT** para autenticar y autorizar a los usuarios. Los clientes deben estar autenticados para acceder a sus propios datos de partidas y personajes. Los administradores tienen permisos para gestionar todos los recursos del sistema.
  
- **Gestión de Partidas**: Los clientes pueden iniciar nuevas partidas y ver partidas pasadas. Cada partida está asociada a un cliente.

- **Gestión de Personajes y Enemigos**: Los clientes pueden crear personajes, modificarlos y eliminarlos. Los enemigos son gestionados por los administradores, y se pueden obtener detalles sobre ellos para ajustar las interacciones en las partidas.

---

## Excepciones y Códigos de Estado

A continuación, se detallan las excepciones que maneja la API y sus correspondientes códigos de estado HTTP.

### Excepciones Definidas

| Excepción              | Código de Estado | Descripción                                           |
|------------------------|------------------|-------------------------------------------------------|
| **NotFoundException**   | 404              | Cuando un recurso solicitado no se encuentra en la base de datos. |
| **BadRequestException** | 400              | Cuando la solicitud tiene datos incorrectos o falta información necesaria. |
| **ForbiddenException**  | 403              | Cuando un cliente intenta acceder a un recurso que no tiene permisos para ver o modificar. |
| **DuplicateException**  | 409              | Cuando se intenta crear un recurso con datos duplicados, como un email ya registrado. |
| **UnauthorizedException** | 401            | Cuando un cliente o administrador intenta acceder a un recurso sin un token de autenticación válido o con un token expirado. |
| **ValidationException** | 422              | Cuando los datos enviados en la solicitud no cumplen con las reglas de validación del sistema, como un formato incorrecto de email o un nombre vacío. |
| **MethodNotAllowedException** | 405         | Cuando un cliente o administrador intenta acceder a un endpoint utilizando un método HTTP incorrecto, por ejemplo, un `GET` cuando el endpoint solo acepta un `POST`. |
| **InternalServerErrorException** | 500       | Cuando ocurre un error inesperado en el servidor, como problemas con la base de datos o un fallo no manejado. |

---

### Códigos de Estado

A continuación se detallan los códigos de estado HTTP utilizados por la API:

| Código | Estado                | Descripción                                                 |
|--------|-----------------------|-------------------------------------------------------------|
| **200** | OK                    | La solicitud fue exitosa.                                   |
| **201** | Created               | El recurso ha sido creado correctamente.                    |
| **204** | No Content            | La operación fue exitosa pero no hay contenido para retornar (por ejemplo, al eliminar un recurso). |
| **400** | Bad Request           | La solicitud está mal formada o falta información.          |
| **404** | Not Found             | El recurso solicitado no existe.                            |
| **409** | Conflict              | Conflicto con el estado actual del recurso (por ejemplo, duplicados). |
| **403** | Forbidden             | El usuario no tiene permisos para acceder o modificar el recurso. |
| **401** | Unauthorized          | Se lanza cuando el cliente o administrador intenta acceder a un recurso sin un token de autenticación válido o con un token expirado. |
| **422** | Unprocessable Entity  | Se lanza cuando los datos enviados en la solicitud no cumplen con las reglas de validación del sistema. |
| **405** | Method Not Allowed    | Se lanza cuando un cliente o administrador intenta acceder a un endpoint utilizando un método HTTP incorrecto. |
| **500** | Internal Server Error | Error interno del servidor, cuando ocurre un problema no manejado específicamente. |

---

## Requisitos

- **Java 17** o superior.
- **Spring Boot** 2.7.x o superior.
- **JWT** para autenticación y autorización.
- **Hibernate** para la gestión de la persistencia.
- **MySQL/PostgreSQL** como sistema de base de datos.
- **Postman** o herramientas similares para probar la API.

---
