package com.erivelton.torneiofutebol.dominio;

import lombok.Getter;

public enum Etapa {
    AVOS(" Avos"),
    OITAVAS("Oitavas de final"),
    QUARTAS("Quartas de final"),
    SEMI("Semifinal"),
    TERCEIRO_LUGAR("Disputa 3 lugar"),
    FINAL("Final");

    @Getter
    private final String etapa;

    Etapa(String etapa) {
        this.etapa = etapa;
    }

}
