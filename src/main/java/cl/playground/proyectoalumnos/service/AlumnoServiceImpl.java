package cl.playground.proyectoalumnos.service;

import cl.playground.proyectoalumnos.dtos.AlumnoDTO;
import cl.playground.proyectoalumnos.dtos.AlumnoMapper;
import cl.playground.proyectoalumnos.dtos.CreateAlumnoDTO;
import cl.playground.proyectoalumnos.dtos.UpdateAlumnoDTO;
import cl.playground.proyectoalumnos.exception.AlumnoNotFoundException;
import cl.playground.proyectoalumnos.model.Alumno;
import cl.playground.proyectoalumnos.repository.AlumnoRepository;
import cl.playground.proyectoalumnos.validation.AlumnoValidator;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AlumnoServiceImpl implements AlumnoService {

    private final AlumnoRepository alumnoRepository;
    private final AlumnoValidator alumnoValidator;

    @Override
    @Transactional
    public AlumnoDTO createAlumno(CreateAlumnoDTO createDto) {
        alumnoValidator.validateCreateAlumno(createDto);

        Alumno alumno = AlumnoMapper.createDtoToEntity(createDto);
        alumno = alumnoRepository.save(alumno);

        return AlumnoMapper.toDto(alumno);
    }

    @Override
    @Transactional(readOnly = true)
    public AlumnoDTO getAlumnoById(Long id) {
        return alumnoRepository.findById(id)
                .map(AlumnoMapper::toDto)
                .orElseThrow(() -> new AlumnoNotFoundException(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<AlumnoDTO> getAllAlumnos() {
        return alumnoRepository.findAll().stream()
                .map(AlumnoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AlumnoDTO> getAllAlumnosPaginated(Pageable pageable) {
        return alumnoRepository.findAll(pageable)
                .map(AlumnoMapper::toDto);
    }

    @Override
    public AlumnoDTO updateAlumno(Long id, UpdateAlumnoDTO updateDto) {
        alumnoValidator.validateUpdateAlumno(updateDto);

        Alumno existingAlumno = alumnoRepository.findById(id)
                .orElseThrow(() -> new AlumnoNotFoundException(id));

        existingAlumno.setNombre(updateDto.getNombre());
        existingAlumno.setApellido(updateDto.getApellido());
        existingAlumno.setEdad(updateDto.getEdad());
        existingAlumno = alumnoRepository.save(existingAlumno);

        return AlumnoMapper.toDto(existingAlumno);
    }

    @Override
    public void deleteAlumno(Long id) {
        if (!alumnoRepository.existsById(id)) {
           throw new AlumnoNotFoundException(id);
        }
        alumnoRepository.deleteById(id);
    }

    @Override
    public List<AlumnoDTO> findByNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        }

        return alumnoRepository.findByNombreContainingIgnoreCase(nombre)
                .stream()
                .map(AlumnoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AlumnoDTO> findByEdad(int edadMinima, int edadMaxima) {
        if (edadMinima < 0) {
            throw new IllegalArgumentException("La edad minima no puede ser negativa");
        }
        if (edadMaxima < edadMinima) {
            throw new IllegalArgumentException("La edad maxima no puede ser menor a la minima");
        }

        return alumnoRepository.findByEdadBetween(edadMinima, edadMaxima)
                .stream()
                .map(AlumnoMapper::toDto)
                .collect(Collectors.toList());
    }
}
