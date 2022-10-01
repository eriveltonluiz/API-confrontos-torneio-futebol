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
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, name = "RG")
    private String rg;

    @Column(nullable = false, name = "NOME")
    private String nome;

    @Column(name = "GOL")
    private Integer gol;

    @Column(name = "CARTAO_AMARELO")
    private Integer cartaoAmarelo;

    @Column(name = "CARTAO_VERMELHO")
    private Integer cartaoVermelho;

    @ManyToOne
    @JoinColumn(name = "EQUIPE", nullable = false)
    private EquipeEntity equipe;
}