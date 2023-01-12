package com.erivelton.torneiofutebol.aplicacao.configuracao;

import com.erivelton.torneiofutebol.dominio.porta.saida.ConfrontoRepositoryPorta;
import com.erivelton.torneiofutebol.dominio.porta.saida.EquipeRepositoryPorta;
import com.erivelton.torneiofutebol.dominio.porta.entrada.ModeloCampeonato;
import com.erivelton.torneiofutebol.dominio.modelo.confronto.ConfrontosEquipesMataMataServico;
import com.erivelton.torneiofutebol.dominio.servicos.EtapasMataMataServico;
import com.erivelton.torneiofutebol.dominio.servicos.MapeamentoTorneio;
import io.micronaut.context.annotation.Factory;
import jakarta.inject.Singleton;

@Factory
public class BeanFactory {

    @Singleton
    public ModeloCampeonato getModeloCampeonato(ConfrontoRepositoryPorta confrontoRepositoryPorta, EquipeRepositoryPorta equipeRepositoryPorta){
        return new MapeamentoTorneio(confrontoRepositoryPorta, equipeRepositoryPorta, new EtapasMataMataServico(), new ConfrontosEquipesMataMataServico());
    }

}
