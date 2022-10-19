package com.erivelton.torneiofutebol.utils;

import com.erivelton.torneiofutebol.dominio.modelo.confronto.Confronto;
import com.erivelton.torneiofutebol.dominio.modelo.Equipe;
import com.erivelton.torneiofutebol.dominio.modelo.Jogador;

import java.util.List;

public class ConstrutorClasses {

    public Equipe builderEquipeTeste(String time, List<Jogador> jogadores){
        if(!time.isEmpty()) {
            Equipe equipe = Equipe.builder()
                    .nome(time)
                    .jogadores(jogadores)
                    .build();

            jogadores.forEach(jogador -> jogador.setEquipe(equipe));

            return equipe;
        }

        return null;
    }

    public Confronto builderConfrontoTeste(String time1, String time2, String etapa, Integer ordem, List<Jogador> jogadores1, List<Jogador> jogadores2){
        return Confronto.builder()
                .mandante(builderEquipeTeste(time1, jogadores1))
                .visitante(builderEquipeTeste(time2, jogadores2))
                .etapa(etapa)
                .ordem(ordem)
                .build();
    }

}
