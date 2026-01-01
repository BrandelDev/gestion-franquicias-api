# Gestion Franquicias API

API REST para la gestion de franquicias, sucursales y productos, construida con Spring Boot.

## Caracteristicas generales
- Arquitectura REST con Spring WebMVC.
- Persistencia con Spring Data JPA.
- Validaciones con Bean Validation.
- Documentacion OpenAPI/Swagger UI.
- Base de datos MySQL.
- Empaquetado con Maven.
- Despliegue automatizable con Terraform (ECS Fargate + Aurora + CodePipeline).

## Requerimientos y versiones
- Java 21.
- Spring Boot 4.0.1.
- Maven 3.x (o `mvnw`).
- MySQL 8.0.x.
- Springdoc OpenAPI 2.5.0.
- Terraform >= 1.5.0 (para infraestructura).

## Instrucciones para correr localmente

### Opcion A: Maven + MySQL local
1) Levanta una instancia de MySQL y crea la BD `db_franquicias`.
2) Configura las variables de entorno (o `application.properties`):
   - `SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/db_franquicias?useSSL=false&serverTimezone=UTC`
   - `SPRING_DATASOURCE_USERNAME=root`
   - `SPRING_DATASOURCE_PASSWORD=root`
   - `SPRING_JPA_HIBERNATE_DDL_AUTO=update`
3) Ejecuta la aplicacion:
```bash
./mvnw spring-boot:run
```

### Opcion B: Docker Compose
```bash
docker-compose up --build
```

La API quedara disponible en `http://localhost:8080`.
La documentacion Swagger UI suele estar en `http://localhost:8080/swagger-ui/index.html`.

## Terraform y despliegue (vision general)
La carpeta `infra/terraform` contiene IaC para desplegar la API en AWS usando:
- ECS Fargate detras de un ALB publico.
- Aurora MySQL como base de datos.
- ECR para imagenes Docker.
- CodePipeline/CodeBuild para CI/CD desde GitHub.
- SSM Parameter Store para secretos de base de datos.

Pasos generales:
```bash
cd infra/terraform
terraform init
terraform apply
```

Luego autoriza la conexion de CodeStar y usa el ALB DNS como base URL de la API.

## Estructura del proyecto
- `src/`: codigo fuente de la API.
- `infra/terraform`: infraestructura como codigo.
- `Dockerfile`, `docker-compose.yml`: contenedorizacion local.
