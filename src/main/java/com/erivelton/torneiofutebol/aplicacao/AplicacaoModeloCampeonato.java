package com.erivelton.torneiofutebol.aplicacao;

import com.erivelton.torneiofutebol.aplicacao.dto.requisicao.ConfrontoRequisicao;
import com.erivelton.torneiofutebol.aplicacao.dto.requisicao.DadosEquipe;
import com.erivelton.torneiofutebol.aplicacao.validacao.ValidacaoCustomizada;
import com.erivelton.torneiofutebol.aplicacao.validacao.ValidacaoDados;
import com.erivelton.torneiofutebol.dominio.modelo.confronto.Confronto;
import com.erivelton.torneiofutebol.dominio.modelo.Equipe;
import com.erivelton.torneiofutebol.dominio.porta.entrada.ModeloCampeonato;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class AplicacaoModeloCampeonato {

    private static final Logger LOG = LoggerFactory.getLogger(AplicacaoModeloCampeonato.class);

    private final ValidacaoDados validacao;

    private final ValidacaoCustomizada validacaoCustomizada;

    private final ModeloCampeonato modeloCampeonato;

    public AplicacaoModeloCampeonato(ValidacaoDados validacao, ValidacaoCustomizada validacaoCustomizada, ModeloCampeonato modeloCampeonato) {
        this.validacao = validacao;
        this.validacaoCustomizada = validacaoCustomizada;
        this.modeloCampeonato = modeloCampeonato;
    }

    public void criar(List<DadosEquipe> dadosEquipe) {
        LOG.info("Recebendo dados das equipes que formarão o campeonato: {}", dadosEquipe);

        LOG.info("Validação dos dados das equipes");
        validacao.dadosEntrada(dadosEquipe);

        List<Equipe> equipes = dadosEquipe.stream()
                .map(Equipe::new)
                .collect(Collectors.toList());

        modeloCampeonato.elaborar(equipes);
    }

    public void adicionarDadosConfronto(ConfrontoRequisicao confrontoRequisicao) {
        validacao.dadosEntrada(confrontoRequisicao);
        validacaoCustomizada.limite(confrontoRequisicao);

        Confronto confronto = confrontoRequisicao.paraConfrontoDominio();
        modeloCampeonato.mapearDadosConfronto(confronto);
    }

}