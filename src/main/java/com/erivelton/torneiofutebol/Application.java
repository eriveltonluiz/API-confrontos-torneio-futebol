package com.erivelton.torneiofutebol;

import com.erivelton.torneiofutebol.aplicacao.AplicacaoConfrontosTorneio;
import com.erivelton.torneiofutebol.aplicacao.dto.DadosEquipe;
import com.erivelton.torneiofutebol.dominio.porta.ConfrontoService;
import com.erivelton.torneiofutebol.dominio.servicos.ConfrontoServiceImpl;
import com.erivelton.torneiofutebol.infraestrutura.mapper.MapperJson;
import io.micronaut.runtime.Micronaut;

import java.io.IOException;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        Micronaut.run(Application.class, args);

        MapperJson mapperJson = new MapperJson();
        try {
            List<DadosEquipe> dadosEquipe = mapperJson.transformarEmObjeto();
            ConfrontoService confrontoService = new ConfrontoServiceImpl();
            AplicacaoConfrontosTorneio aplicacaoConfrontosTorneio = new AplicacaoConfrontosTorneio(confrontoService);
            aplicacaoConfrontosTorneio.criarTorneio(dadosEquipe);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}