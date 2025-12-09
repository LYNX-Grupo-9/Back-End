package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.repository;

import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.LancamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface LancamentoJpaRepository extends JpaRepository<LancamentoEntity, UUID> {
    @Query("SELECT l FROM LancamentoEntity l WHERE l.processo.idProcesso IN :idsProcessos")
    List<LancamentoEntity> findByProcesso_IdProcessoIn(@Param("idsProcessos") List<UUID> idsProcessos);
}
