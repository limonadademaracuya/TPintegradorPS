package ar.edu.centro8.ps.jwt.service;

import ar.edu.centro8.ps.jwt.model.Curso;

import java.util.List;
import java.util.Optional;

public interface ICursoService {
    List<Curso> findAll();
    Optional<Curso> findById(Long id);
    Curso save(Curso curso);
    Curso update(Curso curso);
    void deleteById(Long id);
}
