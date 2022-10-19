package com.erivelton.torneiofutebol.aplicacao;

import com.erivelton.torneiofutebol.aplicacao.dto.requisicao.ConfrontoRequisicao;
import com.erivelton.torneiofutebol.aplicacao.dto.requisicao.DadosEquipe;
import com.erivelton.torneiofutebol.dominio.modelo.confronto.Confronto;
import com.erivelton.torneiofutebol.dominio.modelo.Equipe;
import com.erivelton.torneiofutebol.dominio.porta.entrada.ModeloCampeonato;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class AplicacaoModeloCampeonato {

    private final ModeloCampeonato modeloCampeonato;

    public AplicacaoModeloCampeonato(ModeloCampeonato modeloCampeonato) {
        this.modeloCampeonato = modeloCampeonato;
    }

    public void criar(List<DadosEquipe> dadosEquipe){
        List<Equipe> equipes = dadosEquipe.stream()
                .map(Equipe::new)
                .collect(Collectors.toList());

        modeloCampeonato.elaborar(equipes);
    }

    public void adicionarDadosConfronto(ConfrontoRequisicao confrontoRequisicao){
        Confronto confronto = confrontoRequisicao.paraConfrontoDominio();
        modeloCampeonato.mapearDadosConfronto(confronto);
    }

}