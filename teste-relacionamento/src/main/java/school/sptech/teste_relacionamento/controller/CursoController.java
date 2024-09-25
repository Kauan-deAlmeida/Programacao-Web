package school.sptech.teste_relacionamento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.teste_relacionamento.entity.Curso;
import school.sptech.teste_relacionamento.service.CursoService;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping
    public ResponseEntity<Curso> cadastro(
            @RequestBody Curso curso
    ) {
        Curso cursoSalvo = this.cursoService.cadastrar(curso);
        return ResponseEntity.status(201).body(cursoSalvo);
    }

    @GetMapping
    public ResponseEntity<List<Curso>> listagem() {
        List<Curso> listagem = cursoService.listar();
        if (listagem.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(listagem);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> buscarPorId(@PathVariable Integer id) {
        Curso curso = this.cursoService.buscarPorId(id);
        return ResponseEntity.status(200).body(curso);
    }
}
