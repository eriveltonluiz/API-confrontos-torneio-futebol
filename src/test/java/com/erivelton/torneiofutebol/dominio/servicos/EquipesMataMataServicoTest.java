package com.erivelton.torneiofutebol.dominio.servicos;

import com.erivelton.torneiofutebol.dominio.modelo.confronto.Confronto;
import com.erivelton.torneiofutebol.dominio.modelo.Equipe;
import com.erivelton.torneiofutebol.dominio.modelo.confronto.ConfrontosEquipesMataMataServico;
import com.erivelton.torneiofutebol.dominio.modelo.etapa.Etapa;
import com.erivelton.torneiofutebol.utils.ConstrutorClasses;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EquipesMataMataServicoTest {

    private ConfrontosEquipesMataMataServico equipesMataMataServico;
    private ConstrutorClasses construtorClasses;

    @BeforeEach
    void init(){
        construtorClasses = new ConstrutorClasses();
        equipesMataMataServico = new ConfrontosEquipesMataMataServico();
    }

    @Test
    void deveInserirConfrontosComTimesNaPrimeiraEtapa(){
        List<Confronto> confrontosEsperados = Arrays.asList(
                construtorClasses.builderConfrontoTeste("time1", "time2", Etapa.FINAL.getEtapa(), 1, new ArrayList<>(), new ArrayList<>()).build()
        );

        List<Equipe> mandantes = Arrays.asList(
                new Equipe("time1")
        );

        List<Equipe> visitantes = Arrays.asList(
                new Equipe("time2")
        );

        resultadoConfrontos(mandantes, visitantes, Arrays.asList(Etapa.FINAL.getEtapa()), new Integer[]{1}, confrontosEsperados, Etapa.FINAL.getEtapa());
    }

    @Test
    void deveMapearConfrontosSemDefinicaoDeTimesDepoisDaPrimeiraEtapa(){
        List<Confronto> confrontosEsperados = Arrays.asList(
                construtorClasses.builderConfrontoTeste("time1", "time2", Etapa.FINAL.getEtapa(), 1, new ArrayList<>(), new ArrayList<>()).build(),
                construtorClasses.builderConfrontoTeste("time3", "time4", Etapa.SEMI.getEtapa(), 2, new ArrayList<>(), new ArrayList<>()).build(),
                construtorClasses.builderConfrontoTeste("", "", Etapa.TERCEIRO_LUGAR.getEtapa(), 1, new ArrayList<>(), new ArrayList<>()).build(),
                construtorClasses.builderConfrontoTeste("", "", Etapa.FINAL.getEtapa(), 1, new ArrayList<>(), new ArrayList<>()).build()
        );

        List<Equipe> mandantes = Arrays.asList(
                new Equipe("time1"),
                new Equipe("time3")
        );

        List<Equipe> visitantes = Arrays.asList(
                new Equipe("time2"),
                new Equipe("time4")
        );

        List<String> etapas = Arrays.asList(Etapa.SEMI.getEtapa(), Etapa.TERCEIRO_LUGAR.getEtapa(), Etapa.FINAL.getEtapa());

        resultadoConfrontos(mandantes, visitantes, etapas, new Integer[]{2}, confrontosEsperados, Etapa.TERCEIRO_LUGAR.getEtapa());
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