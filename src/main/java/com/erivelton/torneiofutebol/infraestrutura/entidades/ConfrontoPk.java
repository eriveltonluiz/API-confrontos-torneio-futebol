package com.erivelton.torneiofutebol.infraestrutura.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@EqualsAndHashCode
@Embeddable
public class ConfrontoPk {

    @ManyToOne
    @JoinColumn(name = "mandante_id")
    private EquipeEntity mandante;

    @ManyToOne
    @JoinColumn(name = "visitante_id")
    private EquipeEntity visitante;
}
