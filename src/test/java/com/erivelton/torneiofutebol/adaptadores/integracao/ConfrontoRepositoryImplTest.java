package com.erivelton.torneiofutebol.adaptadores.integracao;

import com.erivelton.torneiofutebol.adaptadores.repositorios.entidades.JogadorEntity;
import com.erivelton.torneiofutebol.adaptadores.repositorios.jpa.MicronautConfrontoRepository;
import com.erivelton.torneiofutebol.adaptadores.repositorios.jpa.MicronautEquipeRepository;
import com.erivelton.torneiofutebol.dominio.exceptions.RecursoNaoEncontradoException;
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
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class ConfrontoRepositoryImplTest {

    @Inject
    private MicronautConfrontoRepository micronautConfrontoRepository;

    @Inject
    private MicronautEquipeRepository micronautEquipeRepository;

    private ConfrontoRepositoryImpl confrontoRepositoryImpl;

    private ConstrutorClasses construtorClasses;

    @BeforeEach
    void init(){
        construtorClasses = new ConstrutorClasses();
        confrontoRepositoryImpl = new ConfrontoRepositoryImpl(micronautConfrontoRepository, micronautEquipeRepository);
    }

    @Test
    void deveInserirEquipesESeusJogadoresNoBancoDeDados(){
        List<Jogador> jogadores1 = Arrays.asList(new Jogador("time1rg1", "time1nome1", null), new Jogador("time1rg2", "time1nome2", null));
        List<Jogador> jogadores2 = Arrays.asList(new Jogador("time2rg1", "time2nome1", null), new Jogador("time2rg2", "time2nome2", null));
        List<Jogador> jogadores3 = Arrays.asList(new Jogador("time3rg1", "time3nome1", null), new Jogador("time3rg2", "time3nome2", null));
        List<Jogador> jogadores4 = Arrays.asList(new Jogador("time4rg1", "time4nome1", null), new Jogador("time4rg2", "time4nome2", null));

        List<Confronto> confrontos = Arrays.asList(
                construtorClasses.builderConfrontoTeste("time1", "time2", Etapa.SEMI.getEtapa(), 1, jogadores1, jogadores2).build(),
                construtorClasses.builderConfrontoTeste("time3", "time4", Etapa.SEMI.getEtapa(), 2, jogadores3, jogadores4).build(),
                construtorClasses.builderConfrontoTeste("", "", Etapa.TERCEIRO_LUGAR.getEtapa(), 1, null, null).build(),
                construtorClasses.builderConfrontoTeste("", "", Etapa.FINAL.getEtapa(), 1, null, null).build()
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

    @Test
    void deveAtualizarConfrontoComIdValido(){
        Confronto confronto = registroConfronto(true);

        confrontoRepositoryImpl.atualizarConfronto(confronto);

        ConfrontoEntity resultadoConfronto = micronautConfrontoRepository.findById(confronto.getIdentificacao()).get();
        List<JogadorEntity> resultadoJogadoresMandante = resultadoConfronto.getMandante().getJogadores();
        List<JogadorEntity> resultadoJogadoresVisitante = resultadoConfronto.getVisitante().getJogadores();

        assertEquals(2, resultadoConfronto.getGolsMandante());
        assertEquals(2, resultadoConfronto.getGolsVisitante());
        assertEquals(2, resultadoJogadoresMandante.stream().filter(jogador -> jogador.getNome().equals("time1nome1")).findAny().get().getGol());
        assertEquals(2, resultadoJogadoresVisitante.stream().filter(jogador -> jogador.getNome().equals("time2nome1")).findAny().get().getGol());
    }

    @Test
    void deveLancarExcecaoQuandoIdConfrontoNaoForEncontradoNaBaseDeDados(){
        Confronto confronto = registroConfronto(false);

        RecursoNaoEncontradoException recursoNaoEncontradoException = assertThrows(
                RecursoNaoEncontradoException.class,
                () -> confrontoRepositoryImpl.atualizarConfronto(confronto)
        );

        assertEquals("Identificação do confronto não foi encontrado", recursoNaoEncontradoException.getMessage());
    }

    private Confronto registroConfronto(boolean valido) {
        List<Jogador> jogadores1 = Arrays.asList(new Jogador("time1rg1", "time1nome1", null), new Jogador("time1rg2", "time1nome2", null));
        List<Jogador> jogadores2 = Arrays.asList(new Jogador("time2rg1", "time2nome1", null), new Jogador("time2rg2", "time2nome2", null));
        Confronto.ConfrontoBuilder confrontoBuilder = construtorClasses
                .builderConfrontoTeste("time1", "time2", Etapa.SEMI.getEtapa(), 1, jogadores1, jogadores2);

        micronautEquipeRepository.saveAll(
                Arrays.asList(
                        new EquipeEntity(construtorClasses.builderEquipeTeste("time1", jogadores1)),
                        new EquipeEntity(construtorClasses.builderEquipeTeste("time2", jogadores2))
                )
        );

        ConfrontoEntity confrontoEntity = micronautConfrontoRepository.save(new ConfrontoEntity(micronautEquipeRepository, confrontoBuilder.build()));

        if (valido) {
            confrontoBuilder.identificacao(confrontoEntity.getId());
        } else {
            confrontoBuilder.identificacao(4L);
        }

        return confrontoBuilder
                .golsMandante(2)
                .golsVisitante(2)
                .golsJogadoresMandante(Map.of("time1nome1", 2))
                .golsJogadoresVisitante(Map.of("time2nome1", 2))
                .build();
    }

}