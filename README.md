# 🚀 Foro Hub - API REST para gestión de tópicos

Una API REST robusta desarrollada con Spring Boot para la gestión de un foro de discusión, permitiendo crear, leer, actualizar y eliminar tópicos.

---

## 📚 Descripción del Proyecto

`Foro Hub - API REST` es un servicio backend diseñado para proporcionar una interfaz programática completa para la creación, lectura, actualización y eliminación de tópicos.

El proyecto se adhiere a una arquitectura en capas clara, promoviendo la modularidad, la mantenibilidad y la escalabilidad. Incorpora principios de Clean Code y buenas prácticas de diseño de APIs REST, con un fuerte enfoque en la seguridad mediante la autenticación basada en JWT y el hashing de contraseñas. La gestión del esquema de la base de datos se realiza de forma automatizada y controlada con Flyway.

---

## ✨ Características Principales

* **Gestión Completa de Tópicos (CRUD):**
    * Creación de nuevos tópicos con título, mensaje, autor (usuario registrado) y curso.
    * Detalle de un tópico específico por ID.
    * Actualización de tópicos existentes (título, autor, mensaje, curso).
    * Eliminación de tópicos.
* **Autenticación y Autorización Segura:**
    * **Spring Security:** Framework robusto para la seguridad de la aplicación.
    * **JSON Web Tokens (JWT):** Autenticación sin estado para proteger los endpoints de la API.
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
* **Seguridad:** Spring Security, JWT (JSON Web Tokens)
* **Validación:** Jakarta Validation (Bean Validation)
* **Herramienta de Construcción:** Maven

---

## 🏛️ Arquitectura del Proyecto

El proyecto sigue una arquitectura en capas limpia y modular, facilitando la separación de responsabilidades y la mantenibilidad:

```
api/
├── .idea/
├── .mvn/
├── src/
│   └── main/
│       └── java/
│           └── foro.hub.api/
│               ├── controller/
│               │   ├── AutenticacionController
│               │   ├── CursoController
│               │   └── TopicoController
│               ├── domain/
│               │   ├── topico/
│               │   │   ├── DatosActualizarTopico
│               │   │   ├── DatosRegistroTopico
│               │   │   ├── Topico
│               │   │   └── TopicoRepository
│               │   └── usuario/
│               │       ├── AutenticacionService
│               │       ├── DatosAutenticacion
│               │       ├── Usuario
│               │       └── UsuarioRepository
│               ├── infra/
│               │   ├── exceptions/
│               │   │   └── GestorDeErrores
│               │   └── security/
│               │       ├── DatosTokenJWT
│               │       ├── SecurityConfiguration
│               │       ├── SecurityFilter
│               │       └── TokenService
│               └── ApiApplication
├── resources/
│   ├── db.migration/
│   │   └── V1__create-table-usuarios.sql
│   ├── static/
│   ├── templates/
│   └── application.properties
└── test/
    └── target/
        ├── .gitattributes
        ├── .gitignore
        ├── HELP.md
        ├── mvnw
        ├── mvnw.cmd
        ├── pom.xml
        └── README.md
├── External Libraries/
└── Scratches and Consoles/
```

---

## 🔒 Seguridad

La seguridad es un pilar fundamental de esta API:

* **Autenticación JWT:** Cada solicitud a un endpoint protegido requiere un token JWT válido en el encabezado `Authorization`.
* **Hashing de Contraseñas:** Las contraseñas de los usuarios nunca se almacenan en texto plano. Se utiliza `BCryptPasswordEncoder` para hashearlas antes de guardarlas en la base de datos.
* **Autorización Flexible:** Los endpoints están configurados para permitir o requerir autenticación según su función (ej. registro y lista de tópicos públicos, creación de tópicos protegida).

---

## 🔍 Endpoints de la API

Una vez que la aplicación esté ejecutándose, puedes interactuar con ella:

### Autenticación

* **`POST /auth`**
    * **Descripción:** Autentica a un usuario y devuelve un token JWT.
    * **Body (JSON):**
        ```json
        {
            "login": "raulduran@gmail.com",
            "contrasena": "ContraseñaSegura!10"
        }
        ```
    * **Respuesta (200 OK):**
        ```json
       {
           "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJBUEkgRm9yb0h1YiIsInN1YiI6InJhdWxkdXJhbjI4MDhAZ21haWwuY29tIiwiZXhwIjoxNzUzNDY2OTc5fQ.fyCr-rlr-Yd4mnKCJR3Vwl9oBMv4AUVGezuAwKJ6_wI",
           "nombreUsuario": "raulduran@gmail.com"
        }
        ```

### Curso

* **`POST /Curso`**
    * **Descripción:** Crea un nuevo curso. (Requiere JWT)
    * **Headers:** `Authorization: Bearer <JWT_TOKEN>`
    * **Body (JSON):**
        ```json
        {
             "id": 3,
             "nombre": "Desarrollo Móvil",
             "descripcion": "EL mejor curso de Android Studio",
             "estado": "ACTIVO"
        }
        ```
    * **Respuesta (200 Created):** Detalles del curso creado. 
---

### Topicos

* **`POST /Topico`**
    * **Descripción:** Crea un nuevo topico. (Requiere JWT)
    * **Headers:** `Authorization: Bearer <JWT_TOKEN>`
    * **Body (JSON):**
        ```json
        {
        
            "titulo": "Error usando Mysql 8.0 en Java Spring boot",
            "mensaje": "Estoy teniendo problemas con una excepción NullPointerException.",
            "estado": "NO_RESPONDIDO",
            "idCurso": 1,
           "idAutor": 1
        }
        ```
    * **Respuesta (200 Created):** Detalles del topico creado.

* **`GET /topicos`**
    * **Descripción:** Lista todos los tópicos paginados. (No requiere JWT)
    * **Respuesta (200 OK):** Lista de tópicos.

* **`GET /topicos/byId`**
    * **Descripción:** Detalla un tópico por su ID. (Requiere JWT)
    * **Headers:** `Authorization: Bearer <JWT_TOKEN>`
    * **Respuesta (200 OK):** Detalles del tópico.

* **`PUT /topicos`**
    * **Descripción:** Actualiza los datos de un tópico (título, mensaje, estado). (Requiere JWT)
    * **Headers:** `Authorization: Bearer <JWT_TOKEN>`
    * **Body (JSON):**
        ```json
        {
            "titulo": "Mensaje del tópico actualizado.",
            "mensaje": "SPRING",
            "estado": "SOLUCIONADO"
        }
        ```
    * **Respuesta (200 OK):** Detalles del tópico actualizado.

* **`DELETE /topicos/deleteByiD`**
    * **Descripción:** Elimina un tópico por su ID. (Requiere JWT)
    * **Headers:** `Authorization: Bearer <JWT_TOKEN>`
    * **Respuesta (200 OK):** Si la eliminación fue exitosa.
 
---

## 📜 **Licencia**

Proyecto educativo para **Alura Latam 2025**
