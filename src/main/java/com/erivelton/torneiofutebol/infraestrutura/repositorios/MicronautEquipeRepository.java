package com.erivelton.torneiofutebol.infraestrutura.repositorios;

import com.erivelton.torneiofutebol.infraestrutura.entidades.EquipeEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface MicronautEquipeRepository extends JpaRepository<EquipeEntity, Long> {

    EquipeEntity findByNome(String nomeTimeMandante);
}
