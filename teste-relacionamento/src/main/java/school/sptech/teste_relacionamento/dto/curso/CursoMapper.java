package school.sptech.teste_relacionamento.dto.curso;

import school.sptech.teste_relacionamento.entity.Curso;

public class CursoMapper {

    public static CursoDetalheRespostaDto toDetalheDto(Curso entity){
        if(entity == null){
            return null;
        }

        return CursoDetalheRespostaDto.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .descricao(entity.getDescricao())
                .categoria(entity.getCategoria())
                .preco(entity.getPreco())
                .build();
    }

    public static CursoResumoRespostaDto toResumoDto(Curso entity){
        if(entity == null){
            return null;
        }

        return CursoResumoRespostaDto.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .preco(entity.getPreco())
                .build();
    }

    public static Curso toCriacaoEntity(CursoCriacaoRequisicaoDto dto){
        if(dto == null) return null;
        return Curso.builder()
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .preco(dto.getPreco())
                .categoria(dto.getCategoria())
                .build();
    }
}
