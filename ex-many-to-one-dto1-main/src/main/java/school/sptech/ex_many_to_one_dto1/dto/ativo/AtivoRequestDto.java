package school.sptech.ex_many_to_one_dto1.dto.ativo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// TODO: TERMINAR A CLASSE
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtivoRequestDto {

    @NotBlank
    private String nome;
    @NotBlank
    private String tipo;
    @NotNull
    @Positive
    private Double valorAtual;
    @NotNull
    @Positive
    private Integer carteiraId;
}
