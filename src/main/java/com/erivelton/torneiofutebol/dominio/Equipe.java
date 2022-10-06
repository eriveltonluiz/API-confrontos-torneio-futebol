package com.erivelton.torneiofutebol.dominio;

import com.erivelton.torneiofutebol.aplicacao.dto.requisicao.DadosEquipe;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SuperBuilder
public class Equipe {

    private String nome;

    private Integer totalGols;

    private List<Jogador> jogadores = new ArrayList<>();

    public Equipe(String nome) {
        this.nome = nome;
    }

    public Equipe() {
    }

    public Equipe(DadosEquipe dadosEquipe) {
        this.nome = dadosEquipe.getEquipe();

        List<Jogador> jogadores = dadosEquipe.getJogadores().stream()
                .map(jogador -> new Jogador(jogador.getRg(), jogador.getNome(), this))
                .collect(Collectors.toList());

        this.jogadores.addAll(jogadores);
    }

    public String getNome() {
        return nome;
    }

    public Integer getTotalGols() {
        return totalGols;
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public void adicionarJogadores(List<Jogador> jogadoresDominio) {
        this.jogadores.addAll(jogadoresDominio);
    }

}