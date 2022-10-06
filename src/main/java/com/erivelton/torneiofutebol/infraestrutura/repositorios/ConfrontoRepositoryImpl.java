package com.erivelton.torneiofutebol.infraestrutura.repositorios;

import com.erivelton.torneiofutebol.dominio.Confronto;
import com.erivelton.torneiofutebol.dominio.porta.ConfrontoRepositoryPorta;
import com.erivelton.torneiofutebol.infraestrutura.entidades.ConfrontoEntity;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Singleton
@AllArgsConstructor
public class ConfrontoRepositoryImpl implements ConfrontoRepositoryPorta {

    private MicronautConfrontoRepository micronautConfrontoRepository;

    private MicronautEquipeRepository micronautEquipeRepository;

    @Override
    public void adicionarTodos(List<Confronto> confrontos) {
        List<ConfrontoEntity> confrontosASeremPersistidos = confrontos.stream()
                .map(confronto -> new ConfrontoEntity(micronautEquipeRepository, confronto))
                .collect(Collectors.toList());

        micronautConfrontoRepository.saveAll(confrontosASeremPersistidos);
    }

    @Override
    public void atualizarNasEtapas() {

    }
}
