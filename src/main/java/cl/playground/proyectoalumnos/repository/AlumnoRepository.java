package cl.playground.proyectoalumnos.repository;

import cl.playground.proyectoalumnos.model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
    List<Alumno> findByNombreContainingIgnoreCase(String nombre);
    List<Alumno> findByEdadBetween(int edadMinima, int edadMaxima);
    List<Alumno> findAlumnoByNombreContainingIgnoreCaseAndEdadBetween(String nombre, int edadMinima, int edadMaxima);

    @Query("SELECT a FROM Alumno a WHERE " +
            "LOWER(a.nombre) LIKE LOWER(CONCAT('%', :termino, '%')) OR " +
            "LOWER(a.apellido) LIKE LOWER(CONCAT('%', :termino, '%'))")
    List<Alumno> findByNombreOrApellido(@Param("termino") String termino);
}


