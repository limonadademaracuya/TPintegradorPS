package ar.edu.centro8.ps.jwt.controller;

import ar.edu.centro8.ps.jwt.model.Curso;
import ar.edu.centro8.ps.jwt.service.ICursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private ICursoService cursoService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR', 'ESTUDIANTE')")
    public ResponseEntity<List<Curso>> getAllCursos() {
        return ResponseEntity.ok(cursoService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR', 'ESTUDIANTE')")
    public ResponseEntity<Curso> getCursoById(@PathVariable Long id) {
        return cursoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Curso> createCurso(@RequestBody Curso curso) {
        return ResponseEntity.ok(cursoService.save(curso));
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR')") // Edición solo si es profesor del curso: implementar validación extra si se desea
    public ResponseEntity<Curso> updateCurso(@PathVariable Long id, @RequestBody Curso curso) {
        curso.setId(id);
        return ResponseEntity.ok(cursoService.update(curso));
    }

    // @PutMapping("/{id}")
    // @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR')") // Edición solo si es profesor del curso: implementar validación extra si se desea
    // public ResponseEntity<Curso> updateCurso(@PathVariable Long id, @RequestBody Curso curso) {
    //     curso.setId(id);
    //     return ResponseEntity.ok(cursoService.update(curso));
    // }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCurso(@PathVariable Long id) {
        cursoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
