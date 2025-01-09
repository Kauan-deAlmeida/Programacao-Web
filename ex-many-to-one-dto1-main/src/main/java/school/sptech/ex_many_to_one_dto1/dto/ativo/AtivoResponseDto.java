package school.sptech.ex_many_to_one_dto1.dto.ativo;

import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;
import school.sptech.ex_many_to_one_dto1.entity.Carteira;

// TODO: TERMINAR A CLASSE
@Data
@Builder
//@AllArgsConstructor
//@NoArgsConstructor
public class AtivoResponseDto {

    private Integer id;
    private String nome;
    private String tipo;
    private Double valorAtual;
    private AtivoCarteiraResponseDto carteira;

    // TODO: TERMINAR A CLASSE
    @Data
    @Builder
//    @NoArgsConstructor
//    @AllArgsConstructor
    public static class AtivoCarteiraResponseDto {
        private int id;
        private String nome;
        private String investidor;
    }
}
