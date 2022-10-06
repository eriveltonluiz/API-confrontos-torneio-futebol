package com.erivelton.torneiofutebol;

import com.erivelton.torneiofutebol.aplicacao.Inicializador;
import io.micronaut.runtime.Micronaut;

public class Application {
    public static void main(String[] args) {
//        Micronaut.build(args)
//                .classes(Inicializador.class)
//                .eagerInitSingletons(true)
//                .args(args)
//                .mainClass(Application.class)
//                .start();

        Micronaut.run(Application.class, args);
    }
}