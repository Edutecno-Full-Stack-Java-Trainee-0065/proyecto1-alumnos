package cl.playground.proyectoalumnos.validation;

import cl.playground.proyectoalumnos.dtos.CreateAlumnoDTO;
import cl.playground.proyectoalumnos.dtos.UpdateAlumnoDTO;
import cl.playground.proyectoalumnos.exception.InvalidAlumnoDataException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AlumnoValidator {

    public void validateCreateAlumno(CreateAlumnoDTO dto) {
        List<String> errors = new ArrayList<>();

        if (dto.getNombre() == null || dto.getNombre().trim().isEmpty()) {
            errors.add("El nombre es requerido");
        }

        if (dto.getNombre().length() > 30) {
            errors.add("El nombre no puede exceder los 30 caracteres");
        }

        if (dto.getApellido() == null || dto.getApellido().trim().isEmpty()) {
            errors.add("El apellido es requerido");
        }

        if (dto.getApellido().length() > 30) {
            errors.add("El apellido no puede exceder los 30 caracteres");
        }

        if (dto.getEdad() < 0 || dto.getEdad() > 120) {
            errors.add("La edad debe estar entre 0 y 120 años");
        }

        if (!errors.isEmpty()) {
            throw new InvalidAlumnoDataException(String.join(", ", errors));
        }
    }

    public void validateUpdateAlumno(UpdateAlumnoDTO dto) {
        List<String> errors = new ArrayList<>();

        if (dto.getNombre() != null) {
            if (dto.getNombre().trim().isEmpty()) {
                errors.add("El nombre no puede estar vacío");
            } else if (dto.getNombre().length() > 30) {
                errors.add("El nombre no puede exceder los 30 caracteres");
            }
        }

        if (dto.getApellido() != null) {
            if (dto.getApellido().trim().isEmpty()) {
                errors.add("El apellido no puede estar vacío");
            } else if (dto.getApellido().length() > 30) {
                errors.add("El apellido no puede exceder los 30 caracteres");
            }
        }

        if (dto.getEdad() < 0 || dto.getEdad() > 120) {
            errors.add("La edad debe estar entre 0 y 120 años");
        }

        if (!errors.isEmpty()) {
            throw new InvalidAlumnoDataException(String.join(", ", errors));
        }
    }

}
