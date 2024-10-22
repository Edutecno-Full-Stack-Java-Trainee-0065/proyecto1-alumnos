package cl.playground.proyectoalumnos.exception;

public class AlumnoNotFoundException extends RuntimeException {

    public AlumnoNotFoundException(Long id) {
        super("No se encontro el alumno con ID: " + id);
    }

}
