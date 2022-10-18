package com.erivelton.torneiofutebol.dominio.servicos;

import com.erivelton.torneiofutebol.dominio.Confronto;
import com.erivelton.torneiofutebol.dominio.Equipe;
import com.erivelton.torneiofutebol.dominio.porta.ConfrontoRepositoryPorta;
import com.erivelton.torneiofutebol.dominio.porta.ModeloCampeonato;
import com.erivelton.torneiofutebol.dominio.porta.EquipeRepositoryPorta;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Torneio implements ModeloCampeonato {

    private final ConfrontoRepositoryPorta confrontoRepositoryPorta;
    private final EquipeRepositoryPorta equipeRepositoryPorta;
    private final EtapasMataMataServico etapasMataMataServico;
    private final EquipesMataMataServico equipesMataMataServico;

    public Torneio(ConfrontoRepositoryPorta confrontoRepositoryPorta, EquipeRepositoryPorta equipeRepositoryPorta, EtapasMataMataServico etapasMataMataServico, EquipesMataMataServico equipesMataMataServico) {
        this.confrontoRepositoryPorta = confrontoRepositoryPorta;
        this.equipeRepositoryPorta = equipeRepositoryPorta;
        this.etapasMataMataServico = etapasMataMataServico;
        this.equipesMataMataServico = equipesMataMataServico;
    }

    @Override
    public void elaborar(List<Equipe> equipes) {
        List<Equipe> equipesRegistradas = equipeRepositoryPorta.salvarTodos(equipes);
        Collections.shuffle(equipesRegistradas);

        Integer[] qtdConfrontos = {equipesRegistradas.size() / 2};

        List<Equipe> equipesMandantes = equipesRegistradas.stream()
                .limit(qtdConfrontos[0])
                .collect(Collectors.toList());

        List<Equipe> equipesVisitantes = equipesRegistradas.stream()
                .filter(equipe -> !equipesMandantes.contains(equipe))
                .collect(Collectors.toList());

        List<String> etapas = etapasMataMataServico.acrescentarEtapas(qtdConfrontos);
        List<Confronto> confrontos = equipesMataMataServico.mapearConfrontos(equipesMandantes, equipesVisitantes, etapas, qtdConfrontos);

        confrontoRepositoryPorta.adicionarTodos(confrontos);
    }

    @Override
    public void mapearDadosConfronto(Confronto confronto) {
        confrontoRepositoryPorta.atualizarConfronto(confronto);
    }

}