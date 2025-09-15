package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.repository;

import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.LeadEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LeadJpaRepository extends JpaRepository<LeadEntity, UUID> {
}
