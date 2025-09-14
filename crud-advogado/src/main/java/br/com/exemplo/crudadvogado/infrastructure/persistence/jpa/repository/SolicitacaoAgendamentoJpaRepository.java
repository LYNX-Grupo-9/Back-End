package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.repository;

import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.SolicitacaoAgendamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SolicitacaoAgendamentoJpaRepository extends JpaRepository<SolicitacaoAgendamentoEntity, UUID> {
    List<SolicitacaoAgendamentoEntity> findByEmail(String email);
    List<SolicitacaoAgendamentoEntity> findByAdvogadoIdAdvogado(UUID idAdvogado);
}
