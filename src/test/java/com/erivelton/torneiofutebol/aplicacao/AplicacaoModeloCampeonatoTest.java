package com.erivelton.torneiofutebol.aplicacao;

import com.erivelton.torneiofutebol.aplicacao.dto.requisicao.ConfrontoRequisicao;
import com.erivelton.torneiofutebol.aplicacao.dto.requisicao.DadosEquipe;
import com.erivelton.torneiofutebol.aplicacao.dto.requisicao.DadosJogador;
import com.erivelton.torneiofutebol.aplicacao.validacao.ValidacaoCustomizada;
import com.erivelton.torneiofutebol.aplicacao.validacao.ValidacaoDados;
import com.erivelton.torneiofutebol.dominio.porta.entrada.ModeloCampeonato;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolationException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class AplicacaoModeloCampeonatoTest {

    @Inject
    private ValidacaoDados validacao;

    @Inject
    private ValidacaoCustomizada validacaoCustomizada;

    @Inject
    private ModeloCampeonato modeloCampeonato;

    private AplicacaoModeloCampeonato aplicacaoModeloCampeonato;

    @BeforeEach
    void init(){
        aplicacaoModeloCampeonato = new AplicacaoModeloCampeonato(validacao, validacaoCustomizada, modeloCampeonato);
    }

    @Test
    void deveCriarModeloDeCampeonato(){
        List<DadosJogador> jogadores1 = Arrays.asList(new DadosJogador("time1rg1", "time1nome1"), new DadosJogador("time1rg2", "time1nome2"));
        List<DadosJogador> jogadores2 = Arrays.asList(new DadosJogador("time2rg1", "time2nome1"), new DadosJogador("time2rg2", "time2nome2"));

        List<DadosEquipe> dadosEquipe = Arrays.asList(
                new DadosEquipe("time1", jogadores1),
                new DadosEquipe("time2", jogadores2)
        );

        aplicacaoModeloCampeonato.criar(dadosEquipe);
    }

    @Test
    void deveInvalidarCriacaoQuandoEquipeForVazio(){
        List<DadosJogador> jogadores1 = Arrays.asList(new DadosJogador("nome1", "time1nome1"), new DadosJogador("time1rg2", "time1nome2"));
        List<DadosJogador> jogadores2 = Arrays.asList(new DadosJogador("nome2", "time2nome1"), new DadosJogador("time2rg2", "time2nome2"));

        List<DadosEquipe> dadosEquipes = Arrays.asList(
                new DadosEquipe("", jogadores1),
                new DadosEquipe("", jogadores2)
        );

        ConstraintViolationException constraintViolationException = assertThrows(
                ConstraintViolationException.class,
                () -> aplicacaoModeloCampeonato.criar(dadosEquipes)
        );

        assertEquals(2, constraintViolationException.getConstraintViolations().size());
    }

    @Test
    void deveInvalidarCriacaoQuandoEquipeForNull(){
        List<DadosJogador> jogadores1 = Arrays.asList(new DadosJogador("nome1", "time1nome1"), new DadosJogador("time1rg2", "time1nome2"));
        List<DadosJogador> jogadores2 = Arrays.asList(new DadosJogador("nome2", "time2nome1"), new DadosJogador("time2rg2", "time2nome2"));

        List<DadosEquipe> dadosEquipe = Arrays.asList(
                new DadosEquipe(null, jogadores1),
                new DadosEquipe(null, jogadores2)
        );

        ConstraintViolationException constraintViolationException = assertThrows(
                ConstraintViolationException.class,
                () -> aplicacaoModeloCampeonato.criar(dadosEquipe)
        );

        assertEquals(2, constraintViolationException.getConstraintViolations().size());
    }

    @Test
    void deveInvalidarCriacaoQuandoNomeJogadorForNull() {
        List<DadosJogador> jogadores1 = Arrays.asList(new DadosJogador(null, "time1nome1"), new DadosJogador("time1rg2", "time1nome2"));
        List<DadosJogador> jogadores2 = Arrays.asList(new DadosJogador(null, "time2nome1"), new DadosJogador("time2rg2", "time2nome2"));

        List<DadosEquipe> dadosEquipe = Arrays.asList(
                new DadosEquipe("time1", jogadores1),
                new DadosEquipe("time2", jogadores2)
        );

        ConstraintViolationException constraintViolationException = assertThrows(
                ConstraintViolationException.class,
                () -> aplicacaoModeloCampeonato.criar(dadosEquipe)
        );

        assertEquals(2, constraintViolationException.getConstraintViolations().size());
    }

    @Test
    void deveInvalidarCriacaoQuandoNomeERGJogadorForVazio() {
        List<DadosJogador> jogadores1 = Arrays.asList(new DadosJogador("", ""), new DadosJogador("", ""));
        List<DadosJogador> jogadores2 = Arrays.asList(new DadosJogador("", ""), new DadosJogador("", ""));

        List<DadosEquipe> dadosEquipe = Arrays.asList(
                new DadosEquipe("time1", jogadores1),
                new DadosEquipe("time2", jogadores2)
        );

        ConstraintViolationException constraintViolationException = assertThrows(
                ConstraintViolationException.class,
                () -> aplicacaoModeloCampeonato.criar(dadosEquipe)
        );

        assertEquals(8, constraintViolationException.getConstraintViolations().size());
    }

    @Test
    void deveInvalidarCriacaoQuandoListaDeJogadorForNull() {
        List<DadosEquipe> dadosEquipe = Arrays.asList(
                new DadosEquipe("time1", null),
                new DadosEquipe("time2", null)
        );

        ConstraintViolationException constraintViolationException = assertThrows(
                ConstraintViolationException.class,
                () -> aplicacaoModeloCampeonato.criar(dadosEquipe)
        );

        assertEquals(2, constraintViolationException.getConstraintViolations().size());
    }

    @Test
    void deveInvalidarAtualizacaoDeConfrontoQuandoDadosForemNull() {
        ConfrontoRequisicao confrontoRequisicao = new ConfrontoRequisicao();

        ConstraintViolationException constraintViolationException = assertThrows(
                ConstraintViolationException.class,
                () -> aplicacaoModeloCampeonato.adicionarDadosConfronto(confrontoRequisicao)
        );

        assertEquals(3, constraintViolationException.getConstraintViolations().size());
    }
}