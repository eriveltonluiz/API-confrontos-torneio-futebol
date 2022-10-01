package com.erivelton.torneiofutebol.dominio.servicos;

import com.erivelton.torneiofutebol.dominio.Equipe;
import com.erivelton.torneiofutebol.dominio.porta.ConfrontoService;

import java.util.List;

public class ConfrontoServiceImpl implements ConfrontoService {

    private EtapasMataMataServico etapasMataMataServico = new EtapasMataMataServico();

    @Override
    public void elaborar(List<Equipe> equipes) {
        Integer[] qtdConfrontos = {equipes.size() / 2};
        etapasMataMataServico.acrescentarEtapas(qtdConfrontos);
    }

}
