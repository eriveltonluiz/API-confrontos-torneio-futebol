package com.erivelton.torneiofutebol.aplicacao.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class DadosJogador {

    private String nome;

    private String rg;
}
