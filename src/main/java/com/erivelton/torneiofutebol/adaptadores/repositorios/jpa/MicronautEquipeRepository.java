package com.erivelton.torneiofutebol.adaptadores.repositorios.jpa;

import com.erivelton.torneiofutebol.adaptadores.repositorios.entidades.EquipeEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface MicronautEquipeRepository extends JpaRepository<EquipeEntity, Long> {

    EquipeEntity findByNome(String nomeTimeMandante);
}
