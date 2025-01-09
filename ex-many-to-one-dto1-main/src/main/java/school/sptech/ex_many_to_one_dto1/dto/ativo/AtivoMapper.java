package school.sptech.ex_many_to_one_dto1.dto.ativo;

import school.sptech.ex_many_to_one_dto1.entity.Ativo;
import school.sptech.ex_many_to_one_dto1.entity.Carteira;

// TODO: TERMINAR A CLASSE
public class AtivoMapper {

    public static AtivoResponseDto toAtivoResponseDto(Ativo ativo){
        if(ativo == null) return null;

        Carteira carteira = ativo.getCarteira();

        return AtivoResponseDto.builder()
                .id(ativo.getId())
                .tipo(ativo.getTipo())
                .nome(ativo.getNome())
                .carteira(AtivoResponseDto.AtivoCarteiraResponseDto.builder()
                        .id(carteira.getId())
                        .investidor(carteira.getInvestidor())
                        .nome(carteira.getNome())
                        .build())
                .valorAtual(ativo.getValorAtual())
                .build();
    }

    public static Ativo toAtivoEntity(AtivoRequestDto ativoRequestDto){
        if(ativoRequestDto == null) return null;
        return Ativo.builder()
                .nome(ativoRequestDto.getNome())
                .tipo(ativoRequestDto.getTipo())
                .valorAtual(ativoRequestDto.getValorAtual())
                .build();
    }
}
