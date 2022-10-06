package com.erivelton.torneiofutebol.aplicacao.dto.requisicao;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Introspected
public class DadosEquipe {

    private String equipe;

    private List<DadosJogador> jogadores = new ArrayList<>();

}
