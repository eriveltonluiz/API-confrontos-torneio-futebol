package com.erivelton.torneiofutebol.dominio.porta;

import com.erivelton.torneiofutebol.dominio.Confronto;

import java.util.List;

public interface ConfrontoRepositoryPorta {

    void adicionarTodos(List<Confronto> confrontos);

    void atualizarNasEtapas();
}
