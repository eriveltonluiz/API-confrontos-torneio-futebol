package com.erivelton.torneiofutebol.infraestrutura.repositorios;

import com.erivelton.torneiofutebol.dominio.Confronto;
import com.erivelton.torneiofutebol.dominio.RecursoNaoEncontradoException;
import com.erivelton.torneiofutebol.dominio.porta.ConfrontoRepositoryPorta;
import com.erivelton.torneiofutebol.infraestrutura.entidades.ConfrontoEntity;
import jakarta.inject.Singleton;
import lombok.AllArgsConstructor;

import java.util.Arrays;
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
    public void atualizarConfronto(Confronto confronto) {
        ConfrontoEntity confrontoEntity = micronautConfrontoRepository.
                findById(confronto.getIdentificacao())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Identificação do confronto não foi encontrado"));

        confrontoEntity.atulizarGolsTimes(confronto.getGolsMandante(), confronto.getGolsVisitante());
        confrontoEntity.atulizarGolsJogadoresTimes(confronto.getGolsJogadoresMandante(), confronto.getGolsJogadoresVisitante());

        micronautEquipeRepository.updateAll(Arrays.asList(confrontoEntity.getMandante(), confrontoEntity.getVisitante()));

        micronautConfrontoRepository.update(confrontoEntity);
    }

}
