package cl.playground.proyectoalumnos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AlumnoNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAlumnoNotFoundException(AlumnoNotFoundException ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());

        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                "Alumno no encontrado",
                details
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(InvalidAlumnoDataException.class)
    public ResponseEntity<ErrorResponse> handleInvalidAlumnoDataException(InvalidAlumnoDataException ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());

        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                "Datos de alumno invalidos",
                details
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());

        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                "Error en el servidor",
                details
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
