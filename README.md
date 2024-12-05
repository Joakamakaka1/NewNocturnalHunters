# Proyecto API Rest - Gestión de Clientes y Partidas

## Descripción del Proyecto

Este proyecto consiste en el desarrollo de una API Rest utilizando **Spring Boot**, **JWT** y **Hibernate**. La API permite gestionar los datos de los clientes y sus partidas, así como consultar información sobre personajes y enemigos en un juego. Esta solución proporciona una plataforma eficiente y segura para gestionar la interacción con los usuarios a través de autenticación basada en **JWT**.

## Objetivo del Proyecto

El objetivo principal de esta API es proporcionar una plataforma eficiente para gestionar los datos de los jugadores (clientes), sus partidas, y la consulta de personajes y enemigos del juego. A través de una arquitectura REST, los usuarios pueden interactuar con el sistema de manera sencilla y segura.

## Justificación del Proyecto

Este sistema es esencial para los desarrolladores de videojuegos que necesitan almacenar y gestionar datos relacionados con los usuarios, el progreso en sus partidas, así como las características de los personajes y enemigos del juego. Implementando **Spring Boot** y **JWT**, garantizamos una solución robusta, escalable y segura. Además, **Hibernate** facilita la interacción eficiente con la base de datos para la persistencia de datos, optimizando la gestión de relaciones.

---

## Tablas Involucradas

### 1. Tabla de **Clientes**
Contiene la información básica de los usuarios registrados en el sistema.

- **ID**: Campo autoincremental, clave primaria.
- **Nombre**: Cadena de texto, no nulo, único.
- **Email**: Cadena de texto, no nulo, único.
- **Contraseña**: Cadena de texto, no nulo.
- **Rol**: Cadena que define el rol del cliente en el sistema

### 2. Tabla de **Partidas**
Almacena los detalles de las partidas jugadas por los clientes.

- **ID**: Campo autoincremental, clave primaria.
- **Resultado**: Cadena de texto que indica el resultado de la partida (ej. victoria, derrota).
- **Duración**: Tiempo en el que se jugó la partida.
- **Fecha de inicio**: Fecha y hora en la que comenzó la partida.
- **ClienteID**: Clave foránea que se refiere al cliente que jugó la partida (relación 1:N con la tabla Clientes).

### 3. Tabla de **Personajes**
Contiene los datos de los personajes disponibles en el juego, cada uno asociado a un cliente.

- **ID**: Campo autoincremental, clave primaria.
- **Nombre**: Cadena de texto, no nulo, único.
- **Estadísticas**: Datos numéricos que indican las características del personaje (por ejemplo, fuerza, agilidad).
- **Tipo**: Enum con tres posibles valores: `básico`, `intermedio`, `boss`.
- **ClienteID**: Clave foránea que se refiere al cliente que posee el personaje (relación 1:N con la tabla Clientes).
- **Imagen**: Cadena de texto (ruta de archivo) para almacenar la imagen del enemigo

### 4. Tabla de **Enemigos**
Almacena los datos de los enemigos del juego.

- **ID**: Campo autoincremental, clave primaria.
- **Nombre**: Cadena de texto, no nulo, único.
- **Estadísticas**: Datos numéricos que indican las características del enemigo.
- **Tipo**: Enum con tres posibles valores: `básico`, `intermedio`, `boss`.
- **Imagen**: Cadena de texto (ruta de archivo) para almacenar la imagen del enemigo

## Relaciones entre Tablas

- **Clientes a Partidas**: Un cliente puede tener muchas partidas (relación **1:N**). La tabla **Partidas** contiene una clave foránea `clienteID` que hace referencia a la tabla **Clientes**.
  
- **Clientes a Personajes**: Un cliente puede tener varios personajes (relación **1:N**). La tabla **Personajes** contiene una clave foránea `clienteID` que hace referencia a la tabla **Clientes**.

---

## Endpoints

A continuación, se detallan los endpoints para cada tabla de la base de datos:

### 1. **Clientes**

- **POST /clientes/login**: Inicia sesión de un cliente. Requiere email y contraseña. Retorna un token JWT si las credenciales son correctas.
- **POST /clientes/register**: Registra un nuevo cliente. Requiere nombre, email, contraseña y rol.
- **GET /clientes**: Obtiene todos los clientes registrados. Solo accesible para administradores.
- **GET /clientes/{id}**: Obtiene los detalles de un cliente específico por su ID. Un cliente solo puede consultar su propio perfil, no el de otros usuarios.
- **PUT /clientes/{id}**: Permite actualizar los detalles de un cliente. Un cliente solo puede actualziar su propio perfil, no el de otros usuarios.
- **DELETE /clientes/{id}**: Elimina un cliente por su ID. Un cliente solo puede eliminar su propio perfil, no el de otros usuarios.

### 2. **Partidas**

- **POST /partidas**: Crea una nueva partida. Requiere los detalles de la partida y el ID del cliente. Solo accesible para administradores, ya que el cliente solo puede gestionar sus propias partidas.
- **GET /partidas**: Recupera todas las partidas jugadas. Solo accesible para administradores.
- **GET /partidas/{clienteId/username}**: Obtiene todas las partidas asociadas a un cliente específico, usando el ID/username del cliente. Los clientes solo pueden acceder a sus propias partidas.
- **GET /partidas/{id}**: Obtiene los detalles de una partida específica por su ID. Solo los administradores pueden acceder a cualquier partida, mientras que los clientes solo pueden acceder a las partidas asociadas a su ID.
- **PUT /partidas/{id}**: Actualiza los detalles de una partida. Solo accesible por administradores.
- **DELETE /partidas/{id}**: Elimina una partida por su ID. Solo accesible por administradores.

### 3. **Personajes**

- **POST /personajes**: Crea un nuevo personaje. Requiere los detalles del personaje y el ID del cliente. Este endpoint está disponible solo para administradores.
- **GET /personajes**: Obtiene todos los personajes registrados. Accesible para todos los usuarios.
- **GET /personajes/{id}**: Obtiene los detalles de un personaje específico por su ID. Este endpoint está disponible para todos los usuarios.
- **PUT /personajes/{id}**: Actualiza los detalles de un personaje. Solo accesible por administradores.
- **DELETE /personajes/{id}**: Elimina un personaje por su ID. Solo accesible por administradores.

### 4. **Enemigos**

- **POST /enemigos**: Crea un nuevo enemigo. Requiere los detalles del enemigo. Solo accesible para administradores.
- **GET /enemigos**: Obtiene todos los enemigos disponibles. Accesible para todos los usuarios.
- **GET /enemigos/{id}**: Obtiene los detalles de un enemigo específico por su ID. Este endpoint está disponible para todos los usuarios.
- **PUT /enemigos/{id}**: Actualiza los detalles de un enemigo. Solo accesible por administradores.
- **DELETE /enemigos/{id}**: Elimina un enemigo por su ID. Solo accesible por administradores.

---

## Lógica de Negocio

La lógica de negocio está centrada en los siguientes aspectos clave:

- **Autenticación y Autorización**: Utilizamos **JWT** para autenticar y autorizar a los usuarios. Los clientes deben estar autenticados para acceder a sus propios datos de partidas y personajes. Los administradores tienen permisos para gestionar todos los recursos del sistema.
  
- **Gestión de Partidas**: Los clientes pueden iniciar nuevas partidas y ver partidas pasadas. Cada partida está asociada a un cliente.

- **Gestión de Personajes y Enemigos**: Los clientes pueden crear personajes, modificarlos y eliminarlos. Los enemigos son gestionados por los administradores, y se pueden obtener detalles sobre ellos para ajustar las interacciones en las partidas.

---

## Excepciones y Códigos de Estado

Se manejarán las siguientes excepciones:

- **NotFoundException (404)**: Cuando un recurso solicitado no se encuentra en la base de datos.
- **BadRequestException (400)**: Cuando la solicitud tiene datos incorrectos o falta información necesaria.
- **ForbiddenException (403)**: Cuando un cliente intenta acceder a un recurso que no tiene permisos para ver o modificar.
- **DuplicateException (409)**: Cuando se intenta crear un recurso con datos que ya existen, como un email duplicado para un cliente.
- **UnauthorizedException (401)**: Se lanza cuando el cliente o administrador intenta acceder a un recurso sin un token de autenticación válido o con un token expirado.
- **ValidationException (422)**: Se lanza cuando los datos enviados en la solicitud no cumplen con las reglas de validación del sistema, como un formato incorrecto de email, un nombre de cliente vacío o una duración de partida no válida.
- **MethodNotAllowedException (405)**: Se lanza cuando un cliente o administrador intenta acceder a un endpoint utilizando un método HTTP incorrecto, por ejemplo, un `GET` cuando el endpoint solo acepta un `POST`.
- **GenericException (500)**: Error interno del servidor cuando ocurre un problema no manejado específicamente.

### Códigos de Estado
- **200 OK**: Solicitud exitosa.
- **201 Created**: El recurso ha sido creado correctamente.
- **204 No Content**: La operación fue exitosa pero no hay contenido para retornar (por ejemplo, al eliminar un recurso).
- **400 Bad Request**: La solicitud está mal formada o falta información.
- **404 Not Found**: El recurso solicitado no existe.
- **409 Conflict**: Conflicto con el estado actual del recurso (por ejemplo, duplicados).
- **403 Forbidden**: El usuario no tiene permisos para acceder o modificar el recurso.
- **401 UnauthorizedException**: Se lanza cuando el cliente o administrador intenta acceder a un recurso sin un token de autenticación válido o con un token expirado.
- **422 ValidationException**: Se lanza cuando los datos enviados en la solicitud no cumplen con las reglas de validación del sistema, como un formato incorrecto de email, un nombre de cliente vacío o una duración de partida no válida.
- **405 MethodNotAllowedException**: Se lanza cuando un cliente o administrador intenta acceder a un endpoint utilizando un método HTTP incorrecto, por ejemplo, un `GET` cuando el endpoint solo acepta un `POST`.
- **500 Internal Server Error**: Error interno del servidor.

---

## Restricciones de Seguridad

- **Autenticación**: Todos los endpoints que requieren acceso de clientes o administradores utilizan **JWT**. Los tokens JWT deben enviarse en los encabezados de las solicitudes para autenticar al usuario.
- **Autorización**: Los administradores tienen acceso completo a todos los endpoints, mientras que los clientes solo pueden acceder a los recursos que les pertenecen.
- **Protección contra CSRF**: Se aplicará protección contra ataques CSRF para proteger la API de peticiones maliciosas.
- **Validación de datos**: Todos los datos enviados en las solicitudes serán validados para evitar datos erróneos o maliciosos.

---

## Requisitos

- **Java 17** o superior
- **Spring Boot** 2.7.x o superior
- **JWT (JSON Web Tokens)** para autenticación y autorización
- **Hibernate** para la gestión de la persistencia en la base de datos
- **Maven** o **Gradle** para la gestión de dependencias
- **Base de Datos**: MySQL, PostgreSQL o cualquier sistema compatible con Hibernate
- **Postman** o herramientas similares para probar la API

---
