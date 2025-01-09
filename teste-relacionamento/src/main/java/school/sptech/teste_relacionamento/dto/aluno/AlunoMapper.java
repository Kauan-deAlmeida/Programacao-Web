package school.sptech.teste_relacionamento.dto.aluno;

import school.sptech.teste_relacionamento.entity.Aluno;
import school.sptech.teste_relacionamento.entity.Curso;

public class AlunoMapper {

    public static AlunoDetalheRespostaDto toResposataDto(Aluno aluno){
        if(aluno == null) return null;

        Curso curso = aluno.getCurso();

        return AlunoDetalheRespostaDto.builder()
                .id(aluno.getId())
                .nome(aluno.getNome())
                .cpf(aluno.getCpf())
                .dataNascimento(aluno.getDataNascimento())
                .curso(AlunoDetalheRespostaDto.CursoDto.builder()
                        .id(curso.getId())
                        .nome(curso.getNome())
                        .categoria(curso.getCategoria())
                        .descricao(curso.getDescricao())
                        .preco(curso.getPreco())
                        .build())
                .build();
    }

    public static Aluno toEntidadeDto(AlunoCriacaoRequisicaoDto dto){
        if(dto == null) return null;

        Aluno aluno = new Aluno();
        aluno.setNome(dto.getNome());
        aluno.setCpf(dto.getCpf());
        aluno.setDataNascimento(dto.getDataNascimento());

        return aluno;
    }
}
