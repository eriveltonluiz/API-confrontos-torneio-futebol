package com.erivelton.torneiofutebol.adaptadores.repositorios.jpa;

import com.erivelton.torneiofutebol.adaptadores.repositorios.entidades.ConfrontoEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface MicronautConfrontoRepository extends JpaRepository<ConfrontoEntity, Long> {

}
