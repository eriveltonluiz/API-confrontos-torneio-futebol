package com.erivelton.torneiofutebol.aplicacao.dto.requisicao;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Introspected
public class DadosJogador {

    private String nome;

    private String rg;
}
