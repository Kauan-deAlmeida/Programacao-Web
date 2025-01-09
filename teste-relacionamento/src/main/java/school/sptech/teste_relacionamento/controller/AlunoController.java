package school.sptech.teste_relacionamento.controller;


import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.teste_relacionamento.dto.aluno.AlunoCriacaoRequisicaoDto;
import school.sptech.teste_relacionamento.dto.aluno.AlunoDetalheRespostaDto;
import school.sptech.teste_relacionamento.dto.aluno.AlunoMapper;
import school.sptech.teste_relacionamento.entity.Aluno;
import school.sptech.teste_relacionamento.service.AlunoService;

import java.util.List;

@RestController
@RequestMapping("/alunos")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoService alunoService;

    @GetMapping
    @ApiResponse(description = "204, se tiver dados retorna 200")
    private ResponseEntity<List<AlunoDetalheRespostaDto>> listar(){
        List<Aluno> alunos = alunoService.listar();
        if(alunos.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(alunos.stream().map(AlunoMapper::toResposataDto).toList());
    }

    @GetMapping("/{id}")
    @ApiResponse(description = "204, se tiver dados retorna 200")
    private ResponseEntity<AlunoDetalheRespostaDto> buscarPorId(@PathVariable Integer id){
        Aluno aluno = alunoService.buscarPorId(id);
        AlunoDetalheRespostaDto dto = AlunoMapper.toResposataDto(aluno);
        return ResponseEntity.ok(dto);
//        return ResponseEntity.ok(AlunoMapper.toResposataDto(aluno));
    }

    @PostMapping
    public ResponseEntity<AlunoDetalheRespostaDto> cadastrar(
            @RequestBody @Valid AlunoCriacaoRequisicaoDto criacaoDto
            ){
        Aluno alunoEntidade = AlunoMapper.toEntidadeDto(criacaoDto);
        Aluno alunoSalvo = alunoService.cadastrar(alunoEntidade, criacaoDto.getCursoId());
        AlunoDetalheRespostaDto dto = AlunoMapper.toResposataDto(alunoSalvo);

        return ResponseEntity.created(null).body(AlunoMapper.toResposataDto(alunoService.cadastrar(AlunoMapper.toEntidadeDto(criacaoDto), criacaoDto.getCursoId())));   
//        return ResponseEntity.created(null).body(dto);
//        return ResponseEntity.status(200).body(AlunoMapper.toResposataDto(alunoService.cadastrar(AlunoMapper.toEntidadeDto(criacaoDto))));
    }
}
