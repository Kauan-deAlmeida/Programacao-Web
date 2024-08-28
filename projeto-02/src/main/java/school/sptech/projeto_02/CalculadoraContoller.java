package school.sptech.projeto_02;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/calculos")
public class CalculadoraContoller {

//    Singleton - Atributo de instância
    private int contador;
    @GetMapping("/contar")
    public Integer contar(){
        return ++contador;
    }

    @GetMapping("/somar/{numero1}/{numero2}")
    public Integer somar(@PathVariable int numero1, @PathVariable int numero2){
        return numero1 + numero2;
    }
}
