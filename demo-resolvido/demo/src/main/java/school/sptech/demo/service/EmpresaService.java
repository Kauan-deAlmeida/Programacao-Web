package school.sptech.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.demo.entity.Empresa;
import school.sptech.demo.exception.ConflitoException;
import school.sptech.demo.exception.EntidadeNaoEncontradaException;
import school.sptech.demo.repository.EmpresaRepository;

import java.util.List;

@Service // Indica que esta classe é um componente de serviço na camada de negócios (Business)
public class EmpresaService {

    // Injeção de dependência do repositório de empresas para interação com o banco de dados
    @Autowired
    private EmpresaRepository empresaRepository;

    /*
        Camadas do sistema:
        Business -> Representa a camada de negócio (Service, Use case...) onde estão as regras de negócio.
        Persistence -> Camada de persistência (ou banco de dados), que armazena e consulta dados.
        View -> Camada de exposição, responsável pela interação com o usuário e apresentação de dados.
    */

    // O método buscarTodos usa o repositório para buscar e retornar todas as empresas do banco de dados.
    public List<Empresa> buscarTodos() {
        return this.empresaRepository.findAll();
    }

    // O método buscarPorId busca uma empresa específica pelo ID.
    public Empresa buscarPorId(Integer id) {

        /*
            Exemplo usando Optional:
            O método findById retorna um Optional, que representa um valor que pode ou não estar presente.
            Abaixo está um exemplo completo de como utilizar Optional:

            Optional<Empresa> resultado = this.empresaRepository.findById(id);

            if (resultado.isEmpty()) { // Verifica se o Optional está vazio (empresa não encontrada)
                throw new EntidadeNaoEncontradaException("Empresa não encontrada");
            }

            return resultado.get(); // Retorna o valor da empresa, se presente
        */

        // Aqui, usamos orElseThrow para lançar uma exceção se a empresa não for encontrada
        return this.empresaRepository.findById(id)
                .orElseThrow(
                        () -> new EntidadeNaoEncontradaException("Empresa") // Exceção customizada
                );
    }

    public Empresa salvarEmpresa(Empresa novaEmpresa){
        if(empresaRepository.existsByCnpj(novaEmpresa.getCnpj())) throw new ConflitoException("Cpnj já existe.");
        return empresaRepository.save(novaEmpresa);
    }

    public void deletarEmpresa(Integer id){
        if(!empresaRepository.existsById(id)) throw new ConflitoException("Empresa não encontrado com id passado");
        empresaRepository.deleteById(id);
    }
}
