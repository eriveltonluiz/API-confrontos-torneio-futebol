package com.erivelton.torneiofutebol.aplicacao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class DadosEquipe {

    private String equipe;

    private List<DadosJogador> jogadores;

}
