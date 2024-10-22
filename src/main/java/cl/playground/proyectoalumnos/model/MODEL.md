# Guía de Anotaciones en la Clase Alumno

## Anotaciones a Nivel de Clase

### Anotaciones de Lombok

#### `@Data`
- **Propósito**: Genera automáticamente código boilerplate común
- **Funcionalidad**:
    - Genera getters para todos los campos
    - Genera setters para todos los campos no finales
    - Genera `toString()`
    - Genera `equals()`
    - Genera `hashCode()`
    - Genera constructor requerido

#### `@AllArgsConstructor`
- **Propósito**: Genera un constructor con todos los atributos
- **Funcionalidad**:
    - Crea un constructor que incluye todos los campos de la clase
    - Útil para inicializar todos los campos en una sola línea
    - El orden de los parámetros coincide con el orden de declaración de los campos

#### `@NoArgsConstructor`
- **Propósito**: Genera un constructor sin argumentos
- **Funcionalidad**:
    - Crea un constructor vacío
    - Requerido por JPA para la creación de instancias
    - Necesario para la serialización/deserialización

### Anotaciones de JPA/Persistencia

#### `@Entity`
- **Propósito**: Marca la clase como una entidad JPA
- **Funcionalidad**:
    - Indica que la clase es una entidad persistente
    - Permite el mapeo objeto-relacional
    - Habilita la gestión por parte del EntityManager

#### `@Table(name = "alumnos")`
- **Propósito**: Especifica detalles de la tabla en la base de datos
- **Funcionalidad**:
    - Define el nombre de la tabla en la base de datos
    - Si se omite, se usa el nombre de la clase por defecto

## Anotaciones a Nivel de Campo

### `@Id`
- **Propósito**: Marca el campo como clave primaria
- **Funcionalidad**:
    - Identifica el campo como identificador único de la entidad
    - Requerido en todas las entidades JPA

### `@GeneratedValue(strategy = GenerationType.IDENTITY)`
- **Propósito**: Configura la generación automática de valores para la clave primaria
- **Funcionalidad**:
    - Utiliza la estrategia de autoincremento de la base de datos
    - El valor se genera automáticamente al insertar un nuevo registro

### `@Column`
- **Propósito**: Personaliza el mapeo de columnas
- **Funcionalidad**:
    - Define el nombre de la columna en la base de datos
    - Permite especificar restricciones de la columna
    - Ejemplos en la clase:
      ```java
      @Column(name = "nombre")    // Mapea al campo 'nombre' en la BD
      @Column(name = "apellido")  // Mapea al campo 'apellido' en la BD
      @Column(name = "edad")      // Mapea al campo 'edad' en la BD
      ```

## Estructura Resultante en la Base de Datos

La clase generará una tabla con la siguiente estructura:

```sql
CREATE TABLE alumnos (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL,
    apellido VARCHAR(30) NOT NULL,
    edad INTEGER CHECK (edad > 0)
);
```

Esta configuración permite una integración perfecta entre los objetos Java y la base de datos relacional, simplificando significativamente el desarrollo y mantenimiento del código.