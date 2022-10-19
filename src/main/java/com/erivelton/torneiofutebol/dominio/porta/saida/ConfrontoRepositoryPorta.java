package com.erivelton.torneiofutebol.dominio.porta.saida;

import com.erivelton.torneiofutebol.dominio.modelo.confronto.Confronto;

import java.util.List;

public interface ConfrontoRepositoryPorta {

    void adicionarTodos(List<Confronto> confrontos);

    void atualizarConfronto(Confronto confronto);
}
