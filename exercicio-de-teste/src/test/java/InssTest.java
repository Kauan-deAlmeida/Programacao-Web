
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Testes para a classe de Inss.")
public class InssTest {

    @Test
    @DisplayName("Calcular o inss do salário")
    public void calcularInss(){
        //Arrange
        Inss inss = new Inss();

        //ACT
        Double salario = inss.calcularInss(1300.0);

        //Assert
        assertEquals(1430.00, salario);
    }
}
