package school.sptech.projeto_03_http_persistencia;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // sinaliza que a class musica sera uma entidade no banco
public class Musica {

    @Id // esse atributo "@Id" será o nosso identificador da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Integer id;
    private String nome;
    private String artista;
    private Integer anoLancamento; // ano_lancamento padrão de banco

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public Integer getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(Integer anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    //campo virtual
    //Ao ter o prefixo "get" ele se torna um json
    public String getDescricao(){
        return "A musica " + nome  +  " é tocada e cantada por " + artista;
    }
}
