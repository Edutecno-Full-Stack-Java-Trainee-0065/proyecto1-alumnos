## Explicación del `AlumnoController`

El controlador maneja las solicitudes HTTP relacionadas con los alumnos y actúa como la capa de presentación en tu aplicación. Este controlador expone varias rutas que cubren las operaciones CRUD básicas, junto con algunas búsquedas adicionales.

---

### Anotaciones Utilizadas

1. **`@RestController`**: Marca la clase como un controlador REST, lo que indica que los métodos retornarán objetos en formato JSON.
2. **`@RequestMapping("/api/v1/alumnos")`**: Define la URL base para todos los endpoints en este controlador.
3. **`@RequiredArgsConstructor`**: Inyecta automáticamente las dependencias finales marcadas con `final` (en este caso, `AlumnoService`).

---

### Métodos del Controlador

1. **Crear Alumno**
   ```java
   @PostMapping
   public ResponseEntity<AlumnoDTO> createAlumno(@RequestBody CreateAlumnoDTO createAlumnoDTO) {
       AlumnoDTO alumnoDTO = alumnoService.createAlumno(createAlumnoDTO);
       return new ResponseEntity<>(alumnoDTO, HttpStatus.CREATED);
   }
   ```
    - **Descripción**: Crea un nuevo alumno utilizando la información proporcionada en el cuerpo de la solicitud.
    - **Endpoint**: `POST /api/v1/alumnos`

2. **Obtener Alumno por ID**
   ```java
   @GetMapping("/{id}")
   public ResponseEntity<AlumnoDTO> getAlumnoById(@PathVariable Long id) {
       AlumnoDTO alumnoDTO = alumnoService.getAlumnoById(id);
       return ResponseEntity.ok(alumnoDTO);
   }
   ```
    - **Descripción**: Recupera un alumno específico utilizando su ID.
    - **Endpoint**: `GET /api/v1/alumnos/{id}`

3. **Obtener Todos los Alumnos**
   ```java
   @GetMapping
   public ResponseEntity<List<AlumnoDTO>> getAllAlumnos() {
       List<AlumnoDTO> alumnos = alumnoService.getAllAlumnos();
       return ResponseEntity.ok(alumnos);
   }
   ```
    - **Descripción**: Devuelve la lista completa de alumnos.
    - **Endpoint**: `GET /api/v1/alumnos`

4. **Actualizar Alumno**
   ```java
   @PutMapping("/{id}")
   public ResponseEntity<AlumnoDTO> updateAlumno(@PathVariable Long id, @RequestBody UpdateAlumnoDTO updateDto) {
       AlumnoDTO updatedAlumno = alumnoService.updateAlumno(id, updateDto);
       return ResponseEntity.ok(updatedAlumno);
   }
   ```
    - **Descripción**: Actualiza los datos de un alumno con la información proporcionada.
    - **Endpoint**: `PUT /api/v1/alumnos/{id}`

5. **Paginación de Alumnos**
   ```java
   @GetMapping("/paged")
   public ResponseEntity<Page<AlumnoDTO>> getAllAlumnosPaginated(
           @RequestParam(defaultValue = "0") int page,
           @RequestParam(defaultValue = "10") int size,
           @RequestParam(defaultValue = "id") String sortBy,
           @RequestParam(defaultValue = "asc") String sortDir) {
   
       Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortBy);
       PageRequest pageRequest = PageRequest.of(page, size, sort);
       Page<AlumnoDTO> alumnosPage = alumnoService.getAllAlumnosPaginated(pageRequest);
   
       return ResponseEntity.ok(alumnosPage);
   }
   ```
    - **Descripción**: Devuelve una lista de alumnos paginada y ordenada según los parámetros proporcionados.
    - **Endpoint**: `GET /api/v1/alumnos/paged`

6. **Eliminar Alumno**
   ```java
   @DeleteMapping("/{id}")
   public ResponseEntity<Void> deleteAlumno(@PathVariable Long id) {
       alumnoService.deleteAlumno(id);
       return ResponseEntity.noContent().build();
   }
   ```
    - **Descripción**: Elimina un alumno por su ID.
    - **Endpoint**: `DELETE /api/v1/alumnos/{id}`

7. **Buscar Alumnos por Nombre**
   ```java
   @GetMapping("/search/nombre")
   public ResponseEntity<List<AlumnoDTO>> findByNombre(@RequestParam String nombre) {
       List<AlumnoDTO> alumnos = alumnoService.findByNombre(nombre);
       return ResponseEntity.ok(alumnos);
   }
   ```
    - **Descripción**: Busca alumnos cuyos nombres coincidan (parcialmente o totalmente) con el parámetro proporcionado.
    - **Endpoint**: `GET /api/v1/alumnos/search/nombre`

8. **Buscar Alumnos por Rango de Edad**
   ```java
   @GetMapping("/search/edad")
   public ResponseEntity<List<AlumnoDTO>> findByEdad(@RequestParam int edadMinima, @RequestParam int edadMaxima) {
       List<AlumnoDTO> alumnos = alumnoService.findByEdad(edadMinima, edadMaxima);
       return ResponseEntity.ok(alumnos);
   }
   ```
    - **Descripción**: Busca alumnos cuya edad esté dentro del rango proporcionado.
    - **Endpoint**: `GET /api/v1/alumnos/search/edad`

---

### Resumen

Este controlador sigue las buenas prácticas de diseño REST, separando claramente las responsabilidades de las operaciones CRUD y exponiendo endpoints adicionales para búsquedas específicas. Cada método retorna respuestas HTTP adecuadas (como `200 OK`, `201 Created`, y `204 No Content`), lo que garantiza una interacción consistente con los clientes de la API.

Además, la inyección de dependencias mediante `@RequiredArgsConstructor` permite que la clase sea más limpia y fácil de mantener. La lógica del negocio sigue ubicada en la capa de servicio, garantizando que el controlador solo se encargue del enrutamiento y el manejo de las solicitudes.