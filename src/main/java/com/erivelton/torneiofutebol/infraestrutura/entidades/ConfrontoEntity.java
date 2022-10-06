package com.erivelton.torneiofutebol.infraestrutura.entidades;

import com.erivelton.torneiofutebol.dominio.Confronto;
import com.erivelton.torneiofutebol.infraestrutura.repositorios.MicronautEquipeRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@AllArgsConstructor
@Table(name = "CONFRONTO_ENTITY")
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

    @Column(name = "ETAPA", nullable = false)
    private String etapa;

    @Column(name = "ORDEM", nullable = false)
    private Integer ordem;

    @Column(name = "GOLS_MANDANTE")
    private Integer golsMandante;

    @Column(name = "GOLS_VISITANTE")
    private Integer golsVisitante;

    public ConfrontoEntity(){
    }

    public ConfrontoEntity(MicronautEquipeRepository micronautEquipeRepository, Confronto confronto) {
        this.mandante = confronto.getMandante() != null
                ? micronautEquipeRepository.findByNome(confronto.getNomeTimeMandante()) :
                null;

        this.visitante = confronto.getVisitante() != null
                ? micronautEquipeRepository.findByNome(confronto.getNomeTimeVisitante())
                : null;

        this.etapa = confronto.getEtapa();
        this.ordem = confronto.getOrdem();
    }

}