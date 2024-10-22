package cl.playground.proyectoalumnos.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlumnoDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private int edad;

}
