# DTOs y Mapper - Proyecto Alumnos

## 1. AlumnoDTO.java

```java
package cl.playground.proyectoalumnos.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlumnoDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private int edad;

}
```

**Descripción**:  
Este DTO representa los datos expuestos al cliente sobre un alumno. Incluye atributos como `id`, `nombre`, `apellido`, y `edad`. Las anotaciones de **Lombok** (`@Data`, `@NoArgsConstructor`, `@AllArgsConstructor`) simplifican la creación de los métodos `getter`, `setter`, y constructores necesarios.

---

## 2. AlumnoMapper.java

```java
package cl.playground.proyectoalumnos.dtos;

import cl.playground.proyectoalumnos.model.Alumno;

public class AlumnoMapper {

    public static AlumnoDTO toDto(Alumno alumno) {
        return new AlumnoDTO(
                alumno.getId(),
                alumno.getNombre(),
                alumno.getApellido(),
                alumno.getEdad()
        );
    }

    public static Alumno createDtoToEntity(CreateAlumnoDTO createAlumnoDTO) {
        Alumno alumno = new Alumno();
        alumno.setNombre(createAlumnoDTO.getNombre());
        alumno.setApellido(createAlumnoDTO.getApellido());
        alumno.setEdad(createAlumnoDTO.getEdad());

        return alumno;
    }

    public static Alumno updateDtoToEntity(UpdateAlumnoDTO updateAlumnoDTO) {
        Alumno alumno = new Alumno();
        alumno.setNombre(updateAlumnoDTO.getNombre());
        alumno.setApellido(updateAlumnoDTO.getApellido());
        alumno.setEdad(updateAlumnoDTO.getEdad());

        return alumno;
    }
}
```

**Descripción**:  
Esta clase **`AlumnoMapper`** se encarga de convertir entre las entidades del modelo (`Alumno`) y los DTOs. Contiene los siguientes métodos:

- **`toDto`**: Convierte una entidad `Alumno` en un `AlumnoDTO`.
- **`createDtoToEntity`**: Convierte un `CreateAlumnoDTO` en una entidad `Alumno`.
- **`updateDtoToEntity`**: Convierte un `UpdateAlumnoDTO` en una entidad `Alumno`.

---

## 3. CreateAlumnoDTO.java

```java
package cl.playground.proyectoalumnos.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAlumnoDTO {

    private String nombre;
    private String apellido;
    private int edad;
}
```

**Descripción**:  
Este DTO se utiliza para recibir datos al momento de **crear** un alumno. Contiene los atributos básicos: `nombre`, `apellido`, y `edad`.

---

## 4. UpdateAlumnoDTO.java

```java
package cl.playground.proyectoalumnos.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAlumnoDTO {

    private String nombre;
    private String apellido;
    private int edad;

}
```

**Descripción**:  
Este DTO se usa para **actualizar** la información de un alumno existente. Contiene los mismos campos que el `CreateAlumnoDTO`, pero se aplica en un contexto de actualización.

---

## Resumen

- **`AlumnoDTO`**: Exposición de los datos de un alumno.
- **`CreateAlumnoDTO`**: Datos necesarios para crear un alumno.
- **`UpdateAlumnoDTO`**: Datos necesarios para actualizar un alumno.
- **`AlumnoMapper`**: Convierte entre entidades del modelo y DTOs, asegurando que las capas de servicio y control interactúen de manera adecuada sin exponer la implementación interna del modelo.

Este enfoque de **DTOs y mapeo** garantiza una separación clara entre la lógica del negocio y la representación de datos, siguiendo las **buenas prácticas** de arquitectura.