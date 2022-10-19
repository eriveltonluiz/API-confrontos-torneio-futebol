package com.erivelton.torneiofutebol.adaptadores.integracao;

import com.erivelton.torneiofutebol.adaptadores.mapper.EquipeMapper;
import com.erivelton.torneiofutebol.adaptadores.mapper.JogadorMapper;
import com.erivelton.torneiofutebol.adaptadores.repositorios.jpa.MicronautEquipeRepository;
import com.erivelton.torneiofutebol.dominio.modelo.Equipe;
import com.erivelton.torneiofutebol.dominio.porta.saida.EquipeRepositoryPorta;
import com.erivelton.torneiofutebol.adaptadores.repositorios.entidades.EquipeEntity;
import jakarta.inject.Singleton;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Singleton
@AllArgsConstructor
public class EquipeRepositoryImpl implements EquipeRepositoryPorta {

    private MicronautEquipeRepository micronautEquipeRepository;

    private JogadorMapper jogadorMapper;

    private EquipeMapper equipeMapper;

    @Override
    public List<Equipe> salvarTodos(List<Equipe> equipes) {
        List<EquipeEntity> equipesASeremPersistidas = equipes.stream()
                .map(EquipeEntity::new)
//                .map(equipe -> equipeMapper.paraEquipeEntityJpa(equipe, jogadorMapper))
                .collect(Collectors.toList());

        List<EquipeEntity> equipesPersistidas = micronautEquipeRepository.saveAll(equipesASeremPersistidas);

        return equipesPersistidas.stream()
//                .map(EquipeEntity::paraEquipe)
                .map(equipeEntity -> equipeMapper.paraEquipe(equipeEntity, jogadorMapper))
                .collect(Collectors.toList());
    }

}
