package com.erivelton.torneiofutebol.infraestrutura.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "jogador")
public class JogadorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String rg;

    private String nome;

    private Integer gol;

    @Column(name = "CARTAO_AMARELO")
    private Integer cartaoAmarelo;

    @Column(name = "CARTAO_VERMELHO")
    private Integer cartaoVermelho;

    @ManyToOne
    private EquipeEntity equipe;
}
