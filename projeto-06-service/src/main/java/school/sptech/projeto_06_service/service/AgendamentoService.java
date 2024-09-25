package school.sptech.projeto_06_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.projeto_06_service.entity.Agendamento;
import school.sptech.projeto_06_service.exception.EndidadeNaoEncontradaException;
import school.sptech.projeto_06_service.repository.AgendamentoRepository;

import java.net.http.HttpClient;
import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService {

    @Autowired
    public AgendamentoRepository agendamentoRepository;

    public List<Agendamento> listar(){
        return agendamentoRepository.findAll();
    }

    public Agendamento porId(Integer id){
        Optional<Agendamento> agdOpt = agendamentoRepository.findById(id);
        if(agdOpt.isEmpty()){
            throw new EndidadeNaoEncontradaException("Agendamento");
//            throw new ResponseStatusException(HttpStatus.valueOf(404));
        }

        return agdOpt.get();
//        return agendamentoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Agendamento criar(Agendamento novoAgendamento) {
        if(agendamentoRepository.existsByData(novoAgendamento.getData())){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }


        novoAgendamento.setId(null);
        return agendamentoRepository.save(novoAgendamento);
    }

    public Agendamento atualizar(Integer id, Agendamento agendamentoAtualizado) {
        if(agendamentoRepository.existsById(id)){
            agendamentoAtualizado.setId(id);
            return agendamentoRepository.save(agendamentoAtualizado);
        }
        throw new EndidadeNaoEncontradaException("Agendamento");
    }

    public void deletar(Integer id) {
        if(!agendamentoRepository.existsById(id)){
            throw new EndidadeNaoEncontradaException("Agendamento");
        }
        agendamentoRepository.deleteById(id);
    }
}
