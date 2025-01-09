package school.sptech.demo.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.demo.entity.Empresa;
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
    void buscarTodosListaVazia(){
        // GIVEN/ARRANGE - Configuração "chumbar" no código.

        List<Empresa> lista = Collections.emptyList();

        // WHEN/ARRANGE
        Mockito.when(empresaRepository.findAll()).thenReturn(lista);

        // THEN/ACT
        List<Empresa> resultado = empresaService.buscarTodos();

        // ASSERT/ASSERT
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        assertEquals(0, resultado.size());
    }

    @Test
    @DisplayName("Dado que, tenho algo no banco, retorna lista comparando entidades")
    void buscarTodosListaCheia(){
        // GIVEN
        List<Empresa> empresas = List.of(
                new Empresa(1,
                        "Méqui",
                        "Arcos Dourados LTDA",
                        "19.123.123/0001-20",
                        LocalDate.of(1995,1,1)
                ),
                new Empresa(
                        2,
                        "SPTECO",
                        "Educare",
                        "12.123.123/0001-20",
                        LocalDate.of(2016,1,1)
                )
        );
        // WHEN
        Mockito.when(empresaRepository.findAll()).thenReturn(empresas);

        // THEN
        List<Empresa> resultado = empresaService.buscarTodos();

        // ASSERT
        for (int i = 0; i < empresas.size(); i++) {
            assertEquals(empresas.get(i).getId(), resultado.get(i).getId());
            assertEquals(empresas.get(i).getNomeFantasia(), resultado.get(i).getNomeFantasia());
            assertEquals(empresas.get(i).getRazaoSocial(), resultado.get(i).getRazaoSocial());
            assertEquals(empresas.get(i).getCnpj(), resultado.get(i).getCnpj());
            assertEquals(empresas.get(i).getDataFundacao(), resultado.get(i).getDataFundacao());
        }

        resultado.stream().forEach(empresa -> {
            int index = resultado.indexOf(empresa);
            assertEquals(empresas.get(index).getId(), empresa.getId());
            assertEquals(empresas.get(index).getNomeFantasia(), empresa.getNomeFantasia());
            assertEquals(empresas.get(index).getRazaoSocial(), empresa.getRazaoSocial());
            assertEquals(empresas.get(index).getCnpj(), empresa.getCnpj());
            assertEquals(empresas.get(index).getDataFundacao(), empresa.getDataFundacao());
        });
    }

    @Test
    @DisplayName("Dado que, tenho uma empresa pelo id, retorne corretamente")
    void buscarPorIdCorretamente(){
        // GIVEN
        Empresa empresaEsperada = new Empresa(
                1,
                "Méqui",
                "Arcos Dourados LTDA", "19.123.123/0001-20",
                LocalDate.of(1995,1,1)
        );

        // WHEN
        Mockito.when(empresaRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(empresaEsperada));

        // THEN
        Empresa resultado = empresaService.buscarPorId(1);

        // ASSERT
        assertNotNull(resultado);
        assertEquals(empresaEsperada.getId(), resultado.getId());
    }

    @Test
    @DisplayName("Dado que, o id não existe, retorna exception")
    void buscarPorIdIncorreto(){
        // WHEN/ARRANGE
        Mockito.when(empresaRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

        // THEN/ASSERT
        EntidadeNaoEncontradaException ex = assertThrows(EntidadeNaoEncontradaException.class, () -> empresaService.buscarPorId(1));

        // ASSERT
        assertEquals("Empresa", ex.getMessage());
    }
    
    @Test
    @DisplayName("")
    void criar(){
        
    }
}