package ar.edu.centro8.ps.jwt.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Table(name = "alumnos")
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String apellido;

    @ManyToMany(mappedBy = "alumnos", fetch = FetchType.LAZY)
    private Set<Curso> cursos = new HashSet<>();
}
