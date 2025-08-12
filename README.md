# 🚀 Foro Hub - API REST para gestión de tópicos

Una API REST robusta desarrollada con Spring Boot para la gestión de un foro de discusión, Permite a los usuarios registrarse, iniciar sesión y participar en discusiones.

---

## 📚 Descripción del Proyecto

`Foro Hub - API REST` es una plataforma de foros pensada para facilitar la discusión de temas técnicos, académicos o de interés general, con un enfoque limpio, escalable y profesional.

El proyecto se adhiere a una arquitectura en capas clara, promoviendo la modularidad, la mantenibilidad y la escalabilidad. Incorpora principios de Clean Code y buenas prácticas de diseño de APIs REST, con un fuerte enfoque en la seguridad mediante la autenticación basada en JWT y el hashing de contraseñas. La gestión del esquema de la base de datos se realiza de forma automatizada y controlada con Flyway.
Soporte completo de autenticación y autorización con tokens JWT y cookies seguras.

---

## ✨ Características Principales

* **Gestión Completa de Tópicos (CRUD):**
    * Creación de nuevos tópicos con título, mensaje, autor (usuario registrado) y curso.
    * Detalle de un tópico específico por ID.
    * Actualización de tópicos existentes (título, mensaje).
    * Eliminación de tópicos.
* **Gestión de Comentarios:**
    * Creación de comentarios por parte de usuarios autenticados.
    * Visualización de todos los comentarios de un tópico.
    * Asociación automática del comentario con el autor y el tópico correspondiente.
    * El comentario incluye: mensaje, fecha de creación, autor y tópico al que pertenece.
* **Autenticación y Autorización Segura:**
    * **Spring Security:** Framework robusto para la seguridad de la aplicación.
    * **JSON Web Tokens (JWT):** Autenticación sin estado para proteger los endpoints de la API.
    * **Cookies HTTP-only:** El token se almacena en una cookie segura y de solo lectura por el navegador, protegiendo contra ataques XSS, Actualmente, cuando el token JWT expira, el usuario debe volver a iniciar sesión.

    * En futuras versiones se implementará un sistema de refresh tokens usando cookies HTTP-only para renovar los tokens de forma transparente y mejorar la experiencia del usuario.

    * **Hashing de Contraseñas:** Las contraseñas se almacenan de forma segura utilizando BCrypt.
* **Validación de Datos:**
    * Uso de `jakarta.validation` para asegurar la integridad y el formato de los datos de entrada.
* **Manejo de Errores Global:**
    * Respuestas HTTP estandarizadas (`400 Bad Request`, `403 Forbidden`, `404 Not Found`, `500 Internal Server Error`) para una comunicación clara con el cliente.
* **Gestión de Base de Datos con Flyway:**
    * Control de versiones del esquema de la base de datos para un despliegue y mantenimiento consistentes.

---

## 🛠️ Tecnologías Utilizadas

* **Lenguaje:** Java 21+
* **Framework:** Spring Boot 3
* **Base de Datos:** MySQL
* **ORM:** Spring Data JPA / Hibernate
* **Migraciones DB:** Flyway
* **Seguridad:** Implementada con Spring Security y autenticación basada en JWT (JSON Web Tokens), usando cookies HTTP-only para el almacenamiento seguro de tokens.
* **Validación:** Jakarta Validation (Bean Validation)
* **Herramienta de Construcción:** Maven

---

## 🏛️ Arquitectura del Proyecto

El proyecto sigue una arquitectura en capas limpia y modular, facilitando la separación de responsabilidades y la mantenibilidad:

---

## 🔒 Seguridad

La seguridad es un pilar fundamental de esta API:

* **Autenticación JWT:** Cada solicitud a un endpoint protegido requiere un token JWT válido en el encabezado `Authorization`.
* **Cookies HTTP-only:** El token (`token`) se gestiona de forma segura mediante una cookie marcada como `HttpOnly` y `Secure`, lo que impide su acceso vía JavaScript y protege contra ataques XSS.

* **Hashing de Contraseñas:** Las contraseñas de los usuarios nunca se almacenan en texto plano. Se utiliza `BCryptPasswordEncoder` para hashearlas antes de guardarlas en la base de datos.
* **Autorización Flexible:** Los endpoints están configurados para permitir o requerir autenticación según su función (ej. registro y lista de tópicos públicos, creación de tópicos protegida).

---

## 🖼️ Ejemplos en la Interfaz
* **Login:**
![TopicoNuevo](https://i.imgur.com/lsfdTp2.png)

* **Nuevo Topico:**
![TopicoNuevo](https://i.imgur.com/c0UokZk.png)

* **Lista de Topicos:**
![TopicoLista](https://i.imgur.com/Re0sIyF.png)

* **Nuevo Comentario:**
![Comentario](https://i.imgur.com/DVDAuPT.png)

* **Lista de Comentarios:**
![TopicoUno](https://i.imgur.com/C9z278a.png)


 
---

## 📜 **Licencia**

Proyecto educativo para **Alura Latam 2025**
