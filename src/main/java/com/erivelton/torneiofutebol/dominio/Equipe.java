package com.erivelton.torneiofutebol.dominio;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

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

    public void adicionarJogadores(List<Jogador> jogadores) {
        if (jogadores == null) {
            this.jogadores = new ArrayList<>();
        }
        this.jogadores.addAll(jogadores);
    }

}
