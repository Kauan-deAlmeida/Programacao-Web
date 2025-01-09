package school.sptech.demo.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import school.sptech.demo.entity.Empresa;
import school.sptech.demo.service.EmpresaService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.List;

@WebMvcTest(EmpresaController.class)
@DisplayName("Testes de integração da controller")
public class EmpresaControllerIntegrationTest {

    @MockBean
    private EmpresaService empresaService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Dado que, tenho empresas no banco, retornar a lista")
    void buscarTodosCorretamente() throws Exception {
        List<Empresa> empresas = List.of(
                new Empresa(
                        1,
                        "Méqui",
                        "Arcos Dourados LTDA",
                        "19.123.123/0001-20",
                        LocalDate.of(1955, 1, 1))
        );

        Mockito.when(empresaService.buscarTodos()).thenReturn(empresas);

        mockMvc.perform(get("/empresas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(empresas.get(0).getId()))
                .andExpect(jsonPath("$[0].nomeFantasia").value(empresas.get(0).getNomeFantasia()))
                .andExpect(jsonPath("$[0].razaoSocial").value(empresas.get(0).getRazaoSocial()))
                .andExpect(jsonPath("$[0].dataFundacao").value(empresas.get(0).getDataFundacao().toString()))
                .andExpect(jsonPath("$[0].cnpj").value(empresas.get(0).getCnpj()));
    }
}
