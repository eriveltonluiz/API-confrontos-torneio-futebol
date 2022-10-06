package com.erivelton.torneiofutebol.infraestrutura.entidades;

import com.erivelton.torneiofutebol.dominio.Equipe;
import com.erivelton.torneiofutebol.dominio.Jogador;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
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

    public JogadorEntity() {
    }

    public JogadorEntity(Jogador jogador, EquipeEntity equipe) {
        this.rg = jogador.getRg();
        this.nome = jogador.getNome();
        this.equipe = equipe;
    }

    public Jogador paraEquipe(Equipe equipe) {
        return new Jogador(this.rg, this.nome, equipe);
    }
}