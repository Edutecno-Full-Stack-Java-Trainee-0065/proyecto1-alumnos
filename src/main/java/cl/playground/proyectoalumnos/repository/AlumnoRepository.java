package cl.playground.proyectoalumnos.repository;

import cl.playground.proyectoalumnos.model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
    List<Alumno> findByNombreContainingIgnoreCase(String nombre);
    List<Alumno> findByEdadBetween(int edadMinima, int edadMaxima);
    List<Alumno> findAlumnoByNombreContainingIgnoreCaseAndEdadBetween(String nombre, int edadMinima, int edadMaxima);
}
