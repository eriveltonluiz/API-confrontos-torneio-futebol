package com.erivelton.torneiofutebol.adaptadores.repositorios.entidades;

import com.erivelton.torneiofutebol.dominio.modelo.Equipe;
import com.erivelton.torneiofutebol.dominio.modelo.Jogador;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Entity
@Getter
@AllArgsConstructor
public class EquipeEntity {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, name = "NOME")
    private String nome;

    @Column(name = "TOTAL_GOLS")
    private Integer totalGols;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "equipe", cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    private List<JogadorEntity> jogadores = new ArrayList<>();

    public EquipeEntity() {
    }

    public EquipeEntity(Equipe equipe) {
        this.nome = equipe.getNome();
        this.jogadores.addAll(equipe.getJogadores().stream()
                .map(jogador -> new JogadorEntity(jogador, this))
                .collect(Collectors.toList())
        );
    }

    public void atulizarGolsJogadoresEquipe(Map<String, Integer> golsJogadores) {
        this.jogadores.stream()
                .filter(jogador -> golsJogadores.containsKey(jogador.getNome()))
                .forEach(
                        jogador -> jogador.atualizarGols(
                                golsJogadores.get(jogador.getNome())
                        )
                );
    }

}