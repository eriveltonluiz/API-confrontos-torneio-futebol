package com.erivelton.torneiofutebol.aplicacao.dto.requisicao;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Introspected
public class DadosJogador {

    @NotBlank
    private String nome;

    @NotBlank
    private String rg;
}
