package com.erivelton.torneiofutebol.dominio.modelo.confronto;

import com.erivelton.torneiofutebol.dominio.modelo.confronto.Confronto;
import com.erivelton.torneiofutebol.dominio.modelo.Equipe;
import com.erivelton.torneiofutebol.dominio.modelo.etapa.Etapa;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class ConfrontosEquipesMataMataServico {

    public List<Confronto> mapearConfrontos(List<Equipe> equipesMandantes, List<Equipe> equipesVisitantes, List<String> etapas, Integer[] qtdConfrontos) {
        List<Confronto> confrontos = new ArrayList<>();

        UnaryOperator<Integer> iteracao = indice -> indice + 1;
        Predicate<Integer> comparador = indice -> indice < qtdConfrontos[0];

        etapas.forEach(
                etapa -> {
                    Stream.iterate(0, comparador, iteracao).forEach(
                            indice -> {
                                Confronto confronto = etapa.equals(etapas.get(0))
                                        ? new Confronto(equipesMandantes.get(indice), equipesVisitantes.get(indice), etapa, indice + 1, 0, 0)
                                        : new Confronto(null, null, etapa, indice + 1, 0, 0);

                                confrontos.add(confronto);
                            }
                    );
                    qtdConfrontos[0] = etapa.equals(Etapa.TERCEIRO_LUGAR.getEtapa()) ? 1 : qtdConfrontos[0] / 2;
                }
        );

        return confrontos;
    }
}