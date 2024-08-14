package school.sptech.primeira_aplicacao;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/frases")
public class FraseController {

    @GetMapping
    public String ola(){
        return "Olá";
    }
    @GetMapping("/boa")
    public String ola2(){
        return "Boa pá nós";
    }

    @GetMapping("/boa2")
    public String legal(){
        return "Frase bacana";
    }

    // /frase/nome/{nome da pessoa}
    @GetMapping("nome/{nomeDaPessoa}")
    public String retornaNome(@PathVariable String nomeDaPessoa){
        return "Seja bem vindo! Seu nome é: " + nomeDaPessoa;
    }

    // /frase/nome/{nome da pessoa}/{sobreNome}
    @GetMapping("nome/{nome}/{sobreNome}")
    public String retornaNome(@PathVariable(value="nome") String nomeDaPessoa,@PathVariable String sobreNome){
        return "Seja bem vindo! Seu nome é: " + nomeDaPessoa + " " + sobreNome;
    }


}
