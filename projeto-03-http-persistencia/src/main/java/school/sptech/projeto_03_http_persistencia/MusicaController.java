package school.sptech.projeto_03_http_persistencia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/musica")
public class MusicaController {

    List<Musica> musicas = new ArrayList<>();

    @Autowired
    private MusicaRepository musicaRepository;


//    pois não usamos o construtor
//    @GetMapping("/favorita")
//    public Musica favorita(){
//        return new Musica("Still loving you", "Scorpions", 1984);
//    }

    @GetMapping
    public List<Musica> listar(){
        return musicaRepository.findAll();
        // SELECT * FROM Musica
    }

//    //GET - /musica/0
//    @GetMapping("/{indice}") //verbo na URI é uma má pratica ex: buscaPorIndice/{indice}
//    public Musica buscaPorIndice(@PathVariable int indice) {
//        if (indice < 0 || indice >= musicas.size()) return null;
//        return musicas.get(indice);
//    }

    //GET - /musica/0
    @GetMapping("/{id}") //verbo na URI é uma má pratica ex: buscaPorIndice/{id}
    public Musica buscaPorIndice(@PathVariable int id) {
        Optional<Musica> musicaOpt =  musicaRepository.findById(id);
        if(musicaOpt.isPresent()){
            return musicaOpt.get();
        }
        return null;

//        return musicaRepository.findById(id).orElse(null);
//        Mesma coisa do if de cima, com o mesmo conceito
    }

    //POST - /musica
    @PostMapping
    public Musica criar(
            @RequestBody Musica novaMusica // recebendo um objeto a partir de um JSON
    ){
//        musicas.add(novaMusica);
        Musica musicaCriada = musicaRepository.save(novaMusica);
        return novaMusica;
//      OU return musicaRepository.save(novaMusica);
    }

    //PUT - /{indice}
    @PutMapping("/{id}")
    public Musica atualizar(@PathVariable int id, @RequestBody Musica musicaAtualizada) {
        if(musicaRepository.existsById(id)){
            musicaAtualizada.setId(id);
            return musicaRepository.save(musicaAtualizada);
        }
        return musicaAtualizada;
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable int id){
        if(musicaRepository.existsById(id)){
           musicaRepository.deleteById(id);
        }
    }
}
