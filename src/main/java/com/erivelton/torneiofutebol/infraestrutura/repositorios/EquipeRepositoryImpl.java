package com.erivelton.torneiofutebol.infraestrutura.repositorios;

import com.erivelton.torneiofutebol.dominio.Equipe;
import com.erivelton.torneiofutebol.dominio.porta.EquipeRepositoryPorta;
import com.erivelton.torneiofutebol.infraestrutura.entidades.EquipeEntity;
import jakarta.inject.Singleton;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Singleton
@AllArgsConstructor
public class EquipeRepositoryImpl implements EquipeRepositoryPorta {

    private MicronautEquipeRepository micronautEquipeRepository;

    @Override
    public List<Equipe> salvarTodos(List<Equipe> equipes) {
        List<EquipeEntity> equipesASeremPersistidas = equipes.stream()
                .map(EquipeEntity::new)
                .collect(Collectors.toList());

        List<EquipeEntity> equipesPersistidas = micronautEquipeRepository.saveAll(equipesASeremPersistidas);

        return equipesPersistidas.stream()
                .map(EquipeEntity::paraEquipe)
                .collect(Collectors.toList());
    }

}
