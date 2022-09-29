package com.erivelton.torneiofutebol.dominio.servicos;

import com.erivelton.torneiofutebol.dominio.Etapa;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EtapasMataMataServico {

    public List<String> acrescentarEtapas(Integer quantidadeConfrontos) {
        String avos = " Avos";
        Map<String, Integer> etapasFinais = new HashMap<>(
                Map.of(
                        Etapa.OITAVAS.getEtapa(), 8, Etapa.QUARTAS.getEtapa(), 4, Etapa.SEMI.getEtapa(), 2,Etapa.TERCEIRO_LUGAR.getEtapa(), 2, Etapa.FINAL.getEtapa(), 1
                )
        );

        List<String> etapas = new ArrayList<>();

        int valorInicial = quantidadeConfrontos;
        Supplier<Stream<Integer>> quantidadeAvos = () -> Stream.iterate(valorInicial, etapa -> etapa > 8, etapa -> etapa / 2);

        if(quantidadeAvos.get().iterator().hasNext()){
            List<String> etapasAvos = quantidadeAvos.get()
                    .map(valor -> String.valueOf(valor).concat(avos))
                    .collect(Collectors.toList());

            etapas.addAll(etapasAvos);

            quantidadeConfrontos = Integer.valueOf(8);
        }

        int comparador = quantidadeConfrontos;
        etapasFinais.values()
                .removeIf(valor -> valor > comparador);

        etapasFinais.forEach((chave, valor) -> etapas.add(chave));

        return etapas;
    }

}
