package school.sptech.projeto_http_code;

import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Integer> {

//    dynamic finders
    List<Livro> findByNome(String nome);
    List<Livro> findByNomeContainsIgnoreCase(String nome);
//    busca por nome, contrando Like "%?%" ordenando por classificação do melhor ao pior
    List<Livro> findByNomeContainsIgnoreCaseOrderByClassificacaoDesc(String nome);
    List<Livro> findByDataLancamentoBetween(LocalDate data1, LocalDate data2);
//    buscar todos, ordenando pela classificação
    List<Livro> findByOrderByClassificacaoDesc();

}
