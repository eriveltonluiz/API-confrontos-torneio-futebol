package com.erivelton.torneiofutebol.aplicacao.dto.requisicao;

import io.micronaut.core.annotation.Introspected;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Introspected
public class DadosEquipe {

    @NotBlank
    private String equipe;

    @NotNull
    @Valid
    @Size(min = 1)
    private List<DadosJogador> jogadores = new ArrayList<>();

    public DadosEquipe(String equipe, List<DadosJogador> jogadores) {
        this.equipe = equipe;

        if (jogadores != null) {
            this.jogadores.addAll(jogadores);
        }
    }
}
