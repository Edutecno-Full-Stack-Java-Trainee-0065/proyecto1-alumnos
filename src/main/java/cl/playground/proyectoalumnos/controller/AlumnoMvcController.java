package cl.playground.proyectoalumnos.controller;

import cl.playground.proyectoalumnos.dtos.AlumnoDTO;
import cl.playground.proyectoalumnos.dtos.CreateAlumnoDTO;
import cl.playground.proyectoalumnos.dtos.UpdateAlumnoDTO;
import cl.playground.proyectoalumnos.service.AlumnoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/alumnos")
@RequiredArgsConstructor
public class AlumnoMvcController {

    private final AlumnoService alumnoService;

    @GetMapping
    public String listarAlumnos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(required = false) String nombreFilter,
            @RequestParam(required = false) Integer edadMin,
            @RequestParam(required = false) Integer edadMax,
            Model model,
            RedirectAttributes redirectAttributes) {

        try {
            Sort.Direction direction = Sort.Direction.fromString(sortDir);
            PageRequest pageRequest = PageRequest.of(page, size, Sort.by(direction, sortBy));

            // Si hay filtros, usamos los métodos de búsqueda existentes
            if ((nombreFilter != null && !nombreFilter.trim().isEmpty()) ||
                    (edadMin != null && edadMax != null)) {

                List<AlumnoDTO> filteredAlumnos;
                if (nombreFilter != null && !nombreFilter.trim().isEmpty()) {
                    filteredAlumnos = alumnoService.findByNombre(nombreFilter);
                } else {
                    filteredAlumnos = alumnoService.findByEdad(edadMin, edadMax);
                }

                // Convertir la lista filtrada a una página
                int start = (int) pageRequest.getOffset();
                int end = Math.min((start + pageRequest.getPageSize()), filteredAlumnos.size());

                Page<AlumnoDTO> alumnosPage = new PageImpl<>(
                        filteredAlumnos.subList(start, end),
                        pageRequest,
                        filteredAlumnos.size()
                );

                model.addAttribute("alumnos", alumnosPage);
            } else {
                // Si no hay filtros, usar la paginación normal
                model.addAttribute("alumnos", alumnoService.getAllAlumnosPaginated(pageRequest));
            }

            model.addAttribute("currentPage", page);
            model.addAttribute("sortBy", sortBy);
            model.addAttribute("sortDir", sortDir);
            model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

            return "alumnos/lista";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/alumnos";
        }
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("alumno", new CreateAlumnoDTO());
        return "alumnos/formulario";
    }

    @PostMapping("/nuevo")
    public String crearAlumno(@ModelAttribute CreateAlumnoDTO alumno, RedirectAttributes redirectAttributes) {
        try {
            alumnoService.createAlumno(alumno);
            redirectAttributes.addFlashAttribute("mensaje", "Alumno creado exitosamente");
            return "redirect:/alumnos";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/alumnos/nuevo";
        }
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        model.addAttribute("alumno", alumnoService.getAlumnoById(id));
        return "alumnos/editar";
    }

    @PostMapping("/editar/{id}")
    public String actualizarAlumno(@PathVariable Long id, @ModelAttribute UpdateAlumnoDTO alumno,
                                   RedirectAttributes redirectAttributes) {
        try {
            alumnoService.updateAlumno(id, alumno);
            redirectAttributes.addFlashAttribute("mensaje", "Alumno actualizado exitosamente");
            return "redirect:/alumnos";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/alumnos/editar/" + id;
        }
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarAlumno(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            alumnoService.deleteAlumno(id);
            redirectAttributes.addFlashAttribute("mensaje", "Alumno eliminado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/alumnos";
    }

    @GetMapping("/buscar")
    public String mostrarFormularioBusqueda() {
        return "alumnos/buscar";
    }

    @GetMapping("/buscar/nombre")
    public String buscarPorNombre(@RequestParam String nombre, Model model) {
        model.addAttribute("alumnos", alumnoService.findByNombre(nombre));
        return "alumnos/resultados-busqueda";
    }

    @GetMapping("/buscar/edad")
    public String buscarPorEdad(@RequestParam int edadMinima, @RequestParam int edadMaxima,
                                Model model, RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("alumnos", alumnoService.findByEdad(edadMinima, edadMaxima));
            return "alumnos/resultados-busqueda";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/alumnos/buscar";
        }
    }
}