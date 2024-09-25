package school.sptech.exerciciojpavalidation.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import school.sptech.exerciciojpavalidation.entity.Evento;
import school.sptech.exerciciojpavalidation.service.EventoService;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("eventos")
public class EventoController {

    @Autowired
    public EventoService service;

    @GetMapping
    public ResponseEntity<List<Evento>> listar(){
        List<Evento> eventos = service.listar();

        if(eventos.isEmpty()){
            return ResponseEntity.noContent().build(); // 204 noContents
        }
        return ResponseEntity.ok(eventos); // 200 ok com corpo
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> listarPorId(@PathVariable Integer id){
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/gratuitos")
    public ResponseEntity<List<Evento>> listarGratuito(){
        return ResponseEntity.ok(service.listarGratuitoTrue());
    }

    @GetMapping("/data")
    public ResponseEntity<List<Evento>> buscarPorDataOcorrencia(@RequestParam @Valid  LocalDate ocorrencia){
        return ResponseEntity.ok(service.listarPorDataOcorrencia(ocorrencia));
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<Evento>> buscarPorInicioFim(
            @RequestParam @Valid LocalDate inicio,
            @RequestParam @Valid LocalDate fim){
        return ResponseEntity.ok(service.listarPorInicioFim(inicio, fim));
    }

    @PostMapping
    public ResponseEntity<Evento> criarEvento(@RequestBody @Valid Evento novoEvento){
        Evento eventos = service.criar(novoEvento);
        return ResponseEntity.status(201).body(eventos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> atualizarEvento(
            @PathVariable Integer id,
            @RequestBody @Valid Evento eventoAtualizado){
        Evento eventos = service.atualizar(id, eventoAtualizado);
        return ResponseEntity.ok(eventos);
    }

    @PatchMapping("/{id}/cancelamento")
    public ResponseEntity<Evento> cancelarEvento(@PathVariable Integer id) {
        service.cancelarEvento(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Evento> delete(@PathVariable @Valid Integer id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
