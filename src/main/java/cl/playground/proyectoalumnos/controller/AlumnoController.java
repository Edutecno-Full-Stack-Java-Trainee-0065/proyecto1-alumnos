package cl.playground.proyectoalumnos.controller;

import cl.playground.proyectoalumnos.dtos.AlumnoDTO;
import cl.playground.proyectoalumnos.dtos.CreateAlumnoDTO;
import cl.playground.proyectoalumnos.dtos.UpdateAlumnoDTO;
import cl.playground.proyectoalumnos.service.AlumnoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/alumnos")
@RequiredArgsConstructor
public class AlumnoController {

    private final AlumnoService alumnoService;

    @PostMapping
    public ResponseEntity<AlumnoDTO> createAlumno(@RequestBody CreateAlumnoDTO createAlumnoDTO) {
        AlumnoDTO alumnoDTO = alumnoService.createAlumno(createAlumnoDTO);
        return new ResponseEntity<>(alumnoDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlumnoDTO> getAlumnoById(@PathVariable Long id) {
        AlumnoDTO alumnoDTO = alumnoService.getAlumnoById(id);
        return ResponseEntity.ok(alumnoDTO);
    }

    @GetMapping
    public ResponseEntity<List<AlumnoDTO>> getAllAlumnos() {
        List<AlumnoDTO> alumnos = alumnoService.getAllAlumnos();
        return ResponseEntity.ok(alumnos);
    }
    @PutMapping("/{id}")
    public ResponseEntity<AlumnoDTO> updateAlumno(
            @PathVariable Long id,
            @RequestBody UpdateAlumnoDTO updateDto) {
        AlumnoDTO updatedAlumno = alumnoService.updateAlumno(id, updateDto);
        return ResponseEntity.ok(updatedAlumno);
    }

    /*
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
    */
}
