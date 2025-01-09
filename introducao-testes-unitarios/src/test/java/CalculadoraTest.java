import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes para a classe da Calculadora.")
class CalculadoraTest {

    @Test
    @DisplayName("Dado que, passei números corretamente, retorna o resultado correto.")
    public void somarCorretamente() {
        // TRIPLE A
        // (A - ARRANGE) (A - ACT) (A - ASSERT)

        // GIVEN WHEN THEN ASSERT

        // ARRANGE
        Calculadora calculadora = new Calculadora();

        // ACT
        Double resultado = calculadora.somar(1.0, 2.0);

        // ASSERT
        assertEquals(3.0, resultado);
    }

    @Test
    @DisplayName("Dado que, passei números corretamente, retorna o resultado correto.")
    public void somarCorretamente2() {
        // TRIPLE A
        // (A - ARRANGE) (A - ACT) (A - ASSERT)

        // GIVEN WHEN THEN ASSERT

        // ARRANGE
        Calculadora calculadora = new Calculadora();

        // ACT
        Double resultado = calculadora.somar(2.0, 2.0);

        // ASSERT
        assertNotNull(resultado, "Retornou null");
        assertEquals(4.0, resultado, "Não retornou corretamente");
    }

    @Test
    @DisplayName("Dado que, passei null, deve retornar uma exception")
    public void somarNull() {
        // ARRANGE
        Calculadora calculadora = new Calculadora();

        // ACT/ASSERT
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> calculadora.somar(null, 1.0));

        // ASSERT
        assertEquals("Não vale null", ex.getMessage());
    }
}