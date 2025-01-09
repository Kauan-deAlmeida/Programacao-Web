package school.sptech.demo.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import school.sptech.demo.entity.Empresa;
import school.sptech.demo.exception.EntidadeNaoEncontradaException;
import school.sptech.demo.service.EmpresaService;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes unitarios da EmpresaController")
class EmpresaControllerUnitTest {

    @Mock
    private EmpresaService empresaService;

    @InjectMocks
    private EmpresaController empresaController;

    @Test
    @DisplayName("Dado que, ao chamar a busarTodos, retorna uma lista completa")
    void buscarTodosCorretamente(){
        List<Empresa> empresas = List.of(
                new Empresa(
                        1,
                        "Méqui",
                        "Arcos Dourados LTDA",
                        "19.123.123/0001-20",
                        LocalDate.of(1955, 1, 1))
        );

        Mockito.when(empresaService.buscarTodos()).thenReturn(empresas);

        ResponseEntity<List<Empresa>> resultados = empresaController.listar();
        assertEquals(HttpStatus.OK, resultados.getStatusCode());
        assertNotNull(resultados.getBody());
        assertFalse(resultados.getBody().isEmpty());

        assertEquals(empresas.get(0).getId(), resultados.getBody().get(0).getId());
    }

    @Test
    @DisplayName("Dado que, ao chamar a busarTodos e não ter dados, retornar exception")
    void buscarTodosIncorreto(){
        Mockito.when(empresaService.buscarTodos()).thenReturn(Collections.emptyList());

        ResponseEntity<List<Empresa>> resultados = empresaController.listar();

        assertNull(resultados.getBody());
        assertEquals(HttpStatus.NO_CONTENT, resultados.getStatusCode());
//        assertTrue(resultados.getBody().isEmpty());
    }

    @Test
    @DisplayName("Dado que, buscarPorId não tenha por id informado, lançar exception")
    void buscarPorIdIncorretamente(){
        Integer id = 1;

        Mockito.when(empresaService.buscarPorId(id)).thenThrow(new EntidadeNaoEncontradaException("Empresa"));

        assertThrows(EntidadeNaoEncontradaException.class, () -> empresaService.buscarPorId(id));
    }

    @Test
    @DisplayName("Dado que, buscarPorId seja encontrado, retornar empresa")
    void buscarPorIdCorretamente(){
        Integer id = 1;
        List<Empresa> empresas = List.of(
                new Empresa(
                        1,
                        "Méqui",
                        "Arcos Dourados LTDA",
                        "19.123.123/0001-20",
                        LocalDate.of(1955, 1, 1))
        );

        Mockito.when(empresaService.buscarPorId(id)).thenReturn(empresas.get(0));

        ResponseEntity<Empresa> resultado = empresaController.buscarPorId(id);

        assertNotNull(resultado.getBody());
        assertEquals(HttpStatus.OK, resultado.getStatusCode());
    }
}