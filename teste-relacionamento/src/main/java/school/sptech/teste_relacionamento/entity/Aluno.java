package school.sptech.teste_relacionamento.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    @ManyToOne
    private Curso curso;
//    @OneToMany
//    @OneToOne
//    ManyToMany
//    @JoinColumn(name = "fk_curos" , referencedColumnName = "Id_aluno")


}
