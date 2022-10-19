package com.erivelton.torneiofutebol.dominio.porta.entrada;

import com.erivelton.torneiofutebol.dominio.modelo.confronto.Confronto;
import com.erivelton.torneiofutebol.dominio.modelo.Equipe;

import java.util.List;

public interface ModeloCampeonato {

    void elaborar(List<Equipe> equipes);

    void mapearDadosConfronto(Confronto confrontoRequisicao);
}
