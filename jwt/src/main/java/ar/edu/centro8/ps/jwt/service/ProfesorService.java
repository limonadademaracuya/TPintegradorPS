package ar.edu.centro8.ps.jwt.service;

import ar.edu.centro8.ps.jwt.model.Profesor;
import ar.edu.centro8.ps.jwt.repository.IProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfesorService implements IProfesorService {

    @Autowired
    private IProfesorRepository profesorRepository;

    @Override
    public List<Profesor> findAll() {
        return profesorRepository.findAll();
    }

    @Override
    public Optional<Profesor> findById(Long id) {
        return profesorRepository.findById(id);
    }

    @Override
    public Profesor save(Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    @Override
    public Profesor update(Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    @Override
    public void deleteById(Long id) {
        profesorRepository.deleteById(id);
    }
}
