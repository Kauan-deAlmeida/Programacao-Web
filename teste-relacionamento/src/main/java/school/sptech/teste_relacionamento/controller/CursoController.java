package school.sptech.teste_relacionamento.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.teste_relacionamento.dto.curso.CursoCriacaoRequisicaoDto;
import school.sptech.teste_relacionamento.dto.curso.CursoDetalheRespostaDto;
import school.sptech.teste_relacionamento.dto.curso.CursoMapper;
import school.sptech.teste_relacionamento.dto.curso.CursoResumoRespostaDto;
import school.sptech.teste_relacionamento.entity.Curso;
import school.sptech.teste_relacionamento.service.CursoService;

import java.util.List;

@RestController
@RequestMapping("/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService cursoService;

    @PostMapping
        public ResponseEntity<CursoDetalheRespostaDto> cadastro(
            @RequestBody CursoCriacaoRequisicaoDto cursoCriacaoDto
    ) {
        Curso entidade = CursoMapper.toCriacaoEntity(cursoCriacaoDto);
        Curso cursoSalvo = this.cursoService.cadastrar(entidade);
        CursoDetalheRespostaDto dtoResposta = CursoMapper.toDetalheDto(cursoSalvo);

//        return ResponseEntity.status(201).body(dtoResposta);
        return ResponseEntity.status(201).body(CursoMapper.toDetalheDto(cursoService.cadastrar(CursoMapper.toCriacaoEntity(cursoCriacaoDto))));
    }

    @GetMapping
    @ApiResponse(description = "204, se tiver dados retorna 200")
    public ResponseEntity<List<CursoResumoRespostaDto>> listagem() {
        List<Curso> listagem = cursoService.listar();
        if (listagem.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(listagem.stream().map(CursoMapper::toResumoDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoDetalheRespostaDto> buscarPorId(@PathVariable Integer id) {
        Curso curso = this.cursoService.buscarPorId(id);
        return ResponseEntity.status(200).body(CursoMapper.toDetalheDto(curso));
    }
}
