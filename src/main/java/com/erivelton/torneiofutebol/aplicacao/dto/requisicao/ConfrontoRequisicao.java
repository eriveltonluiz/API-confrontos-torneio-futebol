package com.erivelton.torneiofutebol.aplicacao.dto.requisicao;

import com.erivelton.torneiofutebol.dominio.modelo.confronto.Confronto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ConfrontoRequisicao {

    @NotNull
    private Long identificacao;

    @NotNull
    private Integer golsMandante;

    @NotNull
    private Integer golsVisitante;

    private Map<String, Integer> golsJogadoresMandante = new HashMap<>();

    private Map<String, Integer> golsJogadoresVisitante = new HashMap<>();

    public Confronto paraConfrontoDominio() {
        return new Confronto(identificacao, golsMandante, golsVisitante, golsJogadoresMandante, golsJogadoresVisitante);
    }
}
