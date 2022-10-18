package com.erivelton.torneiofutebol.infraestrutura.mensageria;

import com.erivelton.torneiofutebol.aplicacao.AplicacaoModeloCampeonato;
import com.erivelton.torneiofutebol.aplicacao.dto.requisicao.ConfrontoRequisicao;
import com.erivelton.torneiofutebol.aplicacao.dto.requisicao.DadosEquipe;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import jakarta.inject.Inject;

import java.util.List;

@KafkaListener(offsetReset = OffsetReset.EARLIEST)
public class ConfrontoListener {

    @Inject
    private AplicacaoModeloCampeonato aplicacaoModeloCampeonato;

    @Topic("${api.confronto.topico}")
    public void listenAcrescimoDadosConfronto(ConfrontoRequisicao confrontoRequisicao){
        aplicacaoModeloCampeonato.adicionarDadosConfronto(confrontoRequisicao);
    }

}
