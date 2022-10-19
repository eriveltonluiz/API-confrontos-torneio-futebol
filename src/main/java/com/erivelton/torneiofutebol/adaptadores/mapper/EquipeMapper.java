package com.erivelton.torneiofutebol.adaptadores.mapper;

import com.erivelton.torneiofutebol.adaptadores.repositorios.entidades.EquipeEntity;
import com.erivelton.torneiofutebol.dominio.modelo.Equipe;
import jakarta.inject.Singleton;

@Singleton
public class EquipeMapper {

    public Equipe paraEquipe(EquipeEntity equipeEntity, JogadorMapper jogadorMapper){
        Equipe equipe = new Equipe(equipeEntity.getNome());
        equipe.adicionarJogadores(jogadorMapper.paraListaJogador(equipeEntity.getJogadores(), equipe));
        return equipe;
    }

}
