package com.erivelton.torneiofutebol.dominio;

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
}
