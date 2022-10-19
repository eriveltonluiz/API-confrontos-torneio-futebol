package com.erivelton.torneiofutebol.adaptadores.repositorios.entidades;

import com.erivelton.torneiofutebol.adaptadores.repositorios.entidades.EquipeEntity;
import com.erivelton.torneiofutebol.dominio.modelo.Equipe;
import com.erivelton.torneiofutebol.dominio.modelo.Jogador;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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

    @Setter
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
        this.gol = 0;
        this.equipe = equipe;
    }

    public void atualizarGols(Integer gols) {
        this.gol = this.gol + gols;
    }
}