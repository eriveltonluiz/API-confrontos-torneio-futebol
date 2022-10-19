package com.erivelton.torneiofutebol.adaptadores.integracao;

import com.erivelton.torneiofutebol.adaptadores.integracao.EquipeRepositoryImpl;
import com.erivelton.torneiofutebol.adaptadores.mapper.EquipeMapper;
import com.erivelton.torneiofutebol.adaptadores.mapper.JogadorMapper;
import com.erivelton.torneiofutebol.adaptadores.repositorios.jpa.MicronautEquipeRepository;
import com.erivelton.torneiofutebol.dominio.modelo.Equipe;
import com.erivelton.torneiofutebol.dominio.modelo.Jogador;
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
class EquipeRepositoryImplTest {

    @Inject
    private MicronautEquipeRepository micronautEquipeRepository;

    @Inject
    private JogadorMapper jogadorMapper;

    @Inject
    private EquipeMapper equipeMapper;

    private EquipeRepositoryImpl equipeRepositoryImpl;

    @BeforeEach
    void init(){
        equipeRepositoryImpl = new EquipeRepositoryImpl(micronautEquipeRepository, jogadorMapper, equipeMapper);
    }

    @Test
    void deveSalvarAsEquipesNoBancoDeDados(){
        List<Jogador> jogadores1 = Arrays.asList(new Jogador("time1rg1", "time1nome1", null), new Jogador("time1rg2", "time1nome2", null));
        List<Jogador> jogadores2 = Arrays.asList(new Jogador("time2rg1", "time2nome1", null), new Jogador("time2rg2", "time2nome2", null));
        List<Jogador> jogadores3 = Arrays.asList(new Jogador("time3rg1", "time3nome1", null), new Jogador("time3rg2", "time3nome2", null));
        List<Jogador> jogadores4 = Arrays.asList(new Jogador("time4rg1", "time4nome1", null), new Jogador("time4rg2", "time4nome2", null));

        ConstrutorClasses construtorClasses = new ConstrutorClasses();

        List<Equipe> equipes = Arrays.asList(
                construtorClasses.builderEquipeTeste("time1", jogadores1),
                construtorClasses.builderEquipeTeste("time2", jogadores2),
                construtorClasses.builderEquipeTeste("time3", jogadores3),
                construtorClasses.builderEquipeTeste("time4", jogadores4)
        );

        List<Equipe> equipesAtualizadas = equipeRepositoryImpl.salvarTodos(equipes);

        assertEquals(4, equipesAtualizadas.size());
        assertIterableEquals(
                Arrays.asList("time1", "time2", "time3", "time4"),
                equipesAtualizadas.stream().map(Equipe::getNome).collect(Collectors.toList())
        );
    }

}