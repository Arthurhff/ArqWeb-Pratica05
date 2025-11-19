package com.example.universidade.controller;

import com.example.universidade.model.Aluno;
import com.example.universidade.model.Curso;
import com.example.universidade.repository.AlunoRepository;
import com.example.universidade.repository.CursoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    private final AlunoRepository alunoRepo;
    private final CursoRepository cursoRepo;

    public AlunoController(AlunoRepository alunoRepo, CursoRepository cursoRepo) {
        this.alunoRepo = alunoRepo;
        this.cursoRepo = cursoRepo;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Map<String, Object> payload){
        // payload expected: nome, email, dataNascimento (YYYY-MM-DD), cursoId
        Object cursoIdObj = payload.get("cursoId");
        if(cursoIdObj == null) return ResponseEntity.badRequest().body("campo cursoId é obrigatório");
        Long cursoId = Long.valueOf(String.valueOf(cursoIdObj));
        Curso curso = cursoRepo.findById(cursoId).orElse(null);
        if(curso == null) return ResponseEntity.badRequest().body("curso não encontrado com id: " + cursoId);

        Aluno a = Aluno.builder()
                .nome((String) payload.get("nome"))
                .email((String) payload.get("email"))
                .dataNascimento(java.time.LocalDate.parse((String) payload.get("dataNascimento")))
                .curso(curso)
                .build();
        Aluno saved = alunoRepo.save(a);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public List<Aluno> list(){ return alunoRepo.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> getById(@PathVariable Long id){
        return alunoRepo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Map<String,Object> payload){
        return alunoRepo.findById(id).map(existing -> {
            if(payload.containsKey("nome")) existing.setNome((String) payload.get("nome"));
            if(payload.containsKey("email")) existing.setEmail((String) payload.get("email"));
            if(payload.containsKey("dataNascimento")) existing.setDataNascimento(java.time.LocalDate.parse((String) payload.get("dataNascimento")));
            if(payload.containsKey("cursoId")){
                Long cursoId = Long.valueOf(String.valueOf(payload.get("cursoId")));
                Curso curso = cursoRepo.findById(cursoId).orElse(null);
                if(curso == null) return ResponseEntity.badRequest().body("curso não encontrado: " + cursoId);
                existing.setCurso(curso);
            }
            alunoRepo.save(existing);
            return ResponseEntity.ok(existing);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        return alunoRepo.findById(id).map(existing -> {
            alunoRepo.deleteById(id);
            return ResponseEntity.noContent().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
