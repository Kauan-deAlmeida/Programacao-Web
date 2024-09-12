package school.sptech.projeto_http_code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/livros")
public class LivroController{

    @Autowired
    private LivroRepository livroRepository;

    @GetMapping
    public ResponseEntity<List<Livro>> listarTodosLivros(){ // colocar ResponseEntity<>
        List<Livro> livros = livroRepository.findAll();

        if(livros.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(livros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarPorId(@PathVariable Integer id){
        Optional<Livro> livroOpt = livroRepository.findById(id);

        if(livroOpt.isPresent()){
            Livro encontrado = livroOpt.get();
            return ResponseEntity.status(200).body(encontrado);
        }
        return ResponseEntity.status(404).build();
        //        return livroOpt.orElse(null);
    }

    @PostMapping
    public ResponseEntity<Livro> cadastrar(@RequestBody Livro novoLivro) {
        novoLivro.setId(null); // não importa o que chegar, vira null e cria
        return ResponseEntity.status(201).body(livroRepository.save(novoLivro));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizarLivro(@PathVariable Integer id, @RequestBody Livro atualizarLivro){
        if(!livroRepository.existsById(id)){
            return ResponseEntity.status(404).build();
        }
        atualizarLivro.setId(id);
        return ResponseEntity.status(200).body(livroRepository.save(atualizarLivro));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id){
        if(livroRepository.existsById(id)){
            livroRepository.deleteById(id);
            return  ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/filtro-nome")
    public ResponseEntity<List<Livro>> porNome(@RequestParam String nome){
//        findBy = Select * from livro
//        nome = where nome = ?
        List<Livro> livros = livroRepository.findByNome(nome);
        if(livros.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(livros);
    }
}
