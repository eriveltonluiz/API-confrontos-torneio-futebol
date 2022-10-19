package com.erivelton.torneiofutebol.adaptadores.mensageria;

import com.erivelton.torneiofutebol.aplicacao.AplicacaoModeloCampeonato;
import com.erivelton.torneiofutebol.aplicacao.dto.requisicao.DadosEquipe;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import jakarta.inject.Inject;

import java.util.List;

@KafkaListener(offsetReset = OffsetReset.EARLIEST)
public class CampeonatoListener {

    @Inject
    private AplicacaoModeloCampeonato aplicacaoModeloCampeonato;

    @Topic("${api.criacao.topico}")
    public void listenCriacaoCampeoanto(List<DadosEquipe> equipes){
        aplicacaoModeloCampeonato.criar(equipes);
    }

}
