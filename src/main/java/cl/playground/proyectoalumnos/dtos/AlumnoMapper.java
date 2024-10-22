package cl.playground.proyectoalumnos.dtos;

import cl.playground.proyectoalumnos.model.Alumno;

public class AlumnoMapper {

    public static AlumnoDTO toDto(Alumno alumno) {
        return new AlumnoDTO(
                alumno.getId(),
                alumno.getNombre(),
                alumno.getApellido(),
                alumno.getEdad()
        );
    }

    public static Alumno createDtoToEntity(CreateAlumnoDTO createAlumnoDTO) {
        Alumno alumno = new Alumno();
        alumno.setNombre(createAlumnoDTO.getNombre());
        alumno.setApellido(createAlumnoDTO.getApellido());
        alumno.setEdad(createAlumnoDTO.getEdad());

        return alumno;
    }

    public static Alumno updateDtoToEntity(UpdateAlumnoDTO updateAlumnoDTO) {
        Alumno alumno = new Alumno();
        alumno.setNombre(updateAlumnoDTO.getNombre());
        alumno.setApellido(updateAlumnoDTO.getApellido());
        alumno.setEdad(updateAlumnoDTO.getEdad());

        return alumno;
    }
}
