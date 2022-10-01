package com.erivelton.torneiofutebol.dominio.servicos;

import com.erivelton.torneiofutebol.dominio.Confronto;
import com.erivelton.torneiofutebol.dominio.Equipe;
import com.erivelton.torneiofutebol.dominio.Etapa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EquipesMataMataServicoTest {

    private EquipesMataMataServico equipesMataMataServico;

    @BeforeEach
    void init(){
        equipesMataMataServico = new EquipesMataMataServico();
    }

    @Test
    void deveInserirConfrontosComTimesNaPrimeiraEtapa(){
        List<Confronto> confrontosEsperados = Arrays.asList(
                builderTeste("time1", "time2", Etapa.FINAL.getEtapa(), 1)
        );

        List<Equipe> mandantes = Arrays.asList(
                new Equipe("time1", null)
        );

        List<Equipe> visitantes = Arrays.asList(
                new Equipe("time2", null)
        );

        resultadoConfrontos(mandantes, visitantes, Arrays.asList(Etapa.FINAL.getEtapa()), new Integer[]{1}, confrontosEsperados, Etapa.FINAL.getEtapa());
    }

    @Test
    void deveMapearConfrontosSemDefinicaoDeTimesDepoisDaPrimeiraEtapa(){
        List<Confronto> confrontosEsperados = Arrays.asList(
                builderTeste("time1", "time2", Etapa.SEMI.getEtapa(), 1),
                builderTeste("time3", "time4", Etapa.SEMI.getEtapa(), 2),
                builderTeste(null, null, Etapa.TERCEIRO_LUGAR.getEtapa(), 1),
                builderTeste(null, null, Etapa.FINAL.getEtapa(), 1)
        );

        List<Equipe> mandantes = Arrays.asList(
                new Equipe("time1", null),
                new Equipe("time3", null)
        );

        List<Equipe> visitantes = Arrays.asList(
                new Equipe("time2", null),
                new Equipe("time4", null)
        );

        List<String> etapas = Arrays.asList(Etapa.SEMI.getEtapa(), Etapa.TERCEIRO_LUGAR.getEtapa(), Etapa.FINAL.getEtapa());

        resultadoConfrontos(mandantes, visitantes, etapas, new Integer[]{2}, confrontosEsperados, Etapa.TERCEIRO_LUGAR.getEtapa());
    }

    private Confronto builderTeste(String time1, String time2, String etapa, Integer ordem){
        return Confronto.builder()
                .mandante(
                        Equipe.builder()
                                .nome(time1)
                                .jogadores(new ArrayList<>())
                                .build()
                )
                .visitante(
                        Equipe.builder()
                                .nome(time2)
                                .jogadores(new ArrayList<>())
                                .build()
                )
                .etapa(etapa)
                .ordem(ordem)
                .golsMandante(0)
                .golsVisitante(0)
                .build();
    }

    private void resultadoConfrontos(List<Equipe> mandantes, List<Equipe> visitantes, List<String> etapas, Integer[] qtdConfrontos, List<Confronto> confrontosEsperados, String etapa) {
        List<Confronto> resultado = equipesMataMataServico.mapearConfrontos(mandantes, visitantes, etapas, qtdConfrontos);

        assertEquals(confrontosEsperados.get(0).getMandante().getNome(), resultado.get(0).getMandante().getNome());
        assertEquals(confrontosEsperados.get(0).getVisitante().getNome(), resultado.get(0).getVisitante().getNome());
        assertTrue(resultado.stream().anyMatch(confronto -> confronto.getEtapa().equals(etapa)));
        assertEquals(confrontosEsperados.size(), resultado.size());
        assertEquals(confrontosEsperados.get(0).getOrdem(), confrontosEsperados.get(0).getOrdem());
    }

}