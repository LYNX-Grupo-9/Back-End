package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.repository;

import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.AnexoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnexoJpaRepository extends JpaRepository<AnexoEntity, Long> {
}
