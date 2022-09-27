package com.erivelton.torneiofutebol.aplicacao.configuracao;

import com.erivelton.torneiofutebol.aplicacao.AplicacaoConfrontosTorneio;
import com.erivelton.torneiofutebol.dominio.porta.ConfrontoService;
import com.erivelton.torneiofutebol.dominio.servicos.ConfrontoServiceImpl;
import io.micronaut.context.annotation.Factory;
import jakarta.inject.Singleton;

@Factory
public class BeanFactory {

    @Singleton
    public AplicacaoConfrontosTorneio getAplicacaoConfrontosTorneio(ConfrontoService confrontoService){
        return new AplicacaoConfrontosTorneio(confrontoService);
    }

    @Singleton
    public ConfrontoService getConfrontoService(){
        return new ConfrontoServiceImpl();
    }

}
