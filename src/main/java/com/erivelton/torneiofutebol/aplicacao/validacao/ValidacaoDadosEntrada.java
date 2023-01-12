package com.erivelton.torneiofutebol.aplicacao.validacao;

import io.micronaut.validation.validator.Validator;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Singleton
public class ValidacaoDadosEntrada implements ValidacaoDados {

    private static final Logger LOG = LoggerFactory.getLogger(ValidacaoDadosEntrada.class);

    private final Validator validator;

    public ValidacaoDadosEntrada(Validator validator) {
        this.validator = validator;
    }

    @Override
    public void dadosEntrada(Object valor) {
        LOG.debug("Iniciando validação dos dados do campeonato recebidos");

        Set<ConstraintViolation<Object>> violacoes;

        if (valor instanceof List) {
            List<Object> valores = (List<Object>) valor;

            violacoes = valores.stream()
                    .map(objeto -> validator.validate(objeto))
                    .flatMap(objeto -> objeto.stream())
                    .collect(Collectors.toSet());
        } else {
            violacoes = validator.validate(valor);
        }

        if(!violacoes.isEmpty()){
            LOG.error("Dados do campeonato não são válidos: {}", violacoes.stream()
                    .map(violacao -> violacao.getPropertyPath().toString().concat(" " + violacao.getMessage()))
                    .collect(Collectors.toList())
            );

            throw new ConstraintViolationException(violacoes);
        }

        LOG.debug("Dados do campeonato recebido: {} foram validados com sucesso", valor);
    }

}
