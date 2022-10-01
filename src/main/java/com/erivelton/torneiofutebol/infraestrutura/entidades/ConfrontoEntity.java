package com.erivelton.torneiofutebol.infraestrutura.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CONFRONTO")
public class ConfrontoEntity {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MANDANTE_ID")
    private EquipeEntity mandante;

    @ManyToOne
    @JoinColumn(name = "VISITANTE_ID")
    private EquipeEntity visitante;

    @Column(name = "ETAPA")
    private String etapa;

    @Column(name = "ORDEM")
    private Integer ordem;

    @Column(name = "GOLS_MANDANTE")
    private Integer golsMandante;

    @Column(name = "GOLS_VISITANTE")
    private Integer golsVisitante;

}