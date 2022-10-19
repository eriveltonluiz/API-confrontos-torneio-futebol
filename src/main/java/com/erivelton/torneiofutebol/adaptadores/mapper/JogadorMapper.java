package com.erivelton.torneiofutebol.adaptadores.mapper;

import com.erivelton.torneiofutebol.adaptadores.repositorios.entidades.JogadorEntity;
import com.erivelton.torneiofutebol.dominio.modelo.Equipe;
import com.erivelton.torneiofutebol.dominio.modelo.Jogador;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class JogadorMapper {

    public List<Jogador> paraListaJogador(List<JogadorEntity> jogadores, Equipe equipe){
        return jogadores.stream()
                .map(jogadorEntity -> paraJogador(jogadorEntity, equipe))
                .collect(Collectors.toList());
    }

    public Jogador paraJogador(JogadorEntity jogador, Equipe equipe){
        return new Jogador(jogador.getRg(), jogador.getNome(), equipe);
    }

}
