package com.erivelton.torneiofutebol.infraestrutura.repositorios;

import com.erivelton.torneiofutebol.infraestrutura.entidades.ConfrontoEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface MicronautConfrontoRepository extends JpaRepository<ConfrontoEntity, Long> {

}
