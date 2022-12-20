package com.erivelton.torneiofutebol.dominio.modelo.confronto;

import com.erivelton.torneiofutebol.dominio.modelo.Equipe;

import java.util.HashMap;
import java.util.Map;

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

    private Confronto(ConfrontoBuilder confrontoBuilder) {
        this.identificacao = confrontoBuilder.identificacao;
        this.mandante = confrontoBuilder.mandante;
        this.visitante = confrontoBuilder.visitante;
        this.etapa = confrontoBuilder.etapa;
        this.ordem = confrontoBuilder.ordem;
        this.golsMandante = confrontoBuilder.golsMandante;
        this.golsVisitante = confrontoBuilder.golsVisitante;
        this.golsJogadoresMandante = confrontoBuilder.golsJogadoresMandante;
        this.golsJogadoresVisitante = confrontoBuilder.golsJogadoresVisitante;
    }

    public static Confronto.ConfrontoBuilder builder() {
        return new Confronto.ConfrontoBuilder();
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

    public static class ConfrontoBuilder {

        private Long identificacao;

        private Equipe mandante;

        private Equipe visitante;

        private String etapa;

        private Integer ordem;

        private Integer golsMandante;

        private Integer golsVisitante;

        private Map<String, Integer> golsJogadoresMandante = new HashMap<>();

        private Map<String, Integer> golsJogadoresVisitante = new HashMap<>();

        public ConfrontoBuilder identificacao(Long identificacao) {
            this.identificacao = identificacao;
            return this;
        }

        public ConfrontoBuilder mandante(Equipe mandante) {
            this.mandante = mandante;
            return this;
        }

        public ConfrontoBuilder visitante(Equipe visitante) {
            this.visitante = visitante;
            return this;
        }

        public ConfrontoBuilder etapa(String etapa) {
            this.etapa = etapa;
            return this;
        }

        public ConfrontoBuilder ordem(Integer ordem) {
            this.ordem = ordem;
            return this;
        }

        public ConfrontoBuilder golsMandante(Integer golsMandante) {
            this.golsMandante = golsMandante;
            return this;
        }

        public ConfrontoBuilder golsVisitante(Integer golsVisitante) {
            this.golsVisitante = golsVisitante;
            return this;
        }

        public ConfrontoBuilder golsJogadoresVisitante(Map<String, Integer> golsJogadoresVisitante) {
            this.golsJogadoresVisitante = golsJogadoresVisitante;
            return this;
        }

        public ConfrontoBuilder golsJogadoresMandante(Map<String, Integer> golsJogadoresMandante) {
            this.golsJogadoresMandante = golsJogadoresMandante;
            return this;
        }

        public Confronto build() {
            return new Confronto(this);
        }

    }
}