package com.erivelton.torneiofutebol.infraestrutura.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CONFRONTO")
public class ConfrontoEntity {

    @EmbeddedId
    private ConfrontoPk id = new ConfrontoPk();

    private String etapa;

    @Column(name = "GOLS_MANDANTE")
    private Integer golsMandante;

    @Column(name = "GOLS_VISITANTE")
    private Integer golsVisitante;
}
