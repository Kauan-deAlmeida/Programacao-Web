package school.sptech.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.demo.entity.Empresa;
import school.sptech.demo.exception.EntidadeNaoEncontradaException;
import school.sptech.demo.repository.EmpresaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    // Ele pode retornar lista com algo ou não (lista vazia).
    public List<Empresa> buscarTodos(){
        return empresaRepository.findAll();
    }

    public Empresa buscarPorId(Integer id){
        return empresaRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Empresa"));
    }
}
