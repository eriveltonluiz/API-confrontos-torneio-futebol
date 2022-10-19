package com.erivelton.torneiofutebol.adaptadores.integracao;

import com.erivelton.torneiofutebol.adaptadores.integracao.ConfrontoRepositoryImpl;
import com.erivelton.torneiofutebol.adaptadores.repositorios.jpa.MicronautConfrontoRepository;
import com.erivelton.torneiofutebol.adaptadores.repositorios.jpa.MicronautEquipeRepository;
import com.erivelton.torneiofutebol.dominio.modelo.confronto.Confronto;
import com.erivelton.torneiofutebol.dominio.modelo.etapa.Etapa;
import com.erivelton.torneiofutebol.dominio.modelo.Jogador;
import com.erivelton.torneiofutebol.adaptadores.repositorios.entidades.ConfrontoEntity;
import com.erivelton.torneiofutebol.adaptadores.repositorios.entidades.EquipeEntity;
import com.erivelton.torneiofutebol.utils.ConstrutorClasses;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class ConfrontoRepositoryImplTest {

    @Inject
    private MicronautConfrontoRepository micronautConfrontoRepository;

    @Inject
    private MicronautEquipeRepository micronautEquipeRepository;

    private ConfrontoRepositoryImpl confrontoRepositoryImpl;

    @BeforeEach
    void init(){
        confrontoRepositoryImpl = new ConfrontoRepositoryImpl(micronautConfrontoRepository, micronautEquipeRepository);
    }

    @Test
    void deveInserirEquipesESeusJogadoresNoBancoDeDados(){
        List<Jogador> jogadores1 = Arrays.asList(new Jogador("time1rg1", "time1nome1", null), new Jogador("time1rg2", "time1nome2", null));
        List<Jogador> jogadores2 = Arrays.asList(new Jogador("time2rg1", "time2nome1", null), new Jogador("time2rg2", "time2nome2", null));
        List<Jogador> jogadores3 = Arrays.asList(new Jogador("time3rg1", "time3nome1", null), new Jogador("time3rg2", "time3nome2", null));
        List<Jogador> jogadores4 = Arrays.asList(new Jogador("time4rg1", "time4nome1", null), new Jogador("time4rg2", "time4nome2", null));

        ConstrutorClasses construtorClasses = new ConstrutorClasses();

        List<Confronto> confrontos = Arrays.asList(
                construtorClasses.builderConfrontoTeste("time1", "time2", Etapa.SEMI.getEtapa(), 1, jogadores1, jogadores2),
                construtorClasses.builderConfrontoTeste("time3", "time4", Etapa.SEMI.getEtapa(), 2, jogadores3, jogadores4),
                construtorClasses.builderConfrontoTeste("", "", Etapa.TERCEIRO_LUGAR.getEtapa(), 1, null, null),
                construtorClasses.builderConfrontoTeste("", "", Etapa.FINAL.getEtapa(), 1, null, null)
        );

        micronautEquipeRepository.saveAll(
                Arrays.asList(
                        new EquipeEntity(construtorClasses.builderEquipeTeste("time1", jogadores1)),
                        new EquipeEntity(construtorClasses.builderEquipeTeste("time2", jogadores2)),
                        new EquipeEntity(construtorClasses.builderEquipeTeste("time3", jogadores3)),
                        new EquipeEntity(construtorClasses.builderEquipeTeste("time4", jogadores4))
                )
        );
        confrontoRepositoryImpl.adicionarTodos(confrontos);

        List<ConfrontoEntity> resultado = micronautConfrontoRepository.findAll();

        assertEquals(4, resultado.size());
        assertIterableEquals(Arrays.asList(1, 2, 1, 1), confrontos.stream().map(confronto -> confronto.getOrdem()).collect(Collectors.toList()));
        assertIterableEquals(
                Arrays.asList(Etapa.SEMI.getEtapa(), Etapa.SEMI.getEtapa(), Etapa.TERCEIRO_LUGAR.getEtapa(), Etapa.FINAL.getEtapa()),
                confrontos.stream().map(confronto -> confronto.getEtapa()).collect(Collectors.toList())
        );

        assertEquals("time1", resultado.get(0).getMandante().getNome());
        assertEquals("time2", resultado.get(0).getVisitante().getNome());
        assertEquals("time3", resultado.get(1).getMandante().getNome());
        assertEquals("time4", resultado.get(1).getVisitante().getNome());

        assertNull(resultado.get(2).getMandante());
        assertNull(resultado.get(2).getVisitante());
        assertNull(resultado.get(3).getMandante());
        assertNull(resultado.get(3).getVisitante());
    }

}