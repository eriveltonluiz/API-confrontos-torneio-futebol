package com.erivelton.torneiofutebol.dominio;

import lombok.Builder;

@Builder
public class Confronto {

    private Equipe mandante;

    private Equipe visitante;

    private String etapa;

    private Integer ordem;

    private Integer golsMandante;

    private Integer golsVisitante;

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

}