package ar.edu.centro8.ps.jwt.service;

import ar.edu.centro8.ps.jwt.model.Profesor;

import java.util.List;
import java.util.Optional;

public interface IProfesorService {
    List<Profesor> findAll();
    Optional<Profesor> findById(Long id);
    Profesor save(Profesor profesor);
    Profesor update(Profesor profesor);
    void deleteById(Long id);
}
