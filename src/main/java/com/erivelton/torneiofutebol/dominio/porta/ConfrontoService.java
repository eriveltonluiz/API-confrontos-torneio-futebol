package com.erivelton.torneiofutebol.dominio.porta;

import com.erivelton.torneiofutebol.dominio.Equipe;

import java.util.List;

public interface ConfrontoService {

    void elaborar(List<Equipe> equipes);

}
