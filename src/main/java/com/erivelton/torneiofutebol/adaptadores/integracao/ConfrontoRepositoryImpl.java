package com.erivelton.torneiofutebol.adaptadores.integracao;

import com.erivelton.torneiofutebol.adaptadores.repositorios.jpa.MicronautConfrontoRepository;
import com.erivelton.torneiofutebol.adaptadores.repositorios.jpa.MicronautEquipeRepository;
import com.erivelton.torneiofutebol.dominio.modelo.confronto.Confronto;
import com.erivelton.torneiofutebol.dominio.exceptions.RecursoNaoEncontradoException;
import com.erivelton.torneiofutebol.dominio.porta.saida.ConfrontoRepositoryPorta;
import com.erivelton.torneiofutebol.adaptadores.repositorios.entidades.ConfrontoEntity;
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
