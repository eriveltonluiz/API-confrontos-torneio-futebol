package com.erivelton.torneiofutebol.aplicacao;

import com.erivelton.torneiofutebol.aplicacao.dto.DadosEquipe;
import com.erivelton.torneiofutebol.aplicacao.dto.DadosJogador;
import com.erivelton.torneiofutebol.dominio.porta.ConfrontoService;
import com.erivelton.torneiofutebol.dominio.Equipe;
import com.erivelton.torneiofutebol.dominio.Jogador;
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
                .map(this::buildEquipe)
                .collect(Collectors.toList());

        confrontoService.elaborar(equipes);
    }

    private Equipe buildEquipe(DadosEquipe dadosEquipe) {
        Equipe equipe = Equipe.builder()
                .nome(dadosEquipe.getEquipe())
                .totalGols(0)
                .build();

        List<Jogador> jogadores = dadosEquipe.getJogadores().stream()
                .map(jogador -> buildJogador(jogador, equipe))
                .collect(Collectors.toList());

        equipe.adicionarJogadores(jogadores);

        return equipe;
    }

    private Jogador buildJogador(DadosJogador jogador, Equipe equipe) {
        return Jogador.builder()
                .nome(jogador.getNome())
                .rg(jogador.getRg())
                .equipe(equipe)
                .build();
    }

}
