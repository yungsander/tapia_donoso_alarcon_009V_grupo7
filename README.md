# Sistema de Gestión Aduanera — Aduana Don Pepe

Sistema de microservicios desarrollado con **Spring Boot 3.4.5** y **Spring Cloud 2024.0.1** para la gestión de operaciones aduaneras: importaciones, exportaciones, mercancias, usuarios, documentos, impuestos, alertas, países e historial de movimientos.

---

## Integrantes

| Nombre | Rol |
|---|---|
| Vicente Tapia | Desarrollador FullStack |
| Andrés Donoso | Desarrollador FullStack |
| Benjamin Alarcon | Desarrollador FullStack |

> **Grupo 7 — DSY1103 Desarrollo FullStack 1 — DuocUC 2025**

---

## Arquitectura del sistema

El sistema sigue una arquitectura de microservicios con Service Discovery (Eureka) y enrutamiento centralizado mediante API Gateway. Cada microservicio posee su propia base de datos MySQL, implementa el patrón CSR (Controller–Service–Repository) y expone documentación Swagger/OpenAPI.

```
Cliente / Postman
      │
      ▼
 API Gateway (:9090)
      │
      ├── Eureka Server (:8761)  ← Service Discovery
      │
      ├── ms-alertas       (:8080)
      ├── ms-documentos    (:8081)
      ├── ms-exportaciones (:8082)
      ├── ms-historial     (:8083)
      ├── ms-importaciones (:8084)
      ├── ms-impuestos     (:8085)
      ├── ms-mercancias    (:8086)
      ├── ms-paises        (:8087)
      ├── ms-reportes      (:8088)
      └── ms-usuarios      (:8089)
           │
           ▼
      MySQL 8.4 (:3306)
      (una base de datos por servicio)
```

---

## Microservicios implementados

| Microservicio | Puerto (host) | Base de datos | Descripción |
|---|---|---|---|
| `eureka-server` | 8761 | — | Registro y descubrimiento de servicios |
| `Gateway` | 9090 | — | Enrutamiento centralizado (Spring Cloud Gateway) |
| `alertas` | 8080 | db_alertas | Gestión de alertas asociadas a mercancias |
| `documentos` | 8081 | db_documentos | Administración de documentos aduaneros |
| `exportaciones` | 8082 | db_exportaciones | Registro y seguimiento de exportaciones |
| `historial` | 8083 | db_historial | Historial de movimientos aduaneros |
| `importaciones` | 8084 | db_importaciones | Registro y seguimiento de importaciones |
| `impuestos` | 8085 | db_impuestos | Cálculo y gestión de impuestos aduaneros |
| `mercancias` | 8086 | db_mercancias | Catálogo y control de mercancias |
| `paises` | 8087 | db_paises | Gestión de países de origen y destino |
| `reportes` | 8088 | db_reportes | Generación de reportes del sistema |
| `usuarios` | 8089 | db_usuarios | Administración de usuarios del sistema |

---

## Rutas principales del Gateway

El Gateway escucha en el puerto **9090**. Todas las rutas se acceden como `http://localhost:9090/api/{servicio}/...`

| Ruta Gateway | Servicio destino | Ruta interna |
|---|---|---|
| `GET /api/alertas/**` | alertas | `/api/v1/alertas/**` |
| `GET /api/documentos/**` | documentos | `/documentos/**` (StripPrefix) |
| `GET /api/exportaciones/**` | exportaciones | `/api/v1/exportaciones/**` |
| `GET /api/historial/**` | historial | `/api/v1/historiales/**` |
| `GET /api/importaciones/**` | importaciones | `/api/v1/importaciones/**` |
| `GET /api/impuestos/**` | impuestos | `/api/v1/impuestos/**` |
| `GET /api/mercancias/**` | mercancias | `/mercancias/**` (StripPrefix) |
| `GET /api/paises/**` | paises | `/paises/**` (StripPrefix) |
| `GET /api/reportes/**` | reportes | `/reportes/**` (StripPrefix) |
| `GET /api/usuarios/**` | usuarios | `/api/v1/usuarios/**` |

---

## Documentación Swagger / OpenAPI

Cada microservicio expone su propia UI Swagger. Acceder con Docker Compose corriendo:

| Microservicio | URL Swagger UI |
|---|---|
| alertas | http://localhost:8080/swagger-ui.html |
| documentos | http://localhost:8081/swagger-ui.html |
| exportaciones | http://localhost:8082/swagger-ui.html |
| historial | http://localhost:8083/swagger-ui.html |
| importaciones | http://localhost:8084/swagger-ui.html |
| impuestos | http://localhost:8085/swagger-ui.html |
| mercancias | http://localhost:8086/swagger-ui.html |
| paises | http://localhost:8087/swagger-ui.html |
| reportes | http://localhost:8088/swagger-ui.html |
| usuarios | http://localhost:8089/swagger-ui.html |
| Eureka Dashboard | http://localhost:8761 |

---

## Ejecución local con Docker Compose

### Requisitos previos

- Docker Desktop instalado y ejecutándose
- Git

### Pasos

```bash
# 1. Clonar el repositorio
git clone <URL_DEL_REPOSITORIO>
cd tapia_donoso_alarcon_009V_grupo7

# 2. Levantar todos los servicios
docker compose up --build

# 3. (Opcional) Reconstruir sin caché si hubo cambios
docker compose down -v
docker compose build --no-cache
docker compose up
```

> La primera vez demora varios minutos mientras Maven descarga dependencias y construye las imágenes.

### Verificar que los servicios estén activos

```bash
# Ver servicios registrados en Eureka
http://localhost:8761

# Probar el Gateway (ejemplo con alertas)
curl http://localhost:9090/api/alertas/api/v1/alertas
```

### Detener los servicios

```bash
docker compose down          # detiene contenedores
docker compose down -v       # detiene y elimina volúmenes (borra datos MySQL)
```

---

## Ejecución local desde el IDE (sin Docker)

> Requiere MySQL corriendo localmente con usuario `aduana` / contraseña `aduana123` y las bases de datos creadas.

1. Crear las bases de datos en MySQL:
```sql
CREATE DATABASE db_alertas;
CREATE DATABASE db_documentos;
CREATE DATABASE db_exportaciones;
CREATE DATABASE db_historial;
CREATE DATABASE db_importaciones;
CREATE DATABASE db_impuestos;
CREATE DATABASE db_mercancias;
CREATE DATABASE db_paises;
CREATE DATABASE db_reportes;
CREATE DATABASE db_usuarios;
GRANT ALL PRIVILEGES ON *.* TO 'aduana'@'%';
FLUSH PRIVILEGES;
```

2. Abrir cada módulo en IntelliJ IDEA o VS Code.
3. Ejecutar en orden: `eureka-server` → microservicios → `Gateway`.
4. Cada servicio usa el perfil `dev` por defecto (`spring.profiles.active=dev`).

---

## Tecnologías utilizadas

| Tecnología | Versión |
|---|---|
| Java | 21 |
| Spring Boot | 3.4.5 |
| Spring Cloud | 2024.0.1 |
| Spring Cloud Gateway | 2024.0.1 |
| Netflix Eureka | 2024.0.1 |
| Spring Data JPA | — |
| OpenFeign | 2024.0.1 |
| Springdoc OpenAPI (Swagger) | 2.8.x |
| MySQL | 8.4 |
| Docker / Docker Compose | — |
| JUnit 5 + Mockito | — |
