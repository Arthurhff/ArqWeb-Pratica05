package com.example.universidade.controller;

import com.example.universidade.model.Curso;
import com.example.universidade.repository.CursoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    private final CursoRepository repo;

    public CursoController(CursoRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public Curso create(@RequestBody Curso curso){
        return repo.save(curso);
    }

    @GetMapping
    public List<Curso> list(){ return repo.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> getById(@PathVariable Long id){
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> update(@PathVariable Long id, @RequestBody Curso curso){
        return repo.findById(id).map(existing -> {
            existing.setNome(curso.getNome());
            existing.setCargaHoraria(curso.getCargaHoraria());
            repo.save(existing);
            return ResponseEntity.ok(existing);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        return repo.findById(id).map(existing -> {
            repo.deleteById(id);
            return ResponseEntity.noContent().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
