package com.erivelton.torneiofutebol.aplicacao;

import com.erivelton.torneiofutebol.aplicacao.dto.requisicao.DadosEquipe;
import com.erivelton.torneiofutebol.infraestrutura.mapper.MapperJson;
import io.micronaut.context.annotation.Context;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;

import java.io.IOException;
import java.util.List;

//@Context
public class Inicializador {

    @Inject
    private final AplicacaoModeloCampeonato aplicacaoModeloCampeonato;

    public Inicializador(AplicacaoModeloCampeonato aplicacaoModeloCampeonato) {
        this.aplicacaoModeloCampeonato = aplicacaoModeloCampeonato;
    }

//    @PostConstruct
    public void inicial() throws IOException {
        MapperJson mapperJson = new MapperJson();
        List<DadosEquipe> dadosEquipe = mapperJson.transformarEmObjeto();
        aplicacaoModeloCampeonato.criar(dadosEquipe);
    }
}
