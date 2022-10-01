package com.erivelton.torneiofutebol.dominio.servicos;

import com.erivelton.torneiofutebol.dominio.Confronto;
import com.erivelton.torneiofutebol.dominio.Equipe;
import com.erivelton.torneiofutebol.dominio.porta.ConfrontoService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ConfrontoServiceImpl implements ConfrontoService {

    private EtapasMataMataServico etapasMataMataServico;
    private EquipesMataMataServico equipesMataMataServico;

    public ConfrontoServiceImpl() {
        this.etapasMataMataServico = new EtapasMataMataServico();
        this.equipesMataMataServico = new EquipesMataMataServico();
    }

    @Override
    public void elaborar(List<Equipe> equipes) {
        Integer[] qtdConfrontos = {equipes.size() / 2};
        List<String> etapas = etapasMataMataServico.acrescentarEtapas(qtdConfrontos);

        Collections.shuffle(equipes);

        List<Equipe> equipesMandantes = equipes.stream()
                .limit(qtdConfrontos[0])
                .collect(Collectors.toList());

        List<Equipe> equipesVisitantes = equipes.stream()
                .filter(equipe -> !equipesMandantes.contains(equipe))
                .collect(Collectors.toList());

        List<Confronto> confrontos = equipesMataMataServico.mapearConfrontos(equipesMandantes, equipesVisitantes, etapas, qtdConfrontos);
    }

}