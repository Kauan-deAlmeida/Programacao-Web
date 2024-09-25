package school.sptech.projeto_06_service.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import school.sptech.projeto_06_service.entity.Agendamento;
import school.sptech.projeto_06_service.service.AgendamentoService;

import java.util.List;

@Controller
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @GetMapping
    public ResponseEntity<List<Agendamento>> listar(){
        List<Agendamento> agendamentos = agendamentoService.listar();

        if(agendamentos.isEmpty()){
            return ResponseEntity.noContent().build(); // 204 noContents
        }
        return ResponseEntity.ok(agendamentos); // 200 ok com corpo
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agendamento> buscarPorId(@PathVariable Integer id){
//            valida se não tiver algo, retorna 404, se tiver, retorna 200 e o corpo
        return ResponseEntity.ok(agendamentoService.porId(id));
    }

    @PostMapping
    public ResponseEntity<Agendamento> criar(@RequestBody @Valid Agendamento novoAgendamento){
        Agendamento agendamentoCriado = agendamentoService.criar(novoAgendamento);
        return ResponseEntity.created(null).body(agendamentoCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agendamento> atualizar(
            @PathVariable Integer id,
            @RequestBody @Valid Agendamento agendamentoAtualizado){
        Agendamento agendamentoRetorno = agendamentoService.atualizar(id, agendamentoAtualizado);
        return ResponseEntity.ok(agendamentoRetorno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Agendamento> deletar(@PathVariable Integer id){
        agendamentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
