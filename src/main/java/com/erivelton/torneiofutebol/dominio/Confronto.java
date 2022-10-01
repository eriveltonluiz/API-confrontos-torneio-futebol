package com.erivelton.torneiofutebol.dominio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Confronto {

    private Equipe mandante;

    private Equipe visitante;

    private String etapa;

    private Integer ordem;

    private Integer golsMandante;

    private Integer golsVisitante;
}