package com.erivelton.torneiofutebol.dominio.servicos;

import com.erivelton.torneiofutebol.dominio.modelo.etapa.Etapa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EtapasMataMataServicoTest {

    private EtapasMataMataServico etapasMataMataServico;

    @BeforeEach
    void init(){
        etapasMataMataServico = new EtapasMataMataServico();
    }

    @Test
    void deveRetornarEtapasFinaisComAvos() {
        List<String> etapasFinaisComAvosEsperada = Arrays.asList("32 Avos", "16 Avos", Etapa.OITAVAS.getEtapa(), Etapa.QUARTAS.getEtapa(), Etapa.SEMI.getEtapa(), Etapa.TERCEIRO_LUGAR.getEtapa(), Etapa.FINAL.getEtapa());
        elaborar(etapasFinaisComAvosEsperada, new Integer[]{32});
    }

    @Test
    void deveRetornarEtapasFinaisAPartirDasOitavas() {
        List<String> etapasFinaisEsperada = Arrays.asList(Etapa.OITAVAS.getEtapa(), Etapa.QUARTAS.getEtapa(), Etapa.SEMI.getEtapa(), Etapa.TERCEIRO_LUGAR.getEtapa(), Etapa.FINAL.getEtapa());
        elaborar(etapasFinaisEsperada, new Integer[]{8});
    }

    @Test
    void deveRetornarEtapasFinaisComMenosDeOitoConfrontos(){
        List<String> etapasFinaisEsperada = Arrays.asList(Etapa.QUARTAS.getEtapa(), Etapa.SEMI.getEtapa(), Etapa.TERCEIRO_LUGAR.getEtapa(), Etapa.FINAL.getEtapa());
        elaborar(etapasFinaisEsperada, new Integer[]{4});
    }

    @Test
    void deveRetornarEtapasFinaisAPartirDaSemifinal(){
        List<String> etapasFinaisEsperada = Arrays.asList(Etapa.SEMI.getEtapa(), Etapa.TERCEIRO_LUGAR.getEtapa(), Etapa.FINAL.getEtapa());
        elaborar(etapasFinaisEsperada, new Integer[]{2});
    }

    private void elaborar(List<String> etapasEsperadas, Integer[] quantidadeConfrontos){
        List<String> resultado = etapasMataMataServico.acrescentarEtapas(quantidadeConfrontos);

        assertIterableEquals(etapasEsperadas, resultado);
        assertEquals(etapasEsperadas.size(), resultado.size());
        assertTrue(resultado.containsAll(etapasEsperadas));
    }
}