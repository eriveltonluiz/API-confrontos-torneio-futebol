package com.erivelton.torneiofutebol.dominio.modelo;

public class Jogador {

    private Long id;

    private String rg;

    private String nome;

    private Integer gol;

    private Integer cartaoAmarelo;

    private Integer cartaoVermelho;

    private Equipe equipe;

    public Jogador() {
    }

    public Jogador(String rg, String nome, Equipe equipe) {
        this.rg = rg;
        this.nome = nome;
        this.equipe = equipe;
    }

    public Long getId() {
        return id;
    }

    public String getRg() {
        return rg;
    }

    public String getNome() {
        return nome;
    }

    public Integer getGol() {
        return gol;
    }

    public Integer getCartaoAmarelo() {
        return cartaoAmarelo;
    }

    public Integer getCartaoVermelho() {
        return cartaoVermelho;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public Equipe getEquipe() {
        return equipe;
    }

}