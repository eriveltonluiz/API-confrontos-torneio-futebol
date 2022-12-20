package com.erivelton.torneiofutebol.dominio.modelo;

import com.erivelton.torneiofutebol.aplicacao.dto.requisicao.DadosEquipe;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    private Equipe(EquipeBuilder equipeBuilder) {
        this.nome = equipeBuilder.nome;
        this.jogadores.addAll(equipeBuilder.jogadores);
    }

    public static Equipe.EquipeBuilder builder() {
        return new Equipe.EquipeBuilder();
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

    public static class EquipeBuilder {

        private String nome;

        private List<Jogador> jogadores = new ArrayList<>();

        public EquipeBuilder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public EquipeBuilder jogadores(List<Jogador> jogadores) {
            this.jogadores.addAll(jogadores);
            return this;
        }

        public Equipe build() {
            return new Equipe(this);
        }

    }
}