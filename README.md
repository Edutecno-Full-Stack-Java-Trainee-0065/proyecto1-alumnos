# 1 - Dependencias Principales del Proyecto Spring Boot

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId> 
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId> 
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId> 
            <scope>test</scope>
        </dependency>
    </dependencies>


## 1. Spring Data JPA
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```
**Propósito**: Facilita la gestión de datos y operaciones con bases de datos relacionales.
- Simplifica el acceso y manipulación de datos mediante JPA (Java Persistence API)
- Permite escribir repositorios sin implementación
- Reduce significativamente el código boilerplate en operaciones CRUD

## 2. Spring Boot Web
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```
**Propósito**: Proporciona todas las dependencias necesarias para crear aplicaciones web y RESTful.
- Incluye Apache Tomcat como servidor embebido
- Facilita la creación de endpoints REST
- Manejo de solicitudes HTTP y respuestas

## 3. Spring Boot DevTools
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
    <optional>true</optional>
</dependency>
```
**Propósito**: Mejora la experiencia de desarrollo.
- Reinicio automático de la aplicación cuando se detectan cambios
- Recarga automática de recursos estáticos
- Mejoras en la depuración
- Deshabilitado automáticamente en producción

## 4. PostgreSQL Driver
```xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <scope>runtime</scope>
</dependency>
```
**Propósito**: Permite la conexión con bases de datos PostgreSQL.
- Proporciona el driver JDBC para PostgreSQL
- Habilita la comunicación entre la aplicación y la base de datos
- Optimizado para PostgreSQL

## 5. Lombok
```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>
```
**Propósito**: Reduce el código repetitivo mediante anotaciones.
- Genera automáticamente getters, setters, constructores
- Reduce la verbosidad del código
- Mejora la legibilidad y mantenimiento
- Minimiza errores por código repetitivo

## 6. Spring Boot Test
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
```
**Propósito**: Facilita la creación de pruebas unitarias e integración.
- Incluye JUnit, MockMvc, Mockito
- Proporciona utilidades para testing
- Permite crear pruebas más robustas
- Facilita la implementación de TDD


# 2- Generacion de los modelos necesarios para la aplicacion
