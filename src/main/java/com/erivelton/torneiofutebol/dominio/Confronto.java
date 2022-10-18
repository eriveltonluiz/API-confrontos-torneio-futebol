package com.erivelton.torneiofutebol.dominio;

import lombok.experimental.SuperBuilder;

import java.util.HashMap;
import java.util.Map;

@SuperBuilder
public class Confronto {

    private Long identificacao;

    private Equipe mandante;

    private Equipe visitante;

    private String etapa;

    private Integer ordem;

    private Integer golsMandante;

    private Integer golsVisitante;

    private Map<String, Integer> golsJogadoresMandante = new HashMap<>();

    private Map<String, Integer> golsJogadoresVisitante = new HashMap<>();

    public Confronto() {
    }

    public Confronto(Equipe mandante, Equipe visitante, String etapa, Integer ordem, Integer golsMandante, Integer golsVisitante) {
        this.mandante = mandante;
        this.visitante = visitante;
        this.etapa = etapa;
        this.ordem = ordem;
        this.golsMandante = golsMandante;
        this.golsVisitante = golsVisitante;
    }

    public Confronto(Long identificacao, Integer golsMandante, Integer golsVisitante, Map<String, Integer> golsJogadoresMandante, Map<String, Integer> golsJogadoresVisitante) {
        this.identificacao = identificacao;
        this.golsMandante = golsMandante;
        this.golsVisitante = golsVisitante;
        this.golsJogadoresMandante.putAll(golsJogadoresMandante);
        this.golsJogadoresVisitante.putAll(golsJogadoresVisitante);
    }

    public Long getIdentificacao() {
        return identificacao;
    }

    public Equipe getMandante() {
        return mandante;
    }

    public Equipe getVisitante() {
        return visitante;
    }

    public String getEtapa() {
        return etapa;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public Integer getGolsMandante() {
        return golsMandante;
    }

    public Integer getGolsVisitante() {
        return golsVisitante;
    }

    public String getNomeTimeMandante() {
        return this.mandante.getNome();
    }

    public String getNomeTimeVisitante() {
        return this.visitante.getNome();
    }

    public Map<String, Integer> getGolsJogadoresMandante() {
        return golsJogadoresMandante;
    }

    public Map<String, Integer> getGolsJogadoresVisitante() {
        return golsJogadoresVisitante;
    }
}