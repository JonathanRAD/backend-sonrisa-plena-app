# 🦷 Sistema de Gestión Clínica "Sonrisa Plena"

![Java](https://img.shields.io/badge/Java-21-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-green?logo=springboot)
![Angular](https://img.shields.io/badge/Angular-Latest-red?logo=angular)
![Bootstrap](https://img.shields.io/badge/Bootstrap-5.3-purple?logo=bootstrap)
![Security](https://img.shields.io/badge/Security-JWT-black?logo=jsonwebtokens)

> **Plataforma Full Stack para la gestión administrativa y clínica de centros odontológicos. Implementa una arquitectura segura basada en roles (RBAC) y servicios RESTful.**

---

## 🚀 Descripción del Proyecto
Este sistema moderniza el flujo operativo de una clínica dental, permitiendo la gestión digital de citas, pacientes y roles administrativos.

Fue diseñado con un enfoque estricto en la **Ingeniería de Software**:
* **Backend:** Arquitectura en capas (Controller, Service, Repository) con **Spring Boot 3**.
* **Frontend:** SPA (Single Page Application) construida con **Angular**, utilizando Guards para la protección de rutas.
* **Seguridad:** Autenticación **Stateless con JWT** (JSON Web Tokens) y autorización dinámica según roles.

---

## 🛠️ Stack Tecnológico

### Backend (API REST)
* **Lenguaje:** Java 21 (LTS)
* **Framework:** Spring Boot 3 (Web, Security, Data JPA)
* **Seguridad:** Spring Security 6 + JWT (jjwt)
* **Base de Datos:** H2 (Dev) / Compatible con PostgreSQL (Prod)
* **Herramientas:** Lombok, Maven

### Frontend (Cliente)
* **Framework:** Angular (Standalone Components)
* **UI/UX:** Bootstrap 5.3 + Bootstrap Icons
* **Gestión de Estado:** RxJS y Servicios inyectables
* **Seguridad Cliente:** Interceptores HTTP y Guards (`authGuard`, `rolGuard`)

---

## ✨ Funcionalidades Clave

### 1. Seguridad Avanzada (RBAC)
Implementación de un sistema de roles granular:
* **🔐 ADMIN:** Acceso total al panel (`/admin`), gestión de usuarios y configuración.
* **🦷 ODONTÓLOGO:** Acceso exclusivo a la gestión de citas y pacientes (`/citas`).
* **Protección:** Las rutas están protegidas por `AutenticacionGuard` y `RolGuard` para prevenir accesos no autorizados.

### 2. Autenticación JWT
* Login y Registro seguros con encriptación de contraseñas (`BCrypt`).
* Generación y validación de tokens JWT para sesiones sin estado (Stateless).

### 3. Gestión de Citas
* Interfaz intuitiva para agendar, reprogramar y visualizar citas médicas.

---

## 📂 Estructura del Proyecto

El proyecto sigue una arquitectura limpia para facilitar la escalabilidad:

```text
/backend-sonrisa-plena
├── config/          # Configuración de Seguridad (SecurityConfig, JwtFilter)
├── controller/      # Endpoints REST (AuthController, DemoController)
├── service/         # Lógica de Negocio (AuthService, JwtService)
├── model/           # Entidades JPA (Usuario, Rol)
└── repository/      # Acceso a Datos (UsuarioRepository)

/frontend-sonrisa-plena
├── core/
│   ├── guards/      # Protección de rutas (auth.guard.ts, rol.guard.ts)
│   ├── interceptors/# Inyección automática del Token
└── features/        # Módulos funcionales (Login, Panel, Citas)
```
🔧 Instalación y Despliegue
Requisitos Previos
Java 21 JDK

Node.js & Angular CLI

1. Levantar el Backend
Bash
```text
cd backend-sonrisa-plena-app
./mvnw spring-boot:run
```
2. Levantar el Frontend

```text
cd frontend-sonrisa-plena-app
ng serve -o
```
El sistema estará disponible en: http://localhost:4200

🛡️ Calidad y Pruebas
SonarQube: Análisis estático integrado para garantizar código limpio y sin "code smells".

Pruebas Unitarias: Configuración base con JUnit 5 y Mockito.

👤 Autor
Jonathan Rujel - Full Stack Developer

