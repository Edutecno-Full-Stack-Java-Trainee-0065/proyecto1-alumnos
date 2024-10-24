package cl.playground.proyectoalumnos.service;

import cl.playground.proyectoalumnos.dtos.AlumnoDTO;
import cl.playground.proyectoalumnos.dtos.CreateAlumnoDTO;
import cl.playground.proyectoalumnos.dtos.UpdateAlumnoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AlumnoService {

    // CRUD básico
    AlumnoDTO createAlumno(CreateAlumnoDTO createDto);
    AlumnoDTO getAlumnoById(Long id);
    List<AlumnoDTO> getAllAlumnos();
    Page<AlumnoDTO> getAllAlumnosPaginated(Pageable pageable);
    AlumnoDTO updateAlumno(Long id, UpdateAlumnoDTO updateDto);
    void deleteAlumno(Long id);

    // Métodos adicionales de búsqueda
    List<AlumnoDTO> findByNombre(String nombre);
    List<AlumnoDTO> findByPalabra(String palabra);
    List<AlumnoDTO> findByEdad(int edadMinima, int edadMaxima);

}
