package school.sptech.projeto_02;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

//    Lista compartilhada entre endpoint (singleton)
    private List<Filme> filmes = new ArrayList<>(List.of(
            new Filme("Jogador", "Steven Spielberg"),
            new Filme("Deadpool e Wolverine", "Ryan Reynolds")
    ));

    @GetMapping
    public List<Filme> filmes(){
        return filmes;
    }

    @GetMapping("/{index}")
    public Filme buscarPorIndex(@PathVariable int index){
        if(index < 0 || index >= filmes.size()){
            return null;
        }
        return filmes.get(index);
    }

    @GetMapping("{index}/{nome}/{diretor}")
    public Filme atualizar(@PathVariable int index ,@PathVariable String nome, @PathVariable String diretor){
        if(index < 0 || index >= filmes.size()){
            return null;
        }
        Filme filmeAtualizado = new Filme(nome, diretor);
        filmes.set(index, filmeAtualizado);
        return filmeAtualizado;
    }

//    int = posição da lista
//    Integer = valor da posição da lista
    @GetMapping("/delete/{index}")
    public String deletar(@PathVariable int index){
        if(index < 0 || index >= filmes.size()){
            return "Não encontrado";
        }
        filmes.remove(index);
        return "Removido com sucesso";
    }



    @GetMapping("/criar/{nome}/{diretor}")
    public Filme criar(@PathVariable String nome, @PathVariable String diretor){
        Filme filmeCriado = new Filme(nome, diretor);
        filmes.add(filmeCriado);
        return filmeCriado;
//   ou return new FIlme(nome, diretor);
    }


//    Serialization
//    Deserialization
//    Jackson é responsável pela conversão de objeto para JSON.

    @GetMapping("/favorito")
    public Filme favorito(){
        Filme favorito = new Filme("Tinanic","James Cameron");
        return favorito;
    }

}
