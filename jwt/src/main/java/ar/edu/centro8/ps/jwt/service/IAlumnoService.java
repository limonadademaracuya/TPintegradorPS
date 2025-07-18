package ar.edu.centro8.ps.jwt.service;

import ar.edu.centro8.ps.jwt.model.Alumno;

import java.util.List;
import java.util.Optional;

public interface IAlumnoService {
    List<Alumno> findAll();
    Optional<Alumno> findById(Long id);
    Alumno save(Alumno alumno);
    Alumno update(Alumno alumno);
    void deleteById(Long id);
}
