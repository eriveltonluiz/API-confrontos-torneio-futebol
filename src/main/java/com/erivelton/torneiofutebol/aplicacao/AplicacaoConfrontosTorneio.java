package com.erivelton.torneiofutebol.aplicacao;

import com.erivelton.torneiofutebol.aplicacao.dto.requisicao.DadosEquipe;
import com.erivelton.torneiofutebol.dominio.Equipe;
import com.erivelton.torneiofutebol.dominio.porta.ConfrontoService;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class AplicacaoConfrontosTorneio {

    private final ConfrontoService confrontoService;

    public AplicacaoConfrontosTorneio(ConfrontoService confrontoService) {
        this.confrontoService = confrontoService;
    }

    public void criarTorneio(List<DadosEquipe> dadosEquipe){
        List<Equipe> equipes = dadosEquipe.stream()
                .map(Equipe::new)
                .collect(Collectors.toList());

        confrontoService.elaborar(equipes);
    }

}