package com.erivelton.torneiofutebol.aplicacao.validacao;

import io.micronaut.validation.validator.Validator;
import jakarta.inject.Singleton;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Singleton
public class ValidacaoDadosEntrada implements ValidacaoDados {

    private final Validator validator;

    public ValidacaoDadosEntrada(Validator validator) {
        this.validator = validator;
    }

    @Override
    public void dadosEntrada(Object valor) {
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
            throw new ConstraintViolationException(violacoes);
        }
    }

}
