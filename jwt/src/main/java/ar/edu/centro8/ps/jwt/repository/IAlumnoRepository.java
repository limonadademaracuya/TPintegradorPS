package ar.edu.centro8.ps.jwt.repository;

import ar.edu.centro8.ps.jwt.model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAlumnoRepository extends JpaRepository<Alumno, Long> {
}
