package com.erivelton.torneiofutebol.dominio.porta.saida;

import com.erivelton.torneiofutebol.dominio.modelo.Equipe;

import java.util.List;

public interface EquipeRepositoryPorta {

    List<Equipe> salvarTodos(List<Equipe> equipes);

}
