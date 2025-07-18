package ar.edu.centro8.ps.jwt.repository;

import ar.edu.centro8.ps.jwt.model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProfesorRepository extends JpaRepository<Profesor, Long> {
}
