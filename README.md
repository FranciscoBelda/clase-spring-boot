# Gestión de Estudiantes - Proyecto Spring Boot (DAM) 🎓

Este repositorio contiene un proyecto práctico de **Spring Boot** diseñado para el ciclo formativo de **Desarrollo de Aplicaciones Multiplataforma (DAM)**. El objetivo principal es aprender a modelar y gestionar relaciones JPA de **Uno a Uno (1:1)** y **Uno a Muchos (1:N)** utilizando la entidad principal `Estudiante`.

---

## 📂 Arquitectura del Proyecto

El proyecto sigue una arquitectura en capas clásica utilizada en el desarrollo con Spring Framework:

* **Controller Layer:** Expone los endpoints REST para la interacción con el cliente.
* **Service Layer:** Contiene la lógica de negocio y la gestión de transacciones.
* **Repository Layer:** Interfaces que heredan de `JpaRepository` para la persistencia en base de datos.
* **Entity Layer:** Modelado de los datos y definición de relaciones JPA con Hibernate.

---

## 🗄️ Modelo de Datos y Relaciones JPA

### 1. Relación Uno a Uno (1:1)
**`Estudiante` ↔ `Expediente` / `Carnet`**
* Cada estudiante tiene asignado un único expediente académico (o carnet universitario), y cada expediente pertenece a un solo estudiante.
* **Anotaciones empleadas:** `@OneToOne`, `@JoinColumn`, `cascade = CascadeType.ALL`.

### 2. Relación Uno a Muchos (1:N)
**`Matrícula / Asignatura` ↔ `Estudiante` / `Modulo`**
* Un estudiante puede estar matriculado en varias asignaturas o módulos (1:N), pero cada matrícula concreta pertenece a un único estudiante.
* **Anotaciones empleadas:** `@OneToMany(mappedBy = "estudiante")`, `@ManyToOne`, `@JoinColumn`.

---

## 🛠️ Tecnologías y Dependencias

* **Java 17 / 21**
* **Spring Boot 3.x**
* **Spring Data JPA** (Persistencia y ORM)
* **Spring Web** (Creación de servicios API REST)
* **H2 Database / MySQL** (Base de datos en memoria o persistente)
* **Lombok** (Generación de getters, setters y constructores)

---

## 🚦 Endpoints Principales

### Estudiantes
* `GET /api/estudiantes`: Obtener todos los estudiantes con sus relaciones cargadas.
* `GET /api/estudiantes/{id}`: Obtener un estudiante por ID.
* `POST /api/estudiantes`: Crear un nuevo estudiante incluyendo su expediente (1:1).
* `PUT /api/estudiantes/{id}`: Actualizar la información del estudiante.
* `DELETE /api/estudiantes/{id}`: Eliminar un estudiante y sus datos asociados.

### Relaciones (Matrículas / Expediente)
* `POST /api/estudiantes/{id}/matriculas`: Añadir una nueva matrícula al estudiante (1:N).
* `GET /api/estudiantes/{id}/expediente`: Consultar el expediente del estudiante (1:1).

---

## 🚀 Instalación y Ejecución

1. **Clonar el repositorio:**
   ```bash
   git clone <URL_DEL_REPOSITORIO>
   cd gestion-estudiantes-springboot
   ```

2. **Compilar y ejecutar la aplicación:**
   ```bash
   ./mvnw spring-boot:run
   ```

3. **Acceso a la consola de H2 (si se utiliza H2):**
   * URL: `http://localhost:8080/h2-console`
   * JDBC URL: `jdbc:h2:mem:testdb`

---

## 👨‍💻 Autor

Creado por **Francisco Belda**.

Aprende más sobre Spring Boot, Java y desarrollo backend para el ciclo de DAM en su canal de YouTube:

👉 [Canal de YouTube de Francisco Belda](https://www.youtube.com/@franciscobelda)
