package cl.playground.proyectoalumnos.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationException extends RuntimeException {

    private final List<String> errors;


    public ValidationException(List<String> errors) {
        super("Errores de validacion");
        this.errors = errors;
    }

    public ValidationException(String message) {
        super(message);
        this.errors = new ArrayList<>();
        this.errors.add(message);
    }

    public List<String> getErrors() {
        return errors;
    }
}
