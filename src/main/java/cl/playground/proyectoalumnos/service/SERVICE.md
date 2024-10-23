# Explicación del Código: `AlumnoService` y `AlumnoServiceImpl`

Este código define la interfaz `AlumnoService` y su implementación en la clase `AlumnoServiceImpl`. El objetivo de este servicio es gestionar las operaciones sobre la entidad `Alumno` a través de métodos CRUD básicos y consultas personalizadas, utilizando **Spring Boot** y **Spring Data JPA**.

---

## 1. Interfaz `AlumnoService`

Ubicación del archivo:

```java
package cl.playground.proyectoalumnos.service;
```

Esta interfaz define las **operaciones de alto nivel** para gestionar alumnos. Su implementación será manejada en la clase `AlumnoServiceImpl`.

### Métodos principales:

- **CRUD Básico**:
    - `createAlumno(CreateAlumnoDTO createDto)`: Crea un nuevo alumno.
    - `getAlumnoById(Long id)`: Obtiene un alumno por su ID.
    - `getAllAlumnos()`: Devuelve una lista de todos los alumnos.
    - `getAllAlumnosPaginated(Pageable pageable)`: Devuelve una lista paginada de alumnos.
    - `updateAlumno(Long id, UpdateAlumnoDTO updateDto)`: Actualiza los datos de un alumno por su ID.
    - `deleteAlumno(Long id)`: Elimina un alumno por su ID.

- **Búsquedas adicionales**:
    - `findByNombre(String nombre)`: Busca alumnos por nombre.
    - `findByEdad(int edadMinima, int edadMaxima)`: Busca alumnos dentro de un rango de edades.

---

## 2. Implementación en `AlumnoServiceImpl`

### Ubicación del archivo:

```java
package cl.playground.proyectoalumnos.service;
```

### Dependencias utilizadas:

```java
import cl.playground.proyectoalumnos.dtos.AlumnoDTO;
import cl.playground.proyectoalumnos.dtos.CreateAlumnoDTO;
import cl.playground.proyectoalumnos.dtos.UpdateAlumnoDTO;
import cl.playground.proyectoalumnos.dtos.AlumnoMapper;
import cl.playground.proyectoalumnos.exception.AlumnoNotFoundException;
import cl.playground.proyectoalumnos.model.Alumno;
import cl.playground.proyectoalumnos.repository.AlumnoRepository;
import cl.playground.proyectoalumnos.validation.AlumnoValidator;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
```

### Explicación de las anotaciones:

- **`@Service`**: Marca esta clase como un componente de servicio de Spring.
- **`@AllArgsConstructor`**: Genera un constructor con todos los argumentos necesarios, facilitando la inyección de dependencias.
- **`@Transactional`**: Define que los métodos se ejecutarán en transacciones. Puede ser de solo lectura (`readOnly = true`) o de escritura.

---

### Implementación de los Métodos CRUD

#### 1. Crear un alumno

```java
@Transactional
public AlumnoDTO createAlumno(CreateAlumnoDTO createDto) {
    alumnoValidator.validateCreateAlumno(createDto);
    Alumno alumno = AlumnoMapper.createDtoToEntity(createDto);
    alumno = alumnoRepository.save(alumno);
    return AlumnoMapper.toDto(alumno);
}
```

- **Descripción**: Valida los datos del DTO de creación, convierte el DTO en entidad y la guarda en la base de datos.

---

#### 2. Obtener un alumno por ID

```java
@Transactional(readOnly = true)
public AlumnoDTO getAlumnoById(Long id) {
    return alumnoRepository.findById(id)
            .map(AlumnoMapper::toDto)
            .orElseThrow(() -> new AlumnoNotFoundException(id));
}
```

- **Descripción**: Busca un alumno por su ID y lo convierte a DTO. Si no se encuentra, lanza una excepción.

---

#### 3. Obtener todos los alumnos

```java
@Transactional(readOnly = true)
public List<AlumnoDTO> getAllAlumnos() {
    return alumnoRepository.findAll().stream()
            .map(AlumnoMapper::toDto)
            .collect(Collectors.toList());
}
```

- **Descripción**: Recupera todos los alumnos y los transforma en una lista de DTOs.

---

#### 4. Obtener alumnos paginados

```java
@Transactional(readOnly = true)
public Page<AlumnoDTO> getAllAlumnosPaginated(Pageable pageable) {
    return alumnoRepository.findAll(pageable)
            .map(AlumnoMapper::toDto);
}
```

- **Descripción**: Permite obtener alumnos con paginación.

---

#### 5. Actualizar un alumno

```java
public AlumnoDTO updateAlumno(Long id, UpdateAlumnoDTO updateDto) {
    alumnoValidator.validateUpdateAlumno(updateDto);
    Alumno existingAlumno = alumnoRepository.findById(id)
            .orElseThrow(() -> new AlumnoNotFoundException(id));
    existingAlumno.setNombre(updateDto.getNombre());
    existingAlumno.setApellido(updateDto.getApellido());
    existingAlumno.setEdad(updateDto.getEdad());
    existingAlumno = alumnoRepository.save(existingAlumno);
    return AlumnoMapper.toDto(existingAlumno);
}
```

- **Descripción**: Valida y actualiza los datos de un alumno existente.

---

#### 6. Eliminar un alumno

```java
public void deleteAlumno(Long id) {
    if (!alumnoRepository.existsById(id)) {
       throw new AlumnoNotFoundException(id);
    }
    alumnoRepository.deleteById(id);
}
```

- **Descripción**: Elimina un alumno si existe, o lanza una excepción si no se encuentra.

---

### Métodos de búsqueda adicionales

#### 1. Buscar por nombre

```java
public List<AlumnoDTO> findByNombre(String nombre) {
    if (nombre == null || nombre.trim().isEmpty()) {
        throw new IllegalArgumentException("El nombre no puede estar vacio");
    }
    return alumnoRepository.findByNombreContainingIgnoreCase(nombre)
            .stream()
            .map(AlumnoMapper::toDto)
            .collect(Collectors.toList());
}
```

- **Descripción**: Busca alumnos cuyo nombre contenga la cadena proporcionada, ignorando mayúsculas.

---

#### 2. Buscar por rango de edad

```java
public List<AlumnoDTO> findByEdad(int edadMinima, int edadMaxima) {
    if (edadMinima < 0) {
        throw new IllegalArgumentException("La edad minima no puede ser negativa");
    }
    if (edadMaxima < edadMinima) {
        throw new IllegalArgumentException("La edad maxima no puede ser menor a la minima");
    }
    return alumnoRepository.findByEdadBetween(edadMinima, edadMaxima)
            .stream()
            .map(AlumnoMapper::toDto)
            .collect(Collectors.toList());
}
```

- **Descripción**: Valida y busca alumnos dentro del rango de edades especificado.

---

## Conclusión

El servicio `AlumnoServiceImpl` proporciona la **lógica de negocio** para gestionar la entidad `Alumno`. Utiliza el repositorio para realizar las operaciones CRUD y define métodos adicionales para búsquedas específicas. Además, implementa validaciones y maneja excepciones para garantizar la integridad de los datos.
