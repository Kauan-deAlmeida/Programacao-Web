package school.sptech.projeto_02;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Component
//@Controller
//@Service
//@Repository
//@Configuration
//@RestController
//Meta progarmação é uma parte que já tem varias aplicações dentro dela
//São todas componentes do Spring boot, responsabilidade dele

@RestController
@RequestMapping("/frases")
public class FraseController {

//    URL = Caminho do recurso :URL =  http://localhost:8080
//    URI = Recurso : URI = /
//    MVC = Model Control View


    @GetMapping
    public String ola(){
        return "Olá";
    }

//    /frases/bom-dia
//    sempre utilizar "-" para separar palavras compostas
    @GetMapping("/bom-dia")
    public String bomDia(){
        return "Bom dia!";
    }

//Parâmetro de requisição = Variável de caminho(@PathVariable)
    @GetMapping("/nome/{nome}")
    public String nome(@PathVariable String nome){
        return "Bem vindo, " + nome;
    }
    @GetMapping("/nome/{nome}/{sobrenome}")
    public String nome(@PathVariable String nome,@PathVariable String sobrenome){
//        "Bem vindo, " + nome + "" + sobrenome
        return String.format("Bem vindo, %s %s", nome, sobrenome);
    }
}
