package school.sptech.demo.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.demo.entity.Empresa;
import school.sptech.demo.exception.ConflitoException;
import school.sptech.demo.exception.EntidadeNaoEncontradaException;
import school.sptech.demo.repository.EmpresaRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EmpresaServiceTest {

    @Mock // dublê - a nossa "repository" vai ser um dublê
    private EmpresaRepository empresaRepository;

    @InjectMocks
    private EmpresaService empresaService;

    // Vamos testar o método "buscarTodos"
    @Test
    @DisplayName("Dado que, não tenho nada no banco, retorna lista vazia")
    void buscarTodosListaVazia() {
        // GIVEN/ARRANGE - Configuração "chumbar" no código.
        List<Empresa> lista = Collections.emptyList();

        // WHEN/ARRANGE - quando qualquer código abaixo dessa linha,
        // chamar um método que tenha findAll sem sua lógica
        Mockito.when(empresaRepository.findAll()).thenReturn(lista);

        // THEN/ACT
        // chama métodos que possível teriam findAll, ao serem chamados,
        // o findAll vai respeitar o comportamento configurado
        List<Empresa> resultado = empresaService.buscarTodos();

        // ASSERT/ASSERT
        assertNotNull(resultado);

        assertTrue(resultado.isEmpty());

        // redundância, mas, outra maneira de verificar lista vazia
        assertEquals(0, resultado.size());
        Mockito.verify(empresaRepository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("Dado que, tenho algo no banco, retorna lista com empresas")
    void buscarTodosListaCheia() {
        // GIVEN
        List<Empresa> empresas = List.of(
                new Empresa(
                        1,
                        "Méqui",
                        "Arcos Dourados LTDA",
                        "19.123.123/0001-20",
                        LocalDate.of(1955, 1, 1)
                ), new Empresa(
                        2,
                        "SPTECO",
                        "Educare",
                        "12.123.123/0001-20",
                        LocalDate.of(2016, 1, 1)
                ));

        // WHEN
        Mockito.when(empresaRepository.findAll()).thenReturn(empresas);

        // THEN
        List<Empresa> resultado = empresaService.buscarTodos();

        // ASSERT

        assertNotNull(resultado);
        assertEquals(empresas.size(), resultado.size());
//        assertEquals(empresas.get(0).getId(), resultado.get(0).getId());
//        assertEquals(empresas.get(1).getId(), resultado.get(1).getId());

        for (int i = 0; i < resultado.size(); i++) {

            Empresa empresaEsperada = empresas.get(i);
            Empresa empresaRetornada = resultado.get(i);

            assertEquals(empresaEsperada.getId(), empresaRetornada.getId());
            assertEquals(empresaEsperada.getNomeFantasia(), empresaRetornada.getNomeFantasia());
            assertEquals(empresaEsperada.getRazaoSocial(), empresaRetornada.getRazaoSocial());
            assertEquals(empresaEsperada.getCnpj(), empresaRetornada.getCnpj());
            assertEquals(empresaEsperada.getDataFundacao(), empresaRetornada.getDataFundacao());

        }

        Mockito.verify(empresaRepository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("Dado que, tenho uma empresa pelo id, retorne corretamente")
    void buscaPorIdCorretamente() {
        // GIVEN
        Empresa empresaEsperada = new Empresa(
                1,
                "Méqui",
                "Arcos Dourados LTDA",
                "19.123.123/0001-20",
                LocalDate.of(1955, 1, 1)
        );

        // WHEN
        Mockito.when(empresaRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.of(empresaEsperada));

        // THEN
        Empresa resultado = empresaService.buscarPorId(1);

        // ASSERT
        assertNotNull(resultado);
        assertEquals(empresaEsperada.getId(), resultado.getId());
        Mockito.verify(empresaRepository, Mockito.times(1)).findById(1);
        Mockito.verify(empresaRepository, Mockito.never()).findAll();
    }

    @Test
    @DisplayName("Dado que, o id não existe, retorna exception")
    void buscarPorIdIncorreto() {
        // GIVEN/WHEN/ARRANGE
        Mockito.when(empresaRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.empty());

        // THEN/ASSERT/ACT
        EntidadeNaoEncontradaException ex = assertThrows(EntidadeNaoEncontradaException.class,
                () -> empresaService.buscarPorId(1));

        // ASSERT
        assertEquals("Empresa", ex.getMessage());

        Mockito.verify(empresaRepository, Mockito.times(1)).findById(1);
        Mockito.verify(empresaRepository, Mockito.never()).findAll();
    }

    @Test
    @DisplayName("Dado que, o cnpj já existe, retornar exception")
    void criarEmpresaConflito(){
        Empresa empresaParaSalvar = new Empresa(
                2,
                "SPTECO",
                "Educare",
                "12.123.123/0001-20",
                LocalDate.of(2016, 1, 1)
        );

        Mockito.when(empresaRepository.existsByCnpj(empresaParaSalvar.getCnpj())).thenReturn(true);
        assertThrows(ConflitoException.class,() -> empresaService.salvarEmpresa(empresaParaSalvar));

        Mockito.verify(empresaRepository, Mockito.times(1)).existsByCnpj(empresaParaSalvar.getCnpj());
        Mockito.verify(empresaRepository, Mockito.never()).save(empresaParaSalvar);
    }

    @Test
    @DisplayName("Dado que, todos os dados são aceitos, salvar empresa")
    void criarEmpresaSemComflito(){
        Empresa empresaParaSalvar = new Empresa(
                2,
                "SPTECO",
                "Educare",
                "12.123.123/0001-20",
                LocalDate.of(2016, 1, 1)
        );

        Mockito.when(empresaRepository.existsByCnpj(empresaParaSalvar.getCnpj())).thenReturn(false);
        Mockito.when(empresaRepository.save(empresaParaSalvar)).thenReturn(empresaParaSalvar);

        Empresa resultado = empresaService.salvarEmpresa(empresaParaSalvar);

        assertEquals(empresaParaSalvar.getId(), resultado.getId());
        assertEquals(empresaParaSalvar.getDataFundacao(), resultado.getDataFundacao());
        assertEquals(empresaParaSalvar.getRazaoSocial(), resultado.getRazaoSocial());
        assertEquals(empresaParaSalvar.getNomeFantasia(), resultado.getNomeFantasia());

        Mockito.verify(empresaRepository, Mockito.times(1)).existsByCnpj(empresaParaSalvar.getCnpj());
        Mockito.verify(empresaRepository, Mockito.times(1)).save(empresaParaSalvar);
    }

    @Test
    @DisplayName("Dado que, não exist id para deletar, retornar exception")
    void deletarEmpresaConflito(){
        Empresa empresa = new Empresa(
                2,
                "SPTECO",
                "Educare",
                "12.123.123/0001-20",
                LocalDate.of(2016, 1, 1)
        );

        Mockito.when(empresaRepository.existsById(Mockito.anyInt())).thenReturn(false);
        assertThrows(ConflitoException.class,() -> empresaService.deletarEmpresa(empresa.getId()));

        Mockito.verify(empresaRepository, Mockito.times(1)).existsById(empresa.getId());
        Mockito.verify(empresaRepository, Mockito.never()).deleteById(empresa.getId());
    }

    @Test
    @DisplayName("Dado que, exist id para deletar, deve deletar empresa")
    void deletarEmpresaSemConflito(){
        Empresa empresa = new Empresa(
                2,
                "SPTECO",
                "Educare",
                "12.123.123/0001-20",
                LocalDate.of(2016, 1, 1)
        );

        Mockito.when(empresaRepository.existsById(Mockito.anyInt())).thenReturn(true);
        empresaService.deletarEmpresa(empresa.getId());

        Mockito.verify(empresaRepository, Mockito.times(1)).existsById(empresa.getId());
        Mockito.verify(empresaRepository, Mockito.times(1)).deleteById(empresa.getId());
    }
}