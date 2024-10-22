package cl.playground.proyectoalumnos.model;

import jakarta.persistence.*;
import lombok.*;

@Data // Lombok
@Entity
@Table(name = "alumnos")
@AllArgsConstructor // Lombok
@NoArgsConstructor // Lombok
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 30)
    private String nombre;

    @Column(name = "apellido", length = 30)
    private String apellido;

    @Column(name = "edad")
    private int edad;
}
