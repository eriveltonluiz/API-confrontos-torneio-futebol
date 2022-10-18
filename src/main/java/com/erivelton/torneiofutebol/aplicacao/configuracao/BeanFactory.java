package com.erivelton.torneiofutebol.aplicacao.configuracao;

import com.erivelton.torneiofutebol.aplicacao.AplicacaoModeloCampeonato;
import com.erivelton.torneiofutebol.dominio.porta.ConfrontoRepositoryPorta;
import com.erivelton.torneiofutebol.dominio.porta.EquipeRepositoryPorta;
import com.erivelton.torneiofutebol.dominio.porta.ModeloCampeonato;
import com.erivelton.torneiofutebol.dominio.servicos.EquipesMataMataServico;
import com.erivelton.torneiofutebol.dominio.servicos.EtapasMataMataServico;
import com.erivelton.torneiofutebol.dominio.servicos.Torneio;
import io.micronaut.context.annotation.Factory;
import jakarta.inject.Singleton;

@Factory
public class BeanFactory {

    @Singleton
    public ModeloCampeonato getModeloCampeonato(ConfrontoRepositoryPorta confrontoRepositoryPorta, EquipeRepositoryPorta equipeRepositoryPorta){
        return new Torneio(confrontoRepositoryPorta, equipeRepositoryPorta, new EtapasMataMataServico(), new EquipesMataMataServico());
    }

}
