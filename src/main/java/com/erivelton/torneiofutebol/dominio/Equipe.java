package com.erivelton.torneiofutebol.dominio;

import com.erivelton.torneiofutebol.aplicacao.dto.requisicao.DadosEquipe;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@SuperBuilder
@NoArgsConstructor
public class Equipe {

    private String nome;

    private Integer totalGols;

    private List<Jogador> jogadores = new ArrayList<>();

    public Equipe(String nome, Integer totalGols) {
        this.nome = nome;
        this.totalGols = totalGols;
    }

    public Equipe(DadosEquipe dadosEquipe) {
        this.nome = dadosEquipe.getEquipe();

        List<Jogador> jogadores = dadosEquipe.getJogadores().stream()
                .map(jogador -> new Jogador(jogador, this))
                .collect(Collectors.toList());

        this.jogadores.addAll(jogadores);
    }

}