package com.erivelton.torneiofutebol.dominio;

public class RecursoNaoEncontradoException extends RuntimeException{

    public RecursoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
