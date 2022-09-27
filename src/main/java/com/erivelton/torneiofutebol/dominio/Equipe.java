package com.erivelton.torneiofutebol.dominio;

import com.erivelton.torneiofutebol.aplicacao.dto.DadosEquipe;
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

//    public void adicionarJogadores(List<Jogador> jogadores) {
//        if (jogadores == null) {
//            this.jogadores = new ArrayList<>();
//        }
//        this.jogadores.addAll(jogadores);
//    }

    public Equipe(DadosEquipe dadosEquipe) {
        nome = dadosEquipe.getEquipe();
        totalGols = 0;

        List<Jogador> jogadores = dadosEquipe.getJogadores().stream()
                .map(jogador -> new Jogador(jogador, this))
                .collect(Collectors.toList());

        this.jogadores.addAll(jogadores);
    }
}
