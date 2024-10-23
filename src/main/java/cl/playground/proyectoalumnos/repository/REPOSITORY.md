
# Explicación del código: `AlumnoRepository`

Este código define una **interfaz** llamada `AlumnoRepository` que extiende de `JpaRepository` en un proyecto Spring Boot. A continuación, se detalla su estructura, propósito y las funcionalidades que ofrece.

---

## Descripción general

La interfaz `AlumnoRepository` se encuentra en el paquete:

```java
package cl.playground.proyectoalumnos.repository;
```

Está orientada a **manejar operaciones de persistencia** sobre la entidad `Alumno`. Al extender de `JpaRepository`, hereda una gran cantidad de **métodos CRUD** (Create, Read, Update, Delete) y de consulta predefinidos. Además, se añaden **métodos personalizados** para realizar consultas específicas.

---

## Importaciones utilizadas

```java
import cl.playground.proyectoalumnos.model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
```

1. **`Alumno`**: Es la entidad que este repositorio gestiona.
2. **`JpaRepository`**: Una interfaz de Spring Data JPA que proporciona métodos CRUD y consultas sobre la base de datos.
3. **`Repository`**: Una anotación que indica que esta interfaz es un **componente de persistencia**. Spring la detecta como un bean que manejará la interacción con la base de datos.
4. **`List`**: Se usa como tipo de retorno para las consultas personalizadas, indicando que los resultados serán listas de objetos `Alumno`.

---

## Definición del Repositorio

```java
@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
    List<Alumno> findByNombreContainingIgnoreCase(String nombre);
    List<Alumno> findByEdadBetween(int edadMinima, int edadMaxima);
}
```

- **`@Repository`**: Marca esta interfaz como un repositorio gestionado por Spring. No es estrictamente necesaria cuando extendemos `JpaRepository`, pero ayuda en la semántica y claridad del código.

- **`extends JpaRepository<Alumno, Long>`**:
    - `Alumno` es el **tipo de entidad** sobre el cual operará este repositorio.
    - `Long` es el **tipo de la clave primaria** de la entidad `Alumno`.

---

## Métodos personalizados

### 1. `findByNombreContainingIgnoreCase(String nombre)`

```java
List<Alumno> findByNombreContainingIgnoreCase(String nombre);
```

Este método realiza una **búsqueda parcial por nombre**, sin importar mayúsculas o minúsculas.
- **`Containing`**: Busca si la cadena de texto proporcionada está contenida en el nombre del alumno.
- **`IgnoreCase`**: Ignora la diferencia entre mayúsculas y minúsculas durante la búsqueda.

**Ejemplo de uso:**
```java
List<Alumno> alumnos = alumnoRepository.findByNombreContainingIgnoreCase("juan");
```
Este ejemplo buscará todos los alumnos cuyos nombres contengan "juan", como "Juan", "juana" o "JUAN".

---

### 2. `findByEdadBetween(int edadMinima, int edadMaxima)`

```java
List<Alumno> findByEdadBetween(int edadMinima, int edadMaxima);
```

Este método busca a los alumnos cuya edad se encuentre **dentro de un rango específico** (inclusive).
- **`Between`**: Indica que la consulta devolverá resultados en los que la edad esté entre los valores mínimos y máximos proporcionados.

**Ejemplo de uso:**
```java
List<Alumno> alumnos = alumnoRepository.findByEdadBetween(18, 25);
```
Este ejemplo devolverá todos los alumnos cuya edad esté entre 18 y 25 años (ambos inclusive).

---

## Conclusión

El repositorio `AlumnoRepository` es una pieza fundamental dentro del proyecto, permitiendo realizar operaciones sobre la base de datos con los **métodos heredados** de `JpaRepository` y proporcionando **consultas personalizadas** mediante la definición de métodos adicionales. Esto garantiza un acceso eficiente y organizado a la información almacenada en la entidad `Alumno`.