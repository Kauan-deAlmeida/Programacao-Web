package school.sptech.exerciciojpavalidation.service;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.exerciciojpavalidation.entity.Evento;
import school.sptech.exerciciojpavalidation.repository.EventoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static java.time.LocalDate.now;

@Service
public class EventoService {

    @Autowired
    public EventoRepository repository;

    public List<Evento> listar(){
        return repository.findAll();
    }

    public Evento buscarPorId(Integer id){
        Optional<Evento> eventoOpt = repository.findById(id);
        if(eventoOpt.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return eventoOpt.get();
    }

    public Evento criar(Evento novoEvento){
        novoEvento.setId(null);
        return repository.save(novoEvento);
    }

    public Evento atualizar(Integer id, Evento eventoAtualizado) {
        Evento eventos = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.valueOf(404)));
        if(eventos.getStatus().equals("CANCELADO") || eventos.getStatus().equals("FINALIZADO")){
            throw new ResponseStatusException(HttpStatus.valueOf(422));
        }

        eventoAtualizado.setId(id);
        return repository.save(eventoAtualizado);
    }

    public void deletar(Integer id) {
        if(!repository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        repository.deleteById(id);
    }

    public List<Evento> listarGratuitoTrue() {
        List<Evento> eventoGratuitos = repository.findByGratuitoTrue();
        if(repository.findAll().isEmpty()){
            throw new ResponseStatusException(HttpStatus.valueOf(204));
        }
        if(eventoGratuitos.isEmpty()){
            throw new ResponseStatusException(HttpStatus.valueOf(204));
        }

        return eventoGratuitos;
    }

    public List<Evento> listarPorDataOcorrencia(LocalDate ocorrencia) {
        List<Evento> eventos = repository.findByDataEventoOrDataPublicacao(ocorrencia, ocorrencia);
        if(eventos.isEmpty()){
            throw new ResponseStatusException(HttpStatus.valueOf(204));
        }
        return eventos;
    }

    public List<Evento> listarPorInicioFim(LocalDate inicio, LocalDate fim) {
        List<Evento> eventos = repository.findByDataEventoBetween(inicio, fim);

        if(inicio.isAfter(fim)){
            throw new ResponseStatusException(HttpStatus.valueOf(400));
        }
        if(eventos.isEmpty()){
            throw new ResponseStatusException(HttpStatus.valueOf(204));
        }

        return eventos;
    }

    public Evento cancelarEvento(Integer id) {
        Evento eventoExistente = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.valueOf(404)));

        if (eventoExistente.getStatus().equals("CANCELADO") || eventoExistente.getStatus().equals("FINALIZADO")) {
            throw new ResponseStatusException(HttpStatus.valueOf(422));
        }

        eventoExistente.setCancelado(true);
        return repository.save(eventoExistente);
    }
}
