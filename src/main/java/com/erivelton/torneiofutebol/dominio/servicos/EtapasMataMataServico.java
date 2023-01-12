package com.erivelton.torneiofutebol.dominio.servicos;

import com.erivelton.torneiofutebol.dominio.modelo.etapa.Etapa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class EtapasMataMataServico {

    public List<String> acrescentarEtapas(Integer[] quantidadeConfrontos) {
        List<String> etapas = new ArrayList<>();

        Map<String, Integer> etapasFinais = new HashMap<>(Map.of(
                Etapa.OITAVAS.getEtapa(), 8, Etapa.QUARTAS.getEtapa(), 4, Etapa.SEMI.getEtapa(), 2,Etapa.TERCEIRO_LUGAR.getEtapa(), 2, Etapa.FINAL.getEtapa(), 1
        ));

        if(quantidadeConfrontos[0] > 8) {
            Stream.iterate(quantidadeConfrontos[0], etapa -> etapa > 8, etapa -> etapa / 2)
                    .forEach(etapa -> etapas.add(String.valueOf(etapa).concat(Etapa.AVOS.getEtapa())));

            quantidadeConfrontos[0] = 8;
        }

        etapasFinais.values().removeIf(confrontosEtapa -> confrontosEtapa > quantidadeConfrontos[0]);
        etapasFinais.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(valor -> etapas.add(valor.getKey()));

        return etapas;
    }

}