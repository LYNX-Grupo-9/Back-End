package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.repository;

import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.ParcelaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParcelaJpaRepository extends JpaRepository<ParcelaEntity, Long> {
}
