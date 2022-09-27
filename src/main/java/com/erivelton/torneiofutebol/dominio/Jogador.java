package com.erivelton.torneiofutebol.dominio;

import com.erivelton.torneiofutebol.aplicacao.dto.DadosJogador;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Jogador {

    private Long id;

    private String rg;

    private String nome;

    private Integer gol;

    private Integer cartaoAmarelo;

    private Integer cartaoVermelho;

    private Equipe equipe;

    public Jogador(DadosJogador jogador, Equipe equipe) {
        this.rg = jogador.getRg();
        this.nome = jogador.getNome();
        this.equipe = equipe;
    }
}
