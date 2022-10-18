package com.erivelton.torneiofutebol.dominio.porta;

import com.erivelton.torneiofutebol.dominio.Confronto;
import com.erivelton.torneiofutebol.dominio.Equipe;

import java.util.List;

public interface ModeloCampeonato {

    void elaborar(List<Equipe> equipes);

    void mapearDadosConfronto(Confronto confrontoRequisicao);
}
