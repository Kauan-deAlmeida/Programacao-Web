package school.sptech.ex_many_to_one_dto1.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.ex_many_to_one_dto1.dto.ativo.AtivoMapper;
import school.sptech.ex_many_to_one_dto1.dto.ativo.AtivoRequestDto;
import school.sptech.ex_many_to_one_dto1.dto.ativo.AtivoResponseDto;
import school.sptech.ex_many_to_one_dto1.entity.Ativo;
import school.sptech.ex_many_to_one_dto1.service.AtivoService;

import java.util.List;

// TODO: TERMINAR A CLASSE
@RestController
@RequiredArgsConstructor
@RequestMapping("/ativos")
public class AtivoController {

    private final AtivoService ativoService;

    @PostMapping
    public ResponseEntity<AtivoResponseDto> cadastrar(
            @RequestBody @Valid AtivoRequestDto ativoRequestDto
    ) {
        return ResponseEntity.created(null).body(
                AtivoMapper.toAtivoResponseDto(
                        ativoService.salvar(
                                AtivoMapper.toAtivoEntity(ativoRequestDto),
                                ativoRequestDto.getCarteiraId())));
    }

    @GetMapping
    public ResponseEntity<List<AtivoResponseDto>> buscarTodos() {
        List<Ativo> ativos = ativoService.buscarTodos();
        if(ativos.isEmpty()) throw new ResponseStatusException(HttpStatusCode.valueOf(204));
        return ResponseEntity.ok(ativos.stream().map(AtivoMapper::toAtivoResponseDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtivoResponseDto> buscarPorId(@PathVariable int id) {
        return ResponseEntity.ok(AtivoMapper.toAtivoResponseDto(ativoService.buscarPorId(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Ativo> deletarPorId(@PathVariable int id) {
        ativoService.deletarPorId(id);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/carteiras")
    public ResponseEntity<List<AtivoResponseDto>> buscarAtivosPorCarteiraNome(@RequestParam @Valid String nomeInvestidor) {
        List<Ativo> ativos = ativoService.buscarAtivosPorInvestidorNome(nomeInvestidor);
        if(ativos.isEmpty()) throw new ResponseStatusException(HttpStatusCode.valueOf(204));
        return ResponseEntity.ok(ativos.stream().map(AtivoMapper::toAtivoResponseDto).toList());
    }

    @GetMapping("/carteiras/media")
    public ResponseEntity<Double> buscarMediaAtivosPorCarteiraNome(@RequestParam String nomeInvestidor) {
        return ResponseEntity.ok(ativoService.buscarMediaAtivosPorInvestidorNome(nomeInvestidor));
    }
}
